package com.biterush.auth_service.service.impl;

import com.biterush.auth_service.model.dto.common.LoginRequestDTO;
import com.biterush.auth_service.model.dto.common.LoginResponseDTO;
import com.biterush.auth_service.model.dto.common.PasswordChangeRequest;
import com.biterush.auth_service.model.dto.common.RefreshTokenRequest;
import com.biterush.auth_service.model.dto.request.UserCredentialsRequestDTO;
import com.biterush.auth_service.model.dto.response.UserCredentialsResponseDTO;
import com.biterush.auth_service.model.entity.EventPublishingStatus;
import com.biterush.auth_service.model.entity.RefreshToken;
import com.biterush.auth_service.model.entity.UserCredentials;
import com.biterush.auth_service.model.enums.EVENT_STATUS;
import com.biterush.auth_service.model.enums.STATUS;
import com.biterush.auth_service.model.mapper.UserCredentialsDTOMapper;
import com.biterush.auth_service.repository.EventPublishingStatusRepo;
import com.biterush.auth_service.repository.RefreshTokenRepo;
import com.biterush.auth_service.repository.UserCredentialsRepo;
import com.biterush.auth_service.service.AuthService;
import com.biterush.auth_service.util.CustomUserDetails;
import com.biterush.auth_service.util.JWTUtil;
import com.biterush.common.event.NotificationEvent;
import com.biterush.common.event.UserCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder encoder;
    private final JWTUtil jwtUtil;
    private final EventPublishingStatusRepo eventPublishingStatusRepo;
    private final RefreshTokenRepo refreshTokenRepo;
    private final UserCredentialsRepo userCredentialsRepo;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Value("${spring.kafka.topic.user}")
    private String userTopic;

    @Value("${spring.kafka.topic.notification}")
    private String notificationTopic;

    @Override
    @Transactional
    public UserCredentialsResponseDTO registerUser(UserCredentialsRequestDTO request) {
        UserCredentials userCredentials = UserCredentialsDTOMapper.toEntity(request);
        userCredentials.setActivationTokenExpiry(LocalDateTime.now().plusMinutes(15));
        userCredentials.setPassword(encoder.encode(request.getPassword()));
        UserCredentials savedUserCred = userCredentialsRepo.save(userCredentials);

        UserCreatedEvent createdEvent = UserCreatedEvent.builder()
                .userId(request.getUserId())
                .phone(request.getPhone())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        EventPublishingStatus userCreationEvent = EventPublishingStatus.builder()
                .producer("Auth")
                .producerId(savedUserCred.getUserId())
                .eventType("User Created")
                .topic(userTopic)
                .payload(toJson(createdEvent))
                .status(EVENT_STATUS.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .nextRetryAt(LocalDateTime.now())
                .build();
        eventPublishingStatusRepo.save(userCreationEvent);

        String activationLink = "http://localhost:8081/api/user/activate/" + savedUserCred.getActivationToken();
        NotificationEvent userCreatedNotificationEvent = NotificationEvent.builder()
                .userId(savedUserCred.getUserId())
                .recipient(savedUserCred.getEmail())
                .phone(savedUserCred.getPhone())
                //ADD Message Later
                .message("""
                Hi %s,
                Welcome to MoneyManager! Please activate your account by clicking the link below:
                %s
                If you didn't create this account, you can safely ignore this email.
                """.formatted(request.getFirstName(), activationLink))
                .subject("Profile Activation Link")
                .build();

        EventPublishingStatus userCreationNotificationEvent = EventPublishingStatus.builder()
                .producer("Auth")
                .producerId(savedUserCred.getUserId())
                .eventType("User Created")
                .topic(notificationTopic)
                .payload(toJson(userCreatedNotificationEvent))
                .status(EVENT_STATUS.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .nextRetryAt(LocalDateTime.now())
                .build();
        eventPublishingStatusRepo.save(userCreationNotificationEvent);

        return UserCredentialsDTOMapper.toDTO(savedUserCred);
    }

    @Override
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.email(),loginRequestDTO.password()));
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if(customUserDetails == null){
            throw new RuntimeException("Invalid email or password!");
        }
        UserCredentials userCredentials = customUserDetails.getUserCredentials();
        String accessToken = jwtUtil.generateAccessToken(userCredentials);
        String refreshToken = jwtUtil.generateRefreshToken(userCredentials);
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .userId(userCredentials.getUserId())
                .refreshToken(refreshToken)
                .build();
        refreshTokenRepo.save(refreshTokenEntity);
        return new LoginResponseDTO(UserCredentialsDTOMapper.toDTO(userCredentials),accessToken,refreshToken);
    }

    @Override
    @Transactional
    public LoginResponseDTO refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.refreshToken();
        String username = jwtUtil.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtUtil.isValid(refreshToken, userDetails)) {
            throw new RuntimeException("Invalid Refresh Token! Login Again");
        }
        UserCredentials user = ((CustomUserDetails) userDetails).getUserCredentials();
        return new LoginResponseDTO(UserCredentialsDTOMapper.toDTO(user), jwtUtil.generateAccessToken(user),null);
    }

    @Override
    @Transactional
    public Boolean logout(UUID userId) {
        RefreshToken refreshTokenEntity = refreshTokenRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("Refresh Token not found"));
        refreshTokenRepo.delete(refreshTokenEntity);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean activateProfile(String activationToken) {
        UserCredentials user = userCredentialsRepo.findByActivationToken(activationToken).orElseThrow(() -> new RuntimeException("User Not Found"));
        if (user.getActivationTokenExpiry().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Activation Link expired");
        user.setStatus(STATUS.ACTIVE);
        user.setActivationToken("");
        userCredentialsRepo.save(user);
        return true;
    }

    @Override
    @Transactional
    public Boolean forgetPassword(String email, String OTP, PasswordChangeRequest passwordChangeRequest) {
        UserCredentials userCredentials = userCredentialsRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
        if(!userCredentials.getOTPVerified()){
            throw new RuntimeException("OTP not Verified");
        }
        userCredentials.setPassword(encoder.encode(passwordChangeRequest.newPassword()));
        userCredentials.setOTP(null);
        userCredentials.setOTPExpiry(null);
        userCredentials.setOTPVerified(false);
        userCredentialsRepo.save(userCredentials);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean sendOTP(String email) {
        UserCredentials userCredentials = userCredentialsRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        String OTP = String.valueOf(100000 + new SecureRandom().nextInt(900000));
        userCredentials.setOTP(OTP);
        userCredentials.setOTPExpiry(LocalDateTime.now().plusMinutes(15));
        userCredentials.setOTPVerified(false);
        userCredentialsRepo.save(userCredentials);
        //Send Email
        NotificationEvent userCreatedNotificationEvent = NotificationEvent.builder()
                .userId(userCredentials.getUserId())
                .recipient(userCredentials.getEmail())
                .phone(userCredentials.getPhone())
                //ADD Message Later
                .message("Your OTP to reset your password is <b>" + OTP + "</b>. It is valid for 15 minutes.<br><br>If you didn't request this, please ignore this email.\\nRegards,\\nBiteRush Team")
                .subject("Forget Password")
                .build();

        EventPublishingStatus userCreationNotificationEvent = EventPublishingStatus.builder()
                .producer("Auth")
                .producerId(userCredentials.getUserId())
                .eventType("Reset Password")
                .topic(notificationTopic)
                .payload(toJson(userCreatedNotificationEvent))
                .status(EVENT_STATUS.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .nextRetryAt(LocalDateTime.now())
                .build();
        eventPublishingStatusRepo.save(userCreationNotificationEvent);
        return null;
    }

    @Override
    @Transactional
    public Boolean changePassword(PasswordChangeRequest passwordChangeRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            UserCredentials user = userDetails.getUserCredentials();
            if (!encoder.matches(user.getPassword(), passwordChangeRequest.oldPassword())) {
                throw new RuntimeException("Old Password do not match");
            }
            user.setPassword(encoder.encode(passwordChangeRequest.newPassword()));
            userCredentialsRepo.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    @Transactional
    public Boolean changeEmail(UUID userId, String newEmail) {
        UserCredentials userCredentials = userCredentialsRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if(userCredentialsRepo.existsByEmailAndUserIdNot(userCredentials.getEmail(), userId)){
            throw new RuntimeException("Email Already Exists");
        }
        userCredentials.setEmail(newEmail);
        userCredentialsRepo.save(userCredentials);
        return Boolean.TRUE;
    }

    @Override
    public UserCredentialsResponseDTO getLoggedInUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserCredentialsDTOMapper.toDTO(user.getUserCredentials());
    }

    @Override
    @Transactional
    public Boolean verifyOTP(String email, String OTP) {
        UserCredentials user = userCredentialsRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOTP() == null) {
            throw new RuntimeException("Something Went Wrong!");
        }

        if (!user.getOTP().equals(OTP))
            throw new RuntimeException("Invalid OTP");

        if (user.getOTPExpiry().isBefore(LocalDateTime.now()))
            throw new RuntimeException("OTP expired");

        user.setOTPVerified(true);

        userCredentialsRepo.save(user);
        return Boolean.TRUE;
    }

    private String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }
}

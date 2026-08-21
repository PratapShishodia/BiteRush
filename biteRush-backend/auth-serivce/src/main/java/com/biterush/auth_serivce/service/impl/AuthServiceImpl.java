package com.biterush.auth_serivce.service.impl;

import com.biterush.auth_serivce.kafka.UserEventProducer;
import com.biterush.auth_serivce.model.dto.PasswordChangeRequest;
import com.biterush.auth_serivce.model.dto.RefreshTokenRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsResponse;
import com.biterush.auth_serivce.model.dto.common.LoginRequestDTO;
import com.biterush.auth_serivce.model.dto.common.LoginResponseDTO;
import com.biterush.auth_serivce.model.entity.RefreshToken;
import com.biterush.auth_serivce.model.entity.UserCredentials;
import com.biterush.auth_serivce.model.enums.STATUS;
import com.biterush.auth_serivce.model.mapper.RefreshDTOMapper;
import com.biterush.auth_serivce.model.mapper.UserCredentialsDTOMapper;
import com.biterush.auth_serivce.repository.RefreshTokenRepository;
import com.biterush.auth_serivce.repository.UserCredentialsRepository;
import com.biterush.auth_serivce.service.AuthService;
import com.biterush.auth_serivce.utils.CustomUserDetailService;
import com.biterush.auth_serivce.utils.CustomUserDetails;
import com.biterush.auth_serivce.utils.JWTUtil;
import com.biterush.common.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserCredentialsRepository userCredentialsRepo;
    private final BCryptPasswordEncoder encoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepo;
    private final CustomUserDetailService userDetailsService;
    private final UserEventProducer userEventProducer;

    @Override
    @Transactional
    public UserCredentialsResponse registerUser(UserCredentialsRequest request) {
        UserCredentials userCredentials = UserCredentialsDTOMapper.toEntity(request);
        if(userCredentialsRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if(userCredentialsRepo.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }
        userCredentials.setActivationToken(UUID.randomUUID().toString());
        userCredentials.setStatus(STATUS.INACTIVE);
        userCredentials.setPasswordHashed(encoder.encode(request.getPassword()));
        userCredentials.setUserId(UUID.randomUUID());
        userCredentials.setCreatedAt(LocalDateTime.now());
        UserCredentials savedUserCredentials = userCredentialsRepo.save(userCredentials);
        //Send Email

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .userId(savedUserCredentials.getUserId())
                .firstName(savedUserCredentials.getFirstName())
                .lastName(savedUserCredentials.getLastName())
                .email(savedUserCredentials.getEmail())
                .phone(savedUserCredentials.getPhone())
                .status(String.valueOf(savedUserCredentials.getStatus()))
                .build();
        userEventProducer.sendUserEvent(userCreatedEvent);
        return UserCredentialsDTOMapper.toDTO(savedUserCredentials);
    }

    @Override
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.email(),loginRequestDTO.password()));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if(userDetails == null){
            throw new RuntimeException("Invalid email or password");
        }

        UserCredentials userCredentials = userDetails.getUserCredentials();

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .userId(userCredentials.getUserId())
                .refreshToken(jwtUtil.generateRefreshToken(userCredentials))
                .expiresAt(LocalDateTime.now().plusDays(15))
                .revokedAt(LocalDateTime.now().plusDays(15))
                .createdAt(LocalDateTime.now())
                .build();
        refreshTokenRepo.save(refreshTokenEntity);
        return new LoginResponseDTO(UserCredentialsDTOMapper.toDTO(userCredentials), jwtUtil.generateAccessToken(userCredentials), RefreshDTOMapper.toDTO(refreshTokenRepo.save(refreshTokenEntity)));
    }

    @Override
    public LoginResponseDTO refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        String username = jwtUtil.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtUtil.isValid(refreshToken, userDetails)) {
            throw new RuntimeException("Invalid Refresh Token");
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
        UserCredentials userCredentials = userCredentialsRepo.findByActivationToken(activationToken).orElseThrow(() -> new RuntimeException("Activation Token not found"));
        if (userCredentials.getActivationTokenExpiry().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Activation Link expired");
        userCredentials.setStatus(STATUS.ACTIVE);
        userCredentials.setActivationToken("");
        userCredentialsRepo.save(userCredentials);
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean verifyOTP(String email, String OTP) {
        UserCredentials user = userCredentialsRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOTP() == null) {
            throw new RuntimeException("Something Went Wrong!");
        }

        if (!user.getOTP().equals(OTP))
            throw new RuntimeException("Invalid OTP");

        if (user.getOtpExpiry().isBefore(LocalDateTime.now()))
            throw new RuntimeException("OTP expired");

        user.setOTPVerified(true);

        userCredentialsRepo.save(user);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean forgetPassword(String email, String OTP, PasswordChangeRequest passwordChangeRequest) {
        UserCredentials userCredentials = userCredentialsRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
        if(!userCredentials.isOTPVerified()){
            throw new RuntimeException("OTP not Verified");
        }
        userCredentials.setPasswordHashed(encoder.encode(passwordChangeRequest.getNewPassword()));
        userCredentials.setOTP(null);
        userCredentials.setOtpExpiry(null);
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
        userCredentials.setOtpExpiry(LocalDateTime.now().plusMinutes(15));
        userCredentials.setOTPVerified(false);
        userCredentialsRepo.save(userCredentials);
        //Send Email
        return null;
    }

    @Override
    @Transactional
    public Boolean changePassword(PasswordChangeRequest passwordChangeRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            UserCredentials user = userDetails.getUserCredentials();
            if (!encoder.matches(user.getPasswordHashed(), passwordChangeRequest.getOldPassword())) {
                throw new RuntimeException("Old Password do not match");
            }
            user.setPasswordHashed(encoder.encode(passwordChangeRequest.getNewPassword()));
            userCredentialsRepo.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
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
    public UserCredentialsResponse getLoggedInUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserCredentialsDTOMapper.toDTO(user.getUserCredentials());
    }
}

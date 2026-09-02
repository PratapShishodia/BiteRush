package com.biterush.user_service.service.impl;

import com.biterush.common.event.UserCreatedEvent;
import com.biterush.user_service.model.dto.mapper.AddressDTOMapper;
import com.biterush.user_service.model.dto.mapper.UserDTOMapper;
import com.biterush.user_service.model.dto.request.AddressRequestDTO;
import com.biterush.user_service.model.dto.request.UserRequestDTO;
import com.biterush.user_service.model.dto.response.AddressResponseDTO;
import com.biterush.user_service.model.dto.response.UserResponseDTO;
import com.biterush.user_service.model.entity.Address;
import com.biterush.user_service.model.entity.Users;
import com.biterush.user_service.repository.AddressRepo;
import com.biterush.user_service.repository.UserRepo;
import com.biterush.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final String UPLOAD_DIR = System.getProperty("user.dir")+"/uploads/profileImgs/";
    private final UserRepo userRepo;
    private final AddressRepo addressRepo;

    @Override
    public UserResponseDTO getMe(UUID userId) {
        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTOMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO update(UUID userId, UserRequestDTO dto) {
        Users user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        if(dto.getFirstName() != null && !dto.getFirstName().isEmpty()){
            user.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null && !dto.getLastName().isEmpty()){
            user.setLastName(dto.getLastName());
        }
        if(dto.getDateOfBirth() != null){
            user.setDateOfBirth(dto.getDateOfBirth());
        }
        if(dto.getGender() != null){
            user.setGender(dto.getGender());
        }
        user.setUpdatedAt(LocalDateTime.now());
        return UserDTOMapper.toDTO(userRepo.save(user));
    }

    @Override
    @SneakyThrows
    @Transactional
    public UserResponseDTO updateProfilePic(UUID userId, MultipartFile file) {
        long MAX_SIZE = 2*1024*1024;
        List<String> ALLOWED_TYPES = List.of("image/jpeg","image/png","image/jpg");
        List<String> ALLOWED_EXTENSIONS = List.of("jpeg","png","jpg");
        Users user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not Found"));
        if(file.isEmpty()){
            throw new RuntimeException("File is Empty");
        }
        if(file.getSize() > MAX_SIZE){
            throw new RuntimeException("File Size should not more than 2MB");
        }
        System.out.println("FILE TYPE: "+file.getContentType());
        System.out.println(ALLOWED_TYPES.contains(file.getContentType()));
        if(!ALLOWED_TYPES.contains(file.getContentType())){
            throw new RuntimeException("File Should be of type jpeg,jpg,png");
        }
        String originalName = file.getOriginalFilename();
        if(originalName == null || !originalName.contains(".")){
            throw new RuntimeException("Invalid File Name");
        }
        String extension = originalName.substring(originalName.lastIndexOf(".")+1);
        System.out.println("EXTENSION:"+extension);
        if(!ALLOWED_EXTENSIONS.contains(extension)){
            throw new RuntimeException("File Should be of .jpeg,.jpg,.png");
        }

        File folder = new File(UPLOAD_DIR);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String fileName = userId+"."+extension;
        Path filePath = Paths.get(UPLOAD_DIR+fileName);
        System.out.println("Path: "+filePath.toString());
        Files.write(filePath,file.getBytes());
        String imageURL = UPLOAD_DIR+"/products/images/"+fileName;
        user.setProfileImage(imageURL);
        return UserDTOMapper.toDTO(userRepo.save(user));
    }

    @Override
    @Transactional
    public UserResponseDTO createProfile(UserCreatedEvent userCreatedEvent) {
        Users user = UserDTOMapper.toEntity(userCreatedEvent);
        user.setCreatedAt(LocalDateTime.now());
        return UserDTOMapper.toDTO(userRepo.save(user));
    }

    @Override
    public AddressResponseDTO createAddress(AddressRequestDTO dto) {
        Address address = AddressDTOMapper.toEntity(dto);
        address.setCreatedAt(LocalDateTime.now());
        if(!dto.getIsDefault()) {
            address.setIsDefault(false);
        }
        address.setUser(userRepo.findById(dto.getUser()).orElseThrow(()->new RuntimeException("User not found")));
        return AddressDTOMapper.toDTO(addressRepo.save(address));
    }

    @Override
    public List<AddressResponseDTO> getAllAddress(UUID userId) {
        List<Address> addressList = addressRepo.findByUserUserId(userId);
        return addressList.stream().map(AddressDTOMapper::toDTO).toList();
    }

    @Override
    public AddressResponseDTO updateAddress(UUID addressId, AddressRequestDTO requestDTO) {
        Address address = addressRepo.findByAddressId(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        address.setAddressType(requestDTO.getAddressType());
        address.setAddressLine1(requestDTO.getAddressLine1());
        address.setAddressLine2(requestDTO.getAddressLine2());
        address.setLandmark(requestDTO.getLandmark());
        address.setCity(requestDTO.getCity());
        address.setState(requestDTO.getState());
        address.setCountry(requestDTO.getCountry());
        address.setPostalCode(requestDTO.getPostalCode());
        address.setLatitude(requestDTO.getLatitude());
        address.setLongitude(requestDTO.getLongitude());

        // If setting this address as default, unset other default addresses
        if (requestDTO.getIsDefault()) {
            addressRepo.unsetDefaultAddress(address.getUser().getUserId(), addressId);
        }

        address.setIsDefault(requestDTO.getIsDefault());

        return AddressDTOMapper.toDTO(addressRepo.save(address));
    }

    @Override
    public Boolean deleteAddress(UUID addressId) {
        Address userAddress = addressRepo.findById(addressId).orElseThrow(()->new RuntimeException("Address not found"));
        addressRepo.delete(userAddress);
        return Boolean.TRUE;
    }

    @Override
    public Boolean setDefaultAddress(UUID addressId) {
        Address userAddress = addressRepo.findById(addressId).orElseThrow(()->new RuntimeException("Address not found"));
        userAddress.setIsDefault(true);
        addressRepo.save(userAddress);
        return Boolean.TRUE;
    }
}

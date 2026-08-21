package com.biterush.user_service.service.impl;

import com.biterush.common.event.UserCreatedEvent;
import com.biterush.user_service.model.DTO.UserAddressRequestDTO;
import com.biterush.user_service.model.DTO.UserAddressResponseDTO;
import com.biterush.user_service.model.DTO.UserResponseDTO;
import com.biterush.user_service.model.DTO.UserUpdateRequestDTO;
import com.biterush.user_service.model.entity.UserAddress;
import com.biterush.user_service.model.entity.Users;
import com.biterush.user_service.model.mapper.UserAddressDTOMapper;
import com.biterush.user_service.model.mapper.UserDTOMapper;
import com.biterush.user_service.repository.UserAddressRepo;
import com.biterush.user_service.repository.UsersRepo;
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
public class UserServiceImpl implements UserService {

    private final String UPLOAD_DIR = System.getProperty("user.dir")+"/uploads/productsImg/";
    private final UsersRepo usersRepo;
    private final UserAddressRepo userAddressRepo;

    @Override
    public UserResponseDTO getMe(UUID userId) {
        Users user = usersRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        return UserDTOMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO update(UUID userId, UserUpdateRequestDTO dto) {
        Users user = usersRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        if(dto.getFirstName() != null && !dto.getFirstName().isEmpty()){
            user.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null && !dto.getLastName().isEmpty()){
            user.setLastName(dto.getLastName());
        }
        if(dto.getDateOfBirth() != null && !dto.getDateOfBirth().isEmpty()){
            user.setDateOfBirth(dto.getDateOfBirth());
        }
        if(dto.getGender() != null){
            user.setGender(dto.getGender());
        }
        user.setUpdatedAt(LocalDateTime.now());
        return UserDTOMapper.toDTO(usersRepo.save(user));
    }

    @Override
    @SneakyThrows
    public UserResponseDTO updateProfilePic(UUID userId, MultipartFile file) {
        long MAX_SIZE = 2*1024*1024;
        List<String> ALLOWED_TYPES = List.of("image/jpeg","image/png","image/jpg");
        List<String> ALLOWED_EXTENSIONS = List.of("jpeg","png","jpg");
        Users user = usersRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not Found"));
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
        user.setProfileImageUrl(imageURL);
        return UserDTOMapper.toDTO(usersRepo.save(user));
    }

    @Override
    public UserResponseDTO createProfile(UserCreatedEvent userCreatedEvent) {
        Users user = UserDTOMapper.toEntity(userCreatedEvent);
        user.setCreatedAt(LocalDateTime.now());
        return UserDTOMapper.toDTO(usersRepo.save(user));
    }

    @Override
    public UserAddressResponseDTO createAddress(UserAddressRequestDTO dto) {
        UserAddress userAddress = UserAddressDTOMapper.toEntity(dto);
        userAddress.setCreatedAt(LocalDateTime.now());
        if(!dto.isDefault()) {
            userAddress.setDefault(false);
        }
        userAddress.setUser(usersRepo.findById(dto.getUserId()).orElseThrow(()->new RuntimeException("User not found")));
        return UserAddressDTOMapper.toDTO(userAddressRepo.save(userAddress));
    }

    @Override
    public List<UserAddressResponseDTO> getAllAddress(UUID userId) {
        List<UserAddress> addressList = userAddressRepo.findByUserUserId(userId);
        return addressList.stream().map(UserAddressDTOMapper::toDTO).toList();
    }

    @Override
    public UserAddressResponseDTO updateAddress(Long addressId, UserAddressRequestDTO requestDTO) {

        UserAddress address = userAddressRepo.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setLabel(requestDTO.getLabel());
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
        if (requestDTO.isDefault()) {
            userAddressRepo
                    .unsetDefaultAddress(address.getUser().getUserId(), addressId);
        }

        address.setDefault(requestDTO.isDefault());

        return UserAddressDTOMapper.toDTO(userAddressRepo.save(address));
    }

    @Override
    public Boolean deleteAddress(Long addressId) {
        UserAddress userAddress = userAddressRepo.findById(addressId).orElseThrow(()->new RuntimeException("Address not found"));
        userAddressRepo.delete(userAddress);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean setDefaultAddress(Long addressId) {

        UserAddress address = userAddressRepo.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Users user = address.getUser();

        // Remove default from all other addresses
        user.getAddressList().forEach(a -> {
            if (!a.getAddressId().equals(addressId)) {
                a.setDefault(false);
            }
        });

        // Set selected address as default
        address.setDefault(true);

        userAddressRepo.save(address);
        return Boolean.TRUE;
    }
}

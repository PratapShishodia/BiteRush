package com.biterush.restaurant_service.service.impl;

import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.model.dto.response.CuisineResponseDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantHoursResponseDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantResponseDTO;
import com.biterush.restaurant_service.model.entity.Cuisine;
import com.biterush.restaurant_service.model.entity.Restaurant;
import com.biterush.restaurant_service.model.entity.RestaurantHours;
import com.biterush.restaurant_service.model.mapper.CuisineDTOMapper;
import com.biterush.restaurant_service.model.mapper.RestaurantDTOMapper;
import com.biterush.restaurant_service.model.mapper.RestaurantHoursDTOMapper;
import com.biterush.restaurant_service.repository.CuisineRepo;
import com.biterush.restaurant_service.repository.RestaurantHoursRepo;
import com.biterush.restaurant_service.repository.RestaurantRepo;
import com.biterush.restaurant_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;
    private final RestaurantHoursRepo restaurantHoursRepo;
    private final CuisineRepo cuisineRepo;

    @Override
    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = RestaurantDTOMapper.toEntity(restaurantRequestDTO);
        restaurant.setRestaurantId(UUID.randomUUID());
        restaurant.setCreatedAt(LocalDateTime.now());
        return RestaurantDTOMapper.toDTO(restaurantRepo.save(restaurant));
    }

    @Override
    public List<RestaurantResponseDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream().map(RestaurantDTOMapper::toDTO).toList();
    }

    @Override
    public RestaurantResponseDTO getRestaurants(UUID restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        return RestaurantDTOMapper.toDTO(restaurant);
    }

    @Override
    public RestaurantResponseDTO updateRestaurant(UUID restaurantId, RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        if(restaurantRequestDTO.getOwnerId() != null){
            restaurant.setOwnerId(restaurantRequestDTO.getOwnerId());
        }
        if(restaurantRequestDTO.getIsPureVeg() != null){
            restaurant.setIsPureVeg(restaurantRequestDTO.getIsPureVeg());
        }
        if(restaurantRequestDTO.getRestaurantName() != null){
            restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
        }
        if(restaurantRequestDTO.getSlug() != null){
            restaurant.setSlug(restaurantRequestDTO.getSlug());
        }
        if(restaurantRequestDTO.getDescription() != null){
            restaurant.setDescription(restaurantRequestDTO.getDescription());
        }
        if(restaurantRequestDTO.getPhone() != null){
            restaurant.setPhone(restaurantRequestDTO.getPhone());
        }
        if(restaurantRequestDTO.getEmail() != null){
            restaurant.setEmail(restaurantRequestDTO.getEmail());
        }
        if(restaurantRequestDTO.getAddressLine1() != null){
            restaurant.setAddressLine1(restaurantRequestDTO.getAddressLine1());
        }
        if(restaurantRequestDTO.getAddressLine2() != null){
            restaurant.setAddressLine2(restaurantRequestDTO.getAddressLine2());
        }
        if(restaurantRequestDTO.getCity() != null){
            restaurant.setCity(restaurantRequestDTO.getCity());
        }
        if(restaurantRequestDTO.getState() != null){
            restaurant.setState(restaurantRequestDTO.getState());
        }
        if(restaurantRequestDTO.getCountry() != null){
            restaurant.setCountry(restaurantRequestDTO.getCountry());
        }
        if(restaurantRequestDTO.getPostalCode() != null){
            restaurant.setPostalCode(restaurantRequestDTO.getPostalCode());
        }
        if(restaurantRequestDTO.getLatitude() != null){
            restaurant.setLatitude(restaurantRequestDTO.getLatitude());
        }
        if(restaurantRequestDTO.getLongitude() != null){
            restaurant.setLongitude(restaurantRequestDTO.getLongitude());
        }
        if(restaurantRequestDTO.getPriceForTwo() != null){
            restaurant.setPriceForTwo(restaurantRequestDTO.getPriceForTwo());
        }
        if(restaurantRequestDTO.getDeliveryTimeMax() != null){
            restaurant.setDeliveryTimeMax(restaurantRequestDTO.getDeliveryTimeMax());
        }
        if(restaurantRequestDTO.getDeliveryTimeMin() != null){
            restaurant.setDeliveryTimeMin(restaurantRequestDTO.getDeliveryTimeMin());
        }
        if(restaurantRequestDTO.getIsPureVeg() != null){
            restaurant.setIsPureVeg(restaurantRequestDTO.getIsPureVeg());
        }
        if(!restaurantRequestDTO.getCuisineIds().isEmpty()){
            for(Long id : restaurantRequestDTO.getCuisineIds()){
                restaurant.getCuisines().add(cuisineRepo.findById(id).orElseThrow(() -> new RuntimeException("Cuisine not found")));
            }
        }
        return RestaurantDTOMapper.toDTO(restaurantRepo.save(restaurant));
    }

    @Override
    public String activateRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setIsActive(Boolean.TRUE);
        restaurantRepo.save(restaurant);
        return "Restaurant Activated Successfully";
    }

    @Override
    public String deactivateRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setIsActive(Boolean.FALSE);
        restaurantRepo.save(restaurant);
        return "Restaurant Deactivated Successfully";
    }

    @Override
    public RestaurantHoursResponseDTO getRestaurantHours(UUID restaurantId) {
        RestaurantHours restaurantHours = restaurantHoursRepo.findByRestaurantRestaurantId(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return RestaurantHoursDTOMapper.toDTO(restaurantHours);
    }

    @Override
    public RestaurantHoursResponseDTO updateRestaurantHours(UUID restaurantId, RestaurantHoursRequestDTO requestDTO) {
        RestaurantHours restaurantHours = restaurantHoursRepo.findByRestaurantRestaurantId(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if(requestDTO.getDayOfWeek()!=null){
            restaurantHours.setDayOfWeek(requestDTO.getDayOfWeek());
        }
        if(requestDTO.getClosingTime() != null){
            restaurantHours.setClosingTime(requestDTO.getClosingTime());
        }
        if(requestDTO.getOpeningTime() != null){
            restaurantHours.setOpeningTime(requestDTO.getOpeningTime());
        }
        if(requestDTO.getIsClosed() != null){
            restaurantHours.setIsClosed(requestDTO.getIsClosed());
        }

        return RestaurantHoursDTOMapper.toDTO(restaurantHoursRepo.save(restaurantHours));
    }

    @Override
    public List<CuisineResponseDTO> getAllCuisine() {
        List<Cuisine> cuisines = cuisineRepo.findAll();
        return cuisines.stream().map(CuisineDTOMapper::toDTO).toList();
    }

    @Override
    public CuisineResponseDTO createCuisine(CuisineRequestDTO cuisineRequestDTO) {
        Cuisine cuisine = CuisineDTOMapper.toEntity(cuisineRequestDTO);
        return CuisineDTOMapper.toDTO(cuisineRepo.save(cuisine));
    }

    @Override
    public CuisineResponseDTO getCuisine(Long cuisineId) {
        Cuisine cuisine = cuisineRepo.findById(cuisineId).orElseThrow(()-> new RuntimeException("Cuisine not found"));
        return CuisineDTOMapper.toDTO(cuisine);
    }

//    @Override
//    @SneakyThrows
//    public String updateProfilePic(UUID userId, MultipartFile file) {
//        long MAX_SIZE = 2*1024*1024;
//        List<String> ALLOWED_TYPES = List.of("image/jpeg","image/png","image/jpg");
//        List<String> ALLOWED_EXTENSIONS = List.of("jpeg","png","jpg");
//        Users user = usersRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not Found"));
//        if(file.isEmpty()){
//            throw new RuntimeException("File is Empty");
//        }
//        if(file.getSize() > MAX_SIZE){
//            throw new RuntimeException("File Size should not more than 2MB");
//        }
//        System.out.println("FILE TYPE: "+file.getContentType());
//        System.out.println(ALLOWED_TYPES.contains(file.getContentType()));
//        if(!ALLOWED_TYPES.contains(file.getContentType())){
//            throw new RuntimeException("File Should be of type jpeg,jpg,png");
//        }
//        String originalName = file.getOriginalFilename();
//        if(originalName == null || !originalName.contains(".")){
//            throw new RuntimeException("Invalid File Name");
//        }
//        String extension = originalName.substring(originalName.lastIndexOf(".")+1);
//        System.out.println("EXTENSION:"+extension);
//        if(!ALLOWED_EXTENSIONS.contains(extension)){
//            throw new RuntimeException("File Should be of .jpeg,.jpg,.png");
//        }
//
//        File folder = new File(UPLOAD_DIR);
//        if(!folder.exists()){
//            folder.mkdirs();
//        }
//        String fileName = userId+"."+extension;
//        Path filePath = Paths.get(UPLOAD_DIR+fileName);
//        System.out.println("Path: "+filePath.toString());
//        Files.write(filePath,file.getBytes());
//        String imageURL = UPLOAD_DIR+"/products/images/"+fileName;
//        user.setProfileImageUrl(imageURL);
//        return UserDTOMapper.toDTO(usersRepo.save(user));
//    }
}

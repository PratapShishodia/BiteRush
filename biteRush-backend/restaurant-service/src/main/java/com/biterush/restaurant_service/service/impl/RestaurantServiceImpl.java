package com.biterush.restaurant_service.service.impl;

import com.biterush.restaurant_service.model.dto.mapper.CuisineDTOMapper;
import com.biterush.restaurant_service.model.dto.mapper.RestaurantDTOMapper;
import com.biterush.restaurant_service.model.dto.mapper.RestaurantHoursDTOMapper;
import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.model.entity.*;
import com.biterush.restaurant_service.repository.CuisineRepo;
import com.biterush.restaurant_service.repository.RestaurantCuisineRepo;
import com.biterush.restaurant_service.repository.RestaurantHoursRepo;
import com.biterush.restaurant_service.repository.RestaurantRepo;
import com.biterush.restaurant_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

    private final String UPLOAD_DIR = System.getProperty("user.dir")+"/uploads/Imgs/";
    private final RestaurantHoursRepo restaurantHoursRepo;
    private final RestaurantRepo restaurantRepo;
    private final CuisineRepo cuisineRepo;
    private final RestaurantCuisineRepo restaurantCuisineRepo;


    @Override
    @Transactional
    public String createRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = RestaurantDTOMapper.toEntity(restaurantRequestDTO);
        restaurantRequestDTO.getCuisineIds().forEach(cuisineId -> {
            RestaurantCuisine restaurantCuisine = RestaurantCuisine.builder()
                    .cuisineId(cuisineId)
                    .restaurantId(restaurant.getRestaurantId())
                    .build();
            restaurantCuisineRepo.save(restaurantCuisine);
        });
        restaurantRepo.save(restaurant);
        return "Restaurant Added successfully";
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    @Override
    @Transactional
    @SneakyThrows
    public String uploadRestaurantLogo(UUID restaurantId, MultipartFile file) {
        long MAX_SIZE = 2*1024*1024;
        List<String> ALLOWED_TYPES = List.of("image/jpeg","image/png","image/jpg");
        List<String> ALLOWED_EXTENSIONS = List.of("jpeg","png","jpg");
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not Found"));
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
        String fileName = restaurantId+"."+extension;
        Path filePath = Paths.get(UPLOAD_DIR+fileName);
        System.out.println("Path: "+filePath.toString());
        Files.write(filePath,file.getBytes());
        String imageURL = UPLOAD_DIR+"/products/images/"+fileName;
        restaurant.setLogoUrl(imageURL);
        restaurantRepo.save(restaurant);
        return "Restaurant Logo Uploaded successfully";
    }

    @Override
    @Transactional
    @SneakyThrows
    public String uploadRestaurantBanner(UUID restaurantId, MultipartFile file) {
        long MAX_SIZE = 2*1024*1024;
        List<String> ALLOWED_TYPES = List.of("image/jpeg","image/png","image/jpg");
        List<String> ALLOWED_EXTENSIONS = List.of("jpeg","png","jpg");
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not Found"));
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
        String fileName = restaurantId+"."+extension;
        Path filePath = Paths.get(UPLOAD_DIR+fileName);
        System.out.println("Path: "+filePath.toString());
        Files.write(filePath,file.getBytes());
        String imageURL = UPLOAD_DIR+"/products/images/"+fileName;
        restaurant.setCoverImageUrl(imageURL);
        restaurantRepo.save(restaurant);
        return "Restaurant Banner Uploaded successfully";
    }

    @Override
    @Transactional
    @SneakyThrows
    public String uploadCuisineImage(UUID cuisineId, MultipartFile file) {
        long MAX_SIZE = 2*1024*1024;
        List<String> ALLOWED_TYPES = List.of("image/jpeg","image/png","image/jpg");
        List<String> ALLOWED_EXTENSIONS = List.of("jpeg","png","jpg");
        Cuisine cuisine = cuisineRepo.findById(cuisineId).orElseThrow(()-> new RuntimeException("Restaurant not Found"));
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
        String fileName = cuisineId+"."+extension;
        Path filePath = Paths.get(UPLOAD_DIR+fileName);
        System.out.println("Path: "+filePath.toString());
        Files.write(filePath,file.getBytes());
        String imageURL = UPLOAD_DIR+"/products/images/"+fileName;
        cuisine.setImageUrl(imageURL);
        cuisineRepo.save(cuisine);
        return "Cuisine Image Uploaded successfully";
    }

    @Override
    public Restaurant getRestaurants(UUID restaurantId) {
        return restaurantRepo.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not Found"));
    }

    @Override
    @Transactional
    public String updateRestaurant(UUID restaurantId, RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        if(restaurantRequestDTO.getIsPureVeg() != null){
            restaurant.setIsPureVeg(restaurantRequestDTO.getIsPureVeg());
        }
        if(restaurantRequestDTO.getName() != null){
            restaurant.setName(restaurantRequestDTO.getName());
        }

        if(restaurantRequestDTO.getDescription() != null){
            restaurant.setDescription(restaurantRequestDTO.getDescription());
        }
        if(restaurantRequestDTO.getPhoneNumber() != null){
            restaurant.setPhoneNumber(restaurantRequestDTO.getPhoneNumber());
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
        if(restaurantRequestDTO.getDeliveryMaxTime() != null){
            restaurant.setDeliveryMaxTime(restaurantRequestDTO.getDeliveryMaxTime());
        }
        if(restaurantRequestDTO.getDeliveryMinTime() != null){
            restaurant.setDeliveryMinTime(restaurantRequestDTO.getDeliveryMinTime());
        }
        if(restaurantRequestDTO.getIsPureVeg() != null){
            restaurant.setIsPureVeg(restaurantRequestDTO.getIsPureVeg());
        }
        if(!restaurantRequestDTO.getCuisineIds().isEmpty()) {
            for (UUID id : restaurantRequestDTO.getCuisineIds()) {
                if (!restaurantCuisineRepo.existsById(id)) {
                    RestaurantCuisine restaurantCuisine = RestaurantCuisine.builder()
                            .cuisineId(id)
                            .restaurantId(restaurant.getRestaurantId())
                            .build();
                    restaurantCuisineRepo.save(restaurantCuisine);
                }
            }
        }
        restaurantRepo.save(restaurant);
        return "Restaurant Updated successfully";
    }

    @Override
    @Transactional
    public String activateRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        restaurant.setIsActive(true);
        restaurantRepo.save(restaurant);
        return "Restaurant Activated successfully";
    }

    @Override
    @Transactional
    public String deactivateRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        restaurant.setIsActive(false);
        restaurantRepo.save(restaurant);
        return "Restaurant Deactivated successfully";
    }

    @Override
    public String createRestaurantHours(RestaurantHoursRequestDTO requestDTO) {
        RestaurantHours restaurantHours = RestaurantHoursDTOMapper.toEntity(requestDTO);
        restaurantHours.setRestaurant(restaurantRepo.findById(requestDTO.getRestaurant()).orElseThrow(()->new RuntimeException("Restaurant not found")));
        restaurantHoursRepo.save(restaurantHours);
        return "Restaurant hours created successfully";
    }

    @Override
    public RestaurantHours getRestaurantHours(UUID restaurantId) {
        return restaurantHoursRepo.findByRestaurantRestaurantId(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant hours not found"));
    }

    @Override
    @Transactional
    public String updateRestaurantHours(UUID restaurantId, RestaurantHoursRequestDTO requestDTO) {
        RestaurantHours restaurantHours = restaurantHoursRepo.findByRestaurantRestaurantId(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant hours not found"));
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
        restaurantHoursRepo.save(restaurantHours);
        return "Restaurant hours updated successfully";
    }

    @Override
    public List<Cuisine> getAllCuisine() {
        return cuisineRepo.findAll();
    }

    @Override
    @Transactional
    public String createCuisine(CuisineRequestDTO cuisineRequestDTO) {
        Cuisine cuisine = CuisineDTOMapper.toEntity(cuisineRequestDTO);
        cuisineRepo.save(cuisine);
        return "Cuisine created successfully";
    }

    @Override
    public Cuisine getCuisine(UUID cuisineId) {
        return cuisineRepo.findById(cuisineId).orElseThrow(()->new RuntimeException("Cuisine not found"));
    }

    @Override
    public List<Cuisine> getCuisinesByRestaurantId(UUID restaurantId) {
        List<UUID> cuisineIds = restaurantCuisineRepo.findByRestaurantId(restaurantId).stream().map(RestaurantCuisine::getCuisineId).toList();
        List<Cuisine> cuisines = new ArrayList<>();
        for(UUID cuisineId : cuisineIds){
            Cuisine cuisine = cuisineRepo.findById(cuisineId).orElseThrow(()->new RuntimeException("Cuisine not found"));
            cuisines.add(cuisine);
        }
        return cuisines;
    }

    @Override
    public List<Restaurant> getRestaurantsByCuisineId(UUID cuisineId) {
        List<UUID> restaurantIds = restaurantCuisineRepo.findByCuisineId(cuisineId).stream().map(RestaurantCuisine::getRestaurantId).toList();
        List<Restaurant> restaurants = new ArrayList<>();
        for(UUID restaurantId : restaurantIds){
            Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
            restaurants.add(restaurant);
        }
        return restaurants;
    }
}

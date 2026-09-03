package com.biterush.menu_service.service.impl;
import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.entity.MenuCategory;
import com.biterush.menu_service.model.entity.MenuItem;
import com.biterush.menu_service.model.mapper.MenuCategoryDTOMapper;
import com.biterush.menu_service.model.mapper.MenuItemDTOMapper;
import com.biterush.menu_service.repository.MenuCategoryRepo;
import com.biterush.menu_service.repository.MenuItemRepo;
import com.biterush.menu_service.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    private final MenuItemRepo menuItemRepo;
    private final MenuCategoryRepo menuCategoryRepo;

    @Override
    @Transactional
    public String createMenuCategory(MenuCategoryRequestDTO requestDTO) {
        if(menuCategoryRepo.existsByCategoryNameAndRestaurantId(requestDTO.getCategoryName(), requestDTO.getRestaurantId())) {
            throw new RuntimeException("Category Already Present");
        }
        MenuCategory menuCategory = MenuCategoryDTOMapper.toEntity(requestDTO);
        menuCategoryRepo.save(menuCategory);
        return "Category Added Successfully";
    }

    @Override
    @Transactional
    public String createMenuItem(MenuItemRequestDTO requestDTO) {
        if(menuItemRepo.existsByItemNameAndRestaurantId(requestDTO.getItemName(),requestDTO.getRestaurantId())) {
            throw new RuntimeException("Item Already Listed");
        }
        MenuItem menuItem = MenuItemDTOMapper.toEntity(requestDTO);
        menuItem.setCategory(menuCategoryRepo.findById(requestDTO.getCategoryId()).orElseThrow(()->new RuntimeException("Category Not Found")));
        menuItemRepo.save(menuItem);
        return "Menu Item Added Successfully";
    }

    @Override
    @Transactional
    public String updateMenuItem(UUID itemId, MenuItemRequestDTO requestDTO) {
        MenuItem menuItem = menuItemRepo.findById(itemId).orElseThrow(()->new RuntimeException("Item Not Found"));
        if(menuItemRepo.existsByItemNameAndRestaurantIdNot(requestDTO.getItemName(),requestDTO.getRestaurantId())) {
            throw new RuntimeException("Item Already Listed");
        }
        if(requestDTO.getCategoryId()!=null){
            menuItem.setCategory(menuCategoryRepo.findById(requestDTO.getCategoryId()).orElseThrow(()->new RuntimeException("Category Not Found")));
        }
        if (requestDTO.getItemName() != null) {
            menuItem.setItemName(requestDTO.getItemName());
        }

        if (requestDTO.getItemDescription() != null) {
            menuItem.setItemDescription(requestDTO.getItemDescription());
        }

        if (requestDTO.getItemImage() != null) {
            menuItem.setItemImage(requestDTO.getItemImage());
        }

        if (requestDTO.getItemPrice() != null) {
            menuItem.setItemPrice(requestDTO.getItemPrice());
        }

        if (requestDTO.getDiscountedPrice() != null) {
            menuItem.setDiscountedPrice(requestDTO.getDiscountedPrice());
        }

        if (requestDTO.getIsVeg() != null) {
            menuItem.setIsVeg(requestDTO.getIsVeg());
        }

        if (requestDTO.getIsAvailable() != null) {
            menuItem.setIsAvailable(requestDTO.getIsAvailable());
        }

        if (requestDTO.getPreparationTime() != null) {
            menuItem.setPreparationTime(requestDTO.getPreparationTime());
        }

        if (requestDTO.getItemImage() != null) {
            menuItem.setItemImgUrl(requestDTO.getItemImage());
        }
        menuItemRepo.save(menuItem);
        return "Item Updated Successfully";
    }

    @Override
    public String updateMenuCategory(UUID categoryId, MenuCategoryRequestDTO requestDTO) {
        MenuCategory menuCategory = menuCategoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
        if(menuCategoryRepo.existsByCategoryNameAndRestaurantIdNot(requestDTO.getCategoryName(), requestDTO.getRestaurantId())) {
            throw new RuntimeException("Category Already Present");
        }
        if(requestDTO.getCategoryName() != null){
            menuCategory.setCategoryName(requestDTO.getCategoryName());
        }
        if(requestDTO.getCategoryDescription() != null){
            menuCategory.setCategoryDescription(requestDTO.getCategoryDescription());
        }
        if(requestDTO.getIsActive()!=null){
            menuCategory.setIsActive(requestDTO.getIsActive());
        }
        menuCategoryRepo.save(menuCategory);
        return "Menu Category Updated";
    }

    @Override
    public String deleteMenuItem(UUID itemId) {
        MenuItem menuItem = menuItemRepo.findById(itemId).orElseThrow(()->new RuntimeException("Item Not Found"));
        menuItemRepo.delete(menuItem);
        return "Item Deleted Successfully";
    }

    @Override
    public String deleteMenuCategory(UUID categoryId) {
        MenuCategory menuCategory = menuCategoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
        menuCategoryRepo.delete(menuCategory);
        return "Category Deleted Successfully";
    }

    @Override
    public MenuItem getMenuItemById(UUID itemId) {
        return menuItemRepo.findById(itemId).orElseThrow(()->new RuntimeException("Item Not Found"));
    }

    @Override
    public List<MenuItem> getMenuItems(UUID restaurantId) {
        return menuItemRepo.findByRestaurantIdAndIsAvailable(restaurantId);
    }

//    @Override
//    public List<MenuItem> getMenuItemsByCategory(UUID categoryId) {
//        return List.of();
//    }

    @Override
    public List<MenuCategory> getMenuCategories(UUID restaurantId) {
        return menuCategoryRepo.findActiveByRestaurantId(restaurantId);
    }

    @Override
    public MenuCategory getMenuCategory(UUID categoryId) {
        return menuCategoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
    }
}

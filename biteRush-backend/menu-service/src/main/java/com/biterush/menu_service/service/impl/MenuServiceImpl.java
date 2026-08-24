package com.biterush.menu_service.service.impl;

import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemOptionRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.dto.response.MenuCategoryResponseDTO;
import com.biterush.menu_service.model.dto.response.MenuItemOptionResponseDTO;
import com.biterush.menu_service.model.dto.response.MenuItemResponseDTO;
import com.biterush.menu_service.model.entity.MenuCategory;
import com.biterush.menu_service.model.entity.MenuItem;
import com.biterush.menu_service.model.entity.MenuItemOption;
import com.biterush.menu_service.model.mapper.MenuCategoryDTOMapper;
import com.biterush.menu_service.model.mapper.MenuItemDTOMapper;
import com.biterush.menu_service.model.mapper.MenuItemOptionDTOMapper;
import com.biterush.menu_service.repository.MenuCategoryRepo;
import com.biterush.menu_service.repository.MenuItemOptionRepo;
import com.biterush.menu_service.repository.MenuItemRepo;
import com.biterush.menu_service.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuItemRepo menuItemRepo;
    private final MenuCategoryRepo menuCategoryRepo;
    private final MenuItemOptionRepo menuItemOptionRepo;

    @Override
    public List<MenuItemResponseDTO> getMenuItems(UUID restaurantId) {
        List<MenuItem> menuItems = menuItemRepo.findByRestaurantId(restaurantId);
        return menuItems.stream().map(MenuItemDTOMapper::toDTO).toList();
    }

    @Override
    public MenuItemResponseDTO getMenuItem(UUID menuItemId) {
        MenuItem menuItem = menuItemRepo.findById(menuItemId).orElseThrow(()-> new RuntimeException("Item Not Found"));
        return MenuItemDTOMapper.toDTO(menuItem);
    }

    @Override
    public MenuCategoryResponseDTO createMenuCategory(UUID restaurantId,MenuCategoryRequestDTO requestDTO) {
        MenuCategory menuCategory = MenuCategoryDTOMapper.toEntity(requestDTO);
        menuCategory.setCategoryId(UUID.randomUUID());
        menuCategory.setRestaurantId(restaurantId);
        menuCategory.setIsActive(Boolean.TRUE);
        return MenuCategoryDTOMapper.toDTO(menuCategoryRepo.save(menuCategory));
    }

    @Override
    public MenuCategoryResponseDTO updateMenuCategory(UUID categoryId, MenuCategoryRequestDTO request) {
        MenuCategory menuCategory = menuCategoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
        if (request.getRestaurantId() != null)
            menuCategory.setRestaurantId(request.getRestaurantId());

        if (request.getCategoryName() != null)
            menuCategory.setCategoryName(request.getCategoryName());

        if (request.getCategoryDescription() != null)
            menuCategory.setCategoryDescription(request.getCategoryDescription());

        if (request.getDisplayOrder() != null)
            menuCategory.setDisplayOrder(request.getDisplayOrder());

        if (request.getIsActive() != null)
            menuCategory.setIsActive(request.getIsActive());

        return MenuCategoryDTOMapper.toDTO(menuCategoryRepo.save(menuCategory));
    }

    @Override
    public String deleteMenuCategory(UUID categoryId) {
        menuCategoryRepo.deleteById(categoryId);
        return "Category Deleted SuccessFully";
    }

    @Override
    public MenuItemResponseDTO createMenuItem(UUID restaurantId,MenuItemRequestDTO requestDTO) {
        MenuItem menuItem = MenuItemDTOMapper.toEntity(requestDTO);
        menuItem.setMenuItemId(UUID.randomUUID());
        menuItem.setRestaurantId(restaurantId);
        menuItem.setCreatedAt(LocalDateTime.now());
        return MenuItemDTOMapper.toDTO(menuItemRepo.save(menuItem));
    }

    @Override
    public MenuItemResponseDTO updateMenuItem(UUID menuItemId, MenuItemRequestDTO request) {
        MenuItem menuItem = menuItemRepo.findById(menuItemId).orElseThrow(()->new RuntimeException("MenuItem not found"));
        menuItem.setUpdatedAt(LocalDateTime.now());
        if (request.getRestaurantId() != null)
            menuItem.setRestaurantId(request.getRestaurantId());

        if (request.getCategoryId() != null)
            menuItem.setCategoryId(request.getCategoryId());

        if (request.getMenuItemName() != null)
            menuItem.setMenuItemName(request.getMenuItemName());

        if (request.getMenuItemDescription() != null)
            menuItem.setMenuItemDescription(request.getMenuItemDescription());

        if (request.getImageUrl() != null)
            menuItem.setImageUrl(request.getImageUrl());

        if (request.getPrice() != null)
            menuItem.setPrice(request.getPrice());

        if (request.getDiscountedPrice() != null)
            menuItem.setDiscountedPrice(request.getDiscountedPrice());

        if (request.getIsVeg() != null)
            menuItem.setIsVeg(request.getIsVeg());

        if (request.getIsAvailable() != null)
            menuItem.setIsAvailable(request.getIsAvailable());

        if (request.getPreparationTime() != null)
            menuItem.setPreparationTime(request.getPreparationTime());

        if (request.getDisplayOrder() != null)
            menuItem.setDisplayOrder(request.getDisplayOrder());

        return MenuItemDTOMapper.toDTO(menuItemRepo.save(menuItem));
    }

    @Override
    public String deleteMenuItem(UUID menuItemId) {
        menuItemRepo.deleteById(menuItemId);
        return "Menu Item Deleted Successfully";
    }

    @Override
    public String updateMenuItemAvailability(UUID menuItemId,String availableStatus) {
        MenuItem menuItem = menuItemRepo.findById(menuItemId).orElseThrow(()->new RuntimeException("MenuItem not found"));
        menuItem.setIsAvailable(availableStatus.equals("Available") ? Boolean.TRUE : Boolean.FALSE);
        return "Status changed Successfully";
    }

    @Override
    public MenuItemOptionResponseDTO createMenuItemOption(MenuItemOptionRequestDTO requestDTO) {
        MenuItemOption menuItemOption = MenuItemOptionDTOMapper.toEntity(requestDTO);
        menuItemOption.setMenuItemOptionId(UUID.randomUUID());
        return MenuItemOptionDTOMapper.toDTO(menuItemOptionRepo.save(menuItemOption));
    }

    @Override
    public MenuItemOptionResponseDTO updateMenuItemOption(UUID menuItemOptionId, MenuItemOptionRequestDTO request) {
        MenuItemOption option = menuItemOptionRepo.findById(menuItemOptionId).orElseThrow(()->new RuntimeException("MenuItem not found"));

        if (request.getMenuItemId() != null)
            option.setMenuItemId(request.getMenuItemId());

        if (request.getMenuItemOptionName() != null)
            option.setMenuItemOptionName(request.getMenuItemOptionName());

        if (request.getMenuItemType() != null)
            option.setMenuItemType(request.getMenuItemType());

        if (request.getIsRequired() != null)
            option.setIsRequired(request.getIsRequired());

        if (request.getMinSelection() != null)
            option.setMinSelection(request.getMinSelection());

        if (request.getMaxSelection() != null)
            option.setMaxSelection(request.getMaxSelection());

        return MenuItemOptionDTOMapper.toDTO(menuItemOptionRepo.save(option)) ;
    }

    @Override
    public String deleteMenuItemOption(UUID menuItemOptionId) {
        menuItemOptionRepo.deleteById(menuItemOptionId);
        return "Option Deleted Successfully";
    }
}

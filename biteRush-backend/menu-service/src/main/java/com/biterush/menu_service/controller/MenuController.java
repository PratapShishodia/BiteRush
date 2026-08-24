package com.biterush.menu_service.controller;

import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.dto.response.MenuCategoryResponseDTO;
import com.biterush.menu_service.model.dto.response.MenuItemOptionResponseDTO;
import com.biterush.menu_service.model.dto.response.MenuItemResponseDTO;
import com.biterush.menu_service.service.MenuService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/restaurants/{restaurantId}/menu")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuItems(@PathVariable UUID restaurantId) {
        return ResponseEntity.ok(menuService.getMenuItems(restaurantId));
    }

    @PostMapping("/restaurants/{restaurantId}/menu/categories")
    public ResponseEntity<MenuCategoryResponseDTO> createMenuCategory(@PathVariable UUID restaurantId, @RequestBody MenuCategoryRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenuCategory(restaurantId,requestDTO));
    }

    @PutMapping("menu/categories/{categoryId}")
    public ResponseEntity<MenuCategoryResponseDTO> updateMenuCategory(@PathVariable UUID categoryId, @RequestBody MenuCategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(menuService.updateMenuCategory(categoryId,requestDTO));
    }

    @DeleteMapping("/menu/categories/{categoryId}")
    public ResponseEntity<String> deleteMenuCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(menuService.deleteMenuCategory(categoryId));
    }

    @PostMapping("/restaurants/{restaurantId}/menu/items")
    public ResponseEntity<MenuItemResponseDTO> createMenuItem(@PathVariable UUID restaurantId, @RequestBody MenuItemRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenuItem(restaurantId,requestDTO));
    }

    @GetMapping("/menu/items/{itemId}")
    public ResponseEntity<MenuItemResponseDTO> getMenuItem(@PathVariable UUID itemId) {
        return ResponseEntity.ok(menuService.getMenuItem(itemId));
    }

    @PutMapping("/menu/items/{itemId}")
    public ResponseEntity<MenuItemResponseDTO> updateMenuItem(@PathVariable UUID itemId, @RequestBody MenuItemRequestDTO requestDTO) {
        return ResponseEntity.ok(menuService.updateMenuItem(itemId,requestDTO));
    }
}

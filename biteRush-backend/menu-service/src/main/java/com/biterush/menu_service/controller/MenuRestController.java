package com.biterush.menu_service.controller;

import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.entity.MenuCategory;
import com.biterush.menu_service.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MenuRestController {

    private final MenuService menuService;

    @PostMapping("/menuCategory")
    public ResponseEntity<String> createMenuCategory(@RequestBody MenuCategoryRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenuCategory(requestDTO));
    }

    @PostMapping("/menuItem")
    public ResponseEntity<String> createMenuItem(@RequestBody MenuItemRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenuItem(requestDTO));
    }

    @PutMapping("/menuItem/{itemId}")
    public ResponseEntity<String> updateMenuItem(@PathVariable UUID itemId, @RequestBody MenuItemRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(menuService.updateMenuItem(itemId, requestDTO));
    }

    @PutMapping("/menuCategory/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable UUID categoryId, @RequestBody MenuCategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(menuService.updateMenuCategory(categoryId, requestDTO));
    }

    @DeleteMapping("/menuItem/{itemId}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable UUID itemId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(menuService.deleteMenuItem(itemId));
    }

    @DeleteMapping("/menuCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(menuService.deleteMenuCategory(categoryId));
    }

}

package com.biterush.menu_service.controller;

import com.biterush.menu_service.model.entity.MenuCategory;
import com.biterush.menu_service.model.entity.MenuItem;
import com.biterush.menu_service.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MenuGraphQlController {
    private final MenuService menuService;

    @QueryMapping
    public MenuItem getMenuItemById(@Argument UUID itemId) {
        return menuService.getMenuItemById(itemId);
    }

    @QueryMapping
    public List<MenuItem> getMenuItems(@Argument UUID restaurantId) {
        return menuService.getMenuItems(restaurantId);
    }

    @QueryMapping
    public List<MenuCategory> getMenuCategories(@Argument UUID restaurantId) {
        return menuService.getMenuCategories(restaurantId);
    }

    @QueryMapping
    public MenuCategory getMenuCategory(@Argument UUID categoryId) {
        return menuService.getMenuCategory(categoryId);
    }
}

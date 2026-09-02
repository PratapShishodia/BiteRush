import { apiInstance } from "./AxiosInstance"

export const menuService = {

    getMenu(restaurantId){
        return apiInstance.get(`/restaurants/${restaurantId}/menu`)
    },

    createMenuCategory(restaurantId,menuCategoryJSON){
        return apiInstance.post(`/restaurants/${restaurantId}/menu/categories`,menuCategoryJSON)
    },

    updateMenuCategory(categoryId,menuCategoryJSON){
        return apiInstance.put(`menu/categories/${categoryId}`,menuCategoryJSON)
    },

    deleteMenuCategory(categoryId){
        return apiInstance.delete(`/menu/categories/${categoryId}`)
    },

    createMenuItem(restaurantId,menuItemJSON){
        return apiInstance.post(`/restaurants/${restaurantId}/menu/items`,menuItemJSON)
    },

    getMenuItem(itemId){
        return apiInstance.get(`/menu/items/${itemId}`)
    },

    updateMenuItem(itemId,menuItemJSON){
        return apiInstance.put(`/menu/items/${itemId}`,menuItemJSON)
    }
}
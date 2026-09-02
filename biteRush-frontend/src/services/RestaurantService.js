import { apiInstance } from "./AxiosInstance"

export const restaurantService = {
    
    createRestaurant(restaurantJSON){
        return apiInstance.post("/restaurants",restaurantJSON)
    },

    getAllRestaurant(){
        return apiInstance.get("/restaurants")
    },
    
    getRestaurant(restaurantId){
        return apiInstance.get(`/restaurants/${restaurantId}`)
    },

    updateRestaurant(restaurantId,restaurantJSON){
        return apiInstance.put(`/restaurants/${restaurantId}`,restaurantJSON)
    },

    activateRestaurant(restaurantId){
        return apiInstance.patch(`/restaurants/${restaurantId}/activate`)
    },

    deactivateRestaurant(restaurantId){
        return apiInstance.patch(`/restaurants/${restaurantId}/deactivate`)
    },

    getRestaurantHours(restaurantId){
        return apiInstance.get(`/restaurants/${restaurantId}/hours`)
    },

    updateRestaurantHours(restaurantId,restaurantHourJSON){
        return apiInstance.put(`/restaurants/${restaurantId}/hours`,restaurantHourJSON)
    },

    getAllCuisine(){
        return apiInstance.get("/cuisine")
    },

    addCuisine(cuisineJSON){
        return apiInstance.post("/cuisine",cuisineJSON)
    },

    getCuisine(cuisineId){
        return apiInstance.get(`/cuisine/${cuisineId}`)
    }
}
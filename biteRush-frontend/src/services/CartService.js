import { apiInstance } from "./AxiosInstance"

export const cartService = {
    
    getCart(userId){
        return apiInstance.get(`/cart/${userId}`)
    },

    addCartItem(cartItemJSON){
        return apiInstance.post(`/cart/items`,cartItemJSON)
    },

    updateCartItem(userId,quantity){
        return apiInstance.put(`/cart/${userId}/items/${quantity}`)
    },

    deleteCartItem(cartItemId){
        return apiInstance.delete(`/cart/cart/items/${cartItemId}`)
    },

    clearCart(userId){
        return apiInstance.put(`/cart/cart/clear/${userId}`)
    }
}
import { apiInstance } from "./AxiosInstance"

export const orderService = {

    createOrder(orderJSON){
        return apiInstance.post("/orders",orderJSON)
    },

    getOrder(orderId){
        return apiInstance.get(`/orders/${orderId}`)
    },

    getUserOrder(userId){
        return apiInstance.get(`/orders/${userId}`)
    },

    cancelOrder(orderId){
        return apiInstance.put(`/orders/${orderId}/cancel`)
    },

    getOrderStatus(orderId){
        return apiInstance.get(`/orders/${orderId}/status`)
    },

    getOrderStatusHistory(orderId){
        return apiInstance.get(`/orders/${orderId}/status-history`)
    },

    updateOrderStatus(orderId,status){
        return apiInstance.patch(`/orders/${orderId}/status?status=${status}`)
    }
}
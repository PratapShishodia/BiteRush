import { apiInstance } from "./AxiosInstance"

export const userService = {

    getCurrentUser(userId){
        return apiInstance.get(`/users/me?userId=${userId}`)
    },

    updateProfile(userId){
        return apiInstance.put(`/users/update/me?userId=${userId}`)
    },

    updateProfilePic(userId, imageFile) {
        const formData = new FormData()
        formData.append("file", imageFile)

        return apiInstance.put(
            `/users/uploadImage/${userId}`,
            formData
        )
    },

    addAddress(addressJSON){
        return apiInstance.post("/users/me/addresses",addressJSON)
    },

    updateAddress(addressId,addressJSON){
        return apiInstance.put(`/users/addresses/${addressId}`,addressJSON)
    },

    deleteAddress(addressId){
        return apiInstance.delete(`/users/addresses/${addressId}`)
    },

    setDefaultAddress(addressId){
        return apiInstance.patch(`/users/addresses/${addressId}/default`)
    },

    getUserAddresses(userId){
        return apiInstance.get(`/users/me/addresses?userId=${userId}`)
    }

}
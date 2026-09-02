import { apiInstance } from "./AxiosInstance"

export const authService = {
    
    register(registerJSON){
        return apiInstance.post("/auth/register",registerJSON)
    },

    login(loginJSON){
        return apiInstance.post("/auth/login",loginJSON)
    },

    refresh(refreshJSON){
        return apiInstance.post("/auth/refresh",refreshJSON)
    },

    logout(userId){
        return apiInstance.put(`/auth/logout?userId=${userId}`)
    },

    forgetPassword(email,OTP){
        return apiInstance.put(`/auth/forget-password?email=${email}&OTP=${OTP}`)
    },

    sendOTP(email){
        return apiInstance.put(`auth/send-otp?email=${email}`)
    },

    changePassword(passwordJSON){
        return apiInstance.put("/auth/password",passwordJSON)
    },

    updateEmail(userId,email){
        return apiInstance.put(`/auth/email?userId=${userId}&email=${email}`)
    },

    getMe(){
        return apiInstance.get("/auth/me")
    },

    verifyOTP(email,otp){
        return apiInstance.put(`/auth/verify-otp?email=${email}&otp=${otp}`)
    }
}
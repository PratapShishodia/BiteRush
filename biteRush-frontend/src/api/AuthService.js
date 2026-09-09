import { apiInstance } from "./AxiosInstance";

export const register = async (registerJSON) => {
  const response = await apiInstance.post("/auth/signup", registerJSON);
  return response.data;
};

export const login = async (loginRequestJSON) => {
  const response = await apiInstance.post("/auth/login", loginRequestJSON);
  return response.data;
};

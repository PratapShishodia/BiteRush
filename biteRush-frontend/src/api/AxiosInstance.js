import axios from "axios";

console.log("API URL:", import.meta.env.VITE_API_URL);
export const apiInstance = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: Number(import.meta.env.VITE_REQUEST_TIMEOUT) || 10000,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

apiInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("jwtToken");
    console.log("Token:")
    console.log(token)
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => Promise.reject(error),
);

apiInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    console.log("STATUS:", error.response?.status);
    console.log("DATA:", error.response?.data);
    console.log("HEADERS:", error.response?.headers);
    return Promise.reject(error);
  }
);

// apiInstance.interceptors.response.use(
//   (response) => response,
//   async (error) => {
//     const originalRequest = error.config;

//     if (error.response?.status === 401 && !originalRequest._retry) {
//       originalRequest._retry = true;

//       try {
//         const refreshToken = localStorage.getItem("refresh_token");

//         const response = await axios.post(
//           `${import.meta.env.VITE_API_URL}/user/refresh`,
//           {
//             refreshToken,
//           },
//         );

//         localStorage.setItem("jwtToken", response.data.accessToken);

//         originalRequest.headers.Authorization = `Bearer ${response.data.accessToken}`;

//         return apiInstance(originalRequest);
//       } catch (err) {
//         localStorage.removeItem("access_token");
//         window.location.href = "/login";
//       }
//     }

//     return Promise.reject(error);
//   },
// );

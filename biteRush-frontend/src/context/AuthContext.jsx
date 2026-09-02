import { createContext, useContext, useEffect, useState } from "react";
import { authService } from "../services/AuthService";

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  const login = (userData) => {
    console.table(userData);
    setUser(userData);
  };

  const logout = () => {
    localStorage.removeItem("jwtToken");
    setUser(null);
  };

  const fetchUser = async () => {
    console.log("After Refresh");
    const access_token = localStorage.getItem("jwtToken");
    console.log(access_token);
    if (!access_token) return;

    try {
      console.log("In UserContext after Refresh");
      const response = await authService.getMe();
      console.table(response);
      const userJSON = response.data;
      setUser(userJSON);
    } catch (error) {
      console.error(error);
      localStorage.removeItem("jwtToken");
      setUser(null);
    }
  };
  useEffect(() => {
    fetchUser();
  }, []);

  return (
    <AuthContext.Provider
      value={{
        user,
        login,
        logout,
        isLoggedIn: Boolean(user),
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}

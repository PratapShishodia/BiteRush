import { useMutation, useQueryClient } from "@tanstack/react-query"
import { login } from "../api/AuthService";


export const useLogin = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: login,

    onSuccess: (data) => {
      console.log("Login successful:", data);

      queryClient.invalidateQueries({
        queryKey: ["auth", "me"],
      });
    },
  });
};
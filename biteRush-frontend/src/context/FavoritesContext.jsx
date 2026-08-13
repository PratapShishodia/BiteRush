import { createContext, useContext, useState } from "react";

const FavoritesContext = createContext();

export function FavoritesProvider({ children }) {
  const [favorites, setFavorites] = useState([]);

  const isFavorite = (id) => {
    return favorites.some((restaurant) => restaurant.id === id);
  };

  const toggleFavorite = (restaurant) => {
    setFavorites((currentFavorites) => {
      const exists = currentFavorites.some((item) => item.id === restaurant.id);

      if (exists) {
        return currentFavorites.filter((item) => item.id !== restaurant.id);
      }

      return [...currentFavorites, restaurant];
    });
  };

  return (
    <FavoritesContext.Provider
      value={{
        favorites,
        isFavorite,
        toggleFavorite,
      }}
    >
      {children}
    </FavoritesContext.Provider>
  );
}

export function useFavorites() {
  return useContext(FavoritesContext);
}

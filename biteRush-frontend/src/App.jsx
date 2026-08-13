import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import Restaurants from "./pages/Restaurants";
import RestaurantDetails from "./pages/RestaurantDetails";
import Cart from "./pages/Cart";
import Checkout from "./pages/Checkout";
import OrderTracking from "./pages/OrderTracking";
import Orders from "./pages/Orders";
import Search from "./pages/Search";
import Profile from "./pages/Profile";
import Favorites from "./pages/Favorites";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import { CartProvider } from "./context/CartContext";
import { FavoritesProvider } from "./context/FavoritesContext";
import NotFound from "./pages/NotFound";
import { AuthProvider } from "./context/AuthContext";

function App() {
  return (
    <BrowserRouter>
      <CartProvider>
        <FavoritesProvider>
          <AuthProvider>
            <Navbar />

            <main className="min-h-screen bg-white dark:bg-gray-950">
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/restaurants" element={<Restaurants />} />
                <Route path="/restaurant/:id" element={<RestaurantDetails />} />
                <Route path="/search" element={<Search />} />
                <Route path="/cart" element={<Cart />} />
                <Route path="/checkout" element={<Checkout />} />
                <Route path="/orders" element={<Orders />} />
                <Route path="/orders/:id" element={<OrderTracking />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/favorites" element={<Favorites />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="*" element={<NotFound />} />
              </Routes>
            </main>

            <Footer />
          </AuthProvider>
        </FavoritesProvider>
      </CartProvider>
    </BrowserRouter>
  );
}

export default App;

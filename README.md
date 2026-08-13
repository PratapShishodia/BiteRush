# BiteRush

# BiteRush Frontend

A modern, responsive food delivery application built with React and Vite. BiteRush allows users to browse restaurants, search for food items, manage favorites, and place food orders with real-time tracking.

## 🌟 Features

- **User Authentication** - Secure login and signup functionality
- **Restaurant Browsing** - Browse and explore available restaurants
- **Food Search** - Search for specific food items and restaurants
- **Restaurant Details** - View detailed information about restaurants including menus
- **Shopping Cart** - Add items to cart and manage quantities
- **Favorites** - Save favorite restaurants and food items
- **Checkout** - Secure checkout process
- **Order Management** - View order history and details
- **Order Tracking** - Real-time order tracking and status updates
- **User Profile** - Manage user profile information
- **Responsive Design** - Mobile-friendly interface using Tailwind CSS
- **Error Handling** - Comprehensive error state management

## 🛠️ Tech Stack

- **React 19** - UI library
- **Vite** - Build tool and dev server
- **React Router v7** - Client-side routing
- **Tailwind CSS 4** - Utility-first CSS framework
- **Context API** - State management for cart, favorites, and authentication
- **ESLint** - Code quality and linting

## 📋 Prerequisites

- Node.js (v16 or higher)
- npm or yarn package manager

## 🚀 Getting Started

### Installation

1. Clone the repository:

```bash
git clone <repository-url>
cd biteRush-frontend
```

2. Install dependencies:

```bash
npm install
```

### Development

Start the development server:

```bash
npm run dev
```

The application will be available at `http://localhost:5173` with hot module replacement (HMR) enabled.

### Build

Create an optimized production build:

```bash
npm run build
```

### Preview

Preview the production build locally:

```bash
npm run preview
```

### Linting

Run ESLint to check code quality:

```bash
npm run lint
```

## 📁 Project Structure

```
src/
├── components/          # Reusable UI components
│   ├── ErrorState.jsx   # Error display component
│   ├── FoodCard.jsx     # Food item display card
│   ├── Footer.jsx       # Footer component
│   ├── Navbar.jsx       # Navigation bar
│   └── RestaurantCard.jsx # Restaurant display card
├── pages/               # Page components
│   ├── Cart.jsx         # Shopping cart page
│   ├── Checkout.jsx     # Checkout process page
│   ├── Favorites.jsx    # Saved favorites page
│   ├── Home.jsx         # Home/landing page
│   ├── Login.jsx        # User login page
│   ├── NotFound.jsx     # 404 page
│   ├── Orders.jsx       # Order history page
│   ├── OrderTracking.jsx # Real-time order tracking
│   ├── Profile.jsx      # User profile page
│   ├── RestaurantDetails.jsx # Restaurant menu and details
│   ├── Restaurants.jsx  # Restaurant listing page
│   ├── Search.jsx       # Search results page
│   └── Signup.jsx       # User registration page
├── context/             # Context providers
│   ├── AuthContext.jsx  # Authentication state
│   ├── CartContext.jsx  # Shopping cart state
│   └── FavoritesContext.jsx # Favorites state
├── App.jsx              # Main app component
├── main.jsx             # Application entry point
└── index.css            # Global styles
```

## 🔄 State Management

The application uses React Context API for state management:

- **AuthContext** - Handles user authentication state
- **CartContext** - Manages shopping cart items and totals
- **FavoritesContext** - Manages user's favorite restaurants and items

## 🎨 Styling

The project uses **Tailwind CSS 4** for styling with a modern, responsive design approach. Global styles are defined in `index.css` and component-specific styles use Tailwind's utility classes.

## 📝 Available Scripts

| Command           | Description              |
| ----------------- | ------------------------ |
| `npm run dev`     | Start development server |
| `npm run build`   | Build for production     |
| `npm run preview` | Preview production build |
| `npm run lint`    | Run ESLint checks        |

## 🔗 Routing

The application uses React Router v7 with the following main routes:

- `/` - Home page
- `/restaurants` - Browse restaurants
- `/restaurants/:id` - Restaurant details and menu
- `/search` - Food search results
- `/favorites` - Saved favorites
- `/cart` - Shopping cart
- `/checkout` - Checkout process
- `/orders` - Order history
- `/order-tracking/:id` - Real-time order tracking
- `/profile` - User profile
- `/login` - User login
- `/signup` - User registration
- `*` - 404 Not Found

## 🤝 Contributing

1. Create a feature branch (`git checkout -b feature/amazing-feature`)
2. Commit your changes (`git commit -m 'Add amazing feature'`)
3. Push to the branch (`git push origin feature/amazing-feature`)
4. Open a Pull Request

## 📄 License

This project is part of the BiteRush food delivery platform.

## 💡 Notes

- Ensure the backend API is running and accessible for full functionality
- Configure API endpoints in your environment variables if needed
- The application follows modern React best practices with functional components and hooks
- ESLint rules help maintain code quality and consistency

---

For more information or support, please contact the development team.

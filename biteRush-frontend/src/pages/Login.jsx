import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function Login() {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (event) => {
    setForm({
      ...form,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    setLoading(true);

    setTimeout(() => {
      login({
        name: "Rahul Sharma",
        email: form.email,
      });

      navigate("/");
    }, 700);
  };

  return (
    <div className="flex min-h-[80vh] items-center justify-center bg-gray-50 px-4 py-10 dark:bg-gray-950">
      <div className="w-full max-w-md">
        {/* Logo */}
        <Link
          to="/"
          className="block text-center text-3xl font-bold text-red-500"
        >
          BiteRush
        </Link>

        <div className="mt-6 rounded-2xl border border-gray-200 bg-white p-6 shadow-sm sm:p-8 dark:border-gray-800 dark:bg-gray-900">
          <div className="text-center">
            <h1 className="text-2xl font-bold">Welcome back</h1>

            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              Login to continue ordering delicious food.
            </p>
          </div>

          <form onSubmit={handleSubmit} className="mt-8 space-y-5">
            {/* Email */}
            <div>
              <label htmlFor="email" className="mb-2 block text-sm font-medium">
                Email
              </label>

              <input
                id="email"
                name="email"
                type="email"
                required
                value={form.email}
                onChange={handleChange}
                placeholder="you@example.com"
                className="w-full rounded-lg border border-gray-300 bg-white px-4 py-3 text-sm outline-none transition focus:border-red-500 dark:border-gray-700 dark:bg-gray-950"
              />
            </div>

            {/* Password */}
            <div>
              <div className="mb-2 flex items-center justify-between">
                <label htmlFor="password" className="block text-sm font-medium">
                  Password
                </label>

                <button
                  type="button"
                  onClick={() => alert("Password reset coming soon")}
                  className="text-xs text-red-500"
                >
                  Forgot password?
                </button>
              </div>

              <input
                id="password"
                name="password"
                type="password"
                required
                value={form.password}
                onChange={handleChange}
                placeholder="Enter your password"
                className="w-full rounded-lg border border-gray-300 bg-white px-4 py-3 text-sm outline-none transition focus:border-red-500 dark:border-gray-700 dark:bg-gray-950"
              />
            </div>

            {/* Submit */}
            <button
              type="submit"
              disabled={loading}
              className="w-full rounded-lg bg-red-500 px-5 py-3 text-sm font-semibold text-white transition hover:bg-red-600 disabled:cursor-not-allowed disabled:opacity-60"
            >
              {loading ? "Logging in..." : "Login"}
            </button>
          </form>

          <div className="my-6 flex items-center gap-3">
            <div className="h-px flex-1 bg-gray-200 dark:bg-gray-800" />
            <span className="text-xs text-gray-400">OR</span>
            <div className="h-px flex-1 bg-gray-200 dark:bg-gray-800" />
          </div>

          <button
            type="button"
            onClick={() => alert("Google login coming soon")}
            className="w-full rounded-lg border border-gray-300 px-5 py-3 text-sm font-medium hover:border-gray-500 dark:border-gray-700"
          >
            Continue with Google
          </button>

          <p className="mt-6 text-center text-sm text-gray-500 dark:text-gray-400">
            Don't have an account?{" "}
            <Link
              to="/signup"
              className="font-medium text-red-500 hover:text-red-600"
            >
              Create account
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login;

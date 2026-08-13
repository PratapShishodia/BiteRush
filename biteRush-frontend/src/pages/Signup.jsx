import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function Signup() {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [form, setForm] = useState({
    name: "",
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
        name: form.name,
        email: form.email,
      });

      navigate("/");
    }, 700);
  };

  return (
    <div className="flex min-h-[80vh] items-center justify-center bg-gray-50 px-4 py-10 dark:bg-gray-950">
      <div className="w-full max-w-md">
        <Link
          to="/"
          className="block text-center text-3xl font-bold text-red-500"
        >
          BiteRush
        </Link>

        <div className="mt-6 rounded-2xl border border-gray-200 bg-white p-6 shadow-sm sm:p-8 dark:border-gray-800 dark:bg-gray-900">
          <div className="text-center">
            <h1 className="text-2xl font-bold">Create your account</h1>

            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              Join BiteRush and start ordering.
            </p>
          </div>

          <form onSubmit={handleSubmit} className="mt-8 space-y-5">
            {/* Name */}
            <div>
              <label htmlFor="name" className="mb-2 block text-sm font-medium">
                Full name
              </label>

              <input
                id="name"
                name="name"
                type="text"
                required
                value={form.name}
                onChange={handleChange}
                placeholder="Rahul Sharma"
                className="w-full rounded-lg border border-gray-300 bg-white px-4 py-3 text-sm outline-none focus:border-red-500 dark:border-gray-700 dark:bg-gray-950"
              />
            </div>

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
                className="w-full rounded-lg border border-gray-300 bg-white px-4 py-3 text-sm outline-none focus:border-red-500 dark:border-gray-700 dark:bg-gray-950"
              />
            </div>

            {/* Password */}
            <div>
              <label
                htmlFor="password"
                className="mb-2 block text-sm font-medium"
              >
                Password
              </label>

              <input
                id="password"
                name="password"
                type="password"
                required
                minLength={6}
                value={form.password}
                onChange={handleChange}
                placeholder="At least 6 characters"
                className="w-full rounded-lg border border-gray-300 bg-white px-4 py-3 text-sm outline-none focus:border-red-500 dark:border-gray-700 dark:bg-gray-950"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full rounded-lg bg-red-500 px-5 py-3 text-sm font-semibold text-white hover:bg-red-600 disabled:cursor-not-allowed disabled:opacity-60"
            >
              {loading ? "Creating account..." : "Create account"}
            </button>
          </form>

          <p className="mt-6 text-center text-sm text-gray-500 dark:text-gray-400">
            Already have an account?{" "}
            <Link
              to="/login"
              className="font-medium text-red-500 hover:text-red-600"
            >
              Login
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Signup;

import { useState } from "react";
import { useNavigate } from "react-router-dom";

const EditProfile = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    firstName: "Pratap",
    lastName: "Shishodia",
    email: "pratap@example.com",
    phone: "+91 98765 43210",
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Backend API will be connected later
    console.log("Updated profile:", form);

    navigate("/profile");
  };

  return (
    <div className="min-h-screen bg-gray-50 px-4 py-8 text-gray-900 dark:bg-gray-950 dark:text-white">
      <div className="mx-auto max-w-2xl">
        {/* Header */}
        <div className="mb-8">
          <button
            onClick={() => navigate("/profile")}
            className="mb-4 text-sm text-red-500 hover:text-red-600"
          >
            ← Back to Profile
          </button>

          <h1 className="text-3xl font-bold">Edit Profile</h1>

          <p className="mt-1 text-gray-500 dark:text-gray-400">
            Update your personal information
          </p>
        </div>

        {/* Form */}
        <form
          onSubmit={handleSubmit}
          className="rounded-2xl bg-white p-6 shadow-sm dark:bg-gray-900"
        >
          <div className="grid gap-5 sm:grid-cols-2">
            {/* First Name */}
            <div>
              <label className="mb-2 block text-sm font-medium">
                First Name
              </label>

              <input
                type="text"
                name="firstName"
                value={form.firstName}
                onChange={handleChange}
                className="w-full rounded-xl border border-gray-200 bg-gray-50 px-4 py-3 outline-none transition focus:border-red-500 dark:border-gray-700 dark:bg-gray-800"
              />
            </div>

            {/* Last Name */}
            <div>
              <label className="mb-2 block text-sm font-medium">
                Last Name
              </label>

              <input
                type="text"
                name="lastName"
                value={form.lastName}
                onChange={handleChange}
                className="w-full rounded-xl border border-gray-200 bg-gray-50 px-4 py-3 outline-none transition focus:border-red-500 dark:border-gray-700 dark:bg-gray-800"
              />
            </div>

            {/* Email */}
            <div className="sm:col-span-2">
              <label className="mb-2 block text-sm font-medium">Email</label>

              <input
                type="email"
                name="email"
                value={form.email}
                onChange={handleChange}
                className="w-full rounded-xl border border-gray-200 bg-gray-50 px-4 py-3 outline-none transition focus:border-red-500 dark:border-gray-700 dark:bg-gray-800"
              />
            </div>

            {/* Phone */}
            <div className="sm:col-span-2">
              <label className="mb-2 block text-sm font-medium">
                Phone Number
              </label>

              <input
                type="tel"
                name="phone"
                value={form.phone}
                onChange={handleChange}
                className="w-full rounded-xl border border-gray-200 bg-gray-50 px-4 py-3 outline-none transition focus:border-red-500 dark:border-gray-700 dark:bg-gray-800"
              />
            </div>
          </div>

          {/* Actions */}
          <div className="mt-8 flex flex-col-reverse gap-3 sm:flex-row sm:justify-end">
            <button
              type="button"
              onClick={() => navigate("/profile")}
              className="rounded-xl border border-gray-200 px-6 py-3 font-medium hover:bg-gray-50 dark:border-gray-700 dark:hover:bg-gray-800"
            >
              Cancel
            </button>

            <button
              type="submit"
              className="rounded-xl bg-red-500 px-6 py-3 font-medium text-white transition hover:bg-red-600"
            >
              Save Changes
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditProfile;

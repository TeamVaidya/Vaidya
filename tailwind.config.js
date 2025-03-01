/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors :{
        customBlue : '#F2F9FF',
        customTextBlue: '#109CF1',
      }
    },
  },
  plugins: [],
}
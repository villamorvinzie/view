import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Profile from "./pages/profile/Profile";
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import { Typography } from "@mui/material";
import { useCookies, CookiesProvider } from "react-cookie";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";

function App() {
  const [cookies] = useCookies(["user"]);
  return (
    <CookiesProvider>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        {cookies.access_token ? <Profile /> : <Login />}
      </LocalizationProvider>
    </CookiesProvider>
  );
}

export default App;

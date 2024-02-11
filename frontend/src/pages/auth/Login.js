import { Box, Button, Stack, TextField } from "@mui/material";
import Typography from "@mui/material/Typography";
import { useState } from "react";
import axios from "axios";
import { LoadingButton } from "@mui/lab";
import { useCookies } from "react-cookie"

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [cookies, setCookies] = useCookies(["access_token"]);

  const handleOnLogin = () => {
    setIsLoading(true);
    axios
      .post("http://localhost:8080/view/api/v1/auth/login", {
        username: username,
        password: password,
      })
      .then((res) => {
        setCookies("access_token", res.data.token);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setIsLoading(false);
      });
  };
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
      }}
    >
      <Stack
        sx={{
          p: 2,
          width: "50%",
        }}
        direction={"column"}
        spacing={2}
      >
        <Stack direction={"column"}>
          <Typography variant="h5">Sign In</Typography>
          <Typography variant="subtitle1">
            What's happening to you now?
          </Typography>
        </Stack>
        <TextField
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          label="Username"
          variant="outlined"
        />
        <TextField
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          label="Password"
          variant="outlined"
        />
        <LoadingButton
          onClick={handleOnLogin}
          loading={isLoading}
          variant="contained"
        >
          Sign In
        </LoadingButton>
      </Stack>
    </Box>
  );
};

export default Login;

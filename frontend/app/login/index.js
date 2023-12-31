import axios from "axios";
import * as Constants from "expo-constants";
import { useEffect, useState } from "react";
import { StyleSheet, View } from "react-native";
import {
  Button,
  Caption,
  Headline,
  Snackbar,
  TextInput,
  useTheme,
} from "react-native-paper";
import { router, Stack } from "expo-router";
import { get, save } from "../../util/secure_store";

export default function App() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [hasAuthError, setHasAuthError] = useState(false);
  const [errMsg, setErrMsg] = useState("");
  const [errVisible, setErrVisible] = useState(false);

  useEffect(() => {
    let token;
    const validateToken = async () => {
      token = await get("jwt");
    };
    validateToken();
    if (token != null) {
      router.replace("/calendar");
    }
  }, []);

  const theme = useTheme();

  const styles = StyleSheet.create({
    container: {
      height: "100%",
      padding: 20,
      justifyContent: "center",
    },
    title: {
      fontFamily: "Montserrat-Bold",
    },
    caption: {
      fontFamily: "Montserrat-Regular",
    },
    textInput: {
      marginVertical: 10,
      fontFamily: "Montserrat-Regular",
    },
    btn: {
      fontFamily: "Montserrat-Bold",
    },
    snackbar: {
      backgroundColor: theme.colors.error,
    },
  });

  const handleOnSignIn = () => {
    const reqBody = {
      username: username,
      password: password,
    };

    const host = Constants.default.expoConfig.hostUri.split(":")[0];

    setIsLoading(true);

    axios
      .post(`http://${host}:8080/view/api/v1/auth/login`, reqBody)
      .then((res) => {
        save("jwt", res.data.token);
        router.push("/calendar");
      })
      .catch((err) => {
        setHasAuthError(true);
        setErrVisible(true);
        setErrMsg("Please check your username and/or password.");
      })
      .finally(() => setIsLoading(false));
  };

  const handleOnDismissErr = () => {
    setErrVisible(false);
  };

  return (
    <>
      <Stack.Screen
        options={{
          headerShown: false,
        }}
      />
      <View style={styles.container}>
        <Headline style={styles.title}>Sign In</Headline>
        <Caption style={styles.caption}>
          Stay updated on what's gonna happen today.
        </Caption>
        <TextInput
          label="username"
          style={styles.textInput}
          value={username}
          onChangeText={setUsername}
          error={hasAuthError}
        />
        <TextInput
          label="password"
          style={styles.textInput}
          value={password}
          onChangeText={setPassword}
          secureTextEntry={true}
          error={hasAuthError}
        />
        <Button
          loading={isLoading}
          style={styles.btn}
          mode="contained"
          onPress={handleOnSignIn}
        >
          Sign in
        </Button>
      </View>
      <Snackbar
        visible={errVisible}
        onDismiss={handleOnDismissErr}
        style={styles.snackbar}
      >
        {errMsg}
      </Snackbar>
    </>
  );
}

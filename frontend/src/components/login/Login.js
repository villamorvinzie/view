import { StatusBar } from "expo-status-bar";
import { StyleSheet, View } from "react-native";
import {
  TextInput,
  Text,
  Headline,
  Caption,
  Button,
  ActivityIndicator,
} from "react-native-paper";
import axios from "axios";
import { useEffect, useState } from "react";
import * as Constants from "expo-constants";

export default function App() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleOnSignIn = () => {
    const reqBody = {
      username: username,
      password: password,
    };

    const host = Constants.default.expoConfig.hostUri.split(":")[0];

    setIsLoading(true);

    axios
      .post(`http://${host}:8080/view/api/v1/auth/login`, reqBody)
      .then((res) => console.log(res.data.token))
      .finally(() => setIsLoading(false));
  };

  return (
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
      />
      <TextInput
        label="password"
        style={styles.textInput}
        value={password}
        onChangeText={setPassword}
        secureTextEntry={true}
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
  );
}

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
});

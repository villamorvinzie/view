import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
import Login from "./login/index";
import {
  Provider as PaperProvider,
  configureFonts,
  DefaultTheme,
} from "react-native-paper";
import { useFonts } from "expo-font";
import { useCallback } from "react";
import * as SplashScreen from "expo-splash-screen";

const fontProp = {
  regular: {
    fontFamily: "Montserrat-Regular",
    fontWeight: "normal",
  },
  medium: {
    fontFamily: "Montserrat-Medium",
    fontWeight: "normal",
  },
  light: {
    fontFamily: "Montserrat-Light",
    fontWeight: "normal",
  },
  thin: {
    fontFamily: "Montserrat-Thin",
    fontWeight: "normal",
  },
};

const fontConfig = {
  android: {
    ...fontProp,
  },
  default: {
    ...fontProp,
  },
  ios: {
    ...fontProp,
  },
  web: {
    ...fontProp,
  },
};

const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: "#114E55",
    accent: "#102937",
  },
  fonts: configureFonts(fontConfig),
};

export default function App() {
  const customFonts = {
    "Montserrat-Regular": require("./../assets/fonts/Montserrat-Regular.ttf"),
    "Montserrat-Medium": require("./../assets/fonts/Montserrat-Medium.ttf"),
    "Montserrat-Light": require("./../assets/fonts/Montserrat-Light.ttf"),
    "Montserrat-Thin": require("./../assets/fonts/Montserrat-Thin.ttf"),
    "Montserrat-Bold": require("./../assets/fonts/Montserrat-Bold.ttf"),
  };

  const [isFontLoaded] = useFonts(customFonts);

  useCallback(async () => {
    if (isFontLoaded) {
      await SplashScreen.hideAsync();
    }
  }, [isFontLoaded]);

  if (!isFontLoaded) {
    return null;
  }

  return (
    <PaperProvider theme={theme}>
      <Login />
    </PaperProvider>
  );
}

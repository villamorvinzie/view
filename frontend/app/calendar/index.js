import React, { useEffect, useState } from "react";
import { Calendar, LocaleConfig } from "react-native-calendars";
import { Stack } from "expo-router";
import * as SecureStore from "expo-secure-store";
import { Text } from "react-native-paper";
import { get } from "../../util/secure_store.js";
import { View } from "react-native";
import axios from "axios";

const App = () => {
  const [selected, setSelected] = useState("");

  return (
    <>
      <Stack.Screen options={{ headerShown: false }} />
      <View style={{ marginTop: 50 }}>
        <Calendar current={"2023-12-31"} />
      </View>
    </>
  );
};

export default App;

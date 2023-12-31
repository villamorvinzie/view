import * as SecureStore from "expo-secure-store";

export const save = async (key, value) =>
  await SecureStore.setItemAsync(key, value);

export const get = async (key) => await SecureStore.getItemAsync(key);

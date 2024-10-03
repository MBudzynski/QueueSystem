import { configureStore } from "@reduxjs/toolkit";
import locationReducer from "./config/LocationUUID"
import queueConfigurationPlaceholderReducer from "./config/QueueConfigurationPlaceholder";

export const store = configureStore({
  reducer: {
    location:locationReducer,
    queueConfigurationPlaceholder:queueConfigurationPlaceholderReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

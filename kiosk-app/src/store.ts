import { configureStore } from "@reduxjs/toolkit";
import facilityReducer from "./config/FacilityUUID"
import queueConfigurationPlaceholderReducer from "./config/QueueConfigurationPlaceholder";

export const store = configureStore({
  reducer: {
    facility:facilityReducer,
    queueConfigurationPlaceholder:queueConfigurationPlaceholderReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

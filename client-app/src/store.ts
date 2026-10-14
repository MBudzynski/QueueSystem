import { configureStore } from "@reduxjs/toolkit";
import userDataReducer from "./config/UserDataPlaceholder"

export const store = configureStore({
    reducer: {
        user:userDataReducer,
    },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
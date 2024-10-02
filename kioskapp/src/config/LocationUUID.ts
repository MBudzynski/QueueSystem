import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "../store";

export interface State {
    locationUUID: string;
}

const initialState: State = {
    locationUUID: '',
};

export const locationUUID = createSlice({
    name: "locationUUID",
    initialState,
    reducers: {
        set: (state, action: PayloadAction<string>) => {
            // state.value += action.payload;
            state.locationUUID = action.payload;
        },
    },
});
export const { set } = locationUUID.actions;
export const getLocationUUID = (state: RootState) => state.location.locationUUID
export default locationUUID.reducer;
import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "../store";

export interface State {
    facilityUUID: string;
}

const initialState: State = {
    facilityUUID: '',
};

export const facilityUUID = createSlice({
    name: "facilityUUID",
    initialState,
    reducers: {
        set: (state, action: PayloadAction<string>) => {
            state.facilityUUID = action.payload;
        },
    },
});
export const { set } = facilityUUID.actions;
export const getFacilityUUID = (state: RootState) => state.facility.facilityUUID
export default facilityUUID.reducer;
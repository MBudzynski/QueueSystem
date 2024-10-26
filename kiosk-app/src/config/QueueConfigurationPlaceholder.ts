import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "../store";
import {KioskConfiguration} from "../dto/KioskConfiguration";

export interface State {
    kioskConfiguration: KioskConfiguration;
}

const initialState: State = {
    kioskConfiguration: {
        backgroundColor: "",
        city: "",
        houseNumber: "",
        institutionLogoFile: "",
        institutionName: "",
        kioskButtons: [],
        postCode: "",
        postOffice: "",
        street: ""
    },
};

export const queueConfigurationPlaceholder= createSlice({
    name: "queueConfigurationPlaceholder",
    initialState,
    reducers: {
        setKioskConfiguration: (state, action: PayloadAction<KioskConfiguration>) => {
            state.kioskConfiguration = action.payload;
        },
    },
});
export const { setKioskConfiguration } = queueConfigurationPlaceholder.actions;
export const getKioskConfiguration = (state: RootState) => state.queueConfigurationPlaceholder.kioskConfiguration;
export default queueConfigurationPlaceholder.reducer;
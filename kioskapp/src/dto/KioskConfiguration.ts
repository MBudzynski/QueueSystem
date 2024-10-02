
export type KioskConfiguration = {
    institutionName: string;
    city: string;
    street: string;
    houseNumber: string;
    postCode: string;
    postOffice: string;
    institutionLogoFile: string;
    backgroundColor: string;
    kioskButtons: KioskButton[];
}

export type KioskButton = {
    kioskButtonId: number;
    kioskSubButtonId: number;
    buttonText: string;
    queueConfigurationUUID: string;
}
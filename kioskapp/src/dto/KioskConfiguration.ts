
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

interface KioskButton {
    kioskButtonId: number;
    kioskSubButtonId: number;
    buttonText: string;
    queueConfigurationUUID: string;
}
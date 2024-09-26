import {queueSystemCoreClient} from "../config/QueueSystemCoreClient";
import {KioskConfiguration} from "../dto/KioskConfiguration";

export const fetchKioskConfiguration = async (localizationUUID: string, kioskUUID: string) => {

    const findConfigurationRequest = {
        localizationUUID: localizationUUID ,
        kioskUUID: kioskUUID
    }

    try {
        const response = await queueSystemCoreClient.post<KioskConfiguration>("/kiosk/find-configuration", findConfigurationRequest);
        return response.data;
    } catch {
        return {} as KioskConfiguration;
    }
};
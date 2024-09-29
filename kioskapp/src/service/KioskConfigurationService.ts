import {queueSystemCoreClient} from "../config/QueueSystemCoreClient";
import {KioskConfiguration} from "../dto/KioskConfiguration";

export const fetchKioskConfiguration = (localizationUUID: string, kioskUUID: string) => {

    const findConfigurationRequest = {
        localizationUUID: localizationUUID ,
        kioskUUID: kioskUUID
    }

    return queueSystemCoreClient.post<KioskConfiguration>("/kiosk/find-configuration", findConfigurationRequest);
};
import {queueSystemCoreClient} from "../config/QueueSystemCoreClient";
import {KioskConfiguration} from "../dto/KioskConfiguration";
import {QueueNumber} from "../dto/QueueNumber";

export const fetchKioskConfiguration = (localizationUUID: string, kioskUUID: string) => {

    const findConfigurationRequest = {
        localizationUUID: localizationUUID,
        kioskUUID: kioskUUID
    }

    return queueSystemCoreClient.post<KioskConfiguration>("/kiosk/find-configuration", findConfigurationRequest);
};

export const queuePetitioner = (localizationUUID: string, queueConfigurationUUID: string) => {

    const queuePetitionerRequest = {
        localizationUUID: localizationUUID,
        queueConfigurationUUID: queueConfigurationUUID
    }
    return queueSystemCoreClient.post<QueueNumber>("/queue/add", queuePetitionerRequest);
};
import {queueSystemCoreClient} from "../config/QueueSystemCoreClient";
import {KioskConfiguration} from "../dto/KioskConfiguration";
import {QueueNumber} from "../dto/QueueNumber";

export const fetchKioskConfiguration = (facilityUUID: string, kioskUUID: string) => {

    const findConfigurationRequest = {
        facilityUUID: facilityUUID,
        kioskUUID: kioskUUID
    }

    return queueSystemCoreClient.post<KioskConfiguration>("/kiosk/find-configuration", findConfigurationRequest);
};

export const queuePetitioner = (facilityUUID: string, queueConfigurationUUID: string) => {

    const queuePetitionerRequest = {
        facilityUUID: facilityUUID,
        queueConfigurationUUID: queueConfigurationUUID
    }
    return queueSystemCoreClient.post<QueueNumber>("/queue/add", queuePetitionerRequest);
};
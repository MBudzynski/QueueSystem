import {queueSystemFacilityClient} from "../config/QueueSystemFacilityClient"
import {ObservedUserQueueResponse} from "../dto/ObservedUserQueueResponse";

export const fetchUserObservedQueues = (userUUID: string) => {
    return queueSystemFacilityClient.get<ObservedUserQueueResponse>('/observed-queue', {params: {userUUID: userUUID}});
}

export const saveUserObservedQueues = (userUUID: string, queueConfigurationUUIDs:string[]) => {

    const SaveObservedQueueRequest = {
        userUUID: userUUID,
        queueConfigurationUUIDs: queueConfigurationUUIDs
    }

    return queueSystemFacilityClient.post('/observed-queue', SaveObservedQueueRequest);
}
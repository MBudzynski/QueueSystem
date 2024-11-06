import {queueSystemFacilityClient} from "../config/QueueSystemFacilityClient"
import {QueueNumbersResponse} from "../dto/QueueNumbersResponse";

export const getAllNumbersInQueue = (userUUID: string) => {
    return queueSystemFacilityClient.get<QueueNumbersResponse>('/queue/show', {params: {userUUID: userUUID}});
}
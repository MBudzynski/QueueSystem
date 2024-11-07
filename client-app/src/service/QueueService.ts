import {queueSystemFacilityClient} from "../config/QueueSystemFacilityClient"
import {QueueNumbersResponse} from "../dto/QueueNumbersResponse";
import {QueueNumberResponse} from "../dto/QueueNumberResponse";

export const getAllNumbersInQueue = (userUUID: string) => {
    return queueSystemFacilityClient.get<QueueNumbersResponse>('/queue/show', {params: {userUUID: userUUID}});
}

export const getNextNumber = (userUUID: string) => {

    const nextNumberRequest = {
        userUUID: userUUID
    }

    return queueSystemFacilityClient.post<QueueNumberResponse>('/queue/bring', nextNumberRequest);
}

export const postponeQueueNumber = (queueUUID: string) => {

    const postponeNumberrequest = {
        queueUUID: queueUUID
    }

    return queueSystemFacilityClient.patch('/queue/delay', postponeNumberrequest);
}

export const endQueueNumber = (queueUUID: string) => {
    return queueSystemFacilityClient.delete('/queue',  {params: {queueUUID: queueUUID}});
}
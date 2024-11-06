export type QueueNumbersResponse = {
    queueNumbers: QueueNumberDto[];
}

export type QueueNumberDto = {
    queueUUID: string;
    fullNumber: string;
    creationDate: string;
    creationTime: string;
}
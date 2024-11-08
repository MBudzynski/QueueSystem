export type QueueConfigurationDto = {
    queueConfigurationUUID: string;
    configurationDescription: string;
    numberRange: string;
    sign: string;
}

export type QueueConfigurationWithSelection = QueueConfigurationDto & {
    selected: boolean;
};
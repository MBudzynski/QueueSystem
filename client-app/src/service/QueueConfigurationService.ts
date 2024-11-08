import {queueSystemFacilityClient} from "../config/QueueSystemFacilityClient"
import {QueueConfigurationResponse} from "../dto/QueueConfigurationResponse";

export const fetchAllQueueConfigurations = () => {
    return queueSystemFacilityClient.get<QueueConfigurationResponse>('/queue-configuration/all');
}
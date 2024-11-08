import React, {useEffect, useState} from 'react';
import './css/QueueConfigurationPage.css';
import {fetchAllQueueConfigurations} from '../service/QueueConfigurationService';
import {QueueConfigurationWithSelection} from '../dto/QueueConfigurationDto';
import {fetchUserObservedQueues, saveUserObservedQueues} from '../service/ObservedQueueService'
import {useNavigate} from "react-router-dom";

export const QueueConfigurationPage = () => {

    const [queueConfigurations, setQueueConfigurations] = useState<QueueConfigurationWithSelection[]>([]);
    const userUUID = "bf462b96-43d7-4fe9-936c-c25c9e0954b0";
    const navigate = useNavigate();

    useEffect(() => {
        fetchQueueConfigurations();
    }, []);


    const fetchQueueConfigurations = async () => {
        const queueConfiguration = await fetchAllQueueConfigurations();
        const observed = await fetchUserObservedQueues(userUUID);
        setQueueConfigurations(queueConfiguration.data.configuredQueues.map(queueConfiguration => ({...queueConfiguration,
            selected: observed.data.observedQueues.includes(queueConfiguration.queueConfigurationUUID)})));
    };


    const handleCheckboxChange = (uuid: string) => {
        setQueueConfigurations(prevObjects =>
            prevObjects.map(obj =>
                obj.queueConfigurationUUID === uuid ? { ...obj, selected: !obj.selected } : obj
            )
        );
    };


    const handleSave = () => {
        const selectedQueueConfigurationUUIDs = queueConfigurations
            .filter(configuration => configuration.selected)
            .map(configuration => configuration.queueConfigurationUUID);

        saveUserObservedQueues(userUUID, selectedQueueConfigurationUUIDs);
        navigate('/queueMainPage');
    };


    const handleBack = () => {
        navigate('/queueMainPage');
    };

    return (
        <div className="queue-configuration">
            <div className="container">
                <h2>Queue configuration:</h2>
                <ul>
                    {queueConfigurations.map(configuration => (
                        <li key={configuration.queueConfigurationUUID}>
                            <label>
                                <input
                                    type="checkbox"
                                    checked={configuration.selected}
                                    onChange={() => handleCheckboxChange(configuration.queueConfigurationUUID)}
                                />
                                {configuration.configurationDescription}
                            </label>
                        </li>
                    ))}
                </ul>
                <div className="button-container">
                    <button onClick={handleSave}>Save</button>
                    <button onClick={handleBack}>Back</button>
                </div>
            </div>
        </div>
    );
}

export default QueueConfigurationPage;
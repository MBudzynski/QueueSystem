import React, {useEffect, useState} from 'react';
import './css/QueueConfigurationPage.css';
import {fetchAllQueueConfigurations} from '../service/QueueConfigurationService';
import {QueueConfigurationWithSelection} from '../dto/QueueConfigurationDto';
import {fetchUserObservedQueues, saveUserObservedQueues} from '../service/ObservedQueueService'
import {useNavigate} from "react-router-dom";
import {saveAtLocalStorage, getFromLocalStorage} from '../service/LocalStorageService'

export const QueueConfigurationPage = () => {

    const [queueConfigurations, setQueueConfigurations] = useState<QueueConfigurationWithSelection[]>([]);
    const [displayedText, setDisplayedText] = useState<string>("");
    const [readPrefix, setReadPrefix] = useState<string>("");
    const [readSuffix, setReadSuffix] = useState<string>("");
    const userUUID = "bf462b96-43d7-4fe9-936c-c25c9e0954b0";
    const navigate = useNavigate();

    useEffect(() => {
        fetchQueueConfigurations();
        const text = getFromLocalStorage("displayed-text");
        if (text) {
            setDisplayedText(text);
        }
        const prefix = getFromLocalStorage("read-prefix");
        if (prefix) {
            setReadPrefix(prefix);
        }
        const suffix = getFromLocalStorage("read-suffix");
        if (suffix) {
            setReadSuffix(suffix);
        }

    }, []);

    const fetchQueueConfigurations = async () => {
        const queueConfiguration = await fetchAllQueueConfigurations();
        const observed = await fetchUserObservedQueues(userUUID);
        setQueueConfigurations(queueConfiguration.data.configuredQueues.map(queueConfiguration => ({
            ...queueConfiguration,
            selected: observed.data.observedQueues.includes(queueConfiguration.queueConfigurationUUID)
        })));
    };

    const handleCheckboxChange = (uuid: string) => {
        setQueueConfigurations(prevObjects =>
            prevObjects.map(obj =>
                obj.queueConfigurationUUID === uuid ? {...obj, selected: !obj.selected} : obj
            )
        );
    };

    const handleSave = () => {
        const selectedQueueConfigurationUUIDs = queueConfigurations
            .filter(configuration => configuration.selected)
            .map(configuration => configuration.queueConfigurationUUID);

        saveAtLocalStorage("displayed-text", displayedText);
        saveAtLocalStorage("read-prefix", readPrefix);
        saveAtLocalStorage("read-suffix", readSuffix);

        saveUserObservedQueues(userUUID, selectedQueueConfigurationUUIDs);
        navigate('/queueMainPage');
    };

    const handleBack = () => {
        navigate('/queueMainPage');
    };

    const handleDisplayedText = (e: React.ChangeEvent<HTMLInputElement>) => {
        setDisplayedText(e.target.value);
    }

    const handleReadPrefix = (e: React.ChangeEvent<HTMLInputElement>) => {
        setReadPrefix(e.target.value);
    }

    const handleReadSuffix = (e: React.ChangeEvent<HTMLInputElement>) => {
        setReadSuffix(e.target.value);
    }

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

                <h2>Workstation configuration:</h2>
                <label className="workstation" htmlFor="displayedText">Displayed text:</label>
                <input
                    className="input"
                    id="displayedText"
                    type="text"
                    value={displayedText}
                    onChange={handleDisplayedText}
                />

                <label className="workstation" htmlFor="readPrefix">Read prefix:</label>
                <input
                    className="input"
                    id="readPrefix"
                    type="text"
                    value={readPrefix}
                    onChange={handleReadPrefix}
                />

                <label className="workstation" htmlFor="readSuffix">Read suffix:</label>
                <input
                    className="input"
                    id="readSuffix"
                    type="text"
                    value={readSuffix}
                    onChange={handleReadSuffix}
                />
                <div className="button-container">
                    <button onClick={handleSave}>Save</button>
                    <button onClick={handleBack}>Back</button>
                </div>
            </div>
        </div>
    );
}

export default QueueConfigurationPage;
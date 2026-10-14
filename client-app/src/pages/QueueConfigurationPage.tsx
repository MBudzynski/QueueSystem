import React, {useEffect, useState} from 'react';
import './css/QueueConfigurationPage.css';
import {fetchAllQueueConfigurations} from '../service/QueueConfigurationService';
import {QueueConfigurationWithSelection} from '../dto/QueueConfigurationDto';
import {fetchUserObservedQueues, saveUserObservedQueues} from '../service/ObservedQueueService'
import {useNavigate} from "react-router-dom";
import {getUserData, setUserData} from "../config/UserDataPlaceholder";
import {useSelector, useDispatch} from "react-redux";
import {updateUserConfiguration} from '../service/UserService'

export const QueueConfigurationPage = () => {

    const [queueConfigurations, setQueueConfigurations] = useState<QueueConfigurationWithSelection[]>([]);
    const [displayedText, setDisplayedText] = useState<string>("");
    const [readPrefix, setReadPrefix] = useState<string>("");
    const [readSuffix, setReadSuffix] = useState<string>("");
    const navigate = useNavigate();
    const userData = useSelector(getUserData);
    const dispatch = useDispatch();

    useEffect(() => {
        fetchQueueConfigurations();
        setDisplayedText(userData.displayServiceDeskName);
        setReadPrefix(userData.pronouncedNumberPrefix);
        setReadSuffix(userData.pronouncedServiceDeskName);

    }, []);

    const fetchQueueConfigurations = async () => {
        const queueConfiguration = await fetchAllQueueConfigurations();
        const observed = await fetchUserObservedQueues(userData.userUUID);
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

    const handleSave = async () => {
        const selectedQueueConfigurationUUIDs = queueConfigurations
            .filter(configuration => configuration.selected)
            .map(configuration => configuration.queueConfigurationUUID);

        const response= await updateUserConfiguration(userData.userUUID, displayedText, readPrefix, readSuffix);
        dispatch(setUserData(response.data));
        saveUserObservedQueues(userData.userUUID, selectedQueueConfigurationUUIDs);
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
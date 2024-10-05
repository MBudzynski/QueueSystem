import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from 'react-router-dom';
import {KioskButton, KioskConfiguration} from "../dto/KioskConfiguration";
import { useSelector } from "react-redux";
import './css/EkioskMainPage.css';
import {getLocationUUID} from "../config/LocationUUID";
import {queuePetitioner} from '../service/KioskConfigurationService';
import {getKioskConfiguration} from "../config/QueueConfigurationPlaceholder";

export const EkioskMainPage = () => {

    const [image, setImage] = useState<string>('');
    const navigate = useNavigate();
    const [leftButtons, setLeftButtons] = useState<KioskButton[]>([]);
    const [rightButtons, setRightButtons] = useState<KioskButton[]>([]);
    let kioskConfiguration = useSelector(getKioskConfiguration);
    const locationUUID = useSelector(getLocationUUID);

    useEffect(() => {
        const left: KioskButton[]  = [];
        const right: KioskButton[] = [];

        if (kioskConfiguration && kioskConfiguration.institutionLogoFile) {
            setImage(kioskConfiguration.institutionLogoFile);
        }

        kioskConfiguration.kioskButtons.forEach((button, index) => {
            if (index % 2 === 0) {
                left.push(button);
            } else {
                right.push(button);
            }
        });

        setLeftButtons(left);
        setRightButtons(right);

    }, [kioskConfiguration]);

    const handleButtonClick = async (locationUUID: string, queueConfigurationUUID: string) => {
        let response = await queuePetitioner(locationUUID, queueConfigurationUUID);
        let queueNumber = response.data;

        navigate('/informationPage', { state: {queueNumber}});
    }

    document.body.style.display = '';
    document.body.style.marginTop = '';

    return(
        <div className="atm-window">
            <div className="side-buttons left">
                {leftButtons.map((kioskButton, index) => (
                    <button className="side-button" onClick={() => handleButtonClick(locationUUID, kioskButton.queueConfigurationUUID)}>{kioskButton.buttonText}</button>
                ))}
            </div>
            <div className="screen">
                <h1>Welcome to the ATM</h1>
                <img src={`data:image/jpg;base64,${image}`} alt="Company Logo" width="220" height="220" className="logo"/>
                <p>Please select an option:</p>
            </div>
            <div className="side-buttons right">
                {rightButtons.map((kioskButton, index) => (
                    <button className="side-button" onClick={() => handleButtonClick(locationUUID, kioskButton.queueConfigurationUUID)}>{kioskButton.buttonText}</button>
                ))}
            </div>
        </div>
    );
}

export default EkioskMainPage;
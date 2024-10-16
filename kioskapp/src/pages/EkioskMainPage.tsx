import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {KioskButton} from "../dto/KioskConfiguration";
import {useSelector} from "react-redux";
import './css/EkioskMainPage.css';
import {getFacilityUUID} from "../config/FacilityUUID";
import {queuePetitioner} from '../service/KioskConfigurationService';
import {getKioskConfiguration} from "../config/QueueConfigurationPlaceholder";

export const EkioskMainPage = () => {

    const [image, setImage] = useState<string>('');
    const [institutionName, setInstitutionName] = useState<string>('');
    const [institutionAddress, setInstitutionAddress] = useState<string>('');
    const navigate = useNavigate();
    const [leftButtons, setLeftButtons] = useState<KioskButton[]>([]);
    const [rightButtons, setRightButtons] = useState<KioskButton[]>([]);
    let kioskConfiguration = useSelector(getKioskConfiguration);
    const facilityUUID = useSelector(getFacilityUUID);

    useEffect(() => {
        const left: KioskButton[] = [];
        const right: KioskButton[] = [];

        if (kioskConfiguration && kioskConfiguration.institutionLogoFile) {
            setImage(kioskConfiguration.institutionLogoFile);
            setInstitutionName(kioskConfiguration.institutionName)
            setInstitutionAddress(`ul. ${kioskConfiguration.street} ${kioskConfiguration.houseNumber} ${kioskConfiguration.city}, ${kioskConfiguration.postCode} ${kioskConfiguration.postOffice}`);
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

    const handleButtonClick = async (facilityUUID: string, queueConfigurationUUID: string) => {
        let response = await queuePetitioner(facilityUUID, queueConfigurationUUID);
        let queueNumber = response.data;

        navigate('/informationPage', {state: {queueNumber}});
    }

    document.body.style.display = '';
    document.body.style.marginTop = '';

    return (
        <div>
            <div className="greeting-section">
                <h1 >Welcome</h1>
                <h2 >Please select an option:</h2>
            </div>
            <div className="atm-window">
                <div className="side-buttons left">
                    {leftButtons.map((kioskButton, index) => (
                        <button className="side-button"
                                onClick={() => handleButtonClick(facilityUUID, kioskButton.queueConfigurationUUID)}>{kioskButton.buttonText}</button>
                    ))}
                </div>
                <div className="screen">
                    <h1>{institutionName}</h1>
                    <img src={`data:image/jpg;base64,${image}`} alt="Company Logo" width="220" height="220"
                         className="logo"/>
                    <p>{institutionAddress}</p>
                </div>
                <div className="side-buttons right">
                    {rightButtons.map((kioskButton, index) => (
                        <button className="side-button"
                                onClick={() => handleButtonClick(facilityUUID, kioskButton.queueConfigurationUUID)}>{kioskButton.buttonText}</button>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default EkioskMainPage;
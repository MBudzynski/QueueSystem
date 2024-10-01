import React, {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import {KioskConfiguration} from "../dto/KioskConfiguration";
import './css/EkioskMainPage.css';

export const EkioskMainPage = () => {

    const [image, setImage] = useState<string>('');
    const location = useLocation();
    let kioskConfiguration = location.state.kioskConfiguration as KioskConfiguration;

    useEffect(() => {
        console.log(kioskConfiguration)
        if (kioskConfiguration && kioskConfiguration.institutionLogoFile) {
            setImage(kioskConfiguration.institutionLogoFile);
        }
    }, [kioskConfiguration]);

    document.body.style.display = '';
    document.body.style.marginTop = '';

    return(
        <div className="atm-window">
            <div className="side-buttons left">
                <button className="side-button">1asd as dasd dasd as  as</button>
                <button className="side-button">2 asd asd asd as asd asd </button>
                <button className="side-button">3 asd as dsa as dasd as da </button>
                <button className="side-button">4asd asd asd asd asd asd asd as d</button>
            </div>
            <div className="screen">
                <h1>Welcome to the ATM</h1>
                <img src={`data:image/jpg;base64,${image}`} alt="Company Logo" width="220" height="220" className="logo"/>
                <p>Please select an option:</p>
            </div>
            <div className="side-buttons right">
                <button className="side-button">Aasdasdasdasdasd</button>
                <button className="side-button">Basdasda sad asd asd asd  asd </button>
                <button className="side-button">C sad asd asd asd as dasd as d</button>
                <button className="side-button">D asdasd sad asd  a asd as</button>
            </div>
        </div>
    );
}
export default EkioskMainPage;
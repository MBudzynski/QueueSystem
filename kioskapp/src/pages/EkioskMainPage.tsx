import React, {useState} from 'react';
import './css/EkioskMainPage.css';
import { useLocation } from 'react-router-dom';
import {KioskConfiguration} from "../dto/KioskConfiguration";

export const EkioskMainPage = () => {

    const location = useLocation();
    const kioskConfiguration = location.state as { configuration: KioskConfiguration};

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
                <img src={'logo'} alt="Company Logo" className="logo"/>
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
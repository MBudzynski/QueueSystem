import React, {useState} from 'react';
import './css/ConfigurationPage.css';
import {fetchKioskConfiguration} from '../service/KioskConfigurationService'
import {KioskConfiguration} from "../dto/KioskConfiguration";

export const ConfigurationPage = () => {
    const [locationUUID, setLocationUUID] = useState<string>('');
    const [ekioskConfigUUID, setEkioskConfigUUID] = useState<string>('');
    const [error, setError] = useState<string | null>(null);

    const handleLocationUUIDChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLocationUUID(e.target.value)
    }

    const handleEkioskConfigUUID = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEkioskConfigUUID(e.target.value)
    }

    const handleFetchConfiguration = async () => {
        setError(null);
        let configuration = null;

        try {
            configuration = await fetchKioskConfiguration(locationUUID, ekioskConfigUUID);
        } catch (e) {
            setError("Fetch configuration error");
            return;
        }

        console.log('Pobrano konfigurację: ', configuration)
    }

    return (
        <div>
            {error ? (
                <div className="error-box">
                    <p className="error-tex">{error}</p>
                </div>
            ) : null}
            <div className="form-container">
                <div className="form-field">
                    <label className="label" htmlFor="locationUUID">Location UUID:</label>
                    <input
                        className="input"
                        id="locationUUID"
                        type="text"
                        value={locationUUID}
                        onChange={handleLocationUUIDChange}
                    />
                </div>
                <div className="form-field">
                    <label className="label" htmlFor="ekioskConfigUUID">Ekiosk config UUID:</label>
                    <input
                        className="input"
                        id="ekioskConfigUUID"
                        type="text"
                        value={ekioskConfigUUID}
                        onChange={handleEkioskConfigUUID}
                    />
                </div>
                <button className="button" onClick={handleFetchConfiguration}>Download configuration</button>
            </div>
        </div>
    );
};

export default ConfigurationPage;

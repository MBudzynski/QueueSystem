import React, {useState} from 'react';
import './css/ConfigurationPage.css'

export const ConfigurationPage = () => {
    const [locationUUID, setLocationUUID] = useState<string>('');
    const [ekioskConfigUUID, setEkioskConfigUUID] = useState<string>('');

    const handleLocationUUIDChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLocationUUID(e.target.value)
    }

    const handleEkioskConfigUUID = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEkioskConfigUUID(e.target.value)
    }

    const handleGetConfiguration = () => {
        const config = {
            locationUUID,
            ekioskConfigUUID
        };
        console.log('Pobrano konfigurację: ', config)
    }

    return (
        <div className="form-container">
            <div  className="form-field">
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
            <button className="button" onClick={handleGetConfiguration}>Download configuration</button>
        </div>
    );
};

export default ConfigurationPage;

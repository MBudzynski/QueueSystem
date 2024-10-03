import {useEffect, useState} from "react";
import './css/InformationPage.css';
import {useLocation, useNavigate} from 'react-router-dom';
import {QueueNumber} from "../dto/QueueNumber";

export const InformationPage = () => {
    const [number, setNumber] = useState<string>('');
    const location = useLocation();
    const navigate = useNavigate();
    let queueNumber = location.state as QueueNumber;


    useEffect(() => {
        if(queueNumber && queueNumber.queueNumber ){
            setNumber(queueNumber.queueNumber);
        }

        const timer = setTimeout(() => {
            navigate('/mainEkioskPage');
        }, 15000);

        return () => clearTimeout(timer);
    }, []);

    return(
        <div className="container">
            <div className="header">Odbierz wydrukowany numerek i poczekaj na jego wywołanie</div>
            <div className="number">{number}</div>
        </div>

    );
}
export default InformationPage;
import React, {useState, useEffect, useRef} from 'react';
import './css/QueueMainPage.css';
import {getAllNumbersInQueue, getNextNumber, postponeQueueNumber, endQueueNumber} from '../service/QueueService';
import {QueueNumberDto} from '../dto/QueueNumberDto'
import {useNavigate} from "react-router-dom";
import {openInformationPage} from './InformationWindow'

export const QueueMainPage = () => {

    const [numbers, setNumbers] = useState<QueueNumberDto[]>([]);
    const [current, setCurrent] = useState<QueueNumberDto | null>();
    const userUUID = "bf462b96-43d7-4fe9-936c-c25c9e0954b0";
    const navigate = useNavigate();
    const informationWindow = useRef<Window | null>(null);
    const [isPulsing, setIsPulsing] = useState(false);

    const fetchNumbers = async () => {
        let response = null;
        try {
            response = await getAllNumbersInQueue(userUUID);
            setNumbers(response.data.queueNumbers);
        } catch (error) {
            console.error('Error during fetch queue numbers', error);
        }
    };

    useEffect(() => {
        fetchNumbers();
        const intervalId = setInterval(fetchNumbers, 60000)
        return () => clearInterval(intervalId);
    }, []);

    const handleNext = async () => {
        if (current == null) {
            const response = await getNextNumber(userUUID);
            setCurrent(response.data.queueNumber);
            setIsPulsing(true);
            setTimeout(() => {
                setIsPulsing(false);
            }, 7000);
            if (informationWindow.current) {
                informationWindow.current.postMessage({ type: 'UPDATE_NUMBER', data: response.data.queueNumber.fullNumber }, '*');
            }
            fetchNumbers();
        }
    };

    const handleEnd = async () => {
        if (current && current.queueUUID) {
            await endQueueNumber(current.queueUUID);
            setCurrent(null);
            fetchNumbers();
        }
    };

    const handleQueueUserConfigurations = async () => {
        navigate('/configure');
    };

    const handlePostpone = async () => {
        if (current && current.queueUUID) {
            await postponeQueueNumber(current.queueUUID);
            setCurrent(null);
            fetchNumbers();
        }
    };

    if(informationWindow.current == null){
        informationWindow.current = openInformationPage();
    }

    if(informationWindow.current != null){
        informationWindow.current.postMessage({ type: 'UPDATE_WORK_STATION', data: "STANOWISKO 1" }, '*');
    }

    document.body.style.display = '';
    document.body.style.marginTop = '';

    return (
        <div className="queue-container">
            <div className="queue-table-container">
                <div className='queue-title'>Queue</div>
                <table className="queue-table">
                    <thead>
                    <tr>
                        <th>Number</th>
                        <th>Date</th>
                        <th>Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    {numbers.map((num) => (
                        <tr key={num.queueUUID}>
                            <td>{num.fullNumber}</td>
                            <td>{num.creationDate}</td>
                            <td>{num.creationTime}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <button className="configuration-button" onClick={handleQueueUserConfigurations}>
                    Configuration
                </button>
            </div>
            <div className="queue-actions">
                <div className="current-number">
                <div className="queue-title">Current number:</div>
                    <div className= {`queue-current-number ${isPulsing ? 'pulse' : ''}`} >
                        {current != null ? `${current?.fullNumber}` : '----'}
                    </div>
                </div>
                <div className="action-buttons">
                    <button onClick={handleNext}>Next</button>
                    <button onClick={handleEnd}>End</button>
                    <button onClick={handlePostpone}>Postpone</button>
                </div>
            </div>
        </div>
    );
}

export default QueueMainPage;
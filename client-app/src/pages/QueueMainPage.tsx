import React, {useState, useEffect} from 'react';
import './css/QueueMainPage.css';
import {getAllNumbersInQueue, getNextNumber, postponeQueueNumber, endQueueNumber} from '../service/QueueService';
import {QueueNumberDto} from '../dto/QueueNumberDto'
import {useNavigate} from "react-router-dom";

export const QueueMainPage = () => {

    const [numbers, setNumbers] = useState<QueueNumberDto[]>([]);
    const [current, setCurrent] = useState<QueueNumberDto | null>();
    const userUUID = "bf462b96-43d7-4fe9-936c-c25c9e0954b0";
    const navigate = useNavigate();

    const fetchNumbers = async () => {
        let response = null;
        try {
            response = await getAllNumbersInQueue(userUUID);
            console.log(response);
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
                    <div
                        className="queue-current-number">{current != null ? `${current?.fullNumber}` : '----'}</div>
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
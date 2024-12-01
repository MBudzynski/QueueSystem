import React, { useEffect, useState, useRef}  from 'react';
import './css/MainPage.css';
import {readText} from "../services/ReadTextService";
import {QueueNumber} from '../dto/QueueNumber';

export const MainPage = () => {

    const [displayQueue, setDisplayQueue] = useState<QueueNumber[]>([]);
    const processQueue = useRef<QueueNumber[]>([]);
    const [isPulsing, setIsPulsing] = useState(false);

    useEffect(() => {
        const webSocketAddress = import.meta.env.VITE_FACILITY_WEB_SOCKET_ADDRESS;
        const ws = new WebSocket(webSocketAddress);

        ws.onmessage = (event) => {
            const newItem: QueueNumber = JSON.parse(event.data);
            console.log(newItem);
            processQueue.current = [...processQueue.current, newItem];
            console.log(processQueue);
        };

        ws.onerror = (error) => {
            console.error("WebSocket Error:", error);
        };
    }, []);

    useEffect(() => {
        callNextNumber();
        const intervalId = setInterval(callNextNumber, 8000)
        return () => clearInterval(intervalId);
    }, []);

    const callNextNumber = () => {
        console.log(processQueue.current.length);
        if (processQueue.current.length > 0) {
            console.log("Wczytano numer");
            const queueNumberToDisplay = processQueue.current.shift()!;
            setDisplayQueue((prevQueue) => {
                const updatedQueue = [queueNumberToDisplay, ...prevQueue];
                setIsPulsing(true);
                return updatedQueue.slice(0, 6);
            });

            readText(queueNumberToDisplay.callNumberPrefix);
            readText(queueNumberToDisplay.sign + queueNumberToDisplay.number);
            readText(queueNumberToDisplay.callServiceDeskName);

            setTimeout(() => {
                setIsPulsing(false);
            }, 8000);
        }
    }

    return (
        <div className="app-container">
            <h1>Queue Display</h1>
            <ul className="queue-list">
                {displayQueue.map((item, index) => (
                    <li key={index} className={`queue-item ${index === 0 && isPulsing? "pulsing" : ""}`}>
                        <span className="station-name">{item.serviceDeskName}</span>
                        <span className="arrow">&#x2192;</span>
                        <span className="queue-number">{item.fullNumber}</span>
                    </li>
                ))}
            </ul>
        </div>
    );

}

export default MainPage;
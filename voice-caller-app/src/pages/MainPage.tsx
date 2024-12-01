import React, { useEffect, useState }  from 'react';
import './css/MainPage.css';
import {readText} from "../services/ReadTextService";
import {QueueNumber} from '../dto/QueueNumber';

export const MainPage = () => {

    const [queue, setQueue] = useState<QueueNumber[]>([]);
    const [isNewItemProcessing, setIsNewItemProcessing] = useState(false);
    const [isPulsing, setIsPulsing] = useState(false);

    useEffect(() => {
        const ws = new WebSocket("ws://localhost:8086/ws");

        ws.onmessage = (event) => {
            if (!isNewItemProcessing) {
                const newItem: QueueNumber = JSON.parse(event.data);
                setIsNewItemProcessing(true);
                setQueue((prevQueue) => {
                    const updatedQueue = [newItem, ...prevQueue];
                    setIsPulsing(true);
                    return updatedQueue.slice(0, 6);
                });

                readText(newItem.readPrefix);
                readText(newItem.sign);
                readText(newItem.number);
                readText(newItem.readSuffix);

                setTimeout(() => {
                    setIsPulsing(false);
                }, 2000);
                setTimeout(() => {
                    setIsNewItemProcessing(false);
                }, 8000);
            }
        };

        ws.onerror = (error) => {
            console.error("WebSocket Error:", error);
        };
    }, []);

    return (
        <div className="app-container">
            <h1>Queue Display</h1>
            <ul className="queue-list">
                {queue.map((item, index) => (
                    <li key={index} className={`queue-item ${index === 0 && isPulsing? "pulsing" : ""}`}>
                        <span className="station-name">{item.readSuffix}</span>
                        <span className="arrow">&#x2192;</span>
                        <span className="queue-number">{item.fullNumber}</span>
                    </li>
                ))}
            </ul>
        </div>
    );

}

export default MainPage;
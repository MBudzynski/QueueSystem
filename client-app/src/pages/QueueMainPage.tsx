import React, { useState } from 'react';
import './css/QueueMainPage.css';

export const QueueMainPage = () => {

    const [queue, setQueue] = useState<number[]>([101, 102, 103, 104, 105]);
    const [current, setCurrent] = useState<number | null>(null);

    const handleNext = () => {
        if (queue.length > 0) {
            const newQueue = [...queue];
            setCurrent(newQueue.shift() || null);
            setQueue(newQueue);
        }
    };

    const handleEnd = () => {
        setCurrent(null);
    };

    const handlePostpone = () => {
        if (current !== null) {
            setQueue([...queue, current]);
            setCurrent(null);
        }
    };

    document.body.style.display = '';
    document.body.style.marginTop = '';

    return (
        <div className="queue-container">
            <div className="queue-list-container">
                <div className='queue-title'>Queue</div>
                <ul className="queue-list">
                    {queue.map((num) => (
                        <li key={num}>{num}</li>
                    ))}
                </ul>
            </div>
            <div className="queue-actions">
                <div className="current-number">
                    <div className="queue-title">Current number:</div>
                    <div className="queue-current-number">{current !== null ? `${current}` : '----'}</div>
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
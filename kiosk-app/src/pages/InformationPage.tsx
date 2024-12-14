import {useEffect, useState, useRef} from "react";
import './css/InformationPage.css';
import {useLocation, useNavigate} from 'react-router-dom';
import {QueueNumber} from "../dto/QueueNumber";
import {handlePrint} from "../service/PrintPdfService";

export const InformationPage = () => {
    const [number, setNumber] = useState<string>('');
    const facility = useLocation();
    let ref = useRef(0);
    const navigate = useNavigate();
    let queueNumber = facility.state.queueNumber as QueueNumber;

    useEffect(() => {
        if(queueNumber){
            setNumber(queueNumber.number);
            if (ref.current === 0) {
                console.log(ref)
                handlePrint(queueNumber.numberPdf.toString());
                ref.current = 1;
            }

        }

        const timer = setTimeout(() => {
            navigate('/mainEkioskPage');
        }, 15000);

        return () => clearTimeout(timer);
    }, []);

    return(
        <div className="container">
            <div className="header">Pick up the printed number and wait for it to be called</div>
            <div className="number">{number}</div>
        </div>

    );
}
export default InformationPage;
import {useEffect, useState, useRef} from "react";
import './css/InformationPage.css';
import {useLocation, useNavigate} from 'react-router-dom';
import {QueueNumber} from "../dto/QueueNumber";
import {preparePdf} from "../service/PdfService";

export const InformationPage = () => {
    const [number, setNumber] = useState<string>('');
    const facility = useLocation();
    const navigate = useNavigate();
    let queueNumber = facility.state.queueNumber as QueueNumber;
    const queueNumberPDF = useRef<HTMLIFrameElement | null>(null);

    useEffect(() => {
        if(queueNumber){
            setNumber(queueNumber.number);
            let pdf = preparePdf(queueNumber.numberPdf);
            const hidePDF = queueNumberPDF.current;
            if (hidePDF) {
                hidePDF.src = pdf;
                hidePDF.onload = () => {
                    hidePDF.contentWindow?.focus();
                    hidePDF.contentWindow?.print();
                };
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

            <iframe ref={queueNumberPDF} style={{display: 'none'}}/>
        </div>

    );
}
export default InformationPage;
import React, {useState} from 'react';
import './css/LoginPage.css';
import {useNavigate} from 'react-router-dom';
import {loginUser} from '../service/UserService'
import {useDispatch} from "react-redux";
import {setUserData} from "../config/UserDataPlaceholder";

export const LoginPage = () =>{

    const [userName, setUserName] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleLoginUser = async () => {
        setError(null);

        let data = null;
        try {
            const response =  await loginUser(userName, password);
            data = response.data;
        } catch (error) {
            console.log(error);
            setError("Worng username or password");
            return;
        }

        console.log(data);
        dispatch(setUserData(data));
        navigate('/queueMainPage');
    }

    document.body.style.alignItems = 'center';
    document.body.style.justifyContent = 'center';
    document.body.style.display = 'flex';
    document.body.style.marginTop = '10%';

    const handleUserNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUserName(e.target.value)
    }

    const handlePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value)
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
                    <label className="label" htmlFor="User name">User name:</label>
                    <input
                        className="input"
                        id="userName"
                        type="text"
                        value={userName}
                        onChange={handleUserNameChange}
                    />
                </div>
                <div className="form-field">
                    <label className="label" htmlFor="password">Password:</label>
                    <input
                        className="input"
                        id="password"
                        type="password"
                        value={password}
                        onChange={handlePassword}
                    />
                </div>
                <button className="button" onClick={handleLoginUser}>Login</button>
            </div>
        </div>
    );
}

export default LoginPage;
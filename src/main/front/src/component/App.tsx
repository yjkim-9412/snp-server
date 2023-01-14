import React, {useEffect, useState} from 'react';
import axios from "axios";
import Router from "./Router";
import AppRouter from "./Router";
import "bootstrap/dist/css/bootstrap.css";
import '../component/routes/modules/Login.module.css'
import '../axios.config';

function App() {

    const [isLoggedIn, setIsLoggedIn] = useState<Boolean>(false);
    const changeLoginStatus = () => {
        setIsLoggedIn(!isLoggedIn);
        console.log(isLoggedIn);
    }
    return (
        <AppRouter isLoggedIn = {isLoggedIn} sendLoginStatus={changeLoginStatus} />
    );
}
export default App;

import React, {useEffect} from 'react';
import axios from "axios";
import {Link, redirect} from "react-router-dom";

function Main() {

    useEffect(() => {
    });

    return (
        <div className="App">
            <p>메인 페이지</p>
            <Link to="/login">로그인</Link>
        </div>
    );
}

export default Main;

import React, {useEffect, useState} from 'react';
import {BrowserRouter, Route, Routes, useNavigate} from "react-router-dom";
import Auth from "./routes/Auth";
import Home from "./routes/Home";
import {Cookie} from "@mui/icons-material";
import {useCookies} from "react-cookie";
import StudentReCard from "./register/SignUpStudent";
import Dashboard from './main/DashBord';
import CalendarMain from "./today/CalendarMain";
import Info from "./student/Info";

interface ChildLogin {
    isLoggedIn: Boolean,
    setIsLoggedIn:  React.Dispatch<React.SetStateAction<Boolean>>
}
const AppRouter: React.FC = () => {
    const navigate = useNavigate();

    useEffect(() => {
        if (!sessionStorage.getItem('lg')) {
            navigate('/login');
        }
    }, []);

    return (
            <Routes>
                <Route path="/" element={<Dashboard />}>
                    <Route path="/main" element={<CalendarMain/>}/>
                    <Route path="/students/info/:id" element={<Info/>}/>
                </Route>
                <Route path="/login" element={<Auth  />}/>
                <Route path="/student/register" element={<StudentReCard/>}/>
            </Routes>

    );
}
export default AppRouter;
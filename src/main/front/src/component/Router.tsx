import React, {useEffect, useState} from 'react';
import {BrowserRouter, Route, Routes, useNavigate} from "react-router-dom";
import Auth from "./routes/Auth";
import Home from "./routes/Home";
import {Cookie} from "@mui/icons-material";
import {useCookies} from "react-cookie";
import StudentReCard from "./register/SignUpStudent";
// const Router = () => {
//
//     return(
//         <div>
//             <BrowserRouter>
//                 <Routes>
//                     <Route path="/login" element={<Login/>}></Route>
//                     <Route path="/" element={<Main/>}></Route>
//                 </Routes>
//             </BrowserRouter>
//         </div>
//     );
//
// }
interface ChildLogin {
    isLoggedIn: Boolean,
    setIsLoggedIn:  React.Dispatch<React.SetStateAction<Boolean>>
}
const AppRouter: React.FC = () => {
    const navigate = useNavigate();
    const [cookies] = useCookies(['JSESSIONID']);

    useEffect(() => {
        if (!sessionStorage.getItem('lg')) {
            navigate('/login');
        }
    }, []);

    return (
            <Routes>
                <Route path="/" element={<Home />}></Route>
                <Route path="/login" element={<Auth  />}></Route>
                <Route path="/student/register" element={<StudentReCard/>}></Route>
            </Routes>

    );
}
export default AppRouter;
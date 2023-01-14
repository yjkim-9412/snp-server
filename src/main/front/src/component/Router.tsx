import React, {useState} from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Auth from "./routes/Auth";
import Home from "./routes/Home";
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
    sendLoginStatus: () => void
}

const AppRouter: React.FC<ChildLogin> = ({ isLoggedIn, sendLoginStatus} ) => {
    return (
        <BrowserRouter>
            <Routes>
                {isLoggedIn ? (
                    <>
                        <Route path="/" element={<Home sendLoginStatus={sendLoginStatus}/>}></Route>
                    </>) : (<Route path="/" element={<Auth sendLoginStatus={sendLoginStatus} />}></Route>
                )}
            </Routes>
        </BrowserRouter>
    );
}
export default AppRouter;
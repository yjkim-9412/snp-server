import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './component/App';
import "bootstrap/dist/css/bootstrap.css";
import './axios.config';
import {BrowserRouter} from "react-router-dom";
import {CookiesProvider} from "react-cookie";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <CookiesProvider>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </CookiesProvider>
    </React.StrictMode>
);



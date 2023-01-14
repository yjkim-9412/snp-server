import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './component/App';
import "bootstrap/dist/css/bootstrap.css";
import './axios.config';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);



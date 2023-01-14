import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';

axios.interceptors.request.use(config => {
    const token = localStorage.getItem('loginTeacher');
        config.headers = {
            'Authorization' : `Bearer ${token}`,
            'Accept': 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded'
    }

    return config;
});


export default axios;
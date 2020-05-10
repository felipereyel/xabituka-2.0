import axios from 'axios'

const api = axios.create({
    baseURL: 'http://35.225.88.246:8080',
});

export default api
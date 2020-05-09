import axios from 'axios'

const api = axios.create({
    baseURL: 'http://felipereyel.pythonanywhere.com',
});

export default api
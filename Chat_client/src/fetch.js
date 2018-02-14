import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8089';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.withCredentials=true;

function fetch(params) {
  return new Promise((resolve, reject) => {
    axios(params)
      .then((res) => {
        if (res.data.msg === 'success') {
          resolve(res.data.result);
        } else {
          reject(res.data.msg);
        }
      }).catch((err) => {
        reject("请求失败！请检查网络");
    })
  })
}

export default fetch;

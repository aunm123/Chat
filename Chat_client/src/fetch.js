import axios from 'axios';
import {Toast, Indicator} from 'mint-ui';
import router from "./router";

axios.defaults.baseURL = 'http://localhost:8089';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.withCredentials = true;


function fetch(url, params) {
  return new Promise((resolve, reject) => {
    let tempparams = new URLSearchParams();

    for (let key in params) {
      tempparams.append(key, params[key]);
    }
    axios({
      url: url,
      method: 'post',
      data: tempparams,
    })
      .then((res) => {
        if (res.data.msg === 'success') {
          resolve(res.data.result);
        } else {
          if (res.status === 102){
            //未登录
            router.push('/');
          }else {
            Toast({message: res.data.msg, position: 'bottom', duration: 2000});
            reject(res.data.msg);
          }
        }
      }).catch((err) => {
        Indicator.close();
        Toast({message: err, position: 'bottom', duration: 2000});
        reject("请求失败！请检查网络");
    })
  })
}

export default fetch;

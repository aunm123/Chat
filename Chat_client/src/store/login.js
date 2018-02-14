import fetch from '@/fetch';
import { Toast } from 'mint-ui';

const loginStore = {
  actions: {
    loginAction({commit, state},{username,password}){
      return new Promise((resolve,reject)=>{
        let params = new URLSearchParams();
        params.append("userName",username);
        params.append("password",password);
        fetch({
          method: 'post',
          url: '/account/login',
          data: params
        }).then((res) => {
          let user = res.user;
          commit('updateUser',user);
          resolve();
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    }
  },
  mutations: {
  }
}


export default loginStore;

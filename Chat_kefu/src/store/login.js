import fetch from '@/api/fetch';
import {requestLogin} from '@/api'

const loginStore = {
  actions: {
    loginAction({commit, state}, {username, password}) {
      return new Promise((resolve) => {
        requestLogin({
          userName: username,
          password: password
        }).then(res => {
          commit('updateService', res.service);
          resolve();
        });
      })
    },
    getServiceSession({commit, state}) {
      return new Promise((resolve,reject) => {
        fetch('/account/shasLogin')
          .then((res) => {
            commit('updateService', res.service);
            resolve();
          }).catch((e)=>{
          reject();
        })

      })
    }
  },
  mutations: {}
}


export default loginStore;

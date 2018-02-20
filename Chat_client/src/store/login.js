import fetch from '@/fetch';


const loginStore = {
  actions: {
    loginAction({commit, state}, {username, password}) {
      return new Promise((resolve) => {

        fetch('/account/login', {
            userName: username,
            password: password
        })
          .then((res) => {
            let user = res.user;
            commit('updateUser', user);
            resolve();
          })

      })
    },
    getUserSession({commit, state}) {
      return new Promise((resolve,reject) => {
        fetch('/account/uhasLogin')
          .then((res) => {
            let user = res.user;
            commit('updateUser', user);
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

import fetch from '@/fetch';
import { Toast } from 'mint-ui';

const serviceListStore = {
  state:{
    groupListShow: false,
  },
  actions: {
    serverlistAction({commit, state},userid){
      return new Promise((resolve,reject)=>{
        let params = new URLSearchParams();
        params.append("userid",userid);
        fetch({
          method: 'post',
          url: '/user/roomlist',
          data: params
        }).then((res) => {
          let roomlistv = res.roomlist;
          resolve(roomlistv);
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    },
    grouplistAction({commit, state}){
      return new Promise((resolve,reject)=>{
        fetch({
          method: 'post',
          url: '/group/list',
        }).then((res) => {
          let groups = res.groups;
          resolve(groups);
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    },
  },
  mutations: {
  }
}


export default serviceListStore;

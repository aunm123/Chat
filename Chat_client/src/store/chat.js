import fetch from '@/fetch';
import {Toast} from 'mint-ui';

const rootStore = {
  actions: {
    joinRooe({commit, state}, {userId, groupId}) {
      return new Promise((resolve, reject) => {
        let params = new URLSearchParams();
        params.append("userid", userId);
        params.append("groupid", groupId);
        fetch({
          method: 'post',
          url: '/user/joinroom',
          data: params
        }).then((res) => {
          resolve(res);
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    },
    historyMessage({commit, state}, roomid) {
      return new Promise((resolve, reject) => {
        let params = new URLSearchParams();
        params.append("roomid", roomid);
        fetch({
          method: 'post',
          url: '/room/history',
          data: params
        }).then((res) => {
          resolve(res);
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    },
    getRoomData({commit, state}, roomid) {
      return new Promise((resolve, reject) => {
        let params = new URLSearchParams();
        params.append("roomid", roomid);
        fetch({
          method: 'post',
          url: '/room/getdata',
          data: params
        }).then((res) => {
          resolve(res);
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    },
    sendMessage({commit, state}, {content,roomid}){
      return new Promise((resolve, reject) => {
        let params = new URLSearchParams();
        params.append("msg", content);
        params.append("roomid", roomid);
        fetch({
          method: 'post',
          url: '/user/sendmsg',
          data: params
        }).then((res) => {
          resolve(res);
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    },
    requersService({commit, state}, {userid,roomid}){
      return new Promise((resolve, reject) => {
        let params = new URLSearchParams();
        params.append("userid", userid);
        params.append("roomid", roomid);
        fetch({
          method: 'post',
          url: '/user/reservice',
          data: params
        }).then((res) => {
          resolve(res);
        }).catch((err) => {
          Toast({message: err, position: 'bottom', duration: 2000});
        })
      })
    }
  },
  mutations: {}
}


export default rootStore;

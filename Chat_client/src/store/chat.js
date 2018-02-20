import fetch from '@/fetch';
import {Toast} from 'mint-ui';

const rootStore = {
  actions: {
    joinRooe({commit, state}, {userId, groupId}) {
      return new Promise((resolve, reject) => {

        fetch('/user/joinroom', {
          userid: userId,
          groupid: groupId
        }).then((res) => {
          resolve(res);
        })
      })
    },
    historyMessage({commit, state}, roomid) {
      return new Promise((resolve) => {
        fetch('/room/history', {
          roomid: roomid
        }).then((res) => {
          resolve(res);
        })
      })
    },
    getRoomData({commit, state}, roomid) {
      return new Promise((resolve) => {
        fetch('/room/getdata', {
          roomid: roomid
        }).then((res) => {
          resolve(res);
        })
      })
    },
    sendMessage({commit, state}, {content, roomid}) {
      return new Promise((resolve) => {

        fetch('/user/sendmsg', {
          msg: content,
          roomid: roomid
        }).then((res) => {
          resolve(res);
        })
      })
    },
    requersService({commit, state}, {userid, roomid}) {
      return new Promise((resolve) => {

        fetch('/user/reservice', {
          userid: userid,
          roomid: roomid
        }).then((res) => {
          resolve(res);
        })
      })
    }
  },
  mutations: {}
}


export default rootStore;

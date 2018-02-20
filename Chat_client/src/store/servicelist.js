import fetch from '@/fetch';
import {Toast} from 'mint-ui';

const serviceListStore = {
  state: {
    groupListShow: false,
  },
  actions: {
    serverlistAction({commit, state}, userid) {
      return new Promise((resolve) => {

        fetch('/user/roomlist', {
          userid: userid
        }).then((res) => {
          let roomlistv = res.roomlist;
          resolve(roomlistv);
        });
      })
    },
    grouplistAction({commit, state}) {
      return new Promise((resolve) => {

        fetch('/group/list', {})
          .then((res) => {
            let groups = res.groups;
            resolve(groups);
          });
      })
    },
  },
  mutations: {}
}


export default serviceListStore;

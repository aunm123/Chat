import Vue from 'vue';
import Vuex from 'vuex';
import login from './login';
import chat from './chat';
import websocket from './websocket'
import serviceList from './servicelist';
import {Toast} from "mint-ui";

Vue.use(Vuex);

function getMemberList() {
  let result = localStorage.memberlist;
  if (result) {
    return new Set(JSON.parse(localStorage.memberlist));
  } else {
    return new Set();
  }
}

const state = {
  // user: {
  //   createtime: 1517494883000,
  //   email: "asda@email.com",
  //   id: 1,
  //   isonline: false,
  //   isservice: false,
  //   lastupdatetime: 1517494884000,
  //   name: "qwe",
  //   nickname: null,
  //   password: "123123",
  //   phone: null,
  // },
  user: null,
  memberlist: getMemberList(),
  websocketHasConnect: false,
};
const actions = {
  getUserByIdAction({commit, state}, id) {
    return commit('getUserById', id);
  },
};
const getters = {
  getUserInfo: state => state.user,
};
const mutations = {
  updateUser(state, user) {
    state.user = user;
    state.memberlist.add(user);
  },
  saveMemberList(state) {
    let array = Array.from(state.memberlist);
    localStorage.memberlist = JSON.stringify(array);
  },
  getUserById(state, id) {
    let result = {};
    for (let member in state.memberlist) {
      if (member.id === id) {
        result = member;
      }
    }
    return result;
  },

};

export default new Vuex.Store({
  state,
  actions,
  getters,
  mutations,
  modules: {
    login,
    chat,
    serviceList,
    websocket,
  }
})

import Vuex from 'vuex'
import Vue from "vue";
import websocket from './websocket'
import login from './login'

const mutations = {
  updateService(state, service) {
    console.log(service);
    state.service = service;
  },
  getUserById(state, id) {
    let result = {nickname: '你麻痹'};
    return result;
  },
};
const actions = {
  getUserByIdAction({commit, state}, id) {
    return commit('getUserById', id);
  },
};
const state = {
  // service: null,
  service: {
    createtime: 1517495054000,
    groupId: 3,
    id: 3,
    isonline: null,
    isworking: null,
    lastupdatetime: 1517495054000,
    userId: 3,
  }
};
const getters = {
  getServiceInfo: state => state.service,
};

Vue.use(Vuex);

export default new Vuex.Store({
  state,
  actions,
  getters,
  mutations,
  modules: {
    websocket,
    login,
  }
})

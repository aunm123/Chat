import webso from '@/ws/index'



const websocketStore = {
  state:{
    newMessageList: [],
  },
  getters:{
    getNewMessage: state => state.newMessageList,
  },
  actions: {
    connectAction({commit, state, rootState}){

      if (!rootState.websocketHasConnect){
        webso.startWs()
          .then(sock => {
            webso.messageCome((edata)=>{
              commit('handleWebsocketMessage',edata.data);
            })

          });
        rootState.websocketHasConnect = true;
      }
    },
  },
  mutations: {
    handleWebsocketMessage(state, eData) {
      let content = eData;
      let fromName = eData;
      state.newMessageList.push(eData);
    },
    updateNewMessage(state, messageList) {
      state.newMessageList = messageList;
    }
  }
}


export default websocketStore;

import Vue from 'vue'
import App from './App'
import router from './router'
import './assets/css/style.less'
import $ from 'jquery';
import 'mint-ui/lib/style.css'
import MintUI from 'mint-ui'
import Vuex from 'vuex';
import store from './store/index'
import fetch from './fetch'
import './assets/font/iconfont.css'

Vue.use(MintUI);
Vue.use(Vuex);

Vue.config.productionTip = false;


Vue.prototype.fetch = fetch;


/* eslint-disable no-new */
let mainVue = new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});

export default mainVue;

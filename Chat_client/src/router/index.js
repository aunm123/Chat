import Vue from 'vue';
import Router from 'vue-router';
import ChatRoom from '@/components/ChatRoom';
import ServiceList from '@/components/ServiceList';
import Login from '@/components/Login';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/sl',
      name: 'ServiceList',
      component: ServiceList
    },
    {
      path: '/chat/:roomid',
      name: 'ChatRoom',
      component: ChatRoom
    }
  ]
})

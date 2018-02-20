import Vue from 'vue';
import Router from 'vue-router';
import ChatRoom from '@/components/ChatRoom';
import ServiceList from '@/components/ServiceList';
import Login from '@/components/Login';
import store from "../store";
import login from "../store/login";

Vue.use(Router);

let router = new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/sl',
      name: 'ServiceList',
      component: ServiceList,
      meta: {
        requireAuth: true,
      }
    },
    {
      path: '/chat/:roomid',
      name: 'ChatRoom',
      component: ChatRoom,
      meta: {
        requireAuth: true,
      }
    }
  ]
})


router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
    //需要登录
    if (store.state.user !== null) {  // 通过vuex state获取当前的token是否存在
      next();
      if (!store.state.websocketHasConnect) {
        store._actions.connectAction[0]();
      }
    } else {
      store._actions.getUserSession[0]()
        .then(() => {
          next();
          if (!store.state.websocketHasConnect) {
            store._actions.connectAction[0]();
          }
        })
        .catch(() => {
          router.replace({
            path: '/'
          })
        })
    }
  } else {
    next();
  }
})


export default router

import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import UserTable from '@/components/view1/UserTable'
import RoomTable from '@/components/view2/RoomTable'
import store from '@/store'

Vue.use(Router);

let router = new Router({
  routes: [
    {
      path: '/',
      name: '登录页面',
      component: Login
    },
    {
      path: '/home',
      name: '首页',
      component: Home,
      meta: {
        requireAuth: true,
      },
      children: [
        {
          path: '/usertable',
          name: '用户',
          component: UserTable
        },
        {
          path: '/roomtable',
          name: '聊天室',
          component: RoomTable
        }
      ]
    },
  ]
})

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
    //需要登录
    if (store.state.service !== null) {  // 通过vuex state获取当前的token是否存在
      console.log(store.state.service);
      next();
      if (!store.state.websocketHasConnect) {
        store._actions.connectAction[0]();
      }
    } else {
      store._actions.getServiceSession[0]()
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
});

export default router;

import { createRouter, createWebHashHistory } from 'vue-router'

import Login from '../views/Login.vue'
import Page404 from '../views/404.vue'
import Home from '../views/Home.vue'
import store from "../store/index.js";

  const routes = [
      {
        path: '/',
        name: 'Home',
        component: Home
      },

      {
        path: '/login',
        name: 'login',
        component: Login
      },

      {
        path: '/error',
        name: '404',
        component: Page404
      }
]

// const router = new VueRouter({
//   // mode: 'history',
//   // base: process.env.BASE_URL,
//   routes
// })


const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
    // to 将要访问的路径
    // from 代表从哪个路径跳转而来
    // next 是一个函数，表示放行
    //     next()  放行    next('/login')  强制跳转
    let token = localStorage.getItem('token');
    if(token){
        next()
    }else{
        if (to.path === '/login') return next()
        next('/login')
    }

    // 获取token
    // const tokenStr = window.sessionStorage.getItem('token')
    console.log("token:" + token)

})
export default router

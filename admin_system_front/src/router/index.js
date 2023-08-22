import { createRouter, createWebHashHistory } from 'vue-router'

import Login from '../views/Login.vue'
import Page404 from '../views/404.vue'
import Home from '../views/Home.vue'
import store from "../store/index.js";


  const routes = [
      {
          path: '/',
          name: 'Home',
          component: Home,
          redirect:'/index',
          children: [{
                path: '/index',
                name: 'Index',
                component: () => import('../views/Index.vue'),
          }
        ]
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
    let hasRoutes = store.state.hasRoutes;
    let menuList=store.getters.GET_MENU_LIST;
    if(token){
        if(!hasRoutes){
            // console.log("hasRoutes")
            bindRoute(menuList);
            store.commit("SET_ROUTES_STATE",true)
        }
        next()
    }else{
        if (to.path === '/login') return next()
        next('/login')
    }

    // 获取token
    // const tokenStr = window.sessionStorage.getItem('token')
    // console.log("token:" + token)

})

// 动态绑定路由
const bindRoute=(menuList)=>{
    let newRoutes=router.options.routes;
    menuList.forEach(menu=>{
        if(!(menu.children.length == 0)){
            menu.children.forEach(m=>{
                // 菜单转成路由
                let route=menuToRoute(m,menu.menuName);
                if(route){
                    newRoutes[0].children.push(route); // 添加到路由管理
                }
            })
        }else {
            let route=menuToRoute(menu,"0");
            if(route){
                newRoutes[0].children.push(route); // 添加到路由管理
            }
        }
    })
    // 重新添加到路由
    newRoutes.forEach(route=>{
        router.addRoute(route);
    })
}

// 菜单转成路由
const menuToRoute = (menu,parentName) => {

    if (!menu.component) {
        console.log(menu.menuName)
        return null
    }else{
        console.log(menu.menuName)
        let route = {
            name: menu.menuName,
            path: menu.routePath,
            meta:{
                parentName:parentName
            }
        }
        route.component = () => import('../views/' + menu.component +'.vue')

        return route
    }
}
export default router

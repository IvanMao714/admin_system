import { createRouter, createWebHashHistory } from 'vue-router'

import Login from '../views/Login.vue'
import Page404 from '../views/404.vue'
import Home from '../views/Home.vue'
import store from "../store/index.js";
import axios from "axios";
import global from "../global.js";


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
router.beforeEach(async (to, from, next) => {
    // to 将要访问的路径
    // from 代表从哪个路径跳转而来
    // next 是一个函数，表示放行
    //     next()  放行    next('/login')  强制跳转
    let token = sessionStorage.getItem('token');
    let hasRoutes = store.state.hasRoutes;
    if (token) {
        if (!hasRoutes) {
            // console.log("hasRoutes")
            let menuList = null;
            await axios.get(global.HOST_URL + "/menu/list", {headers: {'token':token }}).then(
                res => {
                    res = res.data;
                    // console.log(res.data)
                    menuList = res.data;
                }
            );
            bindRoute(menuList);
        }
        next()
    } else {
        if (to.path === '/login') return next()
        next('/login')
    }

})

// 动态绑定路由
const bindRoute=(menuList)=>{
    let newRoutes=router.options.routes;
    menuList.forEach(menu=>{
        if(!(menu.children.length == 0)){
            // console.log(menu)
            menu.children.forEach(m=>{
                // 菜单转成路由
                let route=menuToRoute(m,menu.menuName);
                // console.log(route)
                if(route){
                    newRoutes[0].children.push(route); // 添加到路由管理
                }
            })
        }else {
            // console.log(menu)
            let route=menuToRoute(menu,"0");
            if(route){
                newRoutes[0].children.push(route); // 添加到路由管理
            }
        }
    })
    // 重新添加到路由
    newRoutes.forEach(route=>{
        // console.log(route)
        router.addRoute(route);
    })
}

// 菜单转成路由
const menuToRoute = (menu,parentName) => {

    if (!menu.component) {
        // console.log(menu.menuName)
        return null
    }else{
        // console.log(menu.menuName)
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
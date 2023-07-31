import { createApp } from "vue";
import "https://kit.fontawesome.com/64d58efce2.js";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from "./App.vue";
import router from './router';
import store from './store'

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(store)
app.mount("#app");



// Vue.use(ElementPlus)
// Vue.use(VCharts)

// Vue.config.productionTip = false
//
// new Vue({
//     el: '#app',
//     router,
//     store,
//     template: '<App/>',
//     components: { App }
// })

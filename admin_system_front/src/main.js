import { createApp } from "vue";
import "https://kit.fontawesome.com/64d58efce2.js";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from "./App.vue";
import router from './router';
import store from './store'
import './components/icon/iconfont.css'
import Vuex from "vuex";

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(store)
app.use(Vuex)
app.mount("#app");


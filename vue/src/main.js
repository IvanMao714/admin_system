import { createApp } from "vue";
import "https://kit.fontawesome.com/64d58efce2.js";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from "./App.vue";

const app = createApp(App)
app.use(ElementPlus)
app.mount("#app");

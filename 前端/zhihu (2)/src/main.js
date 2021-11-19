import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
// import 'element-plus/dist/index.css'
import store from './store'
// import { Popover } from 'element-ui';
import $ from 'jquery'
import mitt from 'mitt';
const app = createApp(App)
//挂载事务总线为全局属性
app.config.globalProperties.$mybus = new mitt()
// import axios from 'axios'
// import VueAxios from 'vue-axios'
// .use(VueAxios)
app.use(ElementPlus)
app.use($).use(store).use(router).mount('#app')

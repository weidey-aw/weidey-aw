import {createApp, ref} from 'vue'
import App from './App.vue'
import router from './router'
// 引入ElementPlus
import ElementPlus from 'element-plus'
import mavonEditor from 'mavon-editor'
import 'element-plus/dist/index.css'
import 'virtual:windi.css'
import '/src/assets/styles.scss'
import "@popperjs/core";
import "bootstrap";
import 'mavon-editor/dist/css/index.css'
// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//pinia
import {createPinia} from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate' //引入持久化插件

const pinia  = createPinia()
pinia.use(piniaPluginPersistedstate);
const app = createApp(App)

app.use(pinia)
app.use(router)
app.use(ElementPlus)
app.use(mavonEditor)


app.mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}


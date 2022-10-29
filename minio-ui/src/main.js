import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'
import ace from 'ace-builds'
import jquery from 'jquery'
Vue.prototype.$ = jquery
Vue.use(ace)
Vue.config.productionTip = false

Vue.use(ElementUI);
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

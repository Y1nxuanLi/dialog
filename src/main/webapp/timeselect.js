import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import enLocale from 'element-ui/lib/locale/lang/en';

Vue.use(ElementUI, { locale: enLocale });

new Vue({
    el: '#app',
    render: h => h(App)
});

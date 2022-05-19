import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
<<<<<<< HEAD
// import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css'
=======
import './index.css'
>>>>>>> localhost

const app= createApp(App)
app.use(router)
app.mount('#app')

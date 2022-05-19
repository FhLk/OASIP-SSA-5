import { createRouter, createWebHistory } from 'vue-router'
<<<<<<< HEAD
import indexPage from '../views/indexPage.vue'
import AddPage from '../views/AddBookingPage.vue'
import ShowPage from '../views/ShowPage.vue'
const history = createWebHistory(import.meta.env.VITE_BASE_URL)
const routes = [
    {path: '/',name: 'indexPage',component: indexPage},
    {path: '/AddPage',name: 'AddPage',component: AddPage},
    {path: '/ShowPage',name: 'ShowPage',component: ShowPage},
=======
import indexPage1 from '../views/indexPage.vue'
import AddPage1 from '../views/AddBookingPage.vue'
import ShowPage1 from '../views/ShowPage.vue'
const history = createWebHistory(import.meta.env.VITE_BASE_URL)
const routes = [
    {path: '/',name: 'indexPage',component: indexPage1},
    {path: '/AddPage',name: 'AddPage',component: AddPage1},
    {path: '/ShowPage',name: 'ShowPage',component: ShowPage1},
>>>>>>> localhost
    
]
const router = createRouter({ history, routes })
export default router
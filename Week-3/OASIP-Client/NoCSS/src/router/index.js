import { createRouter, createWebHistory } from 'vue-router'
import indexPage from '../views/indexPage.vue'
import AddPage from '../views/AddBookingPage.vue'
import ShowPage from '../views/ShowPage.vue'
const history = createWebHistory(import.meta.env.VITE_BASE_URL)
const routes = [
    {path: '/',name: 'indexPage',component: indexPage},
    {path: '/AddPage',name: 'AddPage',component: AddPage},
    {path: '/ShowPage',name: 'ShowPage',component: ShowPage},
    
]
const router = createRouter({ history, routes })
export default router
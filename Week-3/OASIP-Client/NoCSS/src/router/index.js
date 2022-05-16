import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/indexPage.vue'
import AddBooking from '../views/AddBookingPage.vue'
import Show from '../views/ShowPage.vue'
const history = createWebHistory(import.meta.env.VITE_BASE_URL)
const routes = [
    {path: '/',name: 'Home',component: Home},
    {path: '/AddBooking',name: 'AddBooking',component: AddBooking},
    {path: '/ShowBooking',name: 'ShowBooking',component: Show},
    
]
const router = createRouter({ history, routes })
export default router
import { createRouter, createWebHistory } from 'vue-router'
import indexPage1 from '../views/indexPage.vue'
import AddPage1 from '../views/AddPage.vue'
import ShowPage1 from '../views/ShowPage.vue'
const history = createWebHistory('ssa5')
const routes = [
    {path: '/',name: 'indexPage',component: indexPage1},
    {path: '/AddPage',name: 'AddPage',component: AddPage1},
    {path: '/ShowPage',name: 'ShowPage',component: ShowPage1},
    
]
const router = createRouter({ history, routes })
export default router
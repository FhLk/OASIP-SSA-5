<script setup>
import {onMounted,onBeforeMount,ref, onBeforeUpdate, onUpdated, computed} from 'vue';
import List from '../components/List.vue'
let DateFormat="YYYY-MM-DD HH:mm"
const isShow=ref(false)
const getListCategories = ref([]);

const getCategories = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/categories`, {
        method: 'GET'
    })
    getListCategories.value = await res.json()
}

onBeforeMount(async ()=>{
    await getCategories()
})

</script>
 
<template>
<h1>OASIP TEST</h1>
<div>
    <h2>Category List</h2>
    <div>
        <ul>
            <p v-for="(category,index) in getListCategories" :key="index">
               {{index+1}}. {{category.categoryName}} 
               <div>
                   Description: {{category.description}}
               </div>
               <div>
                   Duration: {{category.duration}} (mins.)
               </div>
            </p>
        </ul>
    </div>
    <h2>Booking List</h2>
    <List/>
</div>
</template>
 
<style scoped>

</style>

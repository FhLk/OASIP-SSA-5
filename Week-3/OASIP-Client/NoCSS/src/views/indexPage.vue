<script setup>
import { onBeforeMount, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import ShowBookingPage from '../views/ShowPage.vue';
const getListCategories=ref([])
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
<div>
    <button><router-link :to="{name:'AddPage'}">Add New Booking</router-link></button>
    <ShowBookingPage :getListCategory="getListCategories"/>
</div>
</template>
 
<style scoped>

</style>
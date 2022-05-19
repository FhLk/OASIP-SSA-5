<script setup>
<<<<<<< HEAD
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
=======
import ShowBookingPage from '../views/ShowPage.vue';

>>>>>>> localhost

</script>
 
<template>
<<<<<<< HEAD
<div>
    <button><router-link :to="{name:'AddPage'}">Add New Booking</router-link></button>
    <ShowBookingPage :getListCategory="getListCategories"/>
=======
<div class="bg h-screen">
    <ShowBookingPage/>
    <!-- <button><router-link to="/AddBooking">Add New Booking</router-link></button> -->
>>>>>>> localhost
</div>
</template>
 
<style scoped>
<<<<<<< HEAD
=======
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');
.font{
    font-family: 'Mitr', sans-serif;
}
.bg{
    background-color: rgb(255, 255, 247);
}
>>>>>>> localhost

</style>
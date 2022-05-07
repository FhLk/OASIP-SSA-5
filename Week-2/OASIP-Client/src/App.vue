<script setup>
import {onMounted,onBeforeMount,ref, onBeforeUpdate, onUpdated, computed} from 'vue';
import moment from "moment"
import List from "./components/List.vue"
import Create from './components/Create.vue';
let DateFormat="YYYY-MM-DD HH:mm"
const getListBooking=ref([])
const getListCategories=ref([]);

const getCategories= async () =>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/categories`,{
        method: 'GET'
    })
    getListCategories.value=await res.json()
}

const getBookings= async ()=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'GET'
    })
    getListBooking.value=await res.json()
    getListBooking.value.forEach((data)=>{
        data.startTime=moment(data.startTime).utcOffset(0).format(DateFormat)
    })
    getListBooking.value.sort((a,b)=>{
        return new Date(b.startTime) - new Date(a.startTime)
    })
}

onBeforeMount(async ()=>{
    await getBookings()
    await getCategories()
})

const createBooking= async (booking)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'POST',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:getListBooking.value.length+1,
            bookingName: booking.bookingName + ` (${booking.group})`,
            bookingEmail: booking.bookingEmail,
            category: {
                id: booking.category.id,
                categoryName: booking.category.categoryName,
                description: booking.category.description,
                duration: booking.category.duration
            },
            startTime:`${booking.Date}T${booking.Time}:00Z`,
            bookingNote: booking.bookingNote  
        })
    })
    if(res.status===201){
        const newbooking=await res.json()
        newbooking.startTime=moment(newbooking.startTime).utcOffset(0).format(DateFormat)
        getListBooking.value.push(newbooking)
    }
}
const deleteBooking= async (booking)=>{
    if(confirm("Do you want cancel this Booking ?")){
        const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${booking.id}`, {
            method: 'DELETE'
        })
        if(res.status===200){
            getListBooking.value =getListBooking.value.filter(b => b!==booking)
        }
    }
}
const saveBooking=(updateBooking)=>{
    getListBooking.value=getListBooking.value.map((booking)=>{
        return booking.id===updateBooking.id ? {...booking,updateBooking}:booking
    })
}
</script>
 
<template>
<h1>OASIP TEST</h1>
<div>
    <h2>booking List</h2>
    <Create @add="createBooking" :getCategories="getListCategories" :LastID="getListBooking.length"/>
    <List @save="saveBooking" @delete="deleteBooking" :getListBooking="getListBooking"/>
</div>
</template>
 
<style scoped>

</style>
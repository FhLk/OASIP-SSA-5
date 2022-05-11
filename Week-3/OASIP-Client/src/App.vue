<script setup>
import {onMounted,onBeforeMount,ref, onBeforeUpdate, onUpdated, computed} from 'vue';
import moment from "moment"
import List from './components/List.vue'
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
        data.startTime=ShowDateTime(data.startTime)
    })
    SortByDateTime()
}

const SortByDateTime=()=>{
    getListBooking.value.sort((a,b)=>{
        return new Date(b.startTime) - new Date(a.startTime)
    })
}

const ShowDateTime=(datetime)=>{
    return moment(datetime).utcOffset(0).format(DateFormat)
}

onMounted(async ()=>{
    await getBookings()
    await getCategories()
})

const createBooking= async (booking)=>{
    const Auto_Increment =(increment)=>{
        getListBooking.value.forEach((data)=>{
            if(increment===data.id){
                increment=data.id+1
            }
            else{
                increment=getListBooking.value.length+1
            }
        })
        return increment
    }
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'POST',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id: Auto_Increment(getListBooking.value.length+1),
            bookingName: booking.bookingName,
            bookingEmail: booking.bookingEmail,
            category: {
                id: booking.category.id,
                categoryName: booking.category.categoryName,
                description: booking.category.description,
                duration: booking.category.duration
            },
            startTime:`${booking.Date}T${booking.Time}:00Z`,
            eventNote: booking.eventNote  
        })
    })
    if(res.status===201){
        const newbooking=await res.json()
        newbooking.startTime=ShowDateTime(newbooking.startTime)
        getListBooking.value.push(newbooking)
        SortByDateTime()        
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
const saveBooking= async (updateBooking)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${updateBooking.id}`,{
        method: 'PUT',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:updateBooking.id,
            bookingName: updateBooking.bookingName,
            bookingEmail: updateBooking.bookingEmail,
            category: updateBooking.category,
            startTime: updateBooking.startTime,
            eventNote: updateBooking.eventNote 
        })
    })
    if(res.status===200){
        updateBooking.startTime=ShowDateTime(updateBooking.startTime)
        getListBooking.value=getListBooking.value.map((booking)=>{
            return booking.id===updateBooking.id ? {...booking,updateBooking}:booking
        })
        SortByDateTime()
    }
}
</script>
 
<template>
<h1>OASIP TEST</h1>
<div>
    <h2>booking List</h2>
    <Create @add="createBooking" :getCategories="getListCategories" :getListBooking="getListBooking"/>
    <List @save="saveBooking" @delete="deleteBooking" :getListBooking="getListBooking"/>
</div>
</template>
 
<style scoped>

</style>

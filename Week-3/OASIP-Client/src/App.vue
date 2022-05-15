<script setup>
import { onMounted, onBeforeMount, ref, onBeforeUpdate, onUpdated, computed } from 'vue';
import moment from "moment"
import List from "./components/List.vue"
import Create from './components/Create.vue';
import indexPage from './components/indexPage.vue' ;

let DateFormat = "YYYY-MM-DD HH:mm"
const getListBooking = ref([])
const getListCategories = ref([]);

const getCategories = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/categories`, {
        method: 'GET'
    })
    getListCategories.value = await res.json()
}

const getBookings = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`, {
        method: 'GET'
    })
    getListBooking.value = await res.json()
    getListBooking.value.forEach((data) => {
        data.startTime = moment(data.startTime).utcOffset(0).format(DateFormat)
    })
    getListBooking.value.sort((a, b) => {
        return new Date(b.startTime) - new Date(a.startTime)
    })
}

onBeforeMount(async () => {
    await getBookings()
    await getCategories()
})

const createBooking = async (booking) => {
    const Auto_Increment = (increment) => {
        getListBooking.value.forEach((data) => {
            if (increment === data.id) {
                increment = data.id + 1
            }
            else {
                increment = getListBooking.value.length + 1
            }
        })
        return increment
    }
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id: Auto_Increment(getListBooking.value.length + 1),
            bookingName: booking.bookingName + ` (${booking.group})`,
            bookingEmail: booking.bookingEmail,
            category: {
                id: booking.category.id,
                categoryName: booking.category.categoryName,
                description: booking.category.description,
                duration: booking.category.duration
            },
            startTime: `${booking.Date}T${booking.Time}:00Z`,
            eventNote: booking.eventNote
        })
    })
    if (res.status === 201) {
        await getBookings()
    }
}
const deleteBooking = async (booking) => {
    if (confirm("Do you want cancel this Booking ?")) {
        const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${booking.id}`, {
            method: 'DELETE'
        })
        if (res.status === 200) {
            getListBooking.value = getListBooking.value.filter(b => b !== booking)
        }
    }
}
const saveBooking = (updateBooking) => {
    getListBooking.value = getListBooking.value.map((booking) => {
        return booking.id === updateBooking.id ? { ...booking, updateBooking } : booking
    })
    location.reload()
}
</script>
 
<template>
    <div class="bg h-screen font">
        <h1 class="text-7xl text-white pl-10 pt-2 pb-2 mb-4 oa">OASIP</h1>
            <div class="scd rounded-lg w-48 ml-10 p-2 flex-none">
                <h2 class="text-4xl text-white flex justify-center">Schedule</h2>
            </div>
            <div class="bg">
                <Create @add="createBooking" :getCategories="getListCategories" :getListBooking="getListBooking" />
            </div>
            <div class="bg"> 
                <indexPage/> 
            </div>
        <div class="bg">
            <List @save="saveBooking" @delete="deleteBooking" :getListBooking="getListBooking" />
        </div>
    </div>
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Titan+One&display=swap');

.bg {
    background-color: rgb(25, 25, 112);
}

.font {
    font-family: 'Titan One', cursive;
}

.oa {
    background-color: rgb(66, 155, 219);
}

.scd {
    background-color: rgb(86, 165, 236);
}
</style>
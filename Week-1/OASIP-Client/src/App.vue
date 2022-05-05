<script setup>
import {onMounted,onBeforeMount,ref, onBeforeUpdate} from 'vue';
import moment from "moment"
const isBooking=ref(false)
const isDetail=ref(-1)
let DateFormat="YYYY-MM-DD HH:mm A"
const getListBooking=ref([]);
const getBooking=ref({});
const categories=ref([]);
const booking=ref({
    bookingName: "",
    bookingEmail: "",
    category: {
        id: 0,
        categoryName: "",
        description: "",
        duration: 0
    },
    startTime: "",
    eventNote: ""
});
const group=ref("")
const bookingDate=ref("");
const bookingTime=ref("")
const resetBooking=()=>{
    booking.value={
    id: 0,
    bookingName: "",
    bookingEmail: "",
    category: {
        id: 0,
        categoryName: "",
        description: "",
        duration: 0
    },
    startTime: "",
    eventNote: ""
};
}
const showDetail = async (id)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${id}`,{
        method: 'GET'
    })
    getBooking.value=await res.json()
    getBooking.value.startTime=moment(getBooking.value.startTime).utcOffset(0).format(DateFormat)
    isDetail.value= isDetail.value===id ? -1:id
}

const getBookings= async (startTime="startTime")=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings?startTime=${startTime}`,{
        method: 'GET'
    })
    getListBooking.value=await res.json()
    getListBooking.value.forEach((data)=>{
        data.startTime=moment(data.startTime).utcOffset(0).format(DateFormat)
    })
}


const getCategories= async () =>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/categories`,{
        method: 'GET'
    })
    categories.value=await res.json()
}

onMounted(async ()=>{
    await getBookings()
    await getCategories()
})

const Add= async (event)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'POST',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:getListBooking.value.length+1,
            bookingName: event.bookingName + ` (${group.value})`,
            bookingEmail: event.bookingEmail,
            category: {
                id:event.category.id,
                categoryName: event.category.categoryName,
                description: event.category.description,
                duration: event.category.duration 
                },
            startTime:`${bookingDate.value}T${bookingTime.value}:00Z`,
            eventNote: event.eventNote  
        })
    })
    const newBooking =await res.json()
    getListBooking.value.push(newBooking)
    resetBooking()
    isBooking.value=false
}

const isEditBooking=ref(false);
const EditEvent=(event,index)=>{
    isBooking.value=false;
    isEditBooking.value= isEditBooking.value ? false:true;
}
</script>
 
<template>
<h1>OASIP TEST</h1>
<div>
    <h2>Event List</h2>
    <div>
        <button @click="isBooking= isBooking ? false:true">Booking</button>
        <div v-if="isBooking">
            <p>Full Name: <input type="text" placeholder="Name..." v-model="booking.bookingName"></p>
            <p>Group: <input type="text" placeholder="Group" v-model="group"/></p>
            <p>E-mail: <input type="email" placeholder="E-mail..." v-model="booking.bookingEmail"></p>
            <p>Category: 
                <ul v-for="(category,index) in categories" :key="index">
                    <input type="radio" :id="index" :value="category" v-model="booking.category">
                    - <label :for="index">{{category.categoryName}}</label>
                </ul>
                <label>Date </label>: 
                <input type="date" v-model="bookingDate">
                <br/>
                <label> Start (Time) </label>: 
                <input type="time" v-model="bookingTime">
                <br/>
                <label>Duration (Minute): {{booking.category.duration}}</label>
                <br/>
                <label>Note: </label>
                <textarea rows="5" cols="50" v-model="booking.eventNote"></textarea>
                <div> 
                    <button @click="Add(booking)">OK</button>
                    <button @click="cancle">Cancle</button>
                </div>
            </p>
        </div>
    </div>
    <div v-if="getListBooking
.length!==0">
        <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p>
    <ul>
        <li v-for="(data,index) in getListBooking" :key="index">{{data.startTime}}
            ({{data.category.duration}} min.) {{data.category.categoryName.toLocaleUpperCase()}}
            {{data.bookingName}}
            <div>
            <button @click="showDetail(data.id)">{{isDetail===index ? "Closed":"Detail"}}</button>
            <button>Delete</button>
            <div v-if="isDetail===data.id">
                <p>Name: {{getBooking.bookingName}}</p>
                <p>E-mail: {{getBooking.bookingEmail}}</p>
                <p>Category: {{getBooking.category.categoryName}}</p>
                <p>Date & Time: {{getBooking.startTime}}</p>
                <p>Duration: {{getBooking.category.duration}} min.</p>
                <p>Start Time: {{getBooking.startTime.slice(10)}}</p>
                <p>Note: {{getBooking.eventNote}}</p>
                <button @click="EditEvent(data,index)">Edit</button>
            </div>
            </div>
            <br/>
        </li>
    </ul>
    </div> 
    <div v-else>
        <h2>No Scheduled Events.</h2>
    </div>
</div>
</template>
 
<style scoped>

</style>

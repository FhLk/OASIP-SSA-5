<script setup>
import {onMounted,ref} from 'vue';
const isBooking=ref(false)
const isDetail=ref(-1)
const categories=ref([
    {name:"front-end",duration:60},
    {name:"back-end",duration:30},
    {name:"business",duration:30},
    {name:"project-mange",duration:60},
    {name:"devops",duration:60},
    {name:"database",duration:60},
])
const booking=ref({name:"",email:"",group:"",date:"",startTime:"" ,category:{name:"",duration:0},note:""});
const resetBooking=()=>{
    booking.value={name:"",email:"",group:"",date:"",startTime:"" ,category:{name:"",duration:0},note:""};
}
const listdata=ref([]);
const getBookings= async ()=>{
    const res=await fetch('http://localhost:5000/Events',{
        method: 'GET'
    })
    listdata.value=await res.json()
}

onMounted(async ()=>{
    await getBookings()
})


const Add=(event)=>{
    listdata.value.push(event)
    resetBooking()
    isBooking.value=false
}
</script>
 
<template>
<h1>OASIP TEST</h1>
<div>
    <h2>Event List</h2>
    <div>
        <button @click="isBooking= isBooking ? false:true">Booking</button>
        <div v-show="isBooking">
            <p>Full Name: <input type="text" placeholder="Name..." v-model="booking.name"></p>
            <p>E-mail: <input type="email" placeholder="E-mail..." v-model="booking.email"></p>
            <p>Group: <input type="text" placeholder="Group" v-model="booking.group"/></p>
            <p>Category: 
                <ul v-for="(name,index) in categories" :key="index">
                    <input type="radio" :id="name.name" :value="name" v-model="booking.category">
                    - <label :for="name.name">{{name.name.toLocaleUpperCase()}}</label>
                </ul>
                <label>Date </label>: 
                <input type="date" v-model="booking.date">
                <br/>
                <label> Start (Time) </label>: 
                <input type="time" v-model="booking.startTime">
                <br/>
                <label>Duration (Minute): {{booking.category.duration}}</label>
                <br/>
                <label>Note: </label>
                <textarea rows="5" cols="50" v-model="booking.note"></textarea>
                <div> 
                    <button @click="Add(booking)" :disabled="
                    booking.name==='' || booking.email==='' || !booking.email.includes('@') || booking.date===''
                    || booking.startTime==='' || booking.category.name===''"
                    >OK</button>
                    <button @click="cancle">Cancle</button>
                </div>
            </p>
        </div>
    </div>
    <div v-if="listdata.length!==0">
        <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p>
    <ul>
        <li v-for="(data,index) in listdata" :key="index">{{data.date}}
            {{data.startTime}} ({{data.category.duration}} min.) {{data.category.name.toLocaleUpperCase()}} Clinic
            {{data.name}} ({{data.group}})
            <div>
            <button @click="isDetail= isDetail===index ? -1:index">{{isDetail===index ? "Closed":"Detail"}}</button>
            <button>Delete</button>
            <div v-show="isDetail===index">
                <p>Name: {{data.name}}</p>
                <p>E-mail: {{data.email}}</p>
                <p>Category: {{data.category.name.toLocaleUpperCase()}}</p>
                <p>Start Time: {{data.startTime}} PM.</p>
                <p>Duration: {{data.category.duration}} min.</p>
                <p>Date & Time: {{data.date}} {{data.startTime}}</p>
                <p>Note: {{data.note}}</p>
                <button>Edit</button>
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
<script setup>
import {ref} from 'vue';
const category=ref({name:"",duration:0});
const categories=ref([
    {name:"front-end",duration:60},
    {name:"back-end",duration:30},
    {name:"business",duration:30},
    {name:"project-mange",duration:60},
    {name:"devops",duration:60},
    {name:"database",duration:60},
])
const booking=ref({name:"",email:"",date:null,startTime:null ,category:category.value,note:""});
const listdata=ref([
    {
        date:new Date("2022-4-22"),
        startTime:"13:00",
        duration: 60,
        category:"Front-End Clinic",
        booking:{name:"Paramat",email:"aaa@hotmail.com"},
        note:"Wow"
    },
    {
        date:new Date("2022,5,12"),
        startTime:"10:30",
        duration:30,
        category:"Back-End Clinic",
        booking:{name:"Michel",email:"bbb@hotmail.com"},
        note:"wow"
    },
    {
        date:new Date("2022,5,15"),
        startTime:"09:00",
        duration:30,
        category:"Business Clinic",
        booking:{name:"Jhon",email:"ccc@hotmail.com"},
        note:"Wow"
    },
    {
        date:new Date("2022,5,19"),
        startTime:"10:00",
        duration:60,
        category:"Project-Manage Clinic",
        booking:{name:"Bob",email:"ddd@hotmail.com"},
        note:"Wow"
    },
    {
        date:new Date("2022,5,22"),
        startTime:"13:00",
        duration:60,
        category:"Database Clinic",
        booking:{name:"Alice",email:"eee@hotmail.com"},
        note:"Wow"
    },
    {
        date:new Date("2022,5,30"),
        startTime:"15:00",
        duration:60,
        category:"DevOps Clinic",
        booking:{name:"Angle",email:"fff@hotmail.com"},
        note:"Wow"
    },
]);

const Test=()=>{
    console.log(booking.value.date)
    console.log(booking.value.startTime)
}

</script>
 
<template>
<h1>OASIP TEST</h1>
<div>
    <h2>Event List</h2>
    <div>
        <button>Add Event</button>
        <div>
            <p>Full Name: <input type="text" placeholder="Name..." v-model="booking.name"></p>
            <p>E-mail: <input type="email" placeholder="E-mail..." v-model="booking.email"></p>
            <p>Category: 
            <!-- <form> -->
                <ul v-for="(name,index) in categories" :key="index">
                    <input type="radio" :id="name.name" :value="name" v-model="category">
                    - <label :for="name.name">{{name.name.toLocaleUpperCase()}}</label>
                </ul>
                <label>Date </label>: 
                <input type="date" v-model="booking.date">
                <br/>
                <label> Start (Time) </label>: 
                <input type="time" v-model="booking.startTime">
                <br/>
                <label>Duration (Minute): {{category.duration}}</label>
                <br/>
                <label>Note: </label>
                <textarea rows="5" cols="50" v-model="booking.note"></textarea>
                <div> 
                    <button @click="Test">Sumit</button>
                    <button @click="Test">Cancle</button>
                </div>
            <!-- </form> -->
            </p>
        </div>
    </div>
    <div>
        <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p>
    <ul>
        <li v-for="(data,index) in listdata" :key="index">{{data.date.getMonth()}}/{{data.date.getDate()}}/{{data.date.getFullYear()}}
            {{data.startTime}} ({{data.duration}} min.) {{data.category}}
            {{data.booking.name}}
            <div>
            <button>Detail</button>
            <button>Delete</button>
            <div>
                <p>Name: {{data.booking.name}}</p>
                <p>E-mail: {{data.booking.email}}</p>
                <p>Category: {{data.category}}</p>
                <p>Start Time: {{data.startTime}} PM.</p>
                <p>Duration: {{data.duration}} min.</p>
                <p>Date & Time: {{data.date.getMonth()}}/{{data.date.getDate()}}/{{data.date.getFullYear()}} {{data.startTime}} PM.</p>
                <p>Note: {{data.note}}</p>
                <button>Edit</button>
            </div>
            </div>
            <br/>
        </li>
    </ul>
    </div>
</div>
</template>
 
<style scoped>

</style>
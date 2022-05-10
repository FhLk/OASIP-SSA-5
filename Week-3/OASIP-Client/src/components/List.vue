<script setup>
import { ref } from 'vue';
import moment from "moment"
import { computed } from '@vue/reactivity';

defineEmits(['delete','save'])

let DateFormat="YYYY-MM-DD HH:mm"
const props=defineProps({
  getListBooking: {
    type: Array,
    require: true
  }
})

const getBooking=ref({});
const isDetail=ref(-1)
const isEdit=ref(false)

let count=0
const showDetail = async (id)=>{
    if(id!==count){
        const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${id}`,{
            method: 'GET'
        })
        getBooking.value=await res.json()
        getBooking.value.startTime=moment(getBooking.value.startTime).utcOffset(0).format(DateFormat)
        count=id
    }
    isDetail.value= isDetail.value===id ? -1:id
    isEdit.value=false
}


const EditNote=ref("")
const EditDate=ref("")
const EditTime=ref("")
const EditDateTime=ref("")
const EditEvent=(booking)=>{
    isEdit.value= isEdit.value ? false : true
    if(isEdit.value){
        EditNote.value=booking.eventNote
        EditDateTime.value=booking.startTime
        EditDate.value=EditDateTime.value.slice(0,10)
        EditTime.value=EditDateTime.value.slice(10).trim()
    }
    else{
        EditNote.value=getBooking.value.eventNote
        EditDateTime.value=getBooking.value.startTime
        EditDate.value=""
        EditTime.value=""
    }
}


const savebooking= async (booking)=>{
    if(confirm("You Have Edited Your Event.")){
        const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${booking.id}`,{
            method: 'PUT',
            headers:{
                'content-type': 'application/json'
            },
            body: JSON.stringify({
                id:booking.id,
                bookingName: booking.bookingName,
                bookingEmail: booking.bookingEmail,
                category: booking.category,
                startTime: `${EditDate.value}T${EditTime.value}:00Z`,
                eventNote: EditNote.value  
            })
        })
        if(res.status===200){
            const updateBooking = await res.json()
            isEdit.value=false
            return updateBooking
        }
        else{
            return booking
        }
    }
}

</script>
 
<template>
<div class="bg pt-3 rounded-md bgde mx-10 mb-4 pb-3 tde">
    <div v-if="getListBooking.length!==0" class="mx-5">
        <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p>
    <ul>
        <li v-for="(data,index) in getListBooking" :key="index">{{data.startTime}}
            ({{data.category.duration}} min.) {{data.category.categoryName.toLocaleUpperCase()}}
            {{data.bookingName}}
            <div>
            <button @click="showDetail(data.id)">{{isDetail===data.id ? "Closed":"Detail"}}</button>
            <button @click="$emit('delete',data)">Cancle</button>
            <div v-if="isDetail===data.id">
                <p>Name: {{getBooking.bookingName}}</p>
                <p>E-mail: {{getBooking.bookingEmail}}</p>
                <p>Category: {{getBooking.category.categoryName}}</p>
                <p>Date & Time: 
                    <span v-if="isEdit===false">{{getBooking.startTime}}</span>
                    <span v-else>
                        <input type="date" v-model="EditDate"/> |
                        <input type="time" v-model="EditTime"/>
                    </span>
                </p>
                <p>Duration: {{getBooking.category.duration}} min.</p>
                <p>Note: 
                    <span v-if="isEdit===false">{{getBooking.eventNote}}</span>
                    <span v-else>
                        <textarea rows="5" cols="50" v-model="EditNote"></textarea>
                    </span>
                </p>
                <button @click="$emit('save',savebooking(data))" v-if="isEdit">Save</button>
                <button @click="EditEvent(data)">{{isEdit ? "Cancel":"Edit"}}</button>
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
@import url('https://fonts.googleapis.com/css2?family=Titan+One&display=swap');

.bgm {
    background-color: rgb(254, 252, 255);
}

.bg {
    background-color: rgb(25, 25, 112);
}

.font {
    font-family: 'Titan One', cursive;
}

.dl {
    background-color: rgb(215, 45, 45);
}

.scd {
    background-color: rgb(86, 165, 236);
}

.ins {
    background-color: rgb(130, 202, 255);
}

.oa {
    background-color: rgb(66, 155, 219);
}

.list {
    background-color: rgb(92, 179, 255);
}

.dl {
    background-color: rgb(0, 0, 165);
}

.bgde {
    background-color: rgb(254, 252, 255);
}

.tde {
    color: rgb(12, 9, 10);
}

.tins {
    color: rgb(255, 255, 247);
}

.bgis {
    background-color: rgb(255, 250, 250);
}

.w {
    width: 120px;
}

.h {
    height: 25px;
}

.bgmm {
    background-color: rgb(0, 0, 128);
}

</style>
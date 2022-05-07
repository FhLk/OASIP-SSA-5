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

const getBooking=ref();
const isDetail=ref(-1)

const showDetail = async (id)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${id}`,{
        method: 'GET'
    })
    getBooking.value=await res.json()
    getBooking.value.startTime=moment(getBooking.value.startTime).utcOffset(0).format(DateFormat)
    isDetail.value= isDetail.value===id ? -1:id
}

const isEdit=ref(false)
const EditNote=ref("")
const EditDateTime=ref("")
const EditEvent=(booking)=>{
    isEdit.value= isEdit.value ? false : true
    EditNote.value=booking.eventNote
    EditDateTime.value=booking.startTime
}

const savebooking= async (booking)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'PUT',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:booking.id,
            bookingName: booking.bookingName + ` (${booking.group})`,
            bookingEmail: booking.bookingEmail,
            category: booking.category,
            startTime:EditDateTime.value,
            bookingNote: EditNote.value  
        })
    })
    if(res.status===200){
        const updateBooking = await res.json()
        return updateBooking
    }
    else{
        return booking
    }
}

</script>
 
<template>
<div>
    <div v-if="getListBooking.length!==0">
        <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p>
    <ul>
        <li v-for="(data,index) in getListBooking" :key="index">{{data.startTime}}
            ({{data.category.duration}} min.) {{data.category.categoryName.toLocaleUpperCase()}}
            {{data.bookingName}}
            <div>
            <button @click="showDetail(data.id)">{{isDetail===index ? "Closed":"Detail"}}</button>
            <button @click="$emit('delete',data)">Cancle</button>
            <div v-if="isDetail===data.id">
                <p>Name: {{getBooking.bookingName}}</p>
                <p>E-mail: {{getBooking.bookingEmail}}</p>
                <p>Category: {{getBooking.category.categoryName}}</p>
                <p>Date & Time: 
                    <span v-if="isEdit===false">{{getBooking.startTime}}</span>
                    <span v-else>
                        <input type="datetime-local" v-model="EditDateTime"/> ({{getBooking.startTime}}) 
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

</style>
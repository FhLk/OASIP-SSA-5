<script setup>
import { onBeforeMount, ref } from 'vue';
const isBooking=ref(false)

defineEmits(['add'])

const props=defineProps({
  getCategories: {
    type: Array,
    require: true
  },
  LastID:{
      type:Number,
      require:true
  }
})

const booking=ref({
    bookingName: "",
    bookingEmail: "",
    category: {},
    startTime: "",
    eventNote: ""
});
const group=ref("")
const bookingDate=ref("");
const bookingTime=ref("")

const create=()=>{
    booking.value.group=group.value
    booking.value.Date=bookingDate.value
    booking.value.Time=bookingTime.value
    return booking.value
}

</script>
 
<template>
<div>
    <div>
        <button @click="isBooking= isBooking ? false:true">Booking</button>
        <div v-if="isBooking">
            <p>Full Name: <input type="text" placeholder="Name..." v-model="booking.bookingName"></p>
            <p>Group: <input type="text" placeholder="Group" v-model="group"/></p>
            <p>E-mail: <input type="email" placeholder="E-mail..." v-model="booking.bookingEmail"></p>
            <p>Category: 
                <ul v-for="(category,index) in getCategories " :key="index">
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
                    <button @click="$emit('add',create())">OK</button>
                    <button @click="cancle">Cancle</button>
                </div>
            </p>
        </div>
    </div>
</div>
</template>
 
<style scoped>

</style>
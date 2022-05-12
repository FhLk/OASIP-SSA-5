<script setup>
import moment from 'moment';
import { computed, onBeforeMount, ref } from 'vue';
const isBooking=ref(false)

defineEmits(['add'])

const props=defineProps({
  getCategories: {
    type: Array,
    require: true
  },
  getListBooking:{
      type: Array,
      require:true
  }
})

const newbooking=ref({
    bookingName: "",
    bookingEmail: "",
    Date:"",
    Time:"",
    category: {},
    bookingDuration: 0,
    eventNote: "",
});

const reset=()=>{
    isBooking.value=false
    newbooking.value={
        bookingName: "",
        bookingEmail: "",
        Date:"",
        Time:"",
        category: {},
        bookingDuration: 0,
        eventNote: "",
    }
}
// const countName=computed(()=>{
//     return 100-newbooking.value.bookingName.length
// })
// const countNote=computed(()=>{
//     return 500-newbooking.value.eventNote.length
// })
// const checkInput =()=>{

// }

const test=()=>{
    console.log(moment(newbooking.value.Date))
    console.log(moment(newbooking.value.Time))
}

</script>
 
<template>
<div>
    <div>
        <button @click="isBooking= isBooking ? false:true">Booking</button>
        <div v-if="isBooking">
            <p>Full Name: <input type="text" placeholder="Name..." v-model="newbooking.bookingName"></p>
            <!-- <p>(Number of Charecter: {{countName}})</p> -->
            <p>E-mail: <input type="email" placeholder="E-mail..." v-model="newbooking.bookingEmail"></p>
            <p>Category: 
                <ul v-for="(category,index) in getCategories " :key="index">
                    <input type="radio" :id="index" :value="category" v-model="newbooking.category">
                    - <label :for="index">{{category.categoryName}}</label>
                </ul>
                <label>Date </label>: 
                <input type="date" v-model="newbooking.Date">
                <br/>
                <label> Start (Time) </label>: 
                <input type="time" v-model="newbooking.Time">
                <br/>
                <label>Duration (Minute): {{newbooking.bookingDuration=newbooking.category.duration}}</label>
                <br/>
                <label>Note: </label>
                <textarea rows="5" cols="50" v-model="newbooking.eventNote"></textarea>
                <!-- <p>(Number of Charecter: {{countNote}})</p> -->
                <div> 
                    <button @click="$emit('add',newbooking),reset()">OK</button>
                    <button @click="reset">Cancle</button>
                </div>
            </p>
        </div>
    </div>
</div>
</template>
 
<style scoped>

</style>
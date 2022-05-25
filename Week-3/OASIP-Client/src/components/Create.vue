<script setup>
import moment from 'moment';
import { computed, onBeforeMount, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
const isBooking = ref(false)
let mailFormat1=/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
let mailFormat2=/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
let mailFormat3=/^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
let mailFormat4=/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
let mailFormat5=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/

const isNameEmpty=ref(false);
const isEmailEmpty=ref(false);
const isCategoryEmpty=ref(false);
const isDateEmpty=ref(false);
const isTimeEmpty=ref(false);
const isEmailNotFormat=ref(false)
const isDatePast=ref(false)

let DateFormat = "YYYY-MM-DD HH:mm"

const emits=defineEmits(['add'])

const props = defineProps({
    getCategories: {
        type: Array,
        require: true
    }
})

const newbooking = ref({
    bookingName: "",
    bookingEmail: "",
    Date: "",
    Time: "",
    category: {},
    eventNote: "",
    bookingDuration:0
});

const reset = () => {
    isBooking.value = false
    newbooking.value = {
        bookingName: "",
        bookingEmail: "",
        Date: "",
        Time: "",
        category: {},
        eventNote: "",
        bookingDuration:0
    }
    GoHome()
}

const CheckInput= async (booking)=>{
    let isCheck=true
    if(booking.bookingName===""){
        isCheck=false
        isNameEmpty.value=true
    }
    if(booking.bookingEmail===""){
        isCheck=false
        isEmailEmpty.value=true
    }
    else if(!booking.bookingEmail.match(mailFormat5)){
        if(!booking.bookingEmail.match(mailFormat4)){
            if(!booking.bookingEmail.match(mailFormat3)){
                if(!booking.bookingEmail.match(mailFormat2)){
                    if(!booking.bookingEmail.match(mailFormat1)){
                        isCheck=false
                        isEmailNotFormat.value=true
                        isEmailEmpty.value=false
                    }
                }
                isCheck=false
                isEmailNotFormat.value=true
                isEmailEmpty.value=false
            }
            isCheck=false
            isEmailNotFormat.value=true
            isEmailEmpty.value=false
        }
        isCheck=false
        isEmailNotFormat.value=true
        isEmailEmpty.value=false
    }
    else if(booking.bookingEmail.match(mailFormat5)){
        if(booking.bookingEmail.match(mailFormat4)){
            if(booking.bookingEmail.match(mailFormat3)){
                if(booking.bookingEmail.match(mailFormat2)){
                    if(booking.bookingEmail.match(mailFormat1)){
                        isEmailNotFormat.value=false
                        isEmailEmpty.value=false
                    }
                }
                isEmailNotFormat.value=false
                isEmailEmpty.value=false
            }
            isEmailNotFormat.value=false
            isEmailEmpty.value=false
        }
        isEmailNotFormat.value=false
        isEmailEmpty.value=false
    }
    if(Object.keys(booking.category).length===0){
        isCheck=false
        isCategoryEmpty.value=true
    }
    if(booking.Date===""){
        isCheck=false
        isDateEmpty.value=true
    }
    else if(booking.Date!==""){
        isDateEmpty.value=false
        isTimeEmpty.value=true
        let getDate=moment(`${booking.Date} ${booking.Time}`).local().format(DateFormat);
        let now=moment().local().format(DateFormat)
        if(getDate <= now){
            isDateEmpty.value=false
            isDatePast.value=true
            isCheck=false
        }
        else{
            isDatePast.value=false
        }
    }
    if(booking.Time===""){
        isCheck=false
        isTimeEmpty.value=true
    }
    if(isCheck){
        isEmailEmpty.value=false
        isDatePast.value=false
        isEmailNotFormat.value=false
        isNameEmpty.value=false
        isCategoryEmpty.value=false
        isDateEmpty.value=false
        isTimeEmpty.value=false
        if(confirm("Are You sure ?")){
            await createBooking(booking)
            reset()
        }
    }
}

const myRouter=useRouter()
const GoHome =()=>{
    myRouter.push({name:'ShowPage'})
}

const createBooking= async (booking)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'POST',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:0,
            bookingName: booking.bookingName.trim(),
            bookingEmail: booking.bookingEmail.trim(),
            category: {
                id:booking.category.id,
                categoryName: booking.category.categoryName
                },
            startTime:`${booking.Date}T${booking.Time}`,
            bookingDuration:booking.bookingDuration,
            eventNote: booking.eventNote.trim()
        })
    })
        if(res.status===201){
        alert("You have a new Booking")
    }
}

const countName=computed(()=>{
    return 100-newbooking.value.bookingName.length
})

const countNote=computed(()=>{
    return 500-newbooking.value.eventNote.length
})

const countEmail=computed(()=>{
    return 100-newbooking.value.bookingEmail.length
})

</script>
 
<template>
    <div class="font ccf text-lg mt-28">
        <div>
            <div class="flex justify-center">
                <div class="bgc px-10 py-3 my-4 rounded-lg" >
                    <div class="mr-2 mt-2">
                        <p>Full Name: <input type="text" placeholder="Name..." v-model="newbooking.bookingName" maxlength="100"></p>
                        <p class="text-sm text-stone-500">(Number of Character : {{countName}})</p>
                        <p v-if="isNameEmpty && countName===100" class="text-xs text-red-600">Plase Input your name</p>
                    </div>
                    <div class="mr-2 mt-1">
                        <p>E-mail: <input type="email" placeholder="example@example.com" v-model="newbooking.bookingEmail" maxlength="100"></p>
                        <p class="text-sm text-stone-500">(Number of Character : {{countEmail}})</p>
                        <p v-if="isEmailEmpty && countEmail===100" class="text-xs text-red-600">Plase Input your e-mail</p>
                        <p v-else-if="isEmailNotFormat" class="text-xs text-red-600">Your Email address is not follow format</p>
                    </div>
                    <p class="mr-2 mt-1">Category:
                    <ul v-for="(category, index) in getCategories " :key="index">
                        <input type="radio" :id="index" :value="category" v-model="newbooking.category">
                        - <label :for="index">{{ category.categoryName }}</label>
                    </ul>
                    <p v-if="isCategoryEmpty && Object.keys(newbooking.category).length===0" class="text-xs text-red-600">Plase select category</p>
                   <div class="mt-1">
                    <label >Date: </label>
                    <input type="date" v-model="newbooking.Date" :min="new Date().toISOString().split('T')[0]" >
                    <p v-if="isDateEmpty && newbooking.Date===''" class="text-xs text-red-600">Plase Input your date.</p>
                    <p v-else-if="isDatePast" class="text-xs text-red-600">Can't choose Date in Past or Present</p>
                   </div>
                   <div class="mt-1">
                    <label> Start (Time): </label>
                    <input type="time" v-model="newbooking.Time">
                    <p v-if="isTimeEmpty && newbooking.Time===''" class="text-xs text-red-600">Plase Input your time</p>
                   </div>
                   <div class="mt-1"> 
                    <label class="mr-2 mt-5">Duration (Minute): {{  newbooking.bookingDuration= newbooking.category.duration===undefined ? 0:newbooking.category.duration }}</label>
                   </div>
                   <div class="mt-1">
                       <p class="mr-2 mt-2">Note: </p>
                    <textarea rows="5" cols="50" v-model="newbooking.eventNote" maxlength="500"></textarea>
                    <p class="text-sm text-stone-500">(Number of Character : {{countNote}})</p>
                   </div>
                    <div>
                        <button @click="CheckInput(newbooking)" class="bg-green-600 rounded-full px-2 text-white mx-1 hover:bg-[#4ADE80]">OK</button>
                        <button @click="reset" class="bg-red-600 rounded-full px-2 text-white mx-1 hover:bg-[#F87171]">Cancle</button>
                    </div>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');
.font{
    font-family: 'Mitr', sans-serif;
}
.bgc{
    background-color: rgb(132, 212, 255);
}
.ccf {
    color: rgb(42, 39, 40);
}
</style>
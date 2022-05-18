<script setup>
import { computed, onBeforeMount, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
const isBooking = ref(false)

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

let mailFormat=/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
const isNameEmpty=ref(false);
const isEmailEmpty=ref(false);
const isCategoryEmpty=ref(false);
const isDateEmpty=ref(false);
const isTimeEmpty=ref(false);

const CheckInput=async (booking)=>{
    let isCheck=true
    if(!booking.bookingEmail.match(mailFormat) || booking.bookingEmail.length > 100){
        isCheck=false
        isEmailEmpty.value=true
    }
    if(booking.bookingName==="" || booking.bookingName.length > 100){
        isCheck=false
        isNameEmpty.value=true
    }
    if(Object.keys(booking.category).length===0){
        isCheck=false
        isCategoryEmpty.value=true
    }
    if(booking.Date===""){
        isCheck=false
        isDateEmpty.value=true
    }
    if(booking.Time===""){
        isCheck=false
        isTimeEmpty.value=true
    }
    if(booking.eventNote.length > 500){
        isCheck=false
    }
    if(isCheck){
        await createBooking(booking)
        reset()
    }
}

const myRouter=useRouter()
const GoHome =()=>{
    myRouter.push({name:'indexPage'})
}

const createBooking= async (booking)=>{
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'POST',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:0,
            bookingName: booking.bookingName,
            bookingEmail: booking.bookingEmail,
            category: booking.category,
            startTime:`${booking.Date}T${booking.Time}`,
            bookingDuration:booking.bookingDuration,
            eventNote: booking.eventNote  
        })
    })
    if(res.status===201){
        alert("success")
        // const newbooking=await res.json()
    }
}

const countName=computed(()=>{
    return 100-newbooking.value.bookingName.length
})

const checkNote=computed(()=>{
    return 500-newbooking.value.eventNote.length
})


</script>
 
<template>
    <div class="font ccf text-lg">
        <div>
            <div class="flex justify-center">
                <div class="bgc px-10 py-3 my-4 rounded-lg" >
                    <div class="mr-2 mt-2">
                        <p>Full Name: <input type="text" placeholder="Name..." v-model="newbooking.bookingName" maxlength="100"></p>
                        <p v-if="isNameEmpty" class="text-xs text-red-600">Plase Input your name !!!!!</p>
                        <p class="text-sm text-stone-500">(Number of Character : {{countName}})</p>
                    </div>
                    <div class="mr-2 mt-1">
                        <p>E-mail: <input type="email" placeholder="example@example.com" v-model="newbooking.bookingEmail" maxlength="100"></p>
                        <p v-if="isEmailEmpty" class="text-xs text-red-600">Plase Input your e-mail !!!!!</p>
                    </div>
                    <p class="mr-2 mt-1">Category:
                    <ul v-for="(category, index) in getCategories " :key="index">
                        <input type="radio" :id="index" :value="category" v-model="newbooking.category">
                        - <label :for="index">{{ category.categoryName }}</label>
                    </ul>
                    <p v-if="isCategoryEmpty" class="text-xs text-red-600">Plase select category !!!!!</p>
                   <div class="mt-1">
                    <label >Date: </label>
                    <input type="date" v-model="newbooking.Date">
                    <p v-if="isDateEmpty" class="text-xs text-red-600">Plase Input your date !!!!!</p>
                   </div>
                   <div class="mt-1">
                    <label> Start (Time): </label>
                    <input type="time" v-model="newbooking.Time">
                    <p v-if="isTimeEmpty" class="text-xs text-red-600">Plase Input your time !!!!!</p>
                   </div>
                   <div class="mt-1"> 
                    <label class="mr-2 mt-5">Duration (Minute): {{ newbooking.category.duration }}</label>
                   </div>
                   <div class="mt-1">
                       <label class="mr-2 mt-2">Note: </label>
                    <textarea rows="5" cols="50" v-model="newbooking.eventNote" maxlength="500"></textarea>
                    <p class="text-sm text-stone-500">(Number of Character : {{checkNote}})</p>
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
<script setup>
import { onBeforeMount, ref } from 'vue';
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
    group: "",
    bookingEmail: "",
    Date: "",
    Time: "",
    category: {},
    eventNote: "",
});

const reset = () => {
    isBooking.value = false
    newbooking.value = {
        bookingName: "",
        group: "",
        bookingEmail: "",
        Date: "",
        Time: "",
        category: {},
        eventNote: "",
    }
}

let mailFormat=/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
const CheckInput=(booking)=>{
    let isCheck=true
    if(!booking.bookingEmail.match(mailFormat) || booking.bookingEmail.length > 100){
        isCheck=false
        console.log("Not email")
    }
    if(booking.bookingName==="" || booking.bookingName.length > 100){
        isCheck=false
        console.log("Not name")
    }
    if(Object.keys(booking.category).length===0){
        isCheck=false
        console.log("Not category")
    }
    if(booking.Date==="" || booking.Time===""){
        isCheck=false
        console.log("Not DateTime")
    }
    if(booking.eventNote.length > 500){
        isCheck=false
        console.log("Not Event Note")
    }
    if(isCheck){
        createBooking(booking)
        reset()
    }
}

const createBooking= async (booking)=>{
    const Auto_Increment = (increment)=>{
        getListBooking.value.forEach((data)=>{
            if(increment===data.id){
                increment=data.id+1
            }
            else{
                increment=getListBooking.value.length+1
            }
        })
        return increment===0 ? 1:increment
    }
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`,{
        method: 'POST',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:Auto_Increment(getListBooking.value.length),
            bookingName: booking.bookingName,
            bookingEmail: booking.bookingEmail,
            category: booking.category,
            startTime:`${booking.Date}T${booking.Time}`,
            bookingDuration:booking.bookingDuration,
            eventNote: booking.eventNote  
        })
    })
    if(res.status===201){
        const newbooking=await res.json()
        emits=('add',newbooking)
    }
}
</script>
 
<template>
    <div>
        <div>
            <div>
                <div>
                    <p>Full Name: <input type="text" placeholder="Name..." v-model="newbooking.bookingName" maxlength="100"></p>
                    <p>E-mail: <input type="email" placeholder="E-mail..." v-model="newbooking.bookingEmail" maxlength="100"></p>
                    <p>Category:
                    <ul v-for="(category, index) in getCategories " :key="index">
                        <input type="radio" :id="index" :value="category" v-model="newbooking.category">
                        - <label :for="index">{{ category.categoryName }}</label>
                    </ul>
                    <label>Date </label>:
                    <input type="date" v-model="newbooking.Date">
                    <br />
                    <label> Start (Time) </label>:
                    <input type="time" v-model="newbooking.Time">
                    <br />
                    <label>Duration (Minute): {{ newbooking.category.duration }}</label>
                    <br />
                    <label>Note: </label>
                    <textarea rows="5" cols="50" v-model="newbooking.eventNote" maxlength="500"></textarea>
                    <div>
                        <button @click="CheckInput(newbooking)">OK</button>
                        <button @click="reset">Cancle</button>
                    </div>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Titan+One&display=swap');
</style>
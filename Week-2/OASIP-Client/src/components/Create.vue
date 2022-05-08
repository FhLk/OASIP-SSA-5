<script setup>
import { onBeforeMount, ref } from 'vue';
const isBooking = ref(false)

defineEmits(['add'])

const props = defineProps({
    getCategories: {
        type: Array,
        require: true
    },
    getListBooking: {
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

</script>
 
<template>
<div>
    <div class="">
        <button @click="isBooking = isBooking ? false : true">Add Booking</button>
    </div>
    <div v-if="isBooking">
        <p>Full Name: <input type="text" placeholder="Name..." v-model="newbooking.bookingName"></p>
        <p>Group: <input type="text" placeholder="Group" v-model="newbooking.group" /></p>
        <p>E-mail: <input type="email" placeholder="E-mail..." v-model="newbooking.bookingEmail"></p>
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
        <textarea rows="5" cols="50" v-model="newbooking.eventNote"></textarea>
        <div>
            <button @click="$emit('add', newbooking), reset()">OK</button>
            <button @click="reset">Cancle</button>
        </div>
        </p>
    </div>
</div>        
</template>
 
<style scoped>

</style>
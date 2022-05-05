<script setup>
import { onMounted, onBeforeMount, ref, onBeforeUpdate } from 'vue';
import moment from "moment"
const isBooking = ref(false)
const isDetail = ref(-1)
let DateFormat = "YYYY-MM-DD HH:mm A"
const getListBooking = ref([]);
const getBooking = ref({});
const categories = ref([]);
const booking = ref({
    id: 0,
    bookingName: "",
    bookingEmail: "",
    category: {
        id: 0,
        categoryName: "",
        description: "",
        duration: 0
    },
    startTime: "",
    eventNote: ""
});
const group = ref("")
const bookingDate = ref("");
const bookingTime = ref("")
const resetBooking = () => {
    booking.value = {
        id: 0,
        bookingName: "",
        bookingEmail: "",
        category: {
            id: 0,
            categoryName: "",
            description: "",
            duration: 0
        },
        startTime: "",
        eventNote: ""
    };
}
const showDetail = async (id) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${id}`, {
        method: 'GET'
    })
    getBooking.value = await res.json()
    getBooking.value.startTime = moment(getBooking.value.startTime).utcOffset(7).format(DateFormat)
    isDetail.value = isDetail.value === id ? -1 : id
}

const getBookings = async (startTime = "startTime") => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings?startTime=${startTime}`, {
        method: 'GET'
    })
    getListBooking.value = await res.json()
    getListBooking.value.forEach((data) => {
        data.startTime = moment(data.startTime).utcOffset(7).format(DateFormat)
    })
}


const getCategories = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/categories`, {
        method: 'GET'
    })
    categories.value = await res.json()
}

onMounted(async () => {
    await getBookings()
    await getCategories()
})

const Add = async (event) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id: getListBooking
                .value.length + 1,
            bookingName: event.bookingName + ` (${group.value})`,
            bookingEmail: event.bookingEmail,
            category: {
                id: event.category.id,
                categoryName: event.category.categoryName,
                description: event.category.description,
                duration: event.category.duration
            },
            startTime: bookingDate.value + "T" + bookingTime.value + ":00Z",
            eventNote: event.eventNote
        })
    })
    const newBooking = await res.json()
    getListBooking
        .value.push(newBooking)
    resetBooking()
    isBooking.value = false
}

const isEditBooking = ref(false);
const EditEvent = (event, index) => {
    isBooking.value = false;
    isEditBooking.value = isEditBooking.value ? false : true;
}
</script>
 
<template>
    <div class="bg h-screen font">
        <h1 class="text-7xl text-white pl-10 pt-2 pb-2 mb-4 oa">OASIP</h1>
        <div class="bg">
            <div class="flex">
                <div class="scd rounded-lg w-48 ml-10 p-2">
                    <h2 class="text-4xl text-white flex justify-center">Schedule</h2>
                </div>
                <div>
                    <div class="scd rounded-full w-32 mx-2 p-2 text-white mt-2 flex flex-col- justify-center">
                        <button @click="isBooking = isBooking ? false : true">Add Booking</button>
                    </div>
                </div>
            </div>
            <div class="ins rounded-md w-1/2 mt-2 ml-72">
                <div v-if="isBooking">
                    <p class="pt-2 ml-2">Full Name: <input type="text" placeholder="Name..."
                            v-model="booking.bookingName"></p>
                    <p class="mt-2 ml-2">Group: <input type="text" placeholder="Group" v-model="group" /></p>
                    <p class="mt-2 ml-2">E-mail: <input type="email" placeholder="E-mail..."
                            v-model="booking.bookingEmail"></p>
                    <p class="mt-2 ml-2">Category:
                    <ul v-for="(category, index) in categories" :key="index">
                        <input type="radio" :id="index" :value="category" v-model="booking.category">
                        - <label :for="index">{{ category.categoryName }}</label>
                    </ul>
                    <label class="mt-2 ">Date </label>:
                    <input type="date" v-model="bookingDate">
                    <br />
                    <label class="mt-2 "> Start (Time) </label>:
                    <input type="time" v-model="bookingTime">
                    <br />
                    <label class="mt-2 ">Duration (Minute): {{ booking.category.duration }}</label>
                    <br />
                    <label class="">Note: </label>
                    <textarea rows="5" cols="50" v-model="booking.eventNote" class="ml-2"></textarea>
                    <div>
                        <button @click="Add(booking)">OK</button>
                        <button @click="cancle">Cancle</button>
                    </div>
                    </p>
                </div>
            </div>
            <div class="rounded-md bgde mx-10 pt-4 mt-2 mb-4 pb-3 tde">
                <div v-if="getListBooking
                .length !== 0">
                    <!-- <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p> -->
                    <ul>
                        <li v-for="(data, index) in getListBooking
                        " :key="index" class="ml-5 mr-5 list rounded-lg mb-2 px-2 pt-1 ">{{ data.startTime }}
                            ({{ data.category.duration }} min.) {{ data.category.categoryName.toLocaleUpperCase() }}
                            {{ data.bookingName }}
                            <div class="flex justify-between ">
                                <button @click="showDetail(data.id)" class="bg-green-600 rounded-md px-1 text-white">{{ isDetail === index ? "Closed" : "Detail"
                                }}</button>
                                    <button class="bg-red-600 rounded-md px-1 text-white">Delete</button>
                                </div>
                            <div>
                                <div v-if="isDetail === data.id">
                                <div><p>Name: {{ getBooking.bookingName }}</p></div>
                                <div><p>E-mail: {{ getBooking.bookingEmail }}</p></div>
                                <div><p>Category: {{ getBooking.category.categoryName }}</p></div>
                                <div><p>Date & Time: {{ getBooking.startTime }}</p></div>
                                <div><p>Duration: {{ getBooking.category.duration }} min.</p></div>
                                <div><p>Start Time: {{ getBooking.startTime.slice(10) }}</p></div>
                                <div><p>Note: {{ getBooking.eventNote }}</p></div>
                                    <button @click="EditEvent(data, index)">Edit</button>
                                </div>
                            </div>
                            <br />
                        </li>
                    </ul>
                </div>
                <div v-else class="bg bg-white">
                    <h2>No Scheduled Events.</h2>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Titan+One&display=swap');

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

.list{
    background-color: rgb(92, 179, 255);
}

.dl{
    background-color: rgb(0, 0, 165);
}

.bgde{
    background-color: rgb(254, 252, 255);
}

.tde{
    color: rgb(12, 9, 10);
}
</style>
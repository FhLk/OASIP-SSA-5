<script setup>
import { onMounted, onBeforeMount, ref, onBeforeUpdate } from 'vue';
import moment from "moment"
const isBooking = ref(false)
const isDetail = ref(-1)
let DateFormat = "YYYY-MM-DD HH:mm A"
const listdata = ref([]);
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
const showDetail = (id) => {
    isDetail.value = isDetail.value === id - 1 ? -1 : id - 1
}

const getBookings = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings`, {
        method: 'GET'
    })
    listdata.value = await res.json()
    listdata.value.forEach((data) => {
        data.startTime = moment(data.startTime).format(DateFormat)
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
            id: listdata.value.length + 1,
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
    listdata.value.push(newBooking)
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
        <h1 class="text-7xl  text-white mx-5 mb-4 ">OASIP</h1>
        <div>
            <div class="flex">
                <h2 class="text-4xl  text-white ml-20 rounded-lg scd px-3">Schedule</h2>
                <div
                    class="rounded-lg p-2  flex-col  max-w-6xl mx-3 px-10 sm:px-10 lg:px-4 py-10 shadow-lg bg-neutral-100 space-y-2">
                    <button @click="isBooking = isBooking ? false : true">Add Schedule</button>
                    <div v-if="isBooking">
                        <p>Full Name: <input type="text" placeholder="Name..." v-model="booking.bookingName"></p>
                        <p>Group: <input type="text" placeholder="Group" v-model="group" class="mt-2"></p>
                        <p>E-mail: <input type="email" placeholder="E-mail..." v-model="booking.bookingEmail"
                                class="mt-2"></p>
                        <select class="form-select appearance-none 
                                    block
                                    w-full
                                    px-3
                                    py-1.5
                                    text-base
                                    font-normal
                                    text-gray-700
                                    bg-white bg-clip-padding bg-no-repeat
                                    border border-solid border-gray-300
                                    rounded
                                    transition
                                    ease-in-out
                                    m-0
                                    focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none mt-2"
                            aria-label="Default select example">

                            <option selected>Category</option>
                            <option :for="index" value="1">Back-End clinic</option>l>
                            <option :for="index" value="2">Database clinic</option>l>
                            <option :for="index" value="3">Front-End clinic</option>l>
                            <option :for="index" value="4">Project-Management</option>l>
                            <option :for="index" value="5">DevOps-Infra clinic</option>l>
                            <option :for="index" value="6">Business-Requirement clinic</option>l>

                        </select>
                        <!-- <ul v-for="(category, index) in categories" :key="index" >
                            <input type="radio" :id="index" :value="category" v-model="booking.category" >
                            - <label :for="index" >{{ category.categoryName }}</label>
                        </ul> -->
                        <label class="mt-2">Date </label>:
                        <input type="date" v-model="bookingDate">
                        <br />
                        <label class="mt-2"> Start (Time) </label>:
                        <input type="time" v-model="bookingTime">
                        <br />
                        <label class="mt-2">Duration (Minute): {{ booking.category.duration }}</label>
                        <br />
                        <label>Note: </label>
                        <textarea rows="5" cols="50" v-model="booking.eventNote"></textarea>
                        <div>
                            <button @click="Add(booking)"
                                class="rounded-lg p-2  flex-col  max-w-6xl mx-3 px-10 sm:px-10 lg:px-4 py-10 shadow-lg bg-green-600 space-y-2">OK</button>
                            <button @click="cancle"
                                class="rounded-lg p-2  flex-col  max-w-6xl mx-3 px-10 sm:px-10 lg:px-4 py-10 shadow-lg bg-red-600 space-y-2">Cancle</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="flex justify-end mr-12">
                <button @click="delete" class="rounded-lg p-2 flex justify-end dl">delete</button>
            </div>
            <div class="rounded-lg  bg-white p-20 mt-2 mx-5 ">
                <div v-if="listdata.length !== 0">
                    <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p>
                    <ul>
                        <li v-for="(data, index) in listdata" :key="index">{{ data.startTime }}
                            ({{ data.category.duration }} min.) {{ data.category.categoryName.toLocaleUpperCase() }}
                            Clinic
                            {{ data.bookingName }}
                            <div>
                                <button @click="showDetail(index + 1)">{{ isDetail === index ? "Closed" : "Detail"
                                }}</button>
                                <button>Delete</button>
                                <div v-if="isDetail === index">
                                    <p>Name: {{ data.bookingName }}</p>
                                    <p>E-mail: {{ data.bookingEmail }}</p>
                                    <p>Category: {{ data.category.categoryName }}</p>
                                    <p>Date & Time: {{ data.startTime }}</p>
                                    <p>Duration: {{ data.category.duration }} min.</p>
                                    <p>Start Time: {{ data.startTime.slice(10) }}</p>
                                    <p>Note: {{ data.eventNote }}</p>
                                    <button @click="EditEvent(data, index)">Edit</button>
                                </div>
                            </div>
                            <br />
                        </li>
                    </ul>
                </div>
                <div v-else>
                    <h2 class="text-1xl text-black flex justify-center">No Scheduled Events.</h2>
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
.dl{
    background-color: rgb(215, 45, 45);
}
.scd{
    background-color: rgb(86, 165, 236);
}
</style>
<script setup>
import { ref } from 'vue';
import moment from "moment"
import { computed } from '@vue/reactivity';

defineEmits(['delete', 'save'])

let DateFormat = "YYYY-MM-DD HH:mm"
const props = defineProps({
    getListBooking: {
        type: Array,
        require: true
    }
})

const getBooking = ref({});
const isDetail = ref(-1)
const isEdit = ref(false)

let count = 0
const showDetail = async (id) => {
    if (id !== count) {
        const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${id}`, {
            method: 'GET'
        })
        getBooking.value = await res.json()
        getBooking.value.startTime = moment(getBooking.value.startTime).utcOffset(0).format(DateFormat)
        count = id
    }
    isDetail.value = isDetail.value === id ? -1 : id
    isEdit.value = false
}


const EditNote = ref("")
const EditDate = ref("")
const EditTime = ref("")
const EditDateTime = ref("")
const EditEvent = (booking) => {
    isEdit.value = isEdit.value ? false : true
    if (isEdit.value) {
        EditNote.value = booking.eventNote
        EditDateTime.value = booking.startTime
        EditDate.value = EditDateTime.value.slice(0, 10)
        EditTime.value = EditDateTime.value.slice(10).trim()
    }
    else {
        EditNote.value = getBooking.value.eventNote
        EditDateTime.value = getBooking.value.startTime
        EditDate.value = ""
        EditTime.value = ""
    }
}


const savebooking = async (booking) => {
    if (confirm("You Have Edited Your Event.")) {
        const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${booking.id}`, {
            method: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify({
                id: booking.id,
                bookingName: booking.bookingName,
                bookingEmail: booking.bookingEmail,
                category: booking.category,
                startTime: `${EditDate.value}T${EditTime.value}:00Z`,
                eventNote: EditNote.value
            })
        })
        if (res.status === 200) {
            const updateBooking = await res.json()
            isEdit.value = false
            return updateBooking
        }
        else {
            return booking
        }
    }
}

const ced = " edit rounded-full px-2 text-white " ;
const ccl = " bg-red-600 rounded-full px-2 text-white " ;
const cdet = " bg-green-600 rounded-full px-2 text-white " ;
const note = " bgde px-1 mx-1 rounded-md " ;
const nonote = "" ;

</script>
 
<template>
    <div class="bg pt-3 rounded-md bgde mx-10 mb-4 pb-3 tde">
        <div v-if="getListBooking.length !== 0" class="mx-5 ">
            <!-- <p>Sort By: | <a>Day</a> | <a>Upcoming</a> | <a>Past</a> | <a>Time</a> | </p> -->
            <ul class="ml-5 mr-5 list rounded-lg mb-2 px-2 pt-3 pb-2">
                <li v-for="(data, index) in getListBooking" :key="index" class="bgis rounded-md mb-5 px-2">{{ data.startTime }}
                    ({{ data.category.duration }} min.) {{ data.category.categoryName.toLocaleUpperCase() }}
                    {{ data.bookingName }}
                    <div>
                        <div class="flex justify-between">
                            <button @click="showDetail(data.id)" class="h-6 w-15 mt-4 px-2"
                                :class="isDetail === data.id ? ccl : cdet" >{{ isDetail === data.id ?"Closed" : "Detail" }}</button>
                              <img @click="$emit('delete', data)" src="../assets/trash-can.gif" 
                              class="h-10 w-10 mt-1 ring-1 ring[#728FCE] rounded-md  cursor-pointer shadow-md  hover:shadow-red-500" >
                                
                        </div>
                        <div v-if="isDetail === data.id" class="">
                            <div class="blist mt-4 pl-5 py-2 rounded-md">
                                <div class="flex">
                                    <p>Name : </p><p class="bgde px-1 mx-1 rounded-md ">{{ getBooking.bookingName }}</p>
                                </div>
                                <div class="flex mt-2">
                                    <p>E-mail : </p><p class="bgde px-1 mx-1 rounded-md">{{ getBooking.bookingEmail }}</p>
                                </div>
                                <div class="flex mt-2">
                                    <p>Category : </p><p class="bgde px-1 mx-1 rounded-md">{{ getBooking.category.categoryName }}</p>
                                </div>
                                <div class="flex mt-2">
                                    <p>Date & Time :
                                    <span v-if="isEdit === false" class="bgde px-1 mx-1 rounded-md">{{ getBooking.startTime }}</span>
                                    <span v-else >
                                        <input type="date" v-model="EditDate" class="list px-1 mx-1 rounded-md cursor-pointer" /> |
                                        <input type="time" v-model="EditTime" class="list px-1 mx-1 rounded-md cursor-pointer" />
                                    </span>
                                </p>
                                </div>
                                <div class="flex mt-2">
                                    <p>Duration : </p><p class="bgde px-1 mx-1 rounded-md">{{ getBooking.category.duration }} min.</p>
                                </div>
                                <div class="flex mt-2">
                                    <p>Note :
                                    <span v-if="isEdit === false" :class="getBooking.eventNote ? note : nonote" >{{ getBooking.eventNote }}</span>
                                    <span v-else>
                                        <textarea rows="5" cols="50" v-model="EditNote" class="ring-2 ring-black" ></textarea>
                                    </span>
                                </p>
                                </div>
                            </div>
                            <div class="pt-5">
                                <button @click="$emit('save', savebooking(data))" v-if="isEdit" class="bg-green-600 rounded-full px-1 text-white mr-2" >Save</button>
                                <button @click="EditEvent(data)" :class="isEdit ? ccl : ced" >{{ isEdit ? "Cancel" : "Edit" }}</button>
                            </div>
                       </div>
                    </div>
                    <br />
                </li>
            </ul>
        </div>
        <div v-else class="flex justify-center">
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
.edit {
    background-color: rgb(114, 143, 206);
}
.blist{
    background-color: rgb(189, 237, 255);
}
</style>
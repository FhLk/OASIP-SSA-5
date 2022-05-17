<script setup>
import { onBeforeMount, ref } from 'vue';
import moment from "moment"

let DateFormat = "YYYY-MM-DD HH:mm"

const getBooking = ref({});
const isDetail = ref(-1)
const isEdit = ref(false)

const getListBooking=ref([])
const Page = async (page=0) => {
    if(page >= 0){
        const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings?page=${page}`, {
            method: 'GET'
        })
        getListBooking.value = await res.json()
        getListBooking.value.forEach((data) => {
            data.startTime = ShowDateTime(data.startTime)
        })
        getListBooking.value=SortByDateTime(getListBooking.value)
    }
}

const page=ref(0)
const NextPage=()=>{
    if(page.value<0){
        page.value=0
    }
    reset()
    Page(page.value+=1)
}

const BackPage=()=>{
    if(page.value<0){
        page.value = 0
    }
    reset()
    Page(page.value-=1)
}

const ShowDateTime=(datatime)=>{
    return moment(datatime).local().format(DateFormat)
}

const SortByDateTime=(list)=>{
    return list.sort((a,b)=>{
        return new Date(b.startTime) - new Date(a.startTime)
    })
}

onBeforeMount(async ()=>{
    await Page()
})

let count = 0
const showDetail = async (id) => {
    if (id !== count) {
        const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${id}`, {
            method: 'GET'
        })
        getBooking.value = await res.json()
        getBooking.value.startTime = ShowDateTime(getBooking.value.startTime)
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

const reset=()=>{
    isDetail.value= -1
    count=0
    isEdit.value=false
    EditDate.value=""
    EditNote.value=""
}

const savebooking= async (updateBooking)=>{
    updateBooking.startTime=`${EditDate.value}T${EditTime.value}`
    updateBooking.eventNote=EditNote.value
    const res=await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${updateBooking.id}`,{
        method: 'PUT',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:updateBooking.id,
            bookingName: updateBooking.bookingName,
            bookingEmail: updateBooking.bookingEmail,
            category: updateBooking.category,
            startTime: updateBooking.startTime,
            bookingDuration:updateBooking.bookingDuration,
            eventNote: updateBooking.eventNote 
        })
    })
    if(res.status===200){
        await Page(page.value)
        reset()
    }
}

const deleteBooking= async (booking)=>{
    if(confirm("Do you want cancel this Booking ?")){
        const res = await fetch(`${import.meta.env.VITE_BASE_URL}/bookings/${booking.id}`, {
            method: 'DELETE'
        })
        if(res.status===200){
            await Page(page.value)
        }
    }
}

const ced = " edit rounded-full px-2 text-white background-color: rgb(114, 143, 206) " ;
const ccl = " bg-red-600 rounded-full px-2 text-white " ;
const cdet = " bg-green-600 rounded-full px-2 text-white " ;
const note = " bgde px-1 mx-1 rounded-md " ;
const nonote = "" ;

</script>
 
<template>
    <div class="font ccf pt-3 rounded-md mx-10 mb-4 pb-3 bgl text-lg">
        <div v-if="getListBooking.length !== 0">
            <ul>
                <li v-for="(data, index) in getListBooking" :key="index" class="bgl2 mb-5 px-8 mx-5 rounded-lg pt-2" >
                    {{ data.startTime }}
                    ({{ data.category.duration }} min.) {{ data.category.categoryName.toLocaleUpperCase() }}
                    {{ data.bookingName }}
                    <div>
                        <div class="flex justify-between mt-1">
                            <div>
                                <button @click="showDetail(data.id)" :class="isDetail === data.id ? ccl : cdet" class="mt-4" >{{ isDetail === data.id ? "Closed" : "Detail"
                                }}</button>
                            </div>
                            <div>
                                <img @click="deleteBooking(data)" src="../assets/trash-can.gif" 
                                class="del ring-1 ring[#728FCE] hover:ring-red-500 rounded-md cursor-pointer shadow-md hover:shadow-red-500">
                            </div>
                        </div>
                        <div v-if="isDetail === data.id" class="bgl3 px-5 pt-2 mt-2 pb-3 rounded-md">
                            <div>
                                <div class="flex">
                                    <p class="pr-2">Name : </p>
                                    <p>{{ getBooking.bookingName }}</p>
                                </div>
                                <div class="flex">
                                    <p class="pr-2">E-mail : </p>
                                    <p>{{ getBooking.bookingEmail }}</p>
                                </div>
                                <div class="flex">
                                    <p class="pr-2">Category : </p>
                                    <p>{{ getBooking.category.categoryName }}</p>
                                </div>
                                <div class="flex">
                                    <p>Date & Time :
                                        <span v-if="isEdit === false" class="pl-2">{{ getBooking.startTime }}</span>
                                        <span v-else>
                                            <input type="date" v-model="EditDate" /> |
                                            <input type="time" v-model="EditTime" />
                                        </span>
                                    </p>
                                </div>
                                <div class="flex">
                                    <p class="pr-2">Duration : </p>
                                    <p>{{ getBooking.category.duration }} min.</p>
                                </div>
                                <div class="flex">
                                    <p class="pr-2">Note :
                                        <span v-if="isEdit === false" :class="getBooking.eventNote ? note : nonote">{{ getBooking.eventNote }}</span>
                                        <span v-else>
                                            <textarea rows="5" cols="50" v-model="EditNote" maxlength="500"></textarea>
                                        </span>
                                    </p>
                                </div>
                            </div>
                            <div class="mt-2">
                                <button @click="savebooking(data)" v-if="isEdit" class="bg-green-600 rounded-full px-2 text-white mr-2 ">Save</button>
                                <button @click="EditEvent(data)" :class="isEdit ? ccl : ced">{{ isEdit ? "Cancel" : "Edit" }}</button>
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
        <div class="flex justify-center">
        <button v-if="page !== 0" @click="BackPage" class="mx-10 px-4 py-2 btt cf hover:bg-[#5555AC] rounded-md">Back</button>
        <button v-if="getListBooking.length === 1" @click="NextPage" class="mx-10 px-4 py-2 btt cf hover:bg-[#5555AC] rounded-md">Next</button>
        </div>
    </div>
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');
.font{
    font-family: 'Mitr', sans-serif;
}

.bg {
    background-color: rgb(173, 216, 230);
}

.ccf {
    color: rgb(42, 39, 40);
}

.bgl2 {
    background-color: rgb(164, 220, 255);
}
.del{
    width: 2.5rem ;
    height: 2.5rem ;
    margin-top: 0.25rem;
}
.edit {
    background-color: rgb(114, 143, 206);
}
.bgde {
    background-color: rgb(254, 252, 255);
}
.bgl{
    background-color: rgb(92, 179, 255);
}
.bgl3{
    background-color: rgb(135, 206, 235);
}
.btt{
    background-color: rgb(25, 25, 112);
}
.cf {
    color: rgb(251, 251, 249);
}
</style>
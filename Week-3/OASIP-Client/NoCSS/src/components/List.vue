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
</script>
 
<template>
    <div>
        <div v-if="getListBooking.length !== 0">
            <ul>
                <li v-for="(data, index) in getListBooking" :key="index">{{ data.startTime }}
                    ({{ data.category.duration }} min.) {{ data.category.categoryName.toLocaleUpperCase() }}
                    {{ data.bookingName }}
                    <div>
                        <div>
                            <button @click="showDetail(data.id)">{{ isDetail === data.id ?"Closed" : "Detail" }}</button>                             
                        </div>
                        <div>
                            <button @click="deleteBooking(data)">Delete</button>
                        </div>
                        <div v-if="isDetail === data.id">
                            <div>
                                <div>
                                    <p>Name : </p><p>{{ getBooking.bookingName }}</p>
                                </div>
                                <div >
                                    <p>E-mail : </p><p>{{ getBooking.bookingEmail }}</p>
                                </div>
                                <div>
                                    <p>Category : </p><p>{{ getBooking.category.categoryName }}</p>
                                </div>
                                <div>
                                    <p>Date & Time :
                                    <span v-if="isEdit === false">{{ getBooking.startTime }}</span>
                                    <span v-else >
                                        <input type="date" v-model="EditDate"/> |
                                        <input type="time" v-model="EditTime"/>
                                    </span>
                                </p>
                                </div>
                                <div>
                                    <p>Duration : </p><p>{{ getBooking.category.duration }} min.</p>
                                </div>
                                <div>
                                    <p>Note :
                                    <span v-if="isEdit === false">{{ getBooking.eventNote }}</span>
                                    <span v-else>
                                        <textarea rows="5" cols="50" v-model="EditNote" maxlength="500"></textarea>
                                    </span>
                                </p>
                                </div>
                            </div>
                            <div>
                                <button @click="savebooking(data)" v-if="isEdit" >Save</button>
                                <button @click="EditEvent(data)">{{ isEdit ? "Cancel" : "Edit" }}</button>
                            </div>
                       </div>
                    </div>
                    <br />
                </li>
            </ul>
        </div>
        <div v-else>
            <h2>No Scheduled Events.</h2>
        </div>
            <button v-if="page!==0" @click="BackPage" >Back</button>
            <button v-if="getListBooking.length===2" @click="NextPage" >Next</button>
        </div>
</template>
 
<style scoped>
</style>
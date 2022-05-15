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
    <div class="bg">
        <div class="ml-10 mt-2 bgm w-32 flex justify-center rounded-full">
            <button @click="isBooking = isBooking ? false : true">Add Booking</button>
        </div>
        <div class="bg flex justify-start mx-20 rounded-lg py-2">
            <div v-if="isBooking">
                <div class="tde bgis px-3 pt-1 py-1 rounded-md grow">
                    <p>Full Name: <input type="text" placeholder="Name..." v-model="newbooking.bookingName"
                            class="ml-2 ins rounded-md flex-cols"></p>
                    <p>Group: <input type="text" placeholder="Group" v-model="newbooking.group"
                            class="ml-2 ins rounded-md flex-col mt-2" /></p>
                    <p>E-mail: <input type="email" placeholder="E-mail..." v-model="newbooking.bookingEmail"
                            class="ml-2 ins rounded-md flex-col mt-2"></p>
                    <p>Category:
                    <ul v-for="(category, index) in getCategories " :key="index">
                        <input type="radio" :id="index" :value="category" v-model="newbooking.category"
                            class="ml-2 ins rounded-md flex-col mt-2">
                        - <label :for="index">{{ category.categoryName }}</label>
                    </ul>
                    <label>Date </label>:
                    <input type="date" v-model="newbooking.Date" class="ml-2 mt-2 ring-offset-2 ring">
                    <br />
                    <label> Start (Time) </label>:
                    <input type="time" v-model="newbooking.Time" class="ml-2 mt-3 ring-offset-2 ring">
                    <br />
                    <label class="mt-2">Duration (Minute): {{ newbooking.category.duration }}</label>
                    <br />
                    <label>Note: </label>
                    <textarea rows="5" cols="50" v-model="newbooking.eventNote"
                        class="ml-2 ins rounded-md flex-col"></textarea>
                    <div class="mt-2 flex justify-between">
                        <button class="mb-2 ok rounded-md px-1 tins" @click="$emit('add', newbooking), reset()">OK</button>
                        <button class="mb-2 cl rounded-md px-1 tins" @click="reset">Cancle</button>
                    </div>
                    </p>
                </div>
            </div>
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

.cl {
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
.ok{
    background-color:rgb(22, 163, 74) ;
}
</style>
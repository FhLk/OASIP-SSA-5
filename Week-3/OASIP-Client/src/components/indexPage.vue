<script setup>
import { ref } from 'vue';


const isCat = ref(false);
const ca = ref([
    {
        CategoryName: "Project Management Clinic",
        Description: "ตารางนัดหมายนี้ใช้สำหรับนัดหมาย project management clinic ในวิชา INT221 integrated project I ให้นักศึกษาเตรียมเอกสารที่เกี่ยวข้องเพื่อแสดง EventBooking ระหว่างขอคำปรึกษา",
        Duration: 30
    },
    {
        CategoryName: "DevOps/Infra Clinic",
        Description: "Use this event category for DevOps/Infra clinic.",
        Duration: 20
    },
    {
        CategoryName: "Database Clinic",
        Description: "ตารางนัดหมายนี้ใช้สำหรับนัดหมาย database clinic ในวิชา INT221 integrated project I",
        Duration: 15
    },
    {
        CategoryName: "Client-side Clinic",
        Description: "ตารางนัดหมายนี้ใช้สำหรับนัดหมาย client-side clinic ในวิชา INT221 integrated project",
        Duration: 30
    },
    {
        CategoryName: "Server-side Clinic",
        Description: "",
        Duration: 30
    }
]);



</script>
 
<template>
    <div class="bg">
        <div class="ml-10 bgm w-32 flex justify-center rounded-full">
            <button @click="isCat = isCat ? false : true"> Category List </button>
        </div>
        <div v-if="isCat" class="tde bgis px-3 pt-1 py-1 rounded-md grow mt-1 mx-8" >
            <ul v-for=" (value) in ca " :key="index" class="my-4 cate rounded-md py-3 px-2">
                CategoryName : {{ value.CategoryName }}<br>
                Description : {{ value.Description }}<br>
                Duration : {{ value.Duration }}<br>
            </ul>
        </div>
    </div>
</template>
 
<style>
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
.cate{
    background-color: rgb(135, 206, 250);
}
</style>
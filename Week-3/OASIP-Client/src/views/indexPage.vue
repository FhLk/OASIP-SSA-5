<script setup>
import ShowPage from '../views/ShowPage.vue';


</script>
 
<template>
<div class="bg h-screen h-full">
    <div class="bg h-screen h-full pt-36 font text-5xl flex justify-center">
        <h2> Online Appointment Scheduling </h2>
    </div>
    <div class="bg h-screen h-full flex justify-center font">
            <button class="btnindex hover:bg-[#00A1E1] rounded-md px-1 mt-4 h-8 cf mx-14"><router-link to="/ShowPage"> Show Schedule </router-link></button>
            <button class="btnindex hover:bg-[#00A1E1] rounded-md px-1 mt-4 h-8 cf mx-14"><router-link to="/AddPage">Add New Schedule</router-link></button>
        </div>
    <div class="flex justify-center pb-10 pl-5 pt-14 bg h-full h-screen">
        <router-link to = "/ShowPage"> <img src="../assets/meet.png" class="imgid"> </router-link>
    </div>
</div>
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');
.font{
    font-family: 'Mitr', sans-serif;
}
.bg{
    background-color: rgb(255, 255, 247);
}
.imgid {
  width: 685px ;
  height: 409px ;
}
.btnindex{
    background-color: rgb(0, 191, 255);
}
.cf{
    color: rgb(248 250 252) ;
}

</style>
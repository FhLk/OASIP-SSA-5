<script setup>

</script>
 
<template>
<div class="">
<nav class="scd  border-gray-200 px-2 sm:px-4 py-5 font fixed top-0 left-0 right-0">
    <div class="flex justify-between">
        <router-link to="/"><h1 class="text-7xl cf px-2 ml-8 rounded-md hover:ring ring-[#FBFBF9] font-bold" >OASIP</h1></router-link>
        <div class="justify-self-end">
            <button class="btAddNew hover:bg-[#294592] rounded-md px-1 mt-4 h-8 cf mx-2"><router-link to="/ShowPage"> Show Schedule </router-link></button>
            <button class="btAddNew hover:bg-[#294592] rounded-md px-1 mt-4 h-8 cf mx-2"><router-link to="/AddPage">Add New Schedule</router-link></button>
        </div>
    </div>
</nav>
<div class="navbar pt-1 flex justify-end">
    <button class="text-black hover:bg-[#294592] rounded-md px-1 h-5 hover:text-white font"><router-link to="/AboutPage">About Us</router-link></button>
</div>
</div>
<router-view> </router-view> 

</template>


 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');
.font{
    font-family: 'Mitr', sans-serif;
}
.scd {
    background-color: rgb(86, 165, 236);
}
.cf {
    color: rgb(251, 251, 249);
}
.btAddNew{
    background-color: rgb(21, 49, 126);
}
.hoa{
    background-color: rgb(101, 129, 206);
}
.navbar {
  background-color: rgb(189, 237, 255);
  overflow: hidden;
  position: fixed ;
  bottom: 0;
  width: 100%; 
}
</style>
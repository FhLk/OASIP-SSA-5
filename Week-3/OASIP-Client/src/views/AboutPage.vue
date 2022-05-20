<script setup>
import { ref } from "vue";
const mem = ref( [
    {
        Id : 63130500015 ,
        Name : "Chanatip Ampla" ,
        Occupation : "Student of the School of Information Technology"
    },
    {
        Id : 63130500043 ,
        Name : "Trongsit Sinnuruk" ,
        Occupation : "Student of the School of Information Technology"
    },
    {
        Id : 63130500078 ,
        Name : "Paramet Pet-in" ,
        Occupation : "Student of the School of Information Technology"
    }
] );

</script>
 
<template>
<div class="bg h-screen font">
    <h2 class="mt-32 text-3xl ml-5"> Create and Design  </h2>
    <div class="ml-5">
    <h2 class="text-xl ml-5 mt-5">Team Bento and Bento</h2>
    </div>
    <div class="ml-28 mt-1">
    <ul v-for="members in mem"  class="mt-5">
        Id : {{ members.Id }} <br/>
        Name : {{ members.Name }} <br/>
        Occupation : {{ members.Occupation }} <br/>
    </ul>
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

</style>
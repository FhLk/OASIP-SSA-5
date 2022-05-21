<script setup>
import { ref } from "vue";
const mem = ref( [
    {
        Picture : "https://raw.githubusercontent.com/FhLk/OASIP-SSA-5/SIT_Server/picture/Note/224814776_569579017394700_1348715486412544157_n%20(1).jpg?token=GHSAT0AAAAAABUC2TLRXY5SW7KTA6SBC6JEYUJAENA" ,
        Id : 63130500015 ,
        Name : "Chanatip Ampla" ,
        Occupation : "Student of the School of Information Technology",
        github : "https://github.com/Batbloody"

    },
    {
        Picture : "https://raw.githubusercontent.com/FhLk/OASIP-SSA-5/SIT_Server/picture/Note/1633779977767.jpg?token=GHSAT0AAAAAABUC2TLQBHHQAMPR7F75IGGSYUJAEMA" ,
        Id : 63130500043 ,
        Name : "Trongsit Sinnuruk" ,
        Occupation : "Student of the School of Information Technology",
        github : "https://github.com/FhLk"
    },
    {
        Picture : "https://raw.githubusercontent.com/FhLk/OASIP-SSA-5/SIT_Server/picture/Note/PicFight.jpg?token=GHSAT0AAAAAABUC2TLQAYUJCNPYSRRYP2MMYUJAB2A" ,
        Id : 63130500078 ,
        Name : "Paramet Pet-in" ,
        Occupation : "Student of the School of Information Technology",
        github : "https://github.com/taohoo3698"
    }
] );

</script>
 
<template>
<div class="bg h-screen font">
    <h2 class="mt-32 text-4xl pb-1 pl-5 bg-[#AAF2FF]"> Create and Design  </h2>
    <h2 class="text-2xl ml-96 mt-5 bg1 w-fit p-1 rounded-md ">Team Bento and Bento</h2>
    <div class="ml-28 mt-1 flex grid justify-center">
    <ul v-for="members in mem"  class="mt-6 flex text-xl bg-[#96DEFF] py-2 rounded-lg pr-3">
        <div class="ml-6 mr-10">
        <img :src="members.Picture" class="imgid shadow-gray-600 shadow-md">
        </div>
        <div>
        <p>ID : {{ members.Id }} </p>
        <p>Name : {{ members.Name }} </p>
        <p>Occupation : {{ members.Occupation }} </p>
        <div class="flex mt-1">
        <p class="imggit mr-2"><a :href="members.github"><img src="../assets/github.png"></a></p>
        <p>github</p>
        </div>
        </div>
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
.imgid {
  width: 100px ;
  height: 100px ;
  border-radius: 50%;
}
.imggit {
  width: 30px ;
  height: 30px ;
}
.bg1{
    background-color: rgb(120, 192, 245);
}
</style>
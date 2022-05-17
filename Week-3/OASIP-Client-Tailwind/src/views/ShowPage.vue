<script setup>
import {onMounted,onBeforeMount,ref, onBeforeUpdate, onUpdated, computed} from 'vue';
import List from '../components/List.vue'
let DateFormat="YYYY-MM-DD HH:mm"
const isShow=ref(false)

</script>
 
<template>
<div class="font">
    <h2 class="ccf cr text-5xl mt-5 mb-4 mx-5 ring w-1/6 flex justify-center rounded-md p-2">Schedule</h2>
    <List/>
    
</div>
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');
.font{
    font-family: 'Mitr', sans-serif;
}
.bg{
    background-color: rgb(173, 216, 230);
}
.ccf {
    color: rgb(42, 39, 40);
}
.cr{
    --tw-ring-color: rgb(42, 39, 40);
}
</style>

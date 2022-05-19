<script setup>
<<<<<<< HEAD
import { computed } from '@vue/reactivity';
import List from '../components/List.vue'
const props = defineProps({
    getListCategory:{
        type:Array,
        require:true
    }
})

const categories=computed(()=>{
    return props.getListCategory
})
</script>
 
<template>
<h1>OASIP TEST</h1>
<div>
    <h2>Category List</h2>
    <div>
        <ul>
            <p v-for="(category,index) in categories" :key="index">
               {{index+1}}. {{category.categoryName}} 
               <div>
                   Description: {{category.description}}
               </div>
               <div>
                   Duration: {{category.duration}} (mins.)
               </div>
            </p>
        </ul>
    </div>
    <h2>Booking List</h2>
    <List/>
=======
import {onMounted,onBeforeMount,ref, onBeforeUpdate, onUpdated, computed} from 'vue';
import List from '../components/List.vue'
let DateFormat="YYYY-MM-DD HH:mm"
const isShow=ref(false)

</script>
 
<template>
<div class="font">
    <h1 class="ccf cr text-5xl mt-5 mb-4 ml-5 flex justify-between rounded-md p-2">Schedule</h1>
    <List/>
    
>>>>>>> localhost
</div>
</template>
 
<style scoped>
<<<<<<< HEAD

=======
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
>>>>>>> localhost
</style>

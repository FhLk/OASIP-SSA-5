<script setup>
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
</div>
</template>
 
<style scoped>

</style>

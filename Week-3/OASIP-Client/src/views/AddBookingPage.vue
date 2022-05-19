<script setup>
import Create from '../components/Create.vue';
import { onMounted, onBeforeMount, ref, onBeforeUpdate, onUpdated, computed } from 'vue';
const AddToShow = (newbooking) => {

}

const getListCategories = ref([]);

const getCategories = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/categories`, {
        method: 'GET'
    })
    getListCategories.value = await res.json()
}

onBeforeMount(async ()=>{
    await getCategories()
})

</script>
 
<template>
<div>
    <Create :getCategories="getListCategories"/>
</div>

</template>
 
<style scoped>

</style>
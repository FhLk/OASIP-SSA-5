<script setup>
import { onBeforeMount, ref } from 'vue';
import Create from '../components/Create.vue';

const getListCategories=ref([])
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
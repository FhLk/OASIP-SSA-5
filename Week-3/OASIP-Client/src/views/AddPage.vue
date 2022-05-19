<script setup>
import Create from '../components/Create.vue';
import { onBeforeMount, ref} from 'vue';

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
<div class="bg h-screen">
    <div>
        <Create :getCategories="getListCategories" />
    </div>
    <div class="font ccf mt-5 bgcat mx-7 py-5 rounded-lg">
        <h2 class="ml-10 text-lg">Definition of category</h2>
        <div class="ml-14 rounded-full">
            <ul>
                <p v-for="(category, index) in getListCategories" :key="index" class="mt-2">
                    {{ index + 1 }}. {{ category.categoryName }}
                <div class="ml-4">
                    Description: {{ category.description }}
                </div>
                <div class="ml-4">
                    Duration: {{ category.duration }} (mins.)
                </div>
                </p>
            </ul>
        </div>
    </div>
</div>   
</template>
 
<style scoped>

</style>
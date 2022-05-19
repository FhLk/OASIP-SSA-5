<script setup>
<<<<<<< HEAD
import { onBeforeMount, ref } from 'vue';
import Create from '../components/Create.vue';

const getListCategories=ref([])
=======
import Create from '../components/Create.vue';
import { onMounted, onBeforeMount, ref, onBeforeUpdate, onUpdated, computed } from 'vue';
const AddToShow = (newbooking) => {

}

const getListCategories = ref([]);

>>>>>>> localhost
const getCategories = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/categories`, {
        method: 'GET'
    })
    getListCategories.value = await res.json()
}

<<<<<<< HEAD
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

=======
onBeforeMount(async () => {
    await getCategories()
})


</script>
 
<template>
<div class="bg h-screen">
    <div>
        <Create @add="AddToShow" :getCategories="getListCategories" />
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
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');
.font{
    font-family: 'Mitr', sans-serif;
}
.bg{
    background-color: rgb(255, 255, 247);
}
.ccf {
    color: rgb(42, 39, 40);
}
.bgcat{
    background-color: rgb(167, 214, 255);
}
>>>>>>> localhost
</style>
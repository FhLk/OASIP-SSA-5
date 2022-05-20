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

const isEdit=ref(false)
const isEditId=ref(0)
const EditDescription = ref("")
const EditName = ref("")
const EditDuration= ref("")
const EditCategory = (category) => {
    isEdit.value = isEdit.value ? false : true
    if (isEdit.value) {
        isEditId.value=category.id
        EditName.value=category.categoryName;
        EditDescription.value=category.description
        EditDuration.value=category.duration
    }
    else {
        EditName.value=""
        EditDescription.value=""
        EditDuration.value=0
    }
}

const saveCategory= async (updateCategory)=>{
    updateCategory.categoryName=EditName.value
    updateCategory.description=EditDescription.value
    updateCategory.duration=EditDuration.value
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/categories/${updateCategory.id}`, {
        method: 'PUT',
        headers:{
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            id:updateCategory.id,
            categoryName:updateCategory.categoryName.trim(),
            description:updateCategory.description.trim(),
            duration:updateCategory.duration
        })
    })
    if(res.status===200){
        alert("success")
        await getCategories()
        reset()
    }
}

const ced = " edit rounded-full px-2 text-white hover:bg-[#AECBFF]" ;
const ccl = " bg-red-600 rounded-full px-2 text-white hover:bg-[#F87171]" ;

</script>
 
<template>
<div class="bg h-screen">
    <div>
        <Create :getCategories="getListCategories" />
    </div>
    <div class="font ccf mt-5 bgcat mx-7 py-5 rounded-lg ">
        <h2 class="ml-10 text-lg">Definition of category</h2>
        <div class="ml-14 rounded-full">
            <ul>
                <p v-for="(category, index) in getListCategories" :key="index" class="mt-2 pb-8">
                    {{ index + 1 }}. 
                    <span v-if="isEdit && isEditId===index+1"><input type="text" v-model="EditName"/></span>
                    <span v-else>
                        {{ category.categoryName }}
                    </span>
                <div class="ml-4">
                    <p>Description: </p>
                    <span v-if="isEdit && isEditId===category.id">
                        <textarea rows="5" cols="50" v-model="EditDescription" maxlength="500"></textarea>
                    </span>
                    <span v-else>
                        <div class="description">  
                        {{ category.description }}
                        </div>
                    </span>
                </div>
                <div class="ml-4">
                    Duration: 
                    <span v-if="isEdit && isEditId===index+1">
                        <input type="number" v-model="EditDuration" :min="1" :max="480"/>
                    </span>
                    <span v-else>
                        {{ category.duration }} (mins.)
                    </span>
                </div>
                <!-- <button v-if="isEdit" @click="saveCategory(category)">Save</button> -->
                <button @click="EditCategory(category)" :class="isEdit && isEditId===index+1 ?  ccl : ced ">{{ isEdit && isEditId===index+1  ? "Cancel" : "Edit" }}</button>
                </p>
            </ul>
        </div>
    </div>
</div>   
</template>
 
<style scoped>
.description{
    max-width: 35%;
    margin-left: 2%;
}

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
.edit{
    background-color: rgb(21, 49, 126) ;
}

</style>
<script setup>
import Create from '../components/Create.vue';
import { computed, onBeforeMount, ref} from 'vue';

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
const EditDuration= ref(0)
const EditCategoryOpen = (category) => {
    isEdit.value = true
    isEditId.value=category.id
    EditName.value=category.categoryName;
    EditDescription.value=category.description
    EditDuration.value=category.duration
}

const EditCategoryClose =()=>{
    isDuration.value=false
    isNameEmpty.value=false
    isEditId.value=0
    isEdit.value=false
}

const reset=()=>{
    isEdit.value=false
    isEditId.value=0
    EditName.value=""
    EditDescription.value=""
    EditDuration.value=0
}

const isNameEmpty=ref(false)
const isDuration=ref(false)
const CheckInput= async (updateCategory)=>{
    let isCheck=true
    if(EditName.value === ''){
        isCheck=false
        isNameEmpty.value=true
    }
    if(EditDuration.value < 1 || EditDuration.value > 480){
        isCheck=false
        isDuration.value=true
    }
    else if(EditDuration.value >= 1 && EditDuration.value <= 480){
        isDuration.value=false
    }
    if(isCheck){
        EditDuration.value=updateCategory.duration
        EditName.value=updateCategory.categoryName
        EditDescription.value=updateCategory.description
        isDuration.value=false
        isNameEmpty.value=false
        if(confirm("Are you sure")){
            await saveCategory(updateCategory)
            reset()
        }
    }
}

const saveCategory= async (updateCategory)=>{
    updateCategory.categoryName=EditName.value
    updateCategory.description=EditDescription.value
    updateCategory.duration= EditDuration.value
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
        await getCategories()
        reset()
    }
}

const ced = " edit rounded-full px-2 text-white hover:bg-[#AECBFF]" ;
const ccl = " bg-red-600 rounded-full px-2 text-white hover:bg-[#F87171]" ;

const countDescription=computed(()=>{
    return 500-EditDescription.value.length
})

const countName=computed(()=>{
    return 100-EditName.value.length
})
</script>
 
<template>
<div class="bg h-screen h-full">
    <div>
        <Create :getCategories="getListCategories" />
    </div>
    <div class="font ccf mt-5 bgcat mx-7 py-5 rounded-lg ">
        <h2 class="ml-10 text-lg">Definition of category</h2>
        <div class="ml-14 rounded-full">
            <ul>
                <p v-for="(category, index) in getListCategories" :key="index" class="mt-2 pb-8">
                    {{ index + 1 }}. 
                    <span v-if="isEdit && isEditId===category.id">
                        <input type="text" v-model="EditName" maxlength="100"/>
                        <p class="text-sm text-stone-500">(Number of Character : {{countName}})</p>
                        <p v-if="isNameEmpty && countName===100" class="text-xs text-red-600">Plase Input Category Name.</p>
                    </span>
                    <span v-else>
                        {{ category.categoryName }}
                    </span>
                <div class="ml-4">
                    <p>Description: </p>
                    <span v-if="isEdit && isEditId===category.id">
                        <textarea rows="5" cols="50" v-model="EditDescription" maxlength="500"></textarea>
                        <p class="text-sm text-stone-500">(Number of Character : {{countDescription}})</p>
                    </span>
                    <span v-else>
                        <div class="description">  
                        {{ category.description }}
                        </div>
                    </span>
                </div>
                <div class="ml-4">
                    Duration: 
                    <span v-if="isEdit && isEditId===category.id">
                        <input type="number" v-model="EditDuration" min="1" max="480"/>
                        : <span class="text-sm text-stone-500">(1 to 480 mins)</span>
                        <p v-if="isDuration" class="text-xs text-red-600">Duration have time less/more than range</p>
                    </span>
                    <span v-else>
                        {{ category.duration }} (mins.)
                    </span>
                </div>
                <button v-if="isEditId!==category.id" @click="EditCategoryOpen(category)" :class="ced ">Edit</button>
                <div v-if="isEdit && isEditId===category.id" class="mt-2">
                    <button @click="CheckInput(category)" class="bg-green-600 rounded-full px-2 mx-2 text-white hover:bg-[#4ADE80]" >Save</button>
                    <button @click="EditCategoryClose" :class="ccl">Cancel</button>
                </div>
                </p>
            </ul>
        </div>
    </div>
</div>   
</template>
 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Mitr:wght@600;700&family=Titan+One&display=swap');

.description{
    max-width: 35%;
    margin-left: 2%;
}

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
<template>
  <div id="app" >
    <router-view  v-if="isShow" />
  </div>
</template>

<script setup>
import  {provide}  from 'vue'
import {nextTick, ref} from "vue";
let isShow = ref(true);
const  refreshPage = () => {
    isShow.value = false;
  nextTick(()=>{
    isShow.value = true;
  })
}

provide('reload',refreshPage)
</script>

<style xml:lang="scss" scoped>


</style>
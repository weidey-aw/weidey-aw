<script setup>
import Ikaros from "@/components/ikaros/Ikaros.vue";
import IndexHeader from "@/views/framework/Header.vue";
import IndexFooter from "@/views/framework/Footer.vue";
import IndexMain from "@/views/framework/Main.vue";
</script>


<template>
  <div class="common-layout">
    <el-container>

        <IndexHeader></IndexHeader>

        <IndexMain></IndexMain>

        <IndexFooter></IndexFooter>

      <!--   ikaros   -->
      <Ikaros></Ikaros>

    </el-container>
  </div>
</template>

<!--自建css类 -->
<style scoped>


</style>

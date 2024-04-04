<script setup>
import  url1 from "@/assets/img/banner/rand2.jpg"
import  url2 from "@/assets/img/banner/rand4.jpg"
import  url3 from "@/assets/img/banner/rand5.jpg"
import  url4 from "@/assets/img/banner/rand6.jpg"

const imageUrl = [
  {url: url1},
  {url: url2},
  {url: url3},
  {url: url4},

];
</script>

<template>
  <!--    轮播图      -->
  <el-carousel type="card" height="auto"    >
    <el-carousel-item v-for="item in imageUrl" :key="item"  style="height: auto;" >
      <!--        -->
      <h3 text="2xl" justify="center">
        <img :src="item.url" alt="" style="max-height: 280px; width: 100%" ref="image" />
      </h3>
    </el-carousel-item>
  </el-carousel>
  <!--    轮播图  end    -->
</template>

<style scoped>

</style>
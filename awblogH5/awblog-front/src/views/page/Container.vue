<script setup>
import IndexRanking from "@/views/container/sidebar/ranking.vue";
import Sidebar from "@/views/container/sidebar/sidebar.vue";
import Tables from "@/views/container/tables/Tables.vue";
import {defineAsyncComponent,ref} from "vue";

//显示内容栏
let display = ref(1);

const AsyncCarousel = defineAsyncComponent(() =>import("@/views/container/carousel/carousel.vue" ))

</script>

<template>
  <div class="main-container">

       <!-- 轮播图   -->
    <AsyncCarousel></AsyncCarousel>
      <!--  轮播图end  -->

    <!--按钮分栏    -->
    <el-row>
      <div class="container nav-scroll" style="padding-top: 20px;margin-bottom: 20px">
        <div class="nav-stacked">
          <el-button size="large" @click="display =1">文章</el-button>
          <el-button size="large" @click="display =2">相册</el-button>
          <el-button size="large" @click="display =3">视频</el-button>
          <el-button size="large" @click="display =4">主题</el-button>
        </div>
      </div>
    </el-row>
    <!--按钮分栏  end    -->

    <!-- 内容栏   -->
    <div class="row">
      <div class="col-md-9">
        <div class="grid-content ep-bg-purple mb-4 " >
          <!--  内容栏   -->
          <Tables :display="display" ></Tables>
        </div>
      </div>
      <div class="col-md-3">
        <div class="grid-content ep-bg-purple-light ">
          <!--  侧边栏   -->
          <Sidebar></Sidebar>
        </div>
        <div class="grid-content ep-bg-purple-light " style="margin-top: 25px">
          <!--  侧边栏   -->
          <indexRanking></indexRanking>
        </div>
      </div>
    </div>
    <!-- 内容栏 end   -->
  </div>
</template>

<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>



<!--标签css -->
<style scoped>
.main-container {
  width: 1200px;

  .grid-content {

    .ep-bg-purple {
      padding: 5px;

    }
    .ep-bg-purple-light {
      padding: 5px;
    }
  }
}




.grid-content {
  border-radius: 4px;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.nav-scroll::-webkit-scrollbar {
  display: none;
}

</style>


<!--公共css类-->
<style scoped>
.font-12 {
  font-size: 12px;
  font-weight: 700;
  line-height: 40px;
  text-align: right;
}
.el-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
}
</style>
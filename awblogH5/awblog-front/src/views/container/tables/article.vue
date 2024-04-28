<script setup >
import Empty from "@/views/container/tables/empty.vue";
import {ZoomIn} from "@element-plus/icons-vue";
import {getArticlePage} from "@/api/client/atlas";
import {useRouter} from "vue-router";
import {ref} from "vue";
const router = useRouter();
// 环境配置
const env = import.meta.env;
let isData = ref(false);
const  dataArticle =ref([])
const baseUrl = env.VITE_APP_APT_BASEURL;

//请求文章数据
function getArticleList(){
        getArticlePage().then(res=>{
         dataArticle.value = res.rows;
           isData.value = true;
      })
}
//跳转到文章详情
function articleToDetail(id){
        router.push({
          path:"/index/article",
          query:{
            id:id
         }
        })
}
//图片链接拼接
function articleImg(baseUrl,covers){
      return baseUrl+covers;
}
getArticleList();
</script>

<template>
  <div>
  <div v-if="isData" >
    <el-card v-for="(item,index) in dataArticle" :key="dataArticle.id" @click="articleToDetail(item.id)"   style=" margin-bottom: 30px;" >
      <div class="subject-card row"  >
       <div  class="col-5">
          <div class="img"  >
             <img :src="articleImg(baseUrl,item.covers)" alt="404NotFound"/>
          </div>
      </div>
       <div class="col-7 p-0 " >
       <div class="contents" >
        <div class="title" >{{item.title}}</div>
        <div class="align-items-center" >
        </div>
         <div class="align-items-center" >
           <p> {{item.synopsis}}</p>
         </div>
         <div class="align-items-center" >
           <div class="" style="width: 80%"> <el-button color="#f3d19e" class="w-100" style="" ><span style="color:#e6a23c;" ><el-icon><ZoomIn /></el-icon >查看更多</span> </el-button> </div>
         </div>
       </div>
      </div>
       </div>
    </el-card>
  </div>
    <div v-else>
      <empty></empty>
    </div>
  </div>
</template>

<style scoped>

</style>
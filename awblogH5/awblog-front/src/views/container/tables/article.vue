<script>
import Empty from "@/views/container/tables/empty.vue";
import {ChatLineSquare, Clock, Pointer, View, ZoomIn} from "@element-plus/icons-vue";

export default {
  name: "articled",
  components: {Pointer, ChatLineSquare, Clock, View, ZoomIn, Empty},
  data(){
    return{
      isData:false,
     dataArticle :[{

     }]
    }
  },
  created(){
    this.getArticleList()
  },
  methods:{
    //请求文章数据
    getArticleList(){
      // selectPageArticles(0,4).then(res=>{
      // if(res.code==200){
      //   this.dataArticle = res.data.records;
      //  }else if (this.dataArticle!=null){
      //   this.isData =true;
      // }
      // })
    },
    //跳到详细页面
    articleToDetail(id){
    }
  }
}
</script>
<template>
  <div>
  <div v-if="isData" >
    <el-card class="box-card"  v-for="(item,index) in dataArticle" :key="dataArticle.id" @click="articleToDetail(item.id)"   style=" margin-bottom: 30px;" >
      <div class="row box-card"  >
       <div  class="col-5  ">
          <div  class="img"  >
             <img :src="item.coverImg" alt="404NotFound"/>
          </div>
      </div>
       <div class="col-7 p-0 " >
       <div class="contents" >
        <div class="title" >{{item.title}}</div>
        <div class="align-items-center" >
          <span> <el-icon><Pointer /></el-icon> 点赞 : {{item.upvote}} </span>
          <span><el-icon><ChatLineSquare/></el-icon> 评论 : {{item.review}} </span>
          <span> <el-icon><Clock /></el-icon> 时间 : {{item.creation}}  </span>
          <span><el-icon><View /></el-icon> 访问 : {{item.visit}} </span>
        </div>
         <div class="align-items-center" >
           <p> {{item.context}}</p>
         </div>
         <div class="align-items-center" >
           <div> <el-button  @click="articleToDetail(item.id)" color="#f3d19e"><span style="color:#e6a23c;width: 400px " ><el-icon><ZoomIn /></el-icon >查看更多</span> </el-button> </div>
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
.box-card{
  max-width: 100%;
  width: max-content;
  display: flex;
}

.img{
  max-width: 100%;
  max-height: 100%;
  img{
    width: 250px;
    height: 200px;
  }
}
.contents{
  .title{
    font-weight: 700!important;
    font-size: 20px;
  }
  .align-items-center{
    padding-top: .75rem!important;
    padding-bottom: .75rem!important;

    span{
      margin-left: 10px;
      //padding-right: 10px;
      font-size: 14px;
    }
    p{
      width: 445px;
      height: 45px;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 2;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  }
}
</style>
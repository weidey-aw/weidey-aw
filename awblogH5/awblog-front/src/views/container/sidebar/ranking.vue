<script>
import Index from "../../Index.vue";

export default {
  name: "indexRanking",
  computed: {
    Index() {
      return Index
    }
  },
  data(){
    return{
      isData:false,
      dataRanking :[{

      }]
    }
  },
  created() {
    this.getArticleRanking()
  },
  methods: {
    getArticleRanking() {
      // selectPageArticles(0,10).then(res=>{
      //   if(res.code=="200"){
      //     this.isData =true;
      //     this.dataRanking=  res.data.records
      //   }
      // })
    }
  }

}
</script>

<template>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
<!--          <span><img src="../../../assets/icon/ranking.png" style="margin-right: 10px" /></span>-->
          <p style="margin-bottom: 0 !important;" >
            <span>本月贡献榜</span>
          </p>
        </div>
      </template>

      <div class="RankingContent" v-if="isData" >
         <button v-for="(item,index) in dataRanking" :key="index" class="list-group-item list-group-item-action">{{ index+1 +"."+item.title}}</button>
      </div>

      <div class="RankingContent" v-else>
        <el-empty></el-empty>
      </div>

    </el-card>
</template>

<style scoped>
.card-header{
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.card-header span{
  font-size: 16px;
  font-weight: bold;
  color: #343a40!important;
  max-width: 100%;
  max-height: 100%;
}
.card-header img{
  width: 28px;
  height: 28px;
  border-radius: 50%;
}

.RankingContent{
  height: 300px;
}
</style>
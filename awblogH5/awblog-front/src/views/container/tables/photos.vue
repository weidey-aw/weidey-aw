<script>
import Empty from "@/views/container/tables/empty.vue";
import {selectPage} from "@/api/client/atlas";

export default {
  name: "photos",
  components: {Empty},
  data(){
    return{
      dataPhotos :[{
        img:{
          url:"src/assets/img/rand.jpg"
        }
      }],
      pageNum:0,
      pageSize:4,
      isContent:false
    }
  },
  created() {
    this.getPhotos();
  },
  methods: {
    getPhotos(){
      // selectPage(this.pageNum,this.pageSize).then(res=>{
      //    if(res.code == '200'){
      //      this.dataPhotos = res.data;
      //    }else if (this.dataPhotos != null){
      //      this.isContent =true;
      //    }
      //
      // })

    }
  }


}
</script>

<template>
  <div v-if="isContent">
    <div class="photos">
      <div class="body-photos" >
 <div  v-for="(item, index) in dataPhotos" :key="index" >
        <el-row span="12" >
          <el-card class="card"   >
            <div class="card-photos">
              <div class="card-photos-img">
                <el-carousel class="card-photos-img-carousel" height="200px" direction="vertical" :autoplay="true">
                  <el-carousel-item v-for="img in item.img" :key="img">
                    <img :src="img.url" alt=""/>
                  </el-carousel-item>
                </el-carousel>
              </div>
              <div class="card-photos-test"> <p ></p> </div>
              <div class="cover-shadow" >
                <span><span  ><i class="bi bi-eye"></i> {{item.Atlas.visit}} </span ><span class="m-2" ><i class="bi bi-chat-left"></i>{{item.Atlas.upvote}}</span></span>
                <span>{{item.Atlas.title}}</span>
              </div>
            </div>
          </el-card>
        </el-row>
    </div>

      </div>
    </div>
  </div>
  <div v-else>
    <empty></empty>
  </div>
</template>

<style scoped>

.body-photos{
  display: flex;
  //justify-content: space-around;
  //flex-wrap: wrap;
  //margin: 12px;
}

.card{
  //border-radius: 8px;
  margin-bottom: 20px;
}

.card-photos{
  //border-radius: 12px;

  .card-photos-img{
    max-height: 100%;
    max-width: 100%;
    .card-photos-img-carousel{
      width: 320px;
      height: 200px;
      border-radius: 8px;
    }
  }

  .card-photos-test{
    p{
      color:rgb(108, 117, 125);
      margin-bottom: 0rem !important;
    }
  }

  .cover-shadow{
    position: absolute;
    bottom: -1px;
    left: 20px;
    z-index: 2;
    box-sizing: border-box;
    padding: 16px 8px 6px;
    width: 90%;
    height: 38px;
    border-bottom-right-radius: 6px;
    border-bottom-left-radius: 6px;
    //color: #fff;
    font-size: 13px;
    line-height: 18px;
    opacity: 1;
    display: -webkit-flex;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
</style>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

</style>
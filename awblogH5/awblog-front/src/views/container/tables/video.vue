

<script setup>
import {reactive, ref} from 'vue'
import Empty from "@/views/container/tables/empty.vue";
let isContext =  ref(false);
let  dataVideo = ref({});



</script>


<template>
  <div v-if="isContext" >
    <div class="video">
      <div class="body-video" >
        <el-row span="12">
          <el-card class="card" >
            <div class="card-video">
              <div>
                <video width="320" height="240" controls>
                  <source src="http://localhost:9090/file/80d4d43bc3be43b4860cdc03e47d20ff.mp4" type="video/mp4">
                </video>
              </div>
            </div>
          </el-card>
        </el-row>
      </div>
    </div>
  </div>
  <div v-else>
    <empty></empty>
  </div>
</template>

<style scoped>

.body-video{
  display: flex;
  justify-content: flex-start;
//margin: 12px;
}

.card{
//border-radius: 8px;
  margin-bottom: 20px;
}

.card-video{
//border-radius: 12px;

  .card-video-img{
    max-height: 100%;
    max-width: 100%;
    .card-video-img-carousel{
      width: 320px;
      height: 200px;
      border-radius: 8px;
    }
  }

  .card-video-test{
    p{
      color:rgb(108, 117, 125);
      margin-bottom: 0rem !important;
    }
  }

  .cover-shadow{
    position: absolute;
    bottom: 50px;
    left: 20px;
    z-index: 2;
    box-sizing: border-box;
    padding: 16px 8px 6px;
    width: 87%;
    height: 38px;
    border-bottom-right-radius: 6px;
    border-bottom-left-radius: 6px;
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
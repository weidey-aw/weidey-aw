<script  setup >
import {computed, ref} from "vue";
import {Edit, Message, Star} from "@element-plus/icons-vue";
const circleUrl =ref('https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
import avatarSvg from '@/assets/svg/avatar.svg';

// 全局状态
import {useUserStore} from "@/store/modules/user";
const store = useUserStore();

function isLogin(){
   return store.isLogin();
}

const filteredRemark =  computed(() => {
  if (store.remark === "" ||store.remark == null ){
    return  "还没有备注";
  }
  return store.remark ;
});

const filteredURL = computed(() => {
  if (store.avatar === "" || store.avatar == null) {
    return  avatarSvg;
  }
  else {
    return store.avatar;
  }
});

const filteredName = computed(() => {
   if(store.name === '' || store.name === null){
     return "游客";
   }else {
     return store.name;
   }
})
</script>

<template>
  <el-card class="box-card w-auto">
    <template #header>

      <div class="card-header" v-if="isLogin" >

        <div class="row" >
          <div class="col-4" >
             <img :src="filteredURL" style="margin-right: 10px" >
          </div>

          <div class="col-8" >
            <p style="margin-bottom: 0rem !important;" ><span>{{filteredName}}</span><p style="font-size: 10px;color:#8a8a8a" >{{filteredRemark}}</p></p>
          </div>
        </div>

        <div>
        <el-button style="margin-left: 12px" size="small" type="primary" >签到</el-button>
        </div>

      </div>


      <div class="card-header" v-else >
        <span><img :src="avatarSvg" style="margin-right: 10px; margin-bottom: 15px" ></span> <p style="margin-bottom: 0rem !important;" ><span>欢迎您</span> <p style="font-size: 10px;color:#8a8a8a" >还没有登录</p></p>
        <el-button style="margin-left: 12px" size="small" type="primary" >签到</el-button>
      </div>

    </template>



    <div style="display: flex; justify-content: space-around;" >
      <div class="card-button" ><el-button type="primary" :icon="Edit" circle /> <p>文章</p> </div>
      <div class="card-button" ><el-button type="info" :icon="Message" circle /> <p>消息</p>  </div>
      <div class="card-button" ><el-button type="warning" :icon="Star" circle /> <p>收藏</p>  </div>
    </div>
  </el-card>
</template>

<style scoped>


.card-header{
  display: flex;

  justify-content: space-between;
  //justify-content: space-around;
  //align-items: center;
  //padding: 5px;
}
.card-header span{
  //font-size: 20px;
  //font-weight: bold;
  //color: #343a40!important;
  //max-width: 100%;
  //max-height: 100%;
}
.card-header img{
  max-width: 40px;
  max-height: 40px;
  border-radius: 50%;
}

.card-button{
  //align-items: center!important;
  //margin-top: .75rem!important;
  //color:#8a8a8a;
  //font-size: 14px;

}

</style>
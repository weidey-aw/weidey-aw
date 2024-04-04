<script setup>
import Accounts  from "@/views/model/parsonal/accounts.vue";
import Information from "@/views/model/parsonal/information.vue";
import {ref} from "vue";

let display = ref(1);

function changeDisplay(display){
  return change(display);
}

function change(display){
  switch (display){
    case 1:
      return Information;
    case 2:
      return Accounts;
    default:
      return Information;
  }
}
</script>

<template>
  <div class="main-container">
<!-- begin   -->
  <div>
      <el-card>
        <div class="row">
          <div class="col-md-8" >
            <div class="row align-items-center" >
              <div class="col-auto">
                <el-avatar :size="70"  src="https://q1.qlogo.cn/g?b=qq&nk=2979918783&s=640" class="">
                </el-avatar>
              </div>
              <div class="col" >
                  <div class="m-1" ><span>Moon.</span> <span></span> </div>
                  <div class="m-1" ><span><el-progress :percentage="50" style="max-width: 200px" ></el-progress></span> </div>
                  <div class="m-1" ><span>测试</span> <span>测试</span>  </div>
                  <div class="m-1" > <span>测试文字测试文字测试文字</span> </div>
              </div>
            </div>
          </div>
          <div class="col-md-4 d-flex justify-content-end"></div>
        </div>
      </el-card>
  </div>
<!-- end -->

<!-- begin  -->
    <div class="container" style="margin-top:25px" >
      <div class="">
        <el-button size="small" @click="display=1" > 基本信息 </el-button>
        <el-button size="small" @click="display=2" > 账号设置 </el-button>
      </div>
    </div>
<!-- end -->


<!-- begin  -->
  <div class="container" style="margin-top:25px" >
    <Transition name="fade" mode="out-in" >
      <component :is="changeDisplay(display)" ></component>
    </Transition>
  </div>
<!-- end -->


  </div>
</template>

<style scoped>
.main-container {
  width: 1200px;
}
.fade-enter,
.fade-leave-to {
  transition: opacity 0.2s;
}

.fade-enter-active,
.fade-leave-active {
  opacity: 0;
}



</style>
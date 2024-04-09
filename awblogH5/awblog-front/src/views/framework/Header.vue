<script setup >
import {inject, ref} from "vue";
import Login from "@/views/model/user_diaog/login.vue";
import Forget from "@/views/model/user_diaog/forget.vue";
import Register from "@/views/model/user_diaog/register.vue";
import favicon from "@/assets/favicon.ico";
let dialogVisible = ref(false);
let dialogValue = ref("login");
const reload = inject("reload");
let drawer =  ref(false);
// 全局状态
import {useUserStore} from "@/store/modules/user";
const store = useUserStore();


function register_f(){
  dialogVisible.value =true;
  dialogValue.value = "register";
}
function login_f(){
  dialogVisible.value =true;
  dialogValue.value = "login";
}
function outLogin(){
  store.FedLogOut();
  reload();
}
function  isLogin(){
  return store.isLogin
}
const changeDrawer = () =>  drawer.value = !drawer.value;

//弹出窗
function dialog(newValue){
  dialogValue.value = newValue.dialogValue;
  dialogVisible.value =newValue.dialogVisible;
}
//关闭弹出窗
function handleClose(){
  dialogVisible.value =false;
}
</script>

<template>
  <el-header class="header">
        <div class="container user-select-none" >
          <nav class="navbar navbar-expand-lg">
            <div class="container-fluid row ">
              <div class="col-6" >
                <button @click="changeDrawer"   class="navbar-toggler" type="button" data-bs-target="#navbarSupportedContent">
                  <span class="navbar-toggler-icon" style="font-size: 12px; border: none;" ></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="">
                      <a class="navbar-brand" href="/index"><el-image :size="32" :src="favicon" class="align-items-center"/></a>
                    </li>

                    <li class="">
                      <a class="nav-link active" aria-current="page" href="#">帮助</a>
                    </li>

                    <li class="">
                      <a class="nav-link active" aria-current="page" href="#">关于</a>
                    </li>
                  </ul>
                </div>
              </div>
              <div  class="col-6">
                <div class="grid-content">
                  <div class="ep-bg-purple-light">
                    <div v-if="isLogin()" style="display: flex;justify-content: flex-end;">
                      <el-dropdown>
                        <div class="el-dropdown-link" style="display: inline-flex" >
                          <div class="m-1 align-items-center" >
                            {{ store.name }}
                          </div>
                          <div class="">
                            <el-avatar :src="store.avatar" :size="42" > </el-avatar>
                          </div>
                        </div>

                        <template #dropdown>
                          <el-dropdown-menu>
                            <el-dropdown-item @click="$router.push('/login')" ><el-icon><Operation /></el-icon>控制台</el-dropdown-item>
                            <el-dropdown-item  @click="$router.push('/index/personal')"><el-icon><Avatar/></el-icon>个人中心</el-dropdown-item>
                            <el-dropdown-item @click="outLogin" ><el-icon><SwitchButton /></el-icon>退出登录</el-dropdown-item>
                          </el-dropdown-menu>
                        </template>
                      </el-dropdown>
                    </div>
                    <div v-else style="display: flex;justify-content: flex-end;" >
                      <strong class="small  " @click="register_f" style="margin-right: 5px" >注册</strong>
                      <strong class="small" @click="login_f" >登录</strong>
                    </div>
                  </div>
                </div>
                </div>
              </div>
          </nav>
        </div>

    <!--   登录注册对话框   -->
    <el-dialog :model-value="dialogVisible"
               :close-on-click-modal="false"
               :before-close="handleClose"
               title="登录获取更多权益"
               style="border-radius: 5px;  max-width: 100%;width: max-content;"
               width="100%"
               :lock-scroll="false"
    >
      <div  v-if="dialogValue==='login'" > <login  @update:dialog="(newVal)=>{dialog(newVal)}"></login></div>
      <div  v-if="dialogValue==='register'" > <register  @update:dialog="(newVal)=>{dialog(newVal)}" ></register></div>
      <div  v-if="dialogValue==='forget'" > <forget @update:dialog="(newVal)=>{dialog(newVal)}" ></forget></div>
      <div  style="text-align: center; " ><span >登录注册即表示同意<span style="color:#40a9ff;" >用户协议</span></span></div>
    </el-dialog>

      <!-- 抽屉 -->
    <el-drawer v-model="drawer"   direction="ltr"  :with-header="false">
      <span>Hi there!</span>
    </el-drawer>

  </el-header>
</template>

<style scoped>
.header {
  position: fixed;
  height: 52px;
  width: 100%;
  z-index: 10;
  background-color: #ffffff;
  box-shadow: 0 5px 40px #113a5d1a;
  display: flex;
  align-items: center;
  justify-content: center;
}

.el-row:last-child {
  margin-bottom: 0;
}
.navbar-nav {
  font-size: 13px;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
}
li {
  padding-left: 10px;
  padding-right: 10px;
}
.grid-content {
  border-radius: 4px;
}

</style>

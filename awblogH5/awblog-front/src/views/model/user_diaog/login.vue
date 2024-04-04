<script lang="ts" setup>
import {inject, reactive, ref} from "vue";
import GetCodeButton from "@/components/client/common/GetCodeButton.vue";
import {ElMessage, ElNotification} from "element-plus";
import type {FormInstance, FormRules} from "element-plus";
import {loginApi,emailLoginApi} from '@/api/system/login';


const loginRef = ref<FormInstance>();
const emailRef = ref<FormInstance>();


let  isUsernameOrEmail = ref(true);
const updateDialog = 'update:dialog';
const emits = defineEmits([updateDialog]);
const refreshPage = inject("reload");


interface loginFormData {
  username:string,
  password:string,
}

interface emailLoginFromData{
  email:string,
  code:string,
}


const  loginForm = ref<loginFormData>({
  username: '',
  password: '',
});

const  emailLoginFrom = ref<emailLoginFromData>({
  email: '',
  code: '',
});


const loginRules = reactive<FormRules<loginFormData>>({
  username: [
      { required: true, message: '请输入账号', trigger: 'blur' },
      { min: 4, max: 16, message: '用户名的长度只能在4到16', trigger: 'blur' },
      { pattern: /^[a-zA-Z0-9_-]{4,16}$/, message: '用户名只能是字母，数字，下划线，减号',trigger: 'change' }
  ],
  password: [
       { required: true, message: '请输入密码', trigger: 'blur' },
       { min: 6, max: 24, message: '密码的长度只能在4到24', trigger: 'blur' },
       { pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,24}$/, message: '密码必须是由6-24位字母+数字组成',trigger: 'change' }
  ],
})
const emailRules = reactive<FormRules<emailLoginFromData>>({
  email: [
      { required: true, message: '请输入邮箱', trigger: 'blur' },
      { pattern: /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/, message: '请输入正确的邮箱格式',trigger: 'change' }
  ],
  code: [
      {  required: true ,message: '请输入验证码', trigger: 'blur' },
      { min: 4, max: 6, message: '请输入正确格式', trigger: 'blur' },
      { pattern: /^\d+$/, message: '请输入数字',trigger: 'change' }
  ],
})


function  handleLogin(){
  if(!isUsernameOrEmail.value){
    // 处理用户名登录
    loginRef.value.validate((valid:boolean)=>{
      if (valid) {
        let {password,username} = loginForm.value;
        loginApi({username,password}).then(res=>{
          if (res.code === "200"){
            login_success(res)
          }else {
            const mag = res.msg;
            ElMessage.error(mag);
          }
        })
      }
    })
  }else{
    // 处理邮箱登录
    emailRef.value.validate((valid:boolean)=>{
      if (valid) {
        let {email,code} = emailLoginFrom.value;
        emailLoginApi({email,code}).then(res=>{
          if (res.code === "200"){
            login_success(res)
          }else {
            const msg = res.msg;
            ElMessage.error(msg);
          }
        })
      }
    })
  }
}

const dialog = ref({
  dialogValue:String,
  dialogVisible:Boolean,
})
function goTo(dialogValue,dialogVisible){
  dialog.dialogValue =dialogValue
  dialog.dialogVisible =dialogVisible
  return  dialog
}

function login_success(res){
  localStorage.setItem("user",JSON.stringify(res.data)) //保存用户信息到浏览器
  localStorage.setItem("token",JSON.stringify(res.data.token)) //保存用户信息到浏览器
  localStorage.setItem("tokenStartTime", String(new Date().getTime()))  //设置页面超时时间
  emits(updateDialog, goTo('login',false))
  ElNotification( {title:"Success", message: '登录成功',type: 'success' })
  refreshPage()
}


</script>

<template>
    <div   class="container-fluid"  style="max-width: 800px" >
    <div   class="col-12 h-100" style="width: auto" >
      <div  class="login_form"  v-if="isUsernameOrEmail" >
        <div class="login_title" style="padding-bottom: 10px;display: flex;justify-content: space-between;"  >
          <span> 验证码登录 </span>
          <span @click="emits(updateDialog, goTo('register',true))" > 还没有账号？去注册 </span>
        </div>
        <el-form  :model="emailLoginFrom" :rules="emailRules" ref="emailRef"  size="large" >
          <el-form-item  prop="email" >
            <div class="col-12" >
              <el-input  placeholder="请输入邮箱"  v-model="emailLoginFrom.email" >
                <template #append>@qq.com</template>
              </el-input>
            </div>
          </el-form-item>

          <el-form-item  prop="code" >
            <div class="col-12" style="display: inline-flex; align-items: center;justify-content: center;" >
                <el-input placeholder="获取验证码" style="max-width: 70%"  v-model="emailLoginFrom.code" ></el-input>
              <div  style="margin-left: 8px" >
                <GetCodeButton :email="emailLoginFrom.email"  ></GetCodeButton>
              </div>
            </div>
          </el-form-item>
        </el-form>
      </div>


      <div  class="login_form" v-else >
        <div class="login_title" style="padding-bottom: 10px;display: flex;justify-content: space-between;"  >
          <span> 密码登录 </span>
          <span @click="emits(updateDialog, goTo('register',true))" > 还没有账号？去注册 </span>
        </div>
        <el-form :model="loginForm" ref="loginRef" :rules="loginRules"  size="large" >
          <el-form-item prop="username"  >
            <el-input  placeholder="请输入账号" v-model="loginForm.username" >
            </el-input>
          </el-form-item>

          <el-form-item prop="password" >
            <div class="col-12" style="display: inline-flex; align-items: center;justify-content: center;" >
              <el-input placeholder="请输入密码" type="password" style="max-width: 70%" v-model="loginForm.password"  ></el-input>
              <div  style="margin-left: 8px"> <el-button type="primary"  @click="emits(updateDialog, goTo('forget',true))" >忘记密码？</el-button></div>
            </div>
          </el-form-item>
        </el-form>
      </div>
<!--      -->
      <div  class="login_button"  style="text-align: center">
        <el-button   type="primary"  style="width: 100%; font-size: 12px; " @click="handleLogin"  >登录</el-button>
      </div>
<!--      -->
      <div  class="login_other"  style="display: flex ;justify-content: space-between; padding: 10px 10px 10px
      10px" >
        <span  style="display: flex" >其他登录：  <span> <img src="../../../assets/icon/QQ.png" alt="" style="height: 20px;width: 20px ; " >  </span>   </span>  <span v-if="isUsernameOrEmail" @click="isUsernameOrEmail=!isUsernameOrEmail"  > 密码登录 </span> <span v-else @click="isUsernameOrEmail=!isUsernameOrEmail" >  邮箱登录 </span>
      </div>
    </div>
<!--    <div   class="col-5  h-100  " style=" height: 400px; padding-left: 50px; border-left: #8a8a8a 1px solid; ">-->
<!--      <span>扫码登录</span>-->
<!--      <div style="border: #001529 1px solid;  max-width: 140px; height: 140px" >-->
<!--        <img src="../../../../assets/img/二维码.png" alt="" srcset="" style="max-width: 100% ; max-height: 100% ; height: 120px; width: 120px ;   margin: 10px  " >-->
<!--      </div>-->
<!--      <div>打开博客APP</div>-->
<!--      <div>点击“我-左上角扫一扫”登录</div>-->
<!--    </div >-->
  </div>
</template>

<style scoped>

</style>
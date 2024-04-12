<script lang="ts"  setup>

import {ref} from "vue";
import {reactive} from "vue";
import {ElNotification, FormInstance, FormRules} from "element-plus";
import GetCodeButton from "@/components/client/common/GetCodeButton.vue";
const updateDialog = 'update:dialog';
const emits = defineEmits([updateDialog]);
const registerRef = ref<FormInstance>();

const dialog = ref({
  dialogValue:String,
  dialogVisible:Boolean,
});

interface registerData {
  username:string,
  password:string,
  passwords:string,
  nickname:string,
  email:string,
  code:string,
  type:string
}


const registerFrom = ref<registerData>({
  username:"",
  password:"",
  passwords:"",
  nickname:"",
  email:"",
  code:"",
  type:"REGISTER"
})

const loginRules = reactive<FormRules<registerData>>({
  username: [
      { required: true, message: '请输入账号', trigger: 'blur' },
      { min: 4, max: 16, message: '用户名的长度只能在4到16', trigger: 'blur' },
      { pattern: /^[a-zA-Z0-9_-]{4,16}$/, message: '用户名只能是字母，数字，下划线，减号',trigger: 'change' }
  ],
  password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      { min: 6, max: 24, message: '密码的长度只能在4到24', trigger: 'blur' },
      { pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,24}$/, message: '密码必须是由6-24位字母+数字组成',trigger: 'change' }
  ] ,
  passwords: [
      { required: true, message: '请输入确认密码', trigger: 'blur' },
      { min: 6, max: 24, message: '密码的长度只能在4到24', trigger: 'blur' },
      { pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,24}$/, message: '密码必须是由6-24位字母+数字组成',trigger: 'change' }
  ],
  nickname: [
      { required: true, message: '请输入昵称', trigger: 'blur' },
      { min: 1, max: 32, message: '昵称长度不符合要求', trigger: 'blur' },
  ],
  email: [
      { required: true, message: '请输入邮箱', trigger: 'blur' },
      { pattern: /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/, message: '请输入正确的邮箱格式',trigger: 'change' }
  ],
  code: [
      { required: true, message: '请输入验证码', trigger: 'blur' },
      { min: 4, max: 6, message: '请输入正确格式', trigger: 'blur' },
       { pattern: /^\d+$/, message: '请输入数字',trigger: 'change' }
  ],
})

function goTo(dialogValue,dialogVisible){
  dialog.dialogValue =dialogValue
  dialog.dialogVisible =dialogVisible
  return  dialog
}


function register_fun(){
  // 处理用户名登录
  registerRef.value.validate((valid:boolean)=>{
    if (valid) {
      let data = {
        username:registerFrom.value.username,
        password:registerFrom.value.password,
        passwords:registerFrom.value.passwords,
        nickname:registerFrom.value.nickname,
        email:registerFrom.value.email,
        code:registerFrom.value.code
      }
      // registerEmailApi(data).then(res=>{
      //   if (res.code === "200"){
      //     login_success(res)
      //   }else {
      //     const mag = res.msg;
      //     ElMessage.error(mag);
      //   }
      // })
    }
  })
}

function login_success(res){
  localStorage.setItem("user",JSON.stringify(res.data)) //保存用户信息到浏览器
  localStorage.setItem("token",JSON.stringify(res.data.token)) //保存用户信息到浏览器
  localStorage.setItem("tokenStartTime", String(new Date().getTime()))  //设置页面超时时间
  emits(updateDialog, goTo('login',false))
  ElNotification( {title:"Success", message: '登录成功',type: 'success' })
}


</script>

<template>
  <div class="container-fluid">
    <el-form :model="registerFrom" ref="registerRef"  :rules="loginRules"   >
    <div class="row" >
       <div class="col-md-6" >
         <div class="form-group mb-3 ">
           <el-form-item prop="nickname" >
             <label  class="form-label required"  >昵称</label>
             <el-input placeholder="请输入昵称" v-model="registerFrom.nickname"  ></el-input>
           </el-form-item>
         </div>
       </div>
      <div class="col-md-6" >
        <div class="form-group mb-3 ">
          <el-form-item prop="username" >
            <label  class="form-label required"  >账号</label>
            <el-input placeholder="请输入账号" v-model="registerFrom.username" ></el-input>
          </el-form-item>
        </div>
      </div>
    </div>

    <div class="row" >

      <div class="col-md-6" >
        <div class="form-group mb-3 ">
          <el-form-item prop="password" >
            <label  class="form-label required"  >密码</label>
            <el-input placeholder="请输入密码"  type="password" v-model="registerFrom.password"   ></el-input>
          </el-form-item>
        </div>
      </div>


      <div class="col-md-6" >
        <div class="form-group mb-3 ">
          <el-form-item  prop="passwords" >
            <label  class="form-label required"  >确认密码</label>
            <el-input placeholder="请确认密码"  type="password"  v-model="registerFrom.passwords"  ></el-input>
          </el-form-item>
        </div>
      </div>


    </div>
    <div class="row" >

      <div class="col-md-6" >
        <div class="form-group mb-3 ">
          <el-form-item  prop="email" >
            <label  class="form-label required"  >联系方式</label>
            <el-input placeholder="请输入QQ邮箱" v-model="registerFrom.email"  ></el-input>
          </el-form-item>
        </div>
      </div>


      <div class="col-md-6" >
        <div class="form-group mb-3 ">
          <div  class="form-item"  style="line-height: 32px;" >
            <label class="form-label required" >验证码</label>
            <div class="row" >
              <div class="col-7" >  <el-input placeholder="请输入验证码" v-model="registerFrom.code"  ></el-input></div>
              <div class="col-4"  >  <GetCodeButton :email="registerFrom.email" :type="registerFrom.type" ></GetCodeButton> </div>
            </div>
          </div>
          </div>
      </div>
    </div>
    <div class="modal-footer d-flex justify-content-center" >
      <el-button @click="emits(updateDialog, goTo('login',true))"  >返回登录</el-button>
      <el-button @click="register_fun">注册</el-button>
    </div>
    </el-form>
  </div>
</template>
<style scoped>


</style>
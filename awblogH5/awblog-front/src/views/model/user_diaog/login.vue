<script lang="ts" setup>
import {inject, reactive, ref} from "vue";
import GetCodeButton from "@/components/client/common/GetCodeButton.vue";
import {ElNotification} from "element-plus";
import type {FormInstance, FormRules} from "element-plus";
import {getCodeImg} from "@/api/client/login";

// 全局状态
import {useUserStore} from "@/store/modules/user";
const store = useUserStore();

const loginRef = ref<FormInstance>();
const emailRef = ref<FormInstance>();


const emits = defineEmits([updateDialog]);// 更新父类开关
const updateDialog = 'update:dialog'; // 更新开关


const refreshPage = inject("reload"); //刷新页面
let codeUrl = ref(null); //验证码图片
const captchaEnable = ref(false);// 验证码开关
let  isUsernameOrEmail = ref(true);  //登录和注册切换

//弹窗的属性
const dialog = ref({
  dialogValue:String,
  dialogVisible:Boolean,
})


interface loginFormData {
  username:string,
  password:string,
  code:string,
  uuid:string,
}

interface emailLoginFromData{
  email:string,
  code:string,
}


const  loginForm = ref<loginFormData>({
  username: '',
  password: '',
  code:'',
  uuid:'',
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
  code:[
    { required: true, message: '请输入验证码', trigger: 'blur' },
  ],
  uuid:[]
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


/**
 *  获取验证码
 */
const getCode = () => {
    getCodeImg().then(res=>{
      captchaEnable.value = res.captchaEnable === undefined ? true: res.captchaEnable;
      if (captchaEnable.value){
        codeUrl.value  = "data:image/gif;base64," + res.img;
        loginForm.value.uuid = res.uuid;
      }
    })
}

/**
 *
 */


/**
 * 登录成功
 */
function login_success(){
  //获取用户信息,并保存在store中
  store.GetInfo();
  emits(updateDialog, goTo('login',false)); //关闭弹窗
  ElNotification( {title:"Success", message: '登录成功',type: 'success' });
  refreshPage() //刷新页面
}

/**
 * 点击登录按钮
 */
function  handleLogin(){
  if (isUsernameOrEmail.value){
      loginRef.value.validate((valid:boolean)=>{
        if (valid) {
          let {password,username,code,uuid} = loginForm.value;
            store.Login({password,username,code,uuid}).then(()=>{
              login_success();
          }).catch(()=>{
                if (captchaEnable.value) {
                     getCode();
                }
            })

        }
      })
  }else {
   //处理邮箱登录
  }
}

/**
 * 弹窗切换
 */
function goTo(dialogValue,dialogVisible){
  dialog.dialogValue =dialogValue
  dialog.dialogVisible =dialogVisible
  return  dialog
}

/**
 * 加载弹窗时执行的方法
 */
const load = () => {
  getCode()
}
load();

</script>

<template>
    <div   class="container-fluid"  style="max-width: 800px" >
    <div   class="col-12 h-100" style="width: auto" >
      <div  class="login_form"  v-if="isUsernameOrEmail" >
        <div class="login_title" style="padding-bottom: 10px;display: flex;justify-content: space-between;"  >
          <span @click="emits(updateDialog, goTo('forget',true))" > 忘记密码 </span>
          <span @click="emits(updateDialog, goTo('register',true))" > 还没有账号？去注册 </span>
        </div>
        <el-form :model="loginForm" ref="loginRef" :rules="loginRules"  size="large" >

          <el-form-item prop="username"  >
            <div class="col-12" style="display: inline-flex; align-items: center; justify-content: center;" >
            <el-input   placeholder="请输入账号" v-model="loginForm.username"  >
            </el-input>
            </div>
          </el-form-item>

          <el-form-item prop="password" >
            <div class="col-12" style="display: inline-flex; align-items: center;justify-content: center;" >
              <el-input placeholder="请输入密码" type="password"  v-model="loginForm.password"  ></el-input>
            </div>
          </el-form-item>

          <el-form-item prop="code">
            <div class="col-12" style="display:inline-flex;" >
              <el-input placeholder="请输入验证码"  style="max-width: 70%"  v-model="loginForm.code" ></el-input>
              <div  style="margin-left: 8px">
                <div class="login-code">
                  <img :src="codeUrl" @click="getCode" class="login-code-img" alt="img"/>
                </div>
              </div>
            </div>
          </el-form-item>
        </el-form>
      </div>

      <div  class="login_form" v-else >
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
            <div class="col-12" style="display: inline-flex; align-items: center;" >
              <el-input placeholder="获取验证码" style="max-width: 70%"  v-model="emailLoginFrom.code" ></el-input>
              <div  style="margin-left: 8px" >
                <GetCodeButton :email="emailLoginFrom.email"  ></GetCodeButton>
              </div>
            </div>
          </el-form-item>
        </el-form>
      </div>

      <!--      -->
      <div  class="login_button"  style="text-align: center">
        <el-button  type="primary"  style="width: 100%; font-size: 12px; " @click="handleLogin()"  >登录</el-button>
      </div>
       <!--      -->
      <div  class="login_other" style="display: flex ;justify-content: space-between; padding:10px" >
        <span  style="display: flex" >其他登录： <span><img src="../../../assets/icon/QQ.png" alt="" style="height: 20px;width: 20px ; " >  </span>   </span>
        <span v-if="isUsernameOrEmail" @click="isUsernameOrEmail=!isUsernameOrEmail"  > 密码登录 </span> <span v-else @click="isUsernameOrEmail=!isUsernameOrEmail" >  邮箱登录 </span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-code {
  width:100%;
  height: 100%;
  max-height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
</style>
<script lang="ts"  setup>
import {reactive, ref} from "vue";
import GetCodeButton from "@/components/client/common/GetCodeButton.vue";
import type {FormInstance, FormRules} from "element-plus";
import {ElMessage} from "element-plus";


const updateDialog = 'update:dialog';
const  emits = defineEmits([updateDialog]);
const forgetRef = ref<FormInstance>();

const dialog = ref({
  dialogValue:String,
  dialogVisible:Boolean,
});
function goTo(dialogValue,dialogVisible){
  dialog.dialogValue =dialogValue
  dialog.dialogVisible =dialogVisible
  return  dialog
}


interface forgetFormData {
  email:string,
  code:string,
  phone:string,
  password:string,
  passwordEn:string,
  type:string
}


const  forgetForm = ref<forgetFormData>({
  email:"",
  code:"",
  phone:"",
  password:"",
  passwordEn:"",
  type:"FORGET_PASS"
});


const forgetRules = reactive<FormRules<forgetFormData>>({
  email: [
      { required: true, message: '请输入邮箱', trigger: 'blur' },
      { pattern: /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/, message: '请输入正确的邮箱格式',trigger: 'change' }
  ],
  code: [
     { required: true, message: '请输入验证码', trigger: 'blur' },
     { min: 4, max: 6, message: '请输入正确格式', trigger: 'blur' },
     { pattern: /^\d+$/, message: '请输入数字',trigger: 'change' }
  ],
  password:[
    { required: true, message: '请输入新的密码', trigger: 'blur' },
    { min: 6, max: 24, message: '密码的长度只能在4到24', trigger: 'blur' },
    { pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,24}$/, message: '密码必须是由6-24位字母+数字组成',trigger: 'change' }

  ],
  passwordEn:[
    { required: true, message: '确认密码', trigger: 'blur' },
    { min: 6, max: 24, message: '密码的长度只能在4到24', trigger: 'blur' },
    { pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,24}$/, message: '密码必须是由6-24位字母+数字组成',trigger: 'change' },
  ],
})

function forget_fun(){
  forgetRef.value.validate((valid:boolean)=>{
      if (valid){
        //发送请求
        let  params  = {
          email: forgetForm.value.email,
          code: forgetForm.value.code,
          password:forgetForm.value.password,
          passwordEn:forgetForm.value.passwordEn,
        }
        emits(updateDialog, goTo('forget',false))
        ElMessage.info("功能开发中...")

        // forgetPassword(params).then(res=>{
        //   const msg = res.msg;
        //   if (res.code ==="200"){
        //     ElMessage.success(msg)
        //     //关闭对话窗
        //     emits(updateDialog, goTo('forget',false))
        //   }else {
        //     ElMessage.error(msg)
        //   }
        // })
       }
  })
}


</script>

<template>
  <div  class="container-fluid">
    <el-form :model="forgetForm" :rules="forgetRules" ref="forgetRef"   >
    <div class="row" >
          <div class="col-md-6" >
            <div class="form-group mb-3 ">
              <el-form-item  prop="email">
              <label  class="form-label required">联系方式</label>
              <el-input placeholder="QQ邮箱" v-model="forgetForm.email"  ></el-input>
              </el-form-item>
            </div>
          </div>

        <div class="col-md-6" >
          <div class="form-group mb-3 ">
            <el-form-item  prop="code">
            <label class="form-label required" >验证码</label>
            <div class="el-input" >
              <el-input placeholder="请输入验证码"  v-model="forgetForm.code"  ></el-input>
              <GetCodeButton :email="forgetForm.email" :type="forgetForm.type"  >获取验证码</GetCodeButton>
            </div>
            </el-form-item>
          </div>
        </div>
    </div>
    <div class="row" >
      <div class="col-md-6" >
        <div class="form-group mb-3 ">
          <el-form-item  prop="password" >
          <label  class="form-label required"    >新的密码</label>
          <el-input placeholder="请输入新的密码" type="password"  v-model="forgetForm.password"    ></el-input>
          </el-form-item>
        </div>
      </div>
      <div class="col-md-6" >
        <div class="form-group mb-3 ">
          <el-form-item prop="passwordEn" >
          <label  class="form-label required"   >确认密码</label>
          <el-input placeholder="请确认新的密码"  type="password" v-model="forgetForm.passwordEn"   ></el-input>
          </el-form-item>
        </div>
      </div>

    </div>

    </el-form>

    <div class="modal-footer d-flex justify-content-center" >
      <el-button @click="emits(updateDialog, goTo('login',true))">返回登录</el-button>
      <el-button @click="forget_fun"  >重置密码</el-button>
    </div>
  </div>
</template>

<style scoped>

</style>
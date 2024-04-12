<script setup>
import {ref} from "vue";
import {ElMessage} from "element-plus";
import {getEmailCode} from "@/api/client/user";

let buttonValue = ref('获取验证码')
let buttonDisabled = ref(false)

const qqEmailRegex = /^\d{10}@qq\.com$/;

defineProps({
  email: String,
  type:{
    type: String,
    default: ''
  }
})

function clickButton(email,type) {
  if(verifyEmail(email)){
    disabled()
    getCode(email,type)
  }
}

function getCode(Email,Type) {
  if (buttonDisabled.value){
    let email = Email;
    let type = Type;
    // 获取验证码
    getEmailCode({email,type}).then(res=>{
      const msg = res.msg;
      ElMessage.success(msg);
    }).catch(err=>{
      console.log(err)
    })
  }else {
    ElMessage.error("请勿频繁发送验证码");
  }
}

function  verifyEmail(email){
  if (email === ""){
    ElMessage.error("邮箱不能为空");
    return false;
  }
  if (!qqEmailRegex.test(email)){
    ElMessage.error("邮箱格式不正确");
    return false;
  }
  return true;
}


function disabled(){
  buttonValue.value = '60s后重新获取'
  buttonDisabled.value = true
  let time = 60
  let timer = setInterval(() => {
    time--
    buttonValue.value = time + 's后重新获取'
    if (time === 0) {
      clearInterval(timer)
      buttonValue.value = '获取验证码'
      buttonDisabled.value =false
    }
  }, 1000)
}



</script>

<template>
  <el-button type="primary" @click="clickButton(email,type)" :disabled="buttonDisabled">{{buttonValue}}</el-button>
</template>

<style scoped>

</style>
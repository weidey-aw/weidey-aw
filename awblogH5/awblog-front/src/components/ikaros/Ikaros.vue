<script setup>
import { ref} from "vue";
import audio from "@/assets/audio/audio.json"
import {useUserStore} from "@/store/modules/user";

const store = useUserStore();
const ikaros = ref(null);
const chat = ref(null);
const chatText = ref(null);
let  isFinish = true;
let isShow= false;
const chatAudio = ref(audio);


const chatData =  ref({
  audio: "",
  text: ""
})
// 加载
const load = () => {
  const role =  store.roles;
   if(role.includes("admin")){
     isShow=true;
   }
}
load();
//点击
const  clickChat=()=>{
  //防止多击
  if (isFinish){
    isFinish = false
    //1.点击上下抖动
    ikaros.value.classList.toggle('animationend')
    setTimeout(()=>{
      ikaros.value.classList.remove('animationend')
    },500)

    //2.显示对话框
    chat.value.style.backgroundSize=140+'px';

    //3.显示文字
    readFileChat();
    chatText.value.textContent = chatData.value.text;

    //4.播放语言后隐藏
    setTimeout(()=>{
      chat.value.style.backgroundSize=0+'px';
      chatText.value.textContent = ''
      isFinish = true;
    },3000)
  }
}

//右键菜单
const showContextMenu = (e) => {
  e.preventDefault()
}

//读取
const readFileChat = () => {
  const random =  getRandomNumber(0,17);
  const type  =  chatAudio.value[random]
  if ( type.zh === undefined )
  {
    chatData.value.text = ""
    chatData.value.audio = ""
  }else {
      chatData.value.text = type.zh
  }
}


//不重复随机
function fisherYatesShuffle(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}
function getRandomNumber(min, max) {
  if (min > max) {
    return null;
  }
  let numbers = Array.from({ length: max - min + 1 }, (_, i) => i + min);
  numbers = fisherYatesShuffle(numbers);

  let index = 0;
    if (index < numbers.length) {
      return numbers[index++];
    }
    return 8; // 或者可以抛出错误，表示没有更多数字可用
}

</script>

<template>
  <el-affix position="bottom">
    <div id="ikaros" ref="ikaros" @click.right.native="showContextMenu($event)"  @click="clickChat" v-if="isShow"  >
      <div id="chat" ref="chat" >
        <div id="chatText" ref="chatText"></div>
      </div>
    </div>
  </el-affix>
</template>


<style>

 /*抖动动画*/
.animationend{
  animation: shake 0.5s infinite;
}

@keyframes shake {
  0% { transform: translateY(0); }
  25% { transform: translateY(-5px) translateZ(-5px); }
  75% { transform: translateY(5px) translateZ(5px); }
  100% { transform: translateY(0); }
}


#ikaros {
  position: fixed;
  bottom: 25px;
  left: 50px;
  z-index: 100;
  width: 100%;
  height:100%;
  max-width:200px;
  max-height: 200px;
  min-width: 150px;
  min-height: 150px;
  background-image: url("@/assets/ikaros.png");
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  background-size: 100%; /* 根据缩放比例调整背景图片宽度，高度保持100% */
  background-repeat: no-repeat;
  background-position: center center;
  transition: background-size 0.3s ease; /* 平滑过渡效果 */
}


#chat{
  position: absolute;
  bottom: 140px;
  width:100%;
  height:100%;
  max-width:140px;
  max-height: 140px;
  min-width: 70px;
  min-height: 70px;
  //background-image: url("@/assets/chat.jpg");
  background-size:0px; /* 根据缩放比例调整背景图片宽度，高度保持100% */
  background-repeat: no-repeat;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

#chatText{
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 12px;
  margin-bottom: 20px;
}

</style>

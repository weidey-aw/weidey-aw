<script setup>
import Article from "@/views/container/tables/article.vue";
import Photos from "@/views/container/tables/photos.vue";
import Video from "@/views/container/tables/video.vue";
import Subject from "@/views/container/tables/subject.vue";

defineProps({
  display:1,
})

function  changeDisplay(display){
  return change(display);
}
function change(display){
  switch (display){
    case 1:
      return Article
    case 2:
      return Photos
    case 3:
      return Video
    case 4:
      return Subject
  }
}
</script>


<template>


<div >
  <div>
    <transition name="fade"  mode="out-in"  >
      <keep-alive>
        <component :is="changeDisplay(display)" > </component>
      </keep-alive>
    </transition>
  </div>

</div>
</template>

<style scoped>
.fade-enter,
.fade-leave-to {
   transition: opacity 0.2s;
}

.fade-enter-active,
.fade-leave-active {
  opacity: 0;
}
</style>

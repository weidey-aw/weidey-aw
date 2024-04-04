<script>
import Empty from "@/views/container/tables/empty.vue";

export default {
  name: "subject",
  components: {Empty},
  data(){
    return{
      dataSubject :[{
      }]
    }
  },
}
</script>

<template>
  <div v-if="dataSubject.length===0">

  </div>
  <div v-else>
    <empty></empty>
  </div>
</template>

<style scoped>

</style>
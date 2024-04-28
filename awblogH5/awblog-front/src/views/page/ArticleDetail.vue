<template>

</template>
<script>
export default {
  name: "articled",
  data(){
    return{
      isData:false,
      articledId: this.$router.query.id
    }
  },
  created(){
    console.log(this.articledId);
  },
  methods:{



  }
}

</script>

<style scoped>

</style>
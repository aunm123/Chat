<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  import { mapGetters, mapMutations, mapActions } from 'vuex'
  export default {
    name: 'App',
    methods:{
      ...mapMutations([
        'saveMemberList',
      ]),
      ...mapGetters([
       'getUserInfo',
      ])
    },
    mounted(){

    },
    beforeDestroy() {
      this.saveMemberList();
    }
  }
</script>

<style scoped>
  #app {
    height: 100%;
    width: 100%;
    display: flex;
  }
</style>

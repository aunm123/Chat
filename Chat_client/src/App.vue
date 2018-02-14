<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  import { mapState, mapMutations, mapActions } from 'vuex'
  export default {
    name: 'App',
    methods:{
      ...mapMutations([
        'saveMemberList'
      ])
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

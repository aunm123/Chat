<template>
  <button class="group-row" @click="clickRow(data)">
    {{data.name}}
  </button>
</template>

<script>
  export default {
    name: "group-row",
    props: [
      'data',
    ],
    methods:{
      clickRow(group){
        this.$emit('groupClick',group);
      }
    }
  }
</script>

<style lang="less">
  .group-row {
    height: 60px;
    padding: 5px;
    margin: 7px;
    border: 1px solid red;
  }
</style>

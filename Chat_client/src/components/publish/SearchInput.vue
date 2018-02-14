<template>
  <div class="sinput">
    <input :type="type" :value=value placeholder="搜索"/>
  </div>
</template>

<script>

  export default {
    components: {},
    name: 'search',
    props:[
      'value',
      'type',
    ],
    data() {
      return {
      }
    },
  }
</script>

<style lang="less" >
  .sinput{
    height: 40px;
    display: flex;

    input {
      background-color: #eeeff4;
      font-size: 15px;
      margin: 6px;
      text-align: center;
      border-radius: 4px;
      border: none;
      flex: 1;
      letter-spacing: 1.3px;
      font-weight: 400;
    }
  }

  ::placeholder{
    text-align: center;
    color: #c3c3c8;
  }

</style>

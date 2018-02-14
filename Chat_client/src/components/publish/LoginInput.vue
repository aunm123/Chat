<template>
  <div class="linput">
    <input :type="type" :value=value />
  </div>
</template>

<script>

  export default {
    components: {},
    name: 'systemmessage',
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
  .linput{
    height: 55px;
    display: flex;

    input {
      background-color: transparent;
      font-size: 20px;
      color: white;
      border: none;
      border-bottom: 1px solid #d2b284;
      flex: 1;
      letter-spacing: 1.3px;
      font-weight: 400;
    }
  }

</style>

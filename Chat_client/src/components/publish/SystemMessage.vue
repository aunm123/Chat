<template>
  <div>
    <div class="sys-message">
      <div class="msg">
        {{value}}
      </div>
    </div>
  </div>
</template>

<script>

  export default {
    components: {},
    name: 'systemmessage',
    props:[
      'value'
    ],
    data() {
      return {
      }
    },
  }
</script>

<style scoped>
  .sys-message {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .sys-message .msg{
    color: white;
    background-color: lightgray;
    border-radius: 6px;
    font-size: 13px;
    padding: 3px 6px;
  }

</style>

<template>
  <div class="vnav" :style="mainColor">
    <div class="vnav-content" style="flex: 1;display: flex;">
      <div class="m-content"><slot name="leftc"></slot></div>
      <div class="title" :style="maintitleColor">{{title}}</div>
      <div class="m-content"><slot name="rightc"></slot></div>
    </div>
  </div>
</template>

<script>
  import BScroll from 'better-scroll'

  export default {
    components: {
    },
    props:[
      'title',
      'color',
      'titleColor',
    ],
    name: 'vnav',
    mounted: function () {

    },
    methods: {
    },
    data() {
      return {
        mainColor: { backgroundColor: this.color?this.color:"#fab27b" },
        maintitleColor: { fontWeight: 400,color: this.titleColor?this.titleColor:"#fff" },
      }
    }
  }
</script>

<style lang="less">
  .vnav {
    height: 44px;
    width: 100%;
    padding-top: 20px;
    display: flex;
  }
  .vnav-content{

    .title {
      display: flex;
      flex: 1;
      justify-content: center;
      align-items: center;
      font-size: 17px;
      font-weight: 300;
    }

    .right {
      width: 44px;
      padding: 0px 11px;
    }

  }

  .m-content{
    min-width: 62px;
    display: flex;
    justify-content: center;
    align-self: center;
  }
</style>

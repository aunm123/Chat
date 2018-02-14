<template>
  <div class="listrow">
    <a href="javascript:void(0);" @click="rowclick()">
      <div style="position: relative;">
        <img class="icon" :src="imgSrc"/>
        <mt-badge class="icon-badge" size="small" v-if="newNumCount>0">{{newNumCount}}</mt-badge>
      </div>
      <div class="row-content">
        <div class="row-title">
          {{roomData.roomName}}
          <div class="row-date">下午5:49</div>
        </div>
        <div class="row-detail">{{roomData.roomDescription}}</div>
      </div>
    </a>
  </div>
</template>

<script>
  import {mapGetters, mapMutations, mapActions} from 'vuex';
  import {Badge} from 'mint-ui';

  export default {
    components: {},
    name: 'lisrow',
    props: [
      'roomData',
    ],
    computed: {
      newMessage() {
        return this.$store.getters.getNewMessage;
      }
    },
    methods: {
      ...mapActions([]),
      ...mapGetters([
        'getNewMessage'
      ]),
      rowclick() {
        this.$emit('rowClick', this.roomData);
      },
      updataIconCount(newValue) {
        let vm = this;
        let newNumCount = 0;
        newValue.forEach(function (item) {
          let message = JSON.parse(item);
          if (message.mcode === "404") {
            let newRecord = JSON.parse(message.record);
            if (newRecord.roomId === vm.roomData.id) {
              newNumCount++;
            }
          }
        });
        vm.newNumCount = newNumCount;
      }
    },
    watch: {
      newMessage(newValue) {
        this.updataIconCount(newValue);
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        let rowidth = window.screen.width - 80;
        $('.row-detail').css('width', rowidth);
      });
      this.updataIconCount(this.newMessage);
    },
    data() {
      return {
        imgSrc: "/static" + this.roomData.roomIcon,
        newNumCount: 0,
      }
    },
  }
</script>

<style lang="less">
  .listrow {
    height: 66px;
    width: 100%;

    a {
      display: flex;
      justify-content: flex-start;
      align-items: center;
      padding: 3px;
    }

    .icon {
      width: 49px !important;
      height: 49px !important;
      border-radius: 50%;
    }

    .icon-badge {
      position: absolute;
      top: 0px;
      right: 7px;
    }

    .row-content {
      border-bottom: 1px solid #e7e7e7;
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: flex-start;

      .row-title {
        font-size: 18px;
        font-weight: 700;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        width: 100%;
        display: flex;
        justify-content: space-between;
      }

      .row-detail {
        font-size: 14px;
        font-weight: 400;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        color: #797979;
        line-height: 27px;
      }

      .row-date {
        font-size: 13px;
        font-weight: 300;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        color: #d2d2d2;
        text-align: right;
        display: flex;
        justify-self: flex-start;
        align-self: flex-start;
        margin-right: 7px;
      }
    }
  }

</style>

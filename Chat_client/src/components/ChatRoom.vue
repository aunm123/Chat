<template>
  <div class="root">
    <VNav :title="roomData.roomName" color="#ffffff" titleColor="#000">
      <div slot="leftc" class="backClass">
        <button @click="backBtnPress">返回</button>
      </div>
      <div slot="rightc" v-if="!hasService">
        <button @click="reqService()">
          <i class="iconfont addpeople_fill icon-addpeople_fill" style="font-size: 35px;"></i>
        </button>
      </div>
    </VNav>
    <!--<div class="icon">-->
    <!--<img :src="imageSrc" />-->
    <!--</div>-->
    <div class="bg"></div>
    <div ref="scroll" class="convestation">
      <div>
        <Message v-for=" msg in messageList" :msg="msg" :key="msg.id"/>
      </div>
    </div>
    <div class="message-input">
      <input type="text" placeholder="请输入内容" v-model="message"/>
      <button class="sendBtn" @click="sendMsg">发送</button>
    </div>
  </div>
</template>

<script>
  import VNav from '@/components/publish/VNav'
  import Message from './publish/Message'
  import BScroll from 'better-scroll'
  import {mapGetters, mapMutations, mapActions} from 'vuex'
  import {Toast} from 'mint-ui';

  export default {
    components: {
      Message,
      VNav
    },
    name: 'chat',
    mounted: function () {
      this.history()
        .then((res) => {
          this.messageList = res.records;
          this.$nextTick(function () {
            this.initScroll();
          });
        })
      this.getRoomData(this.roomId)
        .then((res) => {
          this.roomData = res.room;
          this.connectAction();
          this.updateMessage(this.newMessage, false);
        });

    },
    computed: {
      roomId() {
        return this.$route.params.roomid;
      },
      userInfo() {
        return this.$store.getters.getUserInfo;
      },
      newMessage() {
        return this.$store.getters.getNewMessage;
      }
    },
    watch: {
      newMessage(newValue) {
        this.updateMessage(newValue, true);
      }
    },
    methods: {
      ...mapGetters([
        'getUserInfo',
        'getNewMessage'
      ]),
      ...mapActions([
        'historyMessage',
        'getRoomData',
        'sendMessage',
        'requersService',
        'connectAction',
      ]),
      ...mapMutations([
        'updateNewMessage'
      ]),
      initScroll() {
        let height = window.screen.height - 42 - 64;
        $('.convestation').css("height", height + "px");
        this.convestationScroll = new BScroll(this.$refs.scroll, {
          click: true,
          bounce: true,
          bounceTime: 400,
          startX: 0,
          startY: 0,
          scrollY: true,
          scrollbar: true,
          mouseWheel: true,
        });
        this.scrollBottom();
      },
      scrollBottom() {
        this.$nextTick(function () {
          let height = window.screen.height - 42 - 64;
          let vheight = $('.convestation div').outerHeight();
          if (vheight > height) {
            if (this.convestationScroll) {
              this.convestationScroll.scrollTo(0, height - vheight);
              this.convestationScroll.refresh();
            }
          }
        });
      },
      backBtnPress() {
        this.$router.back();
      },
      history() {
        return this.historyMessage(this.roomId);
      },
      sendMsg() {
        let vm = this;
        if (this.message.trim().length > 0) {
          this.sendMessage({content: this.message, roomid: this.roomId})
            .then((res) => {
              vm.message = "";
            })
        } else {
          Toast({message: "不允许为空", position: 'bottom', duration: 2000});
        }
      },
      reqService() {
        this.requersService({userid: this.userInfo.id, roomid: this.roomId})
          .then((res) => {
            let newRecord = res.record;
            let newmessageList = [...vm.messageList, newRecord];
            vm.messageList = newmessageList;
            vm.message = "";
            this.scrollBottom();
          })
      },
      updateMessage(newValue, needsave) {
        let vm = this;
        let needUpdate = false;
        let newMsg = [];
        let newmessageList = [...vm.messageList];
        newValue.forEach(function (item) {
          let message = JSON.parse(item);
          if (message.mcode === "404") {
            let newRecord = JSON.parse(message.record);
            if (newRecord.roomId === vm.roomData.id) {
              if (needsave){
                newmessageList.push(newRecord);
                vm.messageList = newmessageList;
              }
              needUpdate = true;
            } else {
              newMsg.push(item);
            }
          } else {
            newMsg.push(item);
          }
        });
        if (needUpdate) {
          vm.updateNewMessage(newMsg)
          vm.scrollBottom();
        }
      }
    },
    data() {
      return {
        show: true,
        roomData: {},
        message: '',
        messageList: [],
        convestationScroll: null,
        hasService: false,
      }
    }
  }
</script>

<style scoped>
  .convestation {
    width: 100%;
    background-color: #f1f1f1;
    overflow: hidden;
    position: relative;
  }

  .convestation div {
    padding-bottom: 6px;
    padding-top: 6px;
  }

  .root {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-direction: column;
    background-color: #f1f1f1;
  }

  .message-input {
    position: fixed;
    bottom: 0px;
    height: 44px;
    width: 100%;
    display: flex;
    background-color: #f1f1f1;
  }

  .message-input input {
    flex: 1;
    border-radius: 4px;
    border: none;
    padding: 0px 11px;
    margin: 5px;
    outline: none;
    font-size: 14px;
  }

  .sendBtn {
    margin: 4px;
    margin-left: 0px;
    padding-top: 2px;
    padding-bottom: 2px;
  }

  ::placeholder {
    color: #919191;
  }


</style>

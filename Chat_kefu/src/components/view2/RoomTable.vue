<template>
  <el-row :gutter="15" style="height: 100%;">
    <el-col :span="8">
      <el-table
        ref="singleTable"
        :data="tableData"
        highlight-current-row
        cell-class-name="cell"
        @current-change="handleCurrentChange"
        style="width: 100%">
        <el-table-column
          label="姓名">
          <template slot-scope="scope">
            <div class="row-cell">
              <img src="../../assets/logo.png"/>
              <div>
                <div style="font-size: 16px">{{ scope.row.roomName }}</div>
                <div style="height: 22px">{{ scope.row.roomDescription }}</div>
              </div>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-col :span="16" style="height: 100%;">
      <el-container style="height: 100%;">
        <el-header class="roomhead" style="height: 48px;">
          <div>
            我是标题
          </div>
        </el-header>
        <el-main style="padding: 0px!important;">
          <div class="mainScroller" ref="contentscroll">
            <div class="sccontent" style="padding-bottom: 20px">
              <Message v-for=" msg in messageList" :msg="msg" :key="msg.id"/>
            </div>
          </div>
        </el-main>
        <el-footer class="roomfoot" style="padding: 12px;flex-direction: column">
          <el-input
            class="footinput"
            type="textarea"
            :autosize="{ minRows: 8, maxRows: 10}"
            placeholder="请输入内容"
            v-model="textarea">
          </el-input>
          <el-button class="sendBtn">发送</el-button>
        </el-footer>
      </el-container>
    </el-col>
  </el-row>
</template>

<script>

  import Message from './Message'
  import {requestHistoryMessage, requestServiceRooms} from '@/api'
  import { mapGetters, mapMutations, mapActions } from 'vuex'
  import BScroll from 'better-scroll'

  export default {
    name: "room-table",
    components: {
      Message
    },
    data() {
      return {
        tableData: [],
        currentRow: null,
        textarea: '',
        messageList: [],
        convestationScroll: null,
      }
    },
    mounted() {
      this.initScroll();
      this.getServiceRooms(this.getServiceInfo().id);
    },
    methods: {
      ...mapGetters([
        'getServiceInfo'
      ]),
      setCurrent(row) {
        this.$refs.singleTable.setCurrentRow(row);
      },
      handleCurrentChange(val) {
        this.currentRow = val;
        this.initRoomHistoryWithRoomid(val.id);
      },
      getServiceRooms (serviceid) {
        let vm = this;
        requestServiceRooms({serviceid: serviceid})
          .then((res)=>{
            vm.tableData = res.rooms;
            if (vm.tableData.length>0){
              vm.$nextTick(function () {
                vm.setCurrent(vm.tableData[0])
              });
            }
          })
      },
      initScroll() {
        let vm = this;

        if (vm.convestationScroll !== null){
          vm.convestationScroll.refresh();
          vm.scrollBottom();
          return ;
        }

        let height = window.screen.height - 509;
        $('.mainScroller').css("height", height + "px");
        vm.convestationScroll = new BScroll(vm.$refs.contentscroll, {
          click: true,
          bounce: true,
          bounceTime: 400,
          startX: 0,
          startY: 0,
          scrollY: true,
          scrollbar: {
            fade: false,
            interactive: true // 1.8.0 新增
          },
          mouseWheel: true,
        });
      },
      scrollBottom() {
        this.$nextTick(function () {
          let height = window.screen.height - 509;
          let vheight = $('.mainScroller .sccontent').outerHeight(true)
          if (vheight > height) {
            if (this.convestationScroll) {
              this.convestationScroll.scrollTo(0, height - vheight);
              this.convestationScroll.refresh();
            }
          }
        });
      },
      initRoomHistoryWithRoomid(roomid){
        let vm = this;
        requestHistoryMessage({roomid: roomid})
          .then((res)=>{
            vm.messageList = res.records;
            vm.initScroll();

          })
      }
    }
  }
</script>

<style lang="less" scoped>
  .element::-webkit-scrollbar {display:none}
  .cell {
    padding: 0px !important;
  }

  .row-cell {
    display: flex;

    div {
      margin-left: 12px;
      align-self: flex-start;
      font-size: 10px;
    }

    img {
      width: 44px;
      height: 44px;
    }
  }

  .roomhead {
    display: flex;
    justify-content: center;
    align-items: center;
    border-bottom: 1px solid #ededed;
  }

  .roomfoot {
    display: flex;
    justify-content: center;
    align-items: center;
    /*border-top: 1px solid #ededed;*/
    height: 200px !important;
    padding: auto;

    .footinput {
      height: 100%;
      width: 100%;
    }
  }

  .mainScroller {
    border-radius: 6px;
    border: 1px solid #d9d9d9;
    flex: 1;
    padding: 2px 20px 2px 20px;
    margin: 12px;
    background-color: #ededed;
    overflow: hidden;
    position: relative;
  }

  .sendBtn {
    position: absolute;
    bottom: 8px;
    right: 20px;
  }


</style>

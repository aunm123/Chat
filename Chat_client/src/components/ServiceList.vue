<template>
  <div class="list-content">
    <VNav title="消息1231">
      <div slot="leftc" class="icon">
        <img :src="imageSrc"/>
      </div>
      <div slot="rightc" >
        <button @click="changeGroupListModel()">
          <i class="iconfont addition icon-addition" style="font-size: 45px;color: white"></i>
        </button>
      </div>
    </VNav>
    <SearchInput/>
    <div ref="scroll" class="list" style="flex: 1">
      <div>
        <ListRow v-for=" roomdata in roomlist" :key="roomdata.id" :roomData="roomdata" v-on:rowClick="rowClick"/>
      </div>
    </div>
    <mt-popup v-model="grouplistModel" class="grouplistModel" position="bottom">
      <div ref="grouplist" style="overflow: hidden;width: 100%">
        <div class="group-row-content">
          <GroupRow v-for=" group in groups" :data="group" :key="group.id" v-on:groupClick="groupClick"/>
        </div>
      </div>
    </mt-popup>
  </div>
</template>

<script>
  import BScroll from 'better-scroll'
  import VNav from '@/components/publish/VNav'
  import SearchInput from '@/components/publish/SearchInput'
  import ListRow from '@/components/publish/ListRow'
  import { mapGetters, mapMutations, mapActions } from 'vuex'
  import GroupRow from '@/components/Tool/GroupRow'
  import { Popup } from 'mint-ui'

  export default {
    components: {
      VNav,
      SearchInput,
      ListRow,
      GroupRow
    },
    name: 'servicelist',
    mounted: function () {
      this.$nextTick(function () {
        this.initScroll();
      });
      this.getServiceList();
    },

    computed: {
      userInfo() {
        return this.$store.getters.getUserInfo;
      }
    },
    methods: {
      ...mapActions([
        'serverlistAction',
        'grouplistAction',
        'joinRooe',
      ]),
      groupClick(group){
        let vm = this;
        this.grouplistModel = false;
        this.joinRooe({userId:this.userInfo.id,groupId:group.id})
          .then((res)=>{
            vm.$router.push('/chat/'+res.room.id);
          });
      },
      rowClick(room){
        let vm = this;
        this.joinRooe({userId:room.userId,groupId:room.groupId})
          .then((res)=>{
            vm.$router.push('/chat/'+res.room.id);
          });
      },
      changeGroupListModel(){

        this.grouplistAction()
          .then((groups)=>{
            this.groups = groups;
            this.grouplistModel = true;
            if (this.grouplistscroll){
              this.grouplistscroll.refresh()
            }else {
              this.grouplistscroll = new BScroll(this.$refs.grouplist, {
                click: true,
                bounce: true,
                bounceTime: 400,
                startX: 0,
                startY: 0,
                scrollY: true,
                scrollbar: true,
                mouseWheel: true,
              });
            }

          })

      },
      initScroll() {
        let height = window.screen.height - 92;
        $('.list').css("height", height + "px");
        let scroll = new BScroll(this.$refs.scroll, {
          click: true,
          bounce: true,
          bounceTime: 400,
          startX: 0,
          startY: 0,
          scrollY: true,
          scrollbar: true,
          mouseWheel: true,
        });
      },
      getServiceList() {
        let vm = this;
        // this.serverlistAction(this.userInfo.id)
        this.serverlistAction(1)
          .then((roomlist)=>{
            vm.roomlist = roomlist;
          });
      }
    },
    data() {
      return {
        imageSrc: "/static/img/cvs.jpg",
        roomlist: [],
        groups: [],
        grouplistModel: false,
        grouplistscroll: null,
      }
    }
  }
</script>

<style lang="less">

  .headNav {
    background-color: #fab27b;
    height: 64px;
    padding-top: 20px;
    h1 {
      height: 44px;
      margin: inherit;
      line-height: 44px;
    }
  }

  .grouplistModel {
    width: 100%;
    height: 400px;
    display: flex;

    .group-row-content {
      flex: 1;
      display: flex;
      flex-direction: column;
    }
  }

  .list-content {
    display: flex;
    flex: 1;
    flex-direction: column;
  }

  .list {
    overflow: hidden;
    position: relative;
  }

  .icon {
    padding: 0px 11px;
    justify-content: center;
    align-items: center;

    img {
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }
  }
</style>

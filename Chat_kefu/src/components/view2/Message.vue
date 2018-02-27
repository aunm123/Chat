<template>
  <div>
    <div v-if="!checkIsSys" class="co" ref="row">
      <div class="co_icon">
        <img :src="imageSrc" />
      </div>
      <div class="co_content">
        <div class="co_name" style="font-size: 14px">
          {{name}}
        </div>
        <div class="co_message" style="font-size: 14px">
          {{msg.content}}
        </div>
      </div>
    </div>
    <SystemMessage v-if="checkIsSys" :value="msg.content"/>
  </div>
</template>

<script>

  import SystemMessage from './SystemMessage'
  import {mapGetters, mapMutations, mapActions} from 'vuex'
  // {
  //   "id": 9,
  //   "createtime": 1517495586000,
  //   "lastupdatetime": 1517495587000,
  //   "content": "Hello User: qwe",
  //   "fromId": null,
  //   "fromSid": 3,
  //   "roomId": 1
  // }
  // cat /var/log/secure | awk '/faile/{print $(NF-3)}' | sort | uniq -c | awk '{print $2" = "$1;}'
  export default {
    components: {
      SystemMessage
    },
    name: 'message',
    props: [
      'msg'
    ],
    mounted () {
      console.log(this.msg.fromSid,this.serviceInfo);
      if (this.msg.fromSid === this.serviceInfo.id ){
        this.$refs.row.classList.add('co_me');
      }
    },
    computed:{
      serviceInfo() {
        return this.getServiceInfo();
      },
      name(){
        if (this.msg.fromId){
          let namev = this.getUserByIdAction(this.msg.fromId).nickname;
          return namev;
        }else if (this.msg.fromSid){
          return "客服"
        }
      },
      checkIsSys(){
        if (this.msg.fromId||this.msg.fromSid){
          return false;
        }else {
          return true;
        }
      },
    },
    methods: {
      ...mapGetters([
        'getServiceInfo'
      ]),
      ...mapActions([
        'getUserByIdAction'
      ]),
    },
    data() {
      // let icon = require('@/static/'+this.msg.icon);
      let icon = "/static/img/"+this.msg.icon;
      return {
        imageSrc: icon,
      }
    }
  }
</script>

<style scoped>

  .co {
    display: flex;
    margin: 11px;
  }

  .co_me {
    flex-direction: row-reverse!important;;
  }

  .co_me .co_name {
    text-align: right!important;
  }
  .co_me .co_message {
    background-color: deepskyblue;
    color: white;
  }

  .co .co_content {
    margin: 0px 11px;
  }

  .co_message {
    background-color: white;
    margin: 7px 0px;
    padding: 7px;
    border-radius: 4px;
  }

  .co .co_icon img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }
  .co_name {
    text-align: left;
  }

</style>

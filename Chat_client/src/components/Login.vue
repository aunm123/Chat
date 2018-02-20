<template>
  <div class="login">
    <div class="bg" style="background-color: #555555;opacity: 0.3;z-index: 1;"></div>
    <div style="z-index: 10;display: flex;flex-direction: column;margin-top: 60px;">
      <div class="logo">GG</div>
      <LoginInput type="text" value="1122334455" style="margin-top: 34px"/>
      <LoginInput type="password" value="1122334455"/>
      <button class="login-btn" @click="loginClick()">登录</button>
      <div style="flex: 1;display: flex;justify-content:space-between;">
        <button class="reg-btn" style="text-align: left;">忘记密码</button>
        <button class="reg-btn" style="text-align: right;">新用户注册</button>
      </div>
    </div>
    <div class="read" >
      登录即代表阅读并同意<span style="color: #FFA500">服务条款</span>
    </div>
  </div>
</template>

<script>

  import LoginInput from "@/components/publish/LoginInput"
  import { mapGetters, mapMutations, mapActions } from 'vuex'
  import { Toast } from 'mint-ui';

  export default {
    components: {
      LoginInput
    },
    name: 'login',
    mounted: function () {
      //尝试获取用户id
      this.getUserSession()
        .then(()=>{
          this.$router.replace('/sl');
        })
    },

    methods: {
      ...mapActions([
        'loginAction',
        'getUserSession'
      ]),
      loginClick: function () {
        this.loginAction({
          username: 'qwe',
          password: '123123',
        }).then(()=>{
          this.$router.replace('/sl');
        })
      },
    },
    data() {
      return {}
    }
  }
</script>

<style lang="less" >

  .login {
    flex: 1;
    background-image: url("/static/img/login_bg.png");
    background-position: center;
    background-size: auto 100%;
    background-repeat: no-repeat;
    padding: 28px;
    display: flex;
    flex-direction: column;
  }

  .logo {
    color: white;
    font-size: 34px;
    font-weight: 100;
  }

  .login-btn{
    color: white;
    font-size: 18px;
    display: flex;
    font-weight: 300;
    background-color: rgba(238,173,14,0.6);
    outline: none;
    margin: 30px 0px 15px 0px;
    height: 40px;
    border: none;
    border-radius: 2px;
    width: 100%;
    justify-content: center;
    align-items: center;
    letter-spacing: 1.5px;
  }

  .reg-btn{
    font-size: 15px;
    color: #FFA500;
    width: 100px;
    padding: 0px;
  }

  .read{
    color: white;
    letter-spacing: 1.2px;
    font-size: 14px;
    text-align: center;
    position: absolute;
    flex: 1;
    display: flex;
    justify-content: center;
    align-self: center;
    bottom: 40px;
  }

</style>

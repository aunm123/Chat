<template>
  <div class="content">
    <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
      <el-tab-pane label="当前在线用户" name="first">当前在线用户</el-tab-pane>
      <el-tab-pane label="请求客服用户" name="second">请求客服用户</el-tab-pane>
      <el-tab-pane label="用户列表" name="third">用户列表</el-tab-pane>
    </el-tabs>
    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      stripe
      @cell-click="handcellClick"
    >
      style="width: 100%">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        prop="id"
        label="ID"
        width="55">
      </el-table-column>
      <el-table-column
        prop="createtime"
        label="创建日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="手机号码"
        width="120">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click.once="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {requestOnlineUsers, requestUsers, requestWaiteUsers} from '@/api'
  var moment = require('moment');

  export default {
    name: "user-table",
    data() {
      return {
        activeName2: 'first',
        tableData: [],
        multipleSelection: []
      };
    },
    mounted(){
      this.getUserList();
    },
    watch:{
      activeName2(newValue){
        this.getUserList()
      }
    },
    methods: {
      handleClick(tab, event) {
      },
      handcellClick(row, column, cell, event) {
        // this.$refs.multipleTable.toggleRowSelection(row);
      },
      getUserList() {
        let vm = this;
        switch (vm.activeName2){
          case 'first':{
            requestOnlineUsers()
              .then((res)=>{
                vm.tableData = this.handTableData(res.users);
              });
            break;
          }
          case 'second':{
            requestUsers()
              .then((res)=>{
                vm.tableData = this.handTableData(res.users);
              });
            break;
          }
          case 'third':{
            requestWaiteUsers()
              .then((res)=>{
                vm.tableData = this.handTableData(res.users);
              });
            break;
          }
        }
      },
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handTableData(tableData){
        tableData.forEach((item)=>{
          var newDate = new Date();
          newDate.setTime(item.createtime);
          item.createtime = moment(newDate).format('YYYY-MM-DD h:mm');
        })
        return tableData;
      }
    }
  }
</script>

<style lang="less">

  .content {
    padding-top: 10px;
  }

</style>

let meum = [
  {
    name: '用户列表',
    iconCls: 'el-icon-message',//图标样式class
    index: "100",
    children: [
      {name: '主页', hidden: true, path: "/home"},
      {name: '用户列表',path: "/usertable"},
    ]
  },
  {
    name: '聊天室列表',
    iconCls: 'el-icon-message',//图标样式class
    index: "200",
    children: [
      {name: '等待的聊天室',path: "/roomtable"},
    ]
  },
];

export default meum;

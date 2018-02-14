var wsUri = "://localhost:8089/springws/websocket.ws";

var global_sock = null;

function startWs() {

  return new Promise((resolve) =>{
    if (!global_sock){
      if ('WebSocket' in window) {
        //Websocket的连接
        global_sock = new WebSocket("ws"+wsUri);//WebSocket对应的地址
      }
      else if ('MozWebSocket' in window) {
        //Websocket的连接
        global_sock = new MozWebSocket("ws"+wsUri);//SockJS对应的地址
      }
      else {
        //SockJS的连接
        global_sock = new SockJS("http"+wsUri);    //SockJS对应的地址
      }

      global_sock.onopen = function() {
        console.log('websocket 链接成功开始控制全局消息');
        console.log('能量监控开始');

        resolve(global_sock);

      };

      global_sock.onclose = function() {
        console.log('websocket 链接成功断开链接');
        console.log('能量监控结束');
      };
    }
  })



}

function messageCome(callback) {

  if (global_sock){
    global_sock.onmessage = function(e) {
      callback(e);
    };
  }

}

const websocket = {
  startWs: startWs,
  messageCome: messageCome,
};

export default websocket;

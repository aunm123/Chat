import fetch from './fetch';


export const requestLogin = params => {
  return fetch(`/account/slogin`, params).then(res => res);
};

export const requestOnlineUsers = () => {
  return fetch(`/total/onlineuserlist`).then(res => res);
};

export const requestUsers = () => {
  return fetch(`/total/userlist`).then(res => res);
};

export const requestWaiteUsers = () => {
  return fetch(`/total/waiteuserlist`).then(res => res);
};

export const requestHistoryMessage = params => {
  return fetch(`/room/history`, params).then(res => res);
};

export const requestServiceRooms = params => {
  return fetch(`/service/getServiceRoom`, params).then(res => res);
};



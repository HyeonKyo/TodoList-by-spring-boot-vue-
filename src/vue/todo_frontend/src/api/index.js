import axios from "axios";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_SERVER_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance };

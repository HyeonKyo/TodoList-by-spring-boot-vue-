import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import todoStore from "@/store/todoStore.js";

Vue.use(Vuex);

const store = new Vuex.Store({
  namespaced: true,
  modules: {
    todoStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: localStorage,
    }),
  ],
});

export default store;

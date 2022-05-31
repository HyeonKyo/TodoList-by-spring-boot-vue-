import {} from "@/api/index.js";

const memberStore = {
  namespaced: true,
  state: {
    todoList: [],
  },
  getters: {
    getTodoList: function (state) {
      return state.todoList;
    },
  },
  mutations: {
    SET_Todo_List: (state, list) => {
      state.todoList = list;
    },
  },
  actions: {
    async memberLogout({ commit }) {
      await logout(
        () => {
          commit("INIT_USER_STATE");
        },
        () => {
          alert("로그아웃 실패");
          // eslint-disable-next-line
        }
      );
    },
  },
  methods: {},
};

export default todoStore;

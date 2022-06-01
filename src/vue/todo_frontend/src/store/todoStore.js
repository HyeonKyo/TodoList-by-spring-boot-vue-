import { findAll } from "@/api/todo.js";

const todoStore = {
  namespaced: true,
  state: {
    todoList: [],
  },
  getters: {
    activeTodoList: (state) => {
      return state.todoList.filter((item) => item.isCompleted == false);
    },
    doneTodoList: (state) => {
      return state.todoList.filter((item) => item.isCompleted == true);
    },
  },
  mutations: {
    SET_TODO_LIST: (state, list) => {
      state.todoList = list;
    },
  },
  actions: {
    async findTodoListAll({ commit }) {
      await findAll()
        .then((res) => {
          commit("SET_TODO_LIST", res.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default todoStore;

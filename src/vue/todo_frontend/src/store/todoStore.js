import { findAll, save } from "@/api/todo.js";

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
    ADD_TODO_DATA: (state, data) => {
      state.todoList.push(data);
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
    async saveTodo({ commit }, data) {
      //   let data = {
      //     content,
      //     isCompleted,
      //     targetDate,
      //   };
      await save(data)
        .then(() => {
          commit("ADD_TODO_DATA", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default todoStore;

import { findAll, save, modify } from "@/api/todo.js";

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
      await save(data)
        .then((res) => {
          data.id = res.data;
          commit("ADD_TODO_DATA", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async changeTodo(context, data) {
      console.log("데이터 확인");
      console.log(data.id);
      console.log(data);
      await modify(data.id, data)
        .then(() => {
          context.dispatch("findTodoListAll");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default todoStore;

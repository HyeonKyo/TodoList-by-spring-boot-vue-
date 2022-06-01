<template>
  <b-container>
    <h2 class="underline-steelblue">
      <b-icon icon="calendar-check"></b-icon> Todo List
    </h2>
    <todo-regist class="mt-3"></todo-regist>
    <b-container class="mt-3">
      <b-tabs content-class="mt-3" fill>
        <b-tab title="할 일" active
          ><todo-list :list="activeTodoList"></todo-list
        ></b-tab>
        <b-tab title="완료한 일"
          ><todo-list :list="doneTodoList"></todo-list
        ></b-tab>
      </b-tabs>
    </b-container>
  </b-container>
</template>

<script>
import TodoRegist from "@/components/todo/TodoRegist.vue";
import TodoList from "@/components/todo/TodoList.vue";

import { mapGetters, mapActions } from "vuex";

const todoStore = "todoStore";

export default {
  name: "TodoView",
  components: {
    TodoRegist,
    TodoList,
  },
  methods: {
    ...mapActions(todoStore, ["findTodoListAll"]),
  },
  computed: {
    ...mapGetters(todoStore, ["activeTodoList", "doneTodoList"]),
  },
  mounted() {
    this.findTodoListAll();
  },
};
</script>

<style></style>

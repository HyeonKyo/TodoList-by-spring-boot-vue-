<template>
  <b-container>
    <h2 class="underline-steelblue">
      <b-icon icon="calendar-check"></b-icon> Todo List
    </h2>
    <todo-regist class="mt-3"></todo-regist>
    <todo-list class="mt-3"></todo-list>
  </b-container>
</template>

<script>
import TodoRegist from "@/components/todo/TodoRegist.vue";
import TodoList from "@/components/todo/TodoList.vue";

export default {
  namespace: true,
  components: {
    TodoRegist,
    TodoList,
  },
  data() {
    return {
      text: "",
    };
  },
};
</script>

<style></style>

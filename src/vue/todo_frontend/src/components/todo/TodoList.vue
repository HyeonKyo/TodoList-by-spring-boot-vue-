<template>
  <b-container>
    <b-row v-if="!list || list.length <= 0">
      <h3>할 일이 없습니다...</h3>
    </b-row>
    <b-container v-else>
      <b-row v-for="(item, index) in list" :key="index">
        <todo-list-item v-bind="item"></todo-list-item>
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
import TodoListItem from "@/components/todo/TodoListItem.vue";

export default {
  components: {
    TodoListItem,
  },
  props: {
    list: Array,
  },
};
</script>

<style></style>

<template>
  <b-row>
    <b-col cols="7">
      <b-form-input
        v-model="content"
        placeholder="할 일을 등록하세요."
      ></b-form-input
    ></b-col>
    <b-col cols="2">
      <div>
        <b-form-datepicker
          v-model="targetDate"
          placeholder="목표 날짜"
          locale="kor"
        ></b-form-datepicker>
      </div>
    </b-col>
    <!-- 목표일, 등록버튼 -->
    <b-col
      ><b-button variant="outline-primary" @click="saveTodoData"
        >등록</b-button
      ></b-col
    >
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const todoStore = "todoStore";

export default {
  data() {
    return {
      content: "",
      targetDate: "",
    };
  },
  methods: {
    ...mapActions(todoStore, ["saveTodo"]),
    saveTodoData() {
      this.saveTodo({
        content: this.content,
        isCompleted: false,
        targetDate: this.targetDate,
      });
    },
  },
};
</script>

<style></style>

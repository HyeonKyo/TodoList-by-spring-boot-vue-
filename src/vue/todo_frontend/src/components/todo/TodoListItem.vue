<template>
  <b-card
    class="mt-1"
    border-variant="danger"
    v-if="isExpired"
    @click="clickTodo"
  >
    <b-row :class="{ completed: completeFlag }">
      {{ content }}
    </b-row>
  </b-card>
  <b-card class="mt-1" v-else @click="clickTodo">
    <b-row :class="{ completed: completeFlag }">
      {{ content }}
    </b-row>
  </b-card>
</template>
<script>
import { mapActions } from "vuex";

const todoStore = "todoStore";

export default {
  props: {
    content: String,
    id: [Number, String],
    isCompleted: Boolean,
    targetDate: String,
  },
  computed: {
    isChecked() {
      return this.isCompleted;
    },
    isExpired() {
      if (!this.targetDate) return false;
      return this.curDate >= Date.parse(this.targetDate);
    },
  },
  data() {
    return {
      curDate: null,
      completeFlag: false,
    };
  },
  mounted() {
    this.curDate = Date.now();
    this.completeFlag = this.isChecked;
  },
  methods: {
    ...mapActions(todoStore, ["changeTodo"]),
    clickTodo() {
      //일단 폰트만 바꾸고, 5초뒤에 DB에 isCompleted 변경 요청을 보냄
      //그 전에 다시 취소하면 요청 안보냄
      this.completeFlag = !this.completeFlag;
      setTimeout(() => {
        if (this.completeFlag == this.isCompleted) {
          return;
        }
        console.log("요청 보내기");
        this.changeTodo({
          id: this.id,
          isCompleted: this.completeFlag,
          targetDate: this.targetDate,
        });
        //요청 보내기
      }, 1000 * 7);
    },
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
  font-style: italic;
  font-weight: bold;
}
</style>

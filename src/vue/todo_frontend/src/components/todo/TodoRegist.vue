<template>
  <b-row>
    <b-col cols="7">
      <b-form-input
        v-model="text"
        placeholder="할 일을 등록하세요."
      ></b-form-input
    ></b-col>
    <b-col cols="2">
      <div>
        <b-form-datepicker
          id="datepicker-placeholder"
          placeholder="목표 날짜"
          locale="kor"
        ></b-form-datepicker>
      </div>
    </b-col>
    <!-- 목표일, 등록버튼 -->
    <b-col><b-button variant="outline-primary">등록</b-button></b-col>
  </b-row>
</template>

<script>
export default {
  data() {
    return {
      text: "",
    };
  },
};
</script>

<style></style>

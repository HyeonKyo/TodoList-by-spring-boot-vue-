<template>
  <b-card class="mt-1">
    <b-row>
      {{ content }}
    </b-row>
  </b-card>
</template>

<script>
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
  },
};
</script>

<style></style>

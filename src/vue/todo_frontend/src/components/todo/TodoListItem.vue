<template>
  <b-card class="mt-1" border-variant="danger" v-if="isExpired">
    <b-row>
      {{ content }}
    </b-row>
  </b-card>
  <b-card class="mt-1" v-else>
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
    isExpired() {
      if (!this.targetDate) return false;
      return this.curDate >= Date.parse(this.targetDate);
    },
  },
  data() {
    return {
      curDate: null,
    };
  },
  mounted() {
    this.curDate = Date.now();
  },
};
</script>

<style></style>

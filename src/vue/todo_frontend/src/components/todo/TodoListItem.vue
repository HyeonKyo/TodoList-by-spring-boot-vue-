<template>
  <b-card>
    <blockquote class="blockquote mb-0">
      <p>
        {{ content }}
      </p>
    </blockquote>
  </b-card>
</template>

<script>
export default {
  props: {
    item: {
      content: String,
    },
  },
};
</script>

<style></style>

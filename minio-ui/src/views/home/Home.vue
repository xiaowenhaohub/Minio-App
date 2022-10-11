<template>
  <div class="home">
    <div class="mi-file">
        file
    </div>
  </div>
</template>

<script>
export default {
  name: "Home"
}
</script>

<style scoped>
  .home {
    position: fixed;
    height: 100%;
    width: 100%;
    background-color: #ffffff;
    display: flex;
    justify-content: center;
    align-items: center;
    /*padding: 2rem;*/
  }

  .mi-file {
    width: 95%;
    height: 70%;
    border: #EAEDEE 1px solid;
    padding: 0.8rem 15px 0;
  }
</style>

<template>
  <div class="home">
    <div class="mi-file">
        <div class="mi-file-header">
          <div style="flex: 1;"></div>
          <div style="flex: 1;"></div>
        </div>
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
    /* position: fixed; */
    margin-top: 2.3rem;
    height: 100%;
    width: 100%;
    background-color: #ffffff;
    display: flex;
    justify-content: center;
    /* align-items: center; */
    /*padding: 2rem;*/
  }

  .mi-file {
    width: 95%;
    height: 85%;
    border: #EAEDEE 1px solid;
  }

  .mi-file-header {
      /* background-color: rebeccapurple; */
      width: 100%;
      height: 10%;
      border-bottom: #EAEDEE 1px solid;
      display: flex;
  }
</style>

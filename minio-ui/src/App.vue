<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>


export default {
  name: 'App',
}
</script>

<style>
/*#app {*/
/*  font-family: Avenir, Helvetica, Arial, sans-serif;*/
/*  -webkit-font-smoothing: antialiased;*/
/*  -moz-osx-font-smoothing: grayscale;*/
/*  text-align: center;*/
/*  color: #2c3e50;*/
/*  margin-top: 60px;*/
/*}*/
body {
  margin: 0;
}


html,body,#app{
  height: 100%;
  font-family: "Lato", sans-serif;
  -webkit-font-smoothing: antialiased;
  /*background:rgba(38, 24, 49,.6);*/
}
</style>

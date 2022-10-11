<template>
  <div class="app">
    <Header class="header"></Header>
    <AppMain class="main"></AppMain>
  </div>
</template>

<script>
import {Header, AppMain} from './components'
export default {
  name: 'Layout',
  components: {
    Header,
    AppMain
  },

}
</script>

<style lang="scss" scoped>
  .app {
    height: 100%;
    width: 100%;
  }

  .main {
    //padding-top: 20%;
  }

  .header {
    height: 10%;
    width: 100%;
    left: 0;
    position: fixed;
    z-index: 100;
    box-shadow: rgb(0 0 0 / 8%) 0 3px 10px;
    min-height: 83px;
    background-color: #fff;
  }
</style>

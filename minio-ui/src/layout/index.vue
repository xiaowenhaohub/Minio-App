<template>
  <div style="height: 10%">
    <Header class="header"></Header>
    <AppMain class="main"></AppMain>
  </div>
</template>

<script>
import { Header, AppMain } from "./components";
export default {
  name: "Layout",
  components: {
    Header,
    AppMain,
  },
};
</script>

<style lang="scss" scoped>
.app {
  ß height: 100%;
  width: 100%;
}

.main {
  //padding-top: 20%;
  width: 100%;
  height: 100%;
}

.header {
  height: 90%;
  width: 100%;
  left: 0;
  // position: fixed;
    box-shadow: rgb(0 0 0 / 8%) 0 3px 10px;
  min-height: 83px;
  background-color: #fff;
}
</style>

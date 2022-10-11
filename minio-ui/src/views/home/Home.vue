<template>
  <div class="home">
    <div class="mi-file">
        <div class="mi-file-header">
          <div style="flex: 1; display: flex; justify-content: left; align-items: center;">
            <div style="font-size: 30px; margin-left: 20px; margin-right: 15px;"><i class="el-icon-folder"></i></div>
            <div style="font-size: 19px;margin-right: 40px;">{{folder.name}}</div>
            <div class="mi-file-header-meta">创建时间: {{folder.createTime}}</div>
            <div class="mi-file-header-meta">文件数量: {{folder.num}}</div>
            <div class="mi-file-header-meta">文件夹大小: {{folder.size}} MiB</div>
          </div>
          <div style="flex: 1;"></div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      folder: {
        name: 'root',
        createTime: '2022-10-11-10:00',
        size: 20,
        num: 7
      }
    }
  },
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

  .mi-file-header-meta {
    color: #969FA8;
    font-size: 14px;
    margin-right: 30px;
  }
</style>

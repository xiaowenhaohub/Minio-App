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
          <div style="flex: 1;display: flex; justify-content: right; align-items: center;">
              <button class="mi-file-refresh">
                <span>Refresh</span>
              </button>

              <el-dropdown trigger="click">
                <button class="mi-file-upload">
                  <span>Upload</span>
                </button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>
                    <div style="margin-bottom:10px;">
                      
                      <span style="font-family: Lato, sans-serif;font-weight: 400;font-size: 1rem;line-height: 1.5;display: block;">上传文件</span>
                    </div>
                  </el-dropdown-item>
                  <el-dropdown-item><span style="font-family: Lato, sans-serif;font-weight: 400;font-size: 1rem;line-height: 1.5;display: block;">上传文件夹</span></el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
             
          </div>
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

  .mi-file-refresh {
    border-radius: 3px;
    cursor: pointer;
    width: initial;
    height: 39px;
    /* font-family: Lato, sans-serif; */
    font-weight: bold;
    font-size: 14px;
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    margin: 0px;
    padding: 0px 25px;
    transition: all 0.2s linear 0s;
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(91, 92, 92);
    color: rgb(91, 92, 92);
    margin-right: 20px;
  }

  .mi-file-refresh:hover {
    background-color: rgb(230, 234, 235);
    border: 1px solid rgb(91, 92, 92);
    color: rgb(91, 92, 92);
  }

  .mi-file-upload {
    border-radius: 3px;
    cursor: pointer;
    width: initial;
    height: 39px;
    font-family: Lato, sans-serif;
    font-weight: bold;
    font-size: 14px;
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    margin: 0px;
    padding: 0px 25px;
    transition: all 0.2s linear 0s;
    background-color: rgb(7, 25, 62);
    border: 1px solid rgb(7, 25, 62);
    color: rgb(255, 255, 255);
    margin-right: 20px;
  }

  .el-dropdown-menu__item:focus, .el-dropdown-menu__item:not(.is-disabled):hover {
    color: rgb(91, 92, 92);
    text-decoration: none;
    background-color: rgba(0, 0, 0, 0.04);
}
</style>

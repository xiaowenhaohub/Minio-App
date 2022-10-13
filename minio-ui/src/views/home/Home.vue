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
                <svg style="width: 14px;height: 14px;margin-left: 8px;"  xmlns="http://www.w3.org/2000/svg" class="min-icon" fill="currentcolor" viewBox="0 0 256 256"><defs><clipPath id="prefix__a"><path d="M0 0h256v256H0z"></path></clipPath></defs><g clip-path="url(#prefix__a)"><path fill="none" d="M0 0h256v256H0z"></path><path data-name="Trazado 373" d="M18 145.888A110.2 110.2 0 0 1 126.767 35.85L113.78 22.869c-12.378-12.378 6.448-31.2 18.822-18.824l37.722 37.72a13.32 13.32 0 0 1 0 18.979l-37.722 37.714c-12.374 12.374-31.2-6.442-18.822-18.82l14.085-14.085a80.434 80.434 0 0 0-80.1 80.335 80.443 80.443 0 0 0 80.349 80.35 80.441 80.441 0 0 0 80.349-80.35 14.878 14.878 0 0 1 14.879-14.877 14.879 14.879 0 0 1 14.882 14.877A110.234 110.234 0 0 1 128.114 256 110.232 110.232 0 0 1 18 145.888Z"></path><path data-name="Rect\xE1ngulo 871" fill="none" d="M0 0h256v256H0z"></path></g></svg>
              </button>
              <el-dropdown trigger="click">
                <button class="mi-file-upload">
                  <span>Upload</span>
                  <svg style="width: 14px;height: 14px;margin-left: 8px;" xmlns="http://www.w3.org/2000/svg" class="min-icon" fill="currentcolor" viewBox="0 0 256 256"><defs><clipPath id="prefix__a"><path d="M0 0h256v256H0z"></path></clipPath></defs><g clip-path="url(#prefix__a)"><path fill="none" d="M0 0h256v256H0z"></path><path data-name="Rect\xE1ngulo 870" fill="none" d="M0 0h256v256H0z"></path><path data-name="Trazado 454" d="M16.412 256A16.487 16.487 0 0 1-.002 239.463V104.082c0-21.752 32.824-21.752 32.824 0v118.829h190.355V104.082c0-21.752 32.822-21.752 32.822 0v135.381a16.477 16.477 0 0 1-16.4 16.537Zm95.176-61.308V56.465L93 75.208c-15.262 15.385-38.471-8-23.205-23.393l46.5-46.878a16.345 16.345 0 0 1 23.408 0l46.51 46.878c15.266 15.39-7.949 38.777-23.211 23.393L144.41 56.464v138.227c0 10.872-8.205 16.307-16.41 16.307s-16.412-5.435-16.412-16.307Z"></path><path data-name="Trazado 454 - Contorno" d="M239.6 256.5H16.416A17 17 0 0 1-.498 239.463V104.082a16.259 16.259 0 0 1 5.3-12.61 16.393 16.393 0 0 1 5.422-3.156 18.547 18.547 0 0 1 6.193-1.048 18.547 18.547 0 0 1 6.193 1.048 16.393 16.393 0 0 1 5.422 3.156 16.259 16.259 0 0 1 5.3 12.61v118.329h189.355V104.082a16.259 16.259 0 0 1 5.3-12.61 16.374 16.374 0 0 1 5.422-3.156 18.528 18.528 0 0 1 6.191-1.048 18.531 18.531 0 0 1 6.193 1.048 16.374 16.374 0 0 1 5.422 3.156 16.259 16.259 0 0 1 5.3 12.61v135.381a17.034 17.034 0 0 1-1.328 6.625 16.992 16.992 0 0 1-3.621 5.416 16.846 16.846 0 0 1-5.373 3.655 16.663 16.663 0 0 1-6.593 1.341ZM16.414 88.268a17.535 17.535 0 0 0-5.859.991 15.388 15.388 0 0 0-5.094 2.962 15.286 15.286 0 0 0-4.959 11.861v135.381A15.994 15.994 0 0 0 16.416 255.5H239.6a15.989 15.989 0 0 0 15.9-16.037V104.082a15.286 15.286 0 0 0-4.959-11.861 15.368 15.368 0 0 0-5.094-2.962 17.518 17.518 0 0 0-5.859-.991 17.515 17.515 0 0 0-5.857.991 15.368 15.368 0 0 0-5.094 2.962 15.286 15.286 0 0 0-4.959 11.861v119.329H32.323V104.082a15.286 15.286 0 0 0-4.959-11.861 15.388 15.388 0 0 0-5.094-2.962 17.526 17.526 0 0 0-5.86-.992ZM128 211.5a18.547 18.547 0 0 1-6.193-1.048 16.37 16.37 0 0 1-5.422-3.154 16.248 16.248 0 0 1-5.3-12.6V57.685L93.349 75.567a16.238 16.238 0 0 1-11.6 5.179 16.682 16.682 0 0 1-9.711-3.251 19.071 19.071 0 0 1-6.451-8.051 15.968 15.968 0 0 1-1.051-8.961 17 17 0 0 1 4.9-9.013l46.5-46.878a16.869 16.869 0 0 1 12.006-5.084 16.81 16.81 0 0 1 6.482 1.3 17.213 17.213 0 0 1 5.631 3.786l46.51 46.879a16.976 16.976 0 0 1 4.9 9.01 15.975 15.975 0 0 1-1.049 8.958 19.084 19.084 0 0 1-6.453 8.054 16.694 16.694 0 0 1-9.715 3.254 16.237 16.237 0 0 1-11.6-5.179l-17.736-17.882v137.013a16.249 16.249 0 0 1-5.3 12.6 16.351 16.351 0 0 1-5.422 3.154A18.527 18.527 0 0 1 128 211.5ZM112.088 55.252v139.441a15.275 15.275 0 0 0 4.959 11.855 15.365 15.365 0 0 0 5.094 2.961 17.538 17.538 0 0 0 5.859.991 17.547 17.547 0 0 0 5.859-.991 15.375 15.375 0 0 0 5.092-2.961 15.276 15.276 0 0 0 4.959-11.855V55.25l.855.862 18.592 18.744a15.257 15.257 0 0 0 10.891 4.883 15.7 15.7 0 0 0 9.133-3.067 18.064 18.064 0 0 0 6.111-7.625 14.955 14.955 0 0 0 .988-8.4 16 16 0 0 0-4.625-8.482l-46.51-46.878a16.222 16.222 0 0 0-5.3-3.567 15.825 15.825 0 0 0-6.1-1.222 15.868 15.868 0 0 0-11.295 4.789l-46.5 46.878a16.011 16.011 0 0 0-4.627 8.485 15 15 0 0 0 .988 8.4 18.055 18.055 0 0 0 6.111 7.623 15.688 15.688 0 0 0 9.129 3.064 15.259 15.259 0 0 0 10.893-4.883Z" fill="rgba(0,0,0,0)"></path></g></svg>
                </button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>
                    <div style="margin-bottom:10px;">
                      <span style="font-family: Lato, sans-serif;font-weight: 400;font-size: 1rem;line-height: 1.5;display: block;font-weight: bold;">上传文件</span>
                    </div>
                  </el-dropdown-item>
                  <el-dropdown-item><span style="font-family: Lato, sans-serif;font-weight: 400;font-size: 1rem;line-height: 1.5;display: block;font-weight: bold;">上传文件夹</span></el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
          </div>
        </div>
        <div class="mi-file-body">
          <div class="body-header">
            <div style="display: flex;width: 98%; height: 70%;">
              <div class="body-header-left">
                <button class="mi-button-back">
                  <svg style="width: 16px;min-width: 16px;" xmlns="http://www.w3.org/2000/svg" class="min-icon" fill="currentcolor" viewBox="0 0 256 256"><g id="noun_chevron_2320228" transform="translate(5.595 10) rotate(180)"><path id="Path_6842" d="M-178.01,7.8c-3.9-0.03-7.62-1.63-10.34-4.43c-5.81-5.68-5.92-15-0.25-20.81 c0.08-0.08,0.16-0.16,0.25-0.25l100.13-100.13l-100.13-100.48c-5.81-5.68-5.92-15-0.25-20.81c0.08-0.08,0.16-0.16,0.25-0.25 c5.68-5.81,15-5.92,20.81-0.25c0.08,0.08,0.16,0.16,0.25,0.25l110.82,110.82c2.8,2.72,4.39,6.44,4.43,10.34 c0.11,3.93-1.51,7.71-4.43,10.34L-167.29,2.99C-170.07,5.97-173.93,7.71-178.01,7.8z"></path></g></svg>
                </button>
                
                <div class="mi-text-back">
                  <a class="mi-file-path" href="/buckets/public/browse">{{folder.path}}</a>
                </div>
              </div>
              <button class="body-header-button">创建新路径</button>
            </div>
          </div>
          <div class="body-table">
            <el-table
              :data="fileList"
              style="width: 98%"
              :header-cell-style="{fontSize:'14px',fontWeight: '700',fontFamily: 'Lato, sans-serif',color:'black'}"
              height="100%"
              :row-style="{height: '10px'}">
              <el-table-column
                align="left"
                min-width="3%">
                <template slot="header" slot-scope="scope">
                  <input class="file-check" type="checkBox"/>
                </template>
                <template slot-scope="scope">
                  <input class="file-check" type="checkBox"/>
                </template>
              </el-table-column>
              <el-table-column
                prop="name"
                label="Name"
                min-width="30%">
              </el-table-column>
              <el-table-column
                prop="date"
                label="Last Modified"
                min-width="30%">
              </el-table-column>
              <el-table-column
                prop="size"
                label="Size"
                min-width="10%">
              </el-table-column>
            </el-table>
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
        path: 'public',
        size: 20,
        num: 7
      },
      fileList: [
      {
          id: 1,
          date: '2016-05-03',
          name: '王小虎',
          size: 200333
        }, {
          id: 2,
          date: '2016-05-02',
          name: '王小虎',
          size: 200333
        }, {
          id: 3,
          date: '2016-05-04',
          name: '王小虎',
          size: 200333
        }, {
          id: 4,
          date: '2016-05-01',
          name: '王小虎',
          size: 200333
        },
      ]
    }
  },
}
</script>

<style scoped>
 
  .el-table {
    font-size: 12px;
    color: black;
  
  } 

  .file-check {
    width: 15px;
    height: 15px;
    margin: auto;
  }
  .body-table {
    width: 100%;
    height: 90%;
    padding-left: 1%;
  }

.mi-file-path:hover {
    text-decoration: underline;
  }

  .mi-file-path {
    color: #969FA8;
    font-size: 12px;
    font-weight: bold;
    text-decoration: none;
  }
  .mi-text-back {
    width: 0;
    display: inline-block;
    overflow: hidden;
    flex-grow: 1;
    text-align: left;
    margin-left: 15px;
    white-space: nowrap;
    margin-right: 10px;
    text-overflow: ellipsis;
  }

  .mi-button-back:hover {
    background-color: rgba(0, 0, 0, 0.04);
  }

  .mi-button-back {
    display: inline-flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    position: relative;
    box-sizing: border-box;
    -webkit-tap-highlight-color: transparent;
    outline: 0px;
    margin: 0px 10px 0px 0px;
    cursor: pointer;
    user-select: none;
    vertical-align: middle;
    appearance: none;
    text-decoration: none;
    text-align: center;
    flex: 0 0 auto;
    font-size: 1.5rem;
    padding: 8px;
    overflow: visible;
    color: rgba(0, 0, 0, 0.54);
    transition: background-color 150ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
    border-width: 1px 1px 1px 0px;
    border-style: solid;
    border-top-color: rgb(234, 237, 238);
    border-right-color: rgb(234, 237, 238);
    border-bottom-color: rgb(234, 237, 238);
    border-image: initial;
    background-color: rgb(255, 255, 255);
    border-left-color: initial;
    border-radius: 0px;
    width: 38px;
    height: 38px;
  }

  .body-header-button:hover {
    background-color: rgb(230, 234, 235);
    border: 1px solid rgb(91, 92, 92);
    color: rgb(91, 92, 92);
  }

  .body-header-button {
    flex: 1;
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
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(91, 92, 92);
    color: rgb(91, 92, 92);
  }

  .body-header-left {
    flex: 7;
    color: #969FA8;
    border: #EAEDEE 1px solid;
    height: 38px;
    background-color: #FCFCFD;
    margin-right: 20px;
    display: flex;
    justify-content: left;
    align-items: center;
  }

  .body-header {
    height: 10%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
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

  .mi-file-body {
    width: 100%;
    height: 90%;
    /* background-color: #969FA8; */
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

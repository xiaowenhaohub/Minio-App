<template>
  <div class="header">
    <div class="mi-h1">
      <div>小问号的文件夹</div>
    </div>
    <div class="mi-h2">
      <input type="text" placeholder="输入文件名" v-on:keyup.enter="searchFileByName()" v-model="fileName" class="mi-input"
        maxlength="16" />
    </div>
    <div class="mi-h3">
      <div class="mi-button" @click="openCodeWindows">
        <i class="el-icon-setting"></i>
      </div>
      <div class="mi-button" style="margin-left: 5px;" @click="showDownloadWindows"
        :style="{ display: uploading ? '' : 'none' }">
        <svg class="min-icon" fill="currentcolor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256"
          style="width: 20px; height: 20px; margin-top: -2px;">
          <g>
            <g x="2.7" y="36.8">
              <path
                d="M77.2,168.6c4,4.1,10.6,4.3,14.7,0.3c0,0,0,0,0.1-0.1l0.2-0.2l29.7-29.9 c3.9-4.3,3.6-10.9-0.7-14.9c-4-3.7-10.1-3.7-14.1-0.1l-12,12V47.3h0.1c0-5.8-4.7-10.5-10.5-10.5s-10.5,4.7-10.5,10.5v88.3 l-11.9-12c-4.3-4-10.9-3.7-14.9,0.5c-3.8,4.1-3.8,10.4,0.1,14.4L77.2,168.6z">
              </path>
              <path
                d="M148.3,84.9l11.9-12v88.3h-0.1c0,5.8,4.7,10.5,10.5,10.5s10.5-4.7,10.5-10.5V72.9l11.9,12 c4.3,4,10.9,3.7,14.9-0.5c3.8-4.1,3.8-10.4-0.1-14.4l-29.7-30c-4-4.1-10.6-4.2-14.7-0.2l-0.2,0.2l-29.7,29.9 c-4,4.2-3.8,10.9,0.4,14.9C138.1,88.6,144.3,88.7,148.3,84.9">
              </path>
              <path
                d="M242.1,154.9c-6.2,0-11.2,5-11.2,11.1l0,0v27.4c0,1.9-1.6,3.5-3.5,3.5H28.5 c-1.9,0-3.5-1.6-3.5-3.5v-27.3c0.2-6.2-4.7-11.3-10.8-11.5s-11.3,4.7-11.5,10.8c0,0.2,0,0.4,0,0.7v27.4 c0,14.2,11.6,25.7,25.8,25.8h198.8c14.2,0,25.8-11.6,25.8-25.8v-27.4C253.1,159.9,248.1,154.9,242.1,154.9L242.1,154.9">
              </path>
            </g>
          </g>
        </svg>
      </div>
    </div>


    <div class="show-download-upload" :style="{ display: openDownloadWindows ? '' : 'none' }">
      <div style="width: 80%; color: #000;margin: 25px 30px 5px 30px; font-size: 16px; text-align: left; font-weight: bold;
        border-bottom: #E2E2E2 1px solid;
        padding-bottom: 20px;">Downloads / Uploads</div>

      <div style="width: 100%; height: 100%; overflow-y:scroll">
        <div v-for="(fileState, index) in fileStateList" class="file-state-card">
          <div style=""><i :class="checkIco(fileState.state)" style="margin-right: 5px;"></i>
            <span style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden">
              {{ fileState.fileName }}
            </span>
            <i class="el-icon-close cancel-button" @click="cancelFile(fileState, index)"></i>
          </div>
          <el-progress :percentage="fileState.percentage" :color="customColorMethod"></el-progress>

        </div>
      </div>

    </div>

    <el-drawer title="我是标题" :visible.sync="showCodeWindows" :with-header="false" size="40%">
      <div class="text-body">
        <div style="width: 100%;height: 100%; ">
          <ace-editor ref="ace" themePath="xcode" :value="value" class="ace-editor" mode-path="javascript">
          </ace-editor>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import AceEditor from '../../components/AceEditor.vue'
import { mapState, mapActions } from "vuex"
export default {
  name: 'Header',
  components: {
    AceEditor
  },

  data() {
    return {
      fileName: '',
      value: '',
      showCodeWindows: false,
      openDownloadWindows: false,
      customColors: [
        { color: 'rgb(7, 25, 62)', percentage: 99 },
        { color: 'rgb(76, 203, 146)', percentage: 100 }
      ]
    }
  },
  methods: {
    ...mapActions("file", {
      searchFile: "searchFile",
      getFileList: "getFileList",
      setLoading: "setLoading",
      removeFileStateList: 'removeFileStateList'
    }),

    openCodeWindows() {
      this.showCodeWindows = true
    },

    showDownloadWindows() {
      this.openDownloadWindows = this.openDownloadWindows ? false : true
    },
    customColorMethod(percentage) {
      if (percentage == 100) {
        return 'rgb(76, 203, 146';
      } else {
        return 'rgb(7, 25, 62)';
      }
    },

    cancelFile(fileState, index) {
      console.log(fileState)
      fileState.source.cancel(fileState.state);
      fileState.percentage = 100

      setTimeout(() => {
        this.removeFileStateList(index)
      }, 1000)
      // delete this.fileStateList[index]

    }
  },

  computed: {
    ...mapState("file", {
      fileList: "fileList",
      dirInfo: "dirInfo",
      uploading: "uploading",
      fileStateList: "fileStateList"
    }),

    checkIco() {
      return function (state) {
        if (state == 'upload') {
          return 'el-icon-upload2'
        } else {
          return 'el-icon-download'
        }
      }
    }

  },
  watch: {
    fileName(newName, oldName) {
      // this.fullName = newName + ' ' + this.lastName;
      if (newName != '') {
        this.searchFile(newName)
      } else {
        this.getFileList(this.dirInfo.id)
      }

    },
    fileStateList(newList, oldList) {
      // console.log(newList, 'newList')
      // console.log(oldList, 'oldList')
      this.openDownloadWindows = true
    }
  }



}
</script>

<style lang="scss" scoped>
.ace-container {
  position: relative;

  .ace-editor {
    width: 500px;
    height: 200px;
  }

  .bookmarklet {
    position: absolute;
    right: 0;
    bottom: 0;
    width: 20px;
    height: 20px;
    z-index: 2;
    cursor: pointer;
    border-width: 9px;
    border-style: solid;
    border-color: lightblue gray gray rgb(206, 173, 230);
    border-image: initial;
  }
}

.text-body {
  width: 100%;
  height: 100%;
  // background-color: #073052;
  display: flex;
  justify-content: center;
  align-items: center;
}

.cancel-button:hover {
  background-color: #E2E2E2;
}

.cancel-button {
  position: absolute;
  right: 10px;
  border-radius: 50%;
}

.file-state-card {
  margin: 0 30px;
  padding: 15px 5px;
  position: relative;
  border-bottom: #E2E2E2 1px solid;
  width: 80%;
}

.show-download-upload {
  top: 60px;
  right: 20px;
  width: 400px;
  border: #EAEDEE 1px solid;
  height: 400px;
  padding: 0;
  z-index: 1000;
  position: absolute;
  box-shadow: rgb(0 0 0 / 8%) 0 2px 10px;
  overflow-x: hidden;
  overflow-y: hidden;
  // visibility: hidden;
  border-radius: 3px;
  background-color: #fff;
  transition-duration: 0.3s;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.header {
  display: flex;
}

.mi-h1 {
  flex: 1;
  display: flex;
  //justify-content: center;
  padding-left: 2%;
  align-items: center;
  color: #073052;
  font-size: 20px;
  font-weight: 600;
}

.mi-h2 {
  flex: 2;
  display: flex;
  justify-content: center;
  align-items: center;
}

.mi-h3 {
  flex: 1;
  display: flex;
  justify-content: right;
  align-items: center;
  padding-right: 2%;
}

.mi-input {
  outline-style: none;
  border: 1px solid #EAEDEE;
  border-radius: 3px;
  padding: 13px 14px;
  width: 100%;
  font-size: 6px;
  font-weight: 700;
}

.mi-input:focus {
  border-color: #484848;
  outline: 0;
  //-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
  //box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
}

.mi-button {
  width: 42px;
  height: 39px;
  display: flex;
  padding: 0;
  justify-content: center;
  align-items: center;
  border: 1px solid rgb(91, 92, 92);
  border-radius: 3px;
}

.mi-button:hover {
  background-color: rgb(242, 242, 242);
  ;
}
</style>

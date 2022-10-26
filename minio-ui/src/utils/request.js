import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
// import store from '@/store'
// import { getToken } from '@/utils/auth'
// create an axios instance
const service = axios.create({
  // url = base url + request url
  baseURL: process.env.VUE_APP_BASE_URL,
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    // if (store.getters.token) {
    //   // let each request carry token
    //   // ['X-Token'] is a custom headers key
    //   // please modify it according to the actual situation
    //   config.headers['X-Token'] = getToken()
    // }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    if (response.headers["download-type"] == 'file') {
      let name = response.headers["content-disposition"].split(";")[1].split("=")[1] //请求头中文件名
      const blob = new Blob([response.data]);
      if ("download" in document.createElement("a")) {
        // 非IE下载
        const elink = document.createElement("a");
        elink.download = name;
        elink.style.display = "none";
        elink.href = URL.createObjectURL(blob);
        document.body.appendChild(elink);
        elink.click();
        URL.revokeObjectURL(elink.href); // 释放URL 对象
        document.body.removeChild(elink);
      } else {
        // IE10+下载
        navigator.msSaveBlob(blob, name);
      }
      setTimeout(() => {
        // this.setState({ visible: false, progress: 0 });
      }, 3000);
      return
    }


    const res = response.data

    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 200) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      //   if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
      //     // to re-login
      //     MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
      //       confirmButtonText: 'Re-Login',
      //       cancelButtonText: 'Cancel',
      //       type: 'warning'
      //     }).then(() => {
      //       store.dispatch('user/resetToken').then(() => {
      //         location.reload()
      //       })
      //     })
      //   }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service

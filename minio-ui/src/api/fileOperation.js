import request from '@/utils/request'

// 查询文件列表
export function getFileList(dirId) {
    return request({
      url: '/file/list/' + dirId,
      method: 'get'
    })
}
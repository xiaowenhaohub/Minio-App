import request from '@/utils/request'

// 查询文件列表
export function getFileList(dirId) {
  return request({
    url: '/file/list/' + dirId,
    method: 'get'
  })
}

export function createFolder(parentDirId, folderName) {
  return request({
    url: '/file/create/' + parentDirId + '/' + folderName,
    method: 'get'
  })
}

export function getFileDetails(fileId) {
  return request({
    url: 'file/query/' + fileId,
    method: 'get'
  })
}

export function deleteFile(fileId) {
  return request({
    url: '/file/delete/' + fileId,
    method: 'delete'
  })
}
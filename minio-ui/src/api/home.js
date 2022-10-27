import request from '@/utils/request'

// 查询文件列表
export function getFileList(dirId) {
  return request({
    url: '/file/list/' + dirId,
    method: 'get'
  })
}

export function searchFile(fileName) {
  return request({
    url: '/file/search/' + fileName,
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

export function deleteFileList(fileIdList) {
  return request({
    url: '/file/delete/list',
    data: fileIdList,
    method: 'post'
  })
}

export function downloadFile(fileId, event) {
  return request({
    url: '/file/download/' + fileId,
    responseType: "blob",
    method: 'get',
    onDownloadProgress: event
  })
}

export function uploadFile(parentDirId, file, event) {
  return request({
    url: '/file/upload/' + parentDirId,
    method: 'post',
    data: file,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    onUploadProgress: event
  })
}
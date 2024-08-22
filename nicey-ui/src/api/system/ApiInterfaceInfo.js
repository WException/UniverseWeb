import request from '@/utils/request'

// 查询接口信息列表
export function listApiInterfaceInfo(query) {
  return request({
    url: '/system/ApiInterfaceInfo/list',
    method: 'get',
    params: query
  })
}

// 查询接口信息详细
export function getApiInterfaceInfo(id) {
  return request({
    url: '/system/ApiInterfaceInfo/' + id,
    method: 'get'
  })
}

// 新增接口信息
export function addApiInterfaceInfo(data) {
  return request({
    url: '/system/ApiInterfaceInfo',
    method: 'post',
    data: data
  })
}

// 修改接口信息
export function updateApiInterfaceInfo(data) {
  return request({
    url: '/system/ApiInterfaceInfo',
    method: 'put',
    data: data
  })
}

// 删除接口信息
export function delApiInterfaceInfo(id) {
  return request({
    url: '/system/ApiInterfaceInfo/' + id,
    method: 'delete'
  })
}

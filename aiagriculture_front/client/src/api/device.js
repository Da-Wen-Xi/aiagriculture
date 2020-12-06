import request from '../utils/request'

export default {
  list() {
    return request({
      url: '/admin/dev/device/list',
      method: 'get'
    })
  },

  pageList(page, limit, searchObj) {
    return request({
      url: `/admin/dev/device/list/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },

  removeById(id) {
    return request({
      url: `/admin/dev/device/remove/${id}`,
      method: 'delete'
    })
  },

  save(device) {
    return request({
      url: '/admin/dev/device/save',
      method: 'post',
      data: device
    })
  },

  getById(id) {
    return request({
      url: `/admin/dev/device/get/${id}`,
      method: 'get'
    })
  },

  updateById(device) {
    return request({
      url: `/admin/dev/device/update`,
      method: 'put',
      data: device
    })
  }
}


import request from '../utils/request'

export default {
  queryKg(name) {
    return request({
      url: `http://localhost:8018/kwgraph/getByName/${name}`,
      method: 'get',
      data: name
    })
  }
}

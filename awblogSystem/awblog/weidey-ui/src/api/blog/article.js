import request from '@/utils/request'

// 查询博客文章列表
export function listArticle(query) {
  return request({
    url: '/blog/article/list',
    method: 'get',
    params: query
  })
}

// 查询博客文章详细
export function getArticle(id) {
  return request({
    url: '/blog/article/' + id,
    method: 'get'
  })
}

// 新增博客文章
export function addArticle(data) {
  return request({
    url: '/blog/article',
    method: 'post',
    data: data
  })
}

// 修改博客文章
export function updateArticle(data) {
  return request({
    url: '/blog/article',
    method: 'put',
    data: data
  })
}

// 删除博客文章
export function delArticle(id) {
  return request({
    url: '/blog/article/' + id,
    method: 'delete'
  })
}

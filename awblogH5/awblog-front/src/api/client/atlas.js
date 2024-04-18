import {request} from "@/utils/request";


export function getArticlePage() {
  return request({
    url: 'select/article/page',
    method: 'get',
  })
}
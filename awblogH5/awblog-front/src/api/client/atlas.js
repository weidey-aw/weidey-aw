import {request} from "@/utils/request";


export function getArticlePage() {
  return request({
    url: '/article/select/page',
    headers: {
      isToken: false
    },
    method: 'get',
  })
}
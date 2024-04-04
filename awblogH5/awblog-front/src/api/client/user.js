import {request} from "@/utils/request";

export  function selectPageUser(params){
    return request({
        url: '/user/page',
        method: 'get',
        params:params
    })
}

/**
 * token校验
 */
export function verifyToken(){
    return request({
        url: '/user/check/token',
        method:'get',
    })
}

/**
 *  退出登录
 */
export function loginOut(){
    return request({
        url: '/user/logout',
        method:'get',
    })
}

/**
 * 忘记密码
 */
export function forgetPassword(params) {
   return request({
       url:'/user/password/forget',
       method:'post',
       data:params
   })


}
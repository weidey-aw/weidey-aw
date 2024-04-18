import {request} from "@/utils/request";

/**
 * 获取邮箱验证码
 * @param data
 * @returns {*}
 */
export function getEmailCode(data)
{
    return request({
        url: '/code',
        headers: {
            isToken: false
        },
        method: 'post',
        data: data
    })
}

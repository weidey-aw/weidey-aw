import {request} from "@/utils/request";


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
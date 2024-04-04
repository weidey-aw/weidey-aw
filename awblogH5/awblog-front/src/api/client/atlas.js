import {request} from "@/utils/request";

/**
 * 分页查询图集
 *
 *
 */
export function selectPage(pageNum,pageSize) {
    return request({
        url: 'http://localhost:9090/atlas/select/page?pageNum='+pageNum+'&pageSize='+pageSize,
        method: 'get',
    })
}


/**
 * 查询图集图片
 *
 *
 *
 */

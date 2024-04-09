import axios from "axios";
import {ElLoading, ElMessage} from 'element-plus'
import errorCode from "@/utils/errorCode";
import {tansParams} from "@/utils/tools/weidey";
import {getToken} from "@/utils/tools/auth";
import cache from "@/plugins/cache";
import {MessageBox} from "@element-plus/icons-vue";

// 全局状态
import {useUserStore} from "@/store/modules/user";

// 环境配置
const env = import.meta.env;

let baseUrl= env.VITE_APP_APT_BASEURL;
// loading框设置局部刷新，且所有请求完成后关闭loading框
let loading;

// 是否显示重新登录
export let isRelogin = { show: false };

function startLoading() {
    loading = ElLoading.service({
        lock: true,
        text: 'Loading',
        background: 'rgba(0, 0, 0, 0.7)',
    })
    setTimeout(() => {
        loading.close()
    }, 5000)
}

function endLoading() {
    loading.close();
}

//声明一个对象用于存储请求个数
let needLoadingRequestCount = 0;


//开启Loading
function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading();
    }
    needLoadingRequestCount++;
}
//关闭Loading
function tryHideFullScreenLoading() {
    if (needLoadingRequestCount <= 0) return;
    needLoadingRequestCount--;
    if (needLoadingRequestCount === 0) {
        endLoading();
    }
}

export  function request(config){



    const instance = axios.create({
        // axios中请求配置有baseURL选项，表示请求URL公共部分
        baseURL: baseUrl,
        // 超时
        timeout: 10000
    })

    // 添加请求拦截器
    instance.interceptors.request.use(function (config) {


        // 是否需要设置 token
        const isToken = (config.headers || {}).isToken === false;
        // 是否需要防止数据重复提交

        const isRepeatSubmit = (config.headers || {}).repeatSubmit === false;
        // 获取本地存储的token
        let token = getToken()
        if (token && !isToken) {
            config.headers['Authorization'] = 'Bearer ' + token;  // 设置请求头
        }
        // get请求映射params参数
        if (config.method === 'get' && config.params) {
            let url = config.url + '?' + tansParams(config.params);
            url = url.slice(0, -1);
            config.params = {};
            config.url = url;
        }
        //检查请求头中是否设置了repeatSubmit字段，并判断其值是否为false。如果不是false，则意味着不需要防止数据重复提交。
        if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
            // 为请求url进行封装
            const requestObj = {
                url: config.url,
                data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
                time: new Date().getTime()  //获取当前时间戳
            }

            //请求大小限制
            const requestSize = Object.keys(JSON.stringify(requestObj)).length; // 请求数据大小
            const limitSize = 5 * 1024 * 1024; // 限制存放数据5M
            if (requestSize >= limitSize) {
                console.warn(`[${config.url}]: ` + '请求数据大小超出允许的5M限制，无法进行防重复提交验证。')
                return config;
            }

            const sessionObj = cache.session.getJSON('sessionObj')
            if (sessionObj === undefined || sessionObj === null || sessionObj === '') {
                cache.session.setJSON('sessionObj', requestObj)
            } else {
                const s_url = sessionObj.url;                  // 请求地址
                const s_data = sessionObj.data;                // 请求数据
                const s_time = sessionObj.time;                // 请求时间
                const interval = 1000;                         // 间隔时间(ms)，小于此时间视为重复提交
                if (s_data === requestObj.data && requestObj.time - s_time < interval && s_url === requestObj.url) {
                    const message = '数据正在处理，请勿重复提交';
                    console.warn(`[${s_url}]: ` + message)
                    return Promise.reject(new Error(message))
                } else {
                    cache.session.setJSON('sessionObj', requestObj)
                }
            }
        }


        showFullScreenLoading(); //显示加载中
        return config;
    }, function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
    });

// 添加响应拦截器
    instance.interceptors.response.use(function (res) {
        // 2xx 范围内的状态码都会触发该函数。
        // 对响应数据做点什么
        // 未设置状态码则默认成功状态
        // console.log(res.data);

        const store = useUserStore();

        tryHideFullScreenLoading();//隐藏加载中
        const code = res.data.code || 200;
        // 获取错误信息
        const msg = errorCode[code] || res.data.msg || errorCode['default']
        // 二进制数据则直接返回
        if (res.request.responseType ===  'blob' || res.request.responseType ===  'arraybuffer') {
            return res.data
        }
        //未授权
        if (code === 401 || code === '401' ) {
            if (!isRelogin.show) {
                isRelogin.show = true;
                MessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
                    confirmButtonText: '重新登录',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    isRelogin.show = false;
                    store.LogOut().then(() => {
                    }).catch(()=>{
                        isRelogin.show = false;
                    })
                });

            }
            return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
        }
        // 服务器错误
        else if (code === 500 || code === '600' ) {
            ElMessage({ message: msg, type: 'error' })
            return Promise.reject(new Error(msg))
        }
        //
        else if (code === 600 || code === '600') {
            ElMessage({ message: msg, type: 'warning' })
            return Promise.reject('error')
        }
        else if (code != 200 ) {
            ElMessage({ message: msg, type: 'error' })
            return Promise.reject('error')
        }
        else {
            return res.data
        }
        //
    }, function (error) {
        // 超出 2xx 范围的状态码都会触发该函数。
        // 对响应错误做点什么
        tryHideFullScreenLoading();//隐藏加载中
        console.log('err' + error)
        let { message } = error;
        if (message === "Network Error") {
            message = "网络连接异常";
        } else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        } else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substr(message.length - 3) + "异常";
        }
        ElMessage({ message: message, type: 'error', duration: 5 * 1000 })
        return Promise.reject(error);
    });

    return instance(config);
}

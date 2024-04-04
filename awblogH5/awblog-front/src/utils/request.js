import axios from "axios";
import {ElLoading, ElMessage} from 'element-plus'
import errorCode from "@/utils/errorCode";

// 环境配置
const env = import.meta.env;

let baseUrl= env.VITE_APP_APT_BASEURL;



// loading框设置局部刷新，且所有请求完成后关闭loading框
let loading;

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

function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading();
    }
    needLoadingRequestCount++;
}

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
        // 在发送请求之前做些什么
        let token = localStorage.getItem("token") ? JSON.parse(localStorage.getItem("token")) : {}
        if (token) {
            config.headers['token'] = token;  // 设置请求头
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
            localStorage.removeItem('token');
            localStorage.removeItem('user');
            localStorage.removeItem('role');
            ElMessage.warning("登录已过期，请重新登录")
            return Promise.reject(new Error(msg))
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

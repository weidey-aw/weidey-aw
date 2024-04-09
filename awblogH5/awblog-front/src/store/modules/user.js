import {getToken, removeToken, setToken} from "@/utils/tools/auth";
import {getInfo, login, logout} from "@/api/client/login";
import { defineStore } from 'pinia'
// 环境配置
const env = import.meta.env;
let baseUrl= env.VITE_APP_APT_BASEURL;

export const useUserStore = defineStore("user",{
    state: () => ({
        token: getToken(),
        id: '',
        name: '',
        avatar: '',
        roles: [],
        permissions: [],
        remark:'', //称号
        isLogin: ()=>{
            return getToken() === ""
        },
    }),
    getters: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_ID: (state, id) => {
            state.id = id
        },
        SET_NAME: (state, name) => {
            state.name = name
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions
        },
        SET_ISLOGIN: (state, isLogin) => {
            state.isLogin = isLogin ;
        }
    },
    persist: true, //开启持久化
    actions: {
        // 登录
        Login(userInfo) {
            const username = userInfo.username.trim()
            const password = userInfo.password
            const code = userInfo.code
            const uuid = userInfo.uuid
            return new Promise((resolve, reject) => {
                login(username, password, code, uuid).then(res => {
                    setToken(res.token)
                    this.token = res.token;
                    this.isLogin = true;
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 获取用户信息
        GetInfo() {
            return new Promise((resolve, reject) => {
                getInfo().then(res => {
                    console.log(res.user);
                    const user = res.user
                    const avatar = (user.avatar === "" || user.avatar == null) ? require("@/assets/images/profile.jpg") : baseUrl + user.avatar;
                    if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
                        this.roles  =res.roles;
                        this.permissions = res.permissions;
                    } else {
                        this.roles  = ['ROLE_DEFAULT'] ;
                    }
                    this.id = user.id;
                    this.name = user.nickName;
                    this.remark = user.remark;
                    this.avatar = avatar;
                    this.isLogin = true;
                    resolve(res)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 退出系统
        LogOut() {
            return new Promise((resolve, reject) => {
                logout(this.token).then(() => {
                    this.clearStore()
                    removeToken()
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },
        clearStore(){
            this.id ='',
            this.name = '',
            this.avatar = '',
            this.roles = [],
            this.permissions = [],
            this.remark ='', //称号
            this.isLogin = false;
        },
        // 前端 登出
        FedLogOut() {
            return new Promise(resolve => {
                this.clearStore()
                removeToken()
                resolve()
            })
        }






    }

})





























// import {defineStore} from 'pinia'
//
// export const useUserStore = defineStore("user",{
//
//     state: () => ({
//             token: getToken(),
//             id: '',
//             name: '',
//             avatar: '',
//             roles: [],
//             permissions: []
//     }),
//     getters: {
//         SET_TOKEN: (state, token) => {
//             state.token = token
//         },
//         SET_ID: (state, id) => {
//             state.id = id
//         },
//         SET_NAME: (state, name) => {
//             state.name = name
//         },
//         SET_AVATAR: (state, avatar) => {
//             state.avatar = avatar
//         },
//         SET_ROLES: (state, roles) => {
//             state.roles = roles
//         },
//         SET_PERMISSIONS: (state, permissions) => {
//             state.permissions = permissions
//         }
//     },
//     actions: {
//             //前端 登录
//             Login(userInfo) {
//                 const username = userInfo.username.trim()
//                 const password = userInfo.password
//                 const code = userInfo.code
//                 const uuid = userInfo.uuid
//                 return new Promise((resolve, reject) => {
//                     login(username, password, code, uuid).then(res => {
//                         setToken(res.token)
//                         resolve()
//                     }).catch(error => {
//                         reject(error)
//                     })
//                 })
//             },
//
//         // 获取用户信息
//         GetInfo({ commit, state }) {
//             return new Promise((resolve, reject) => {
//                 getInfo().then(res => {
//                     const user = res.user
//                     const avatar = (user.avatar == "" || user.avatar == null) ? require("@/assets/img/defUser.png") : process.env.VUE_APP_BASE_API + user.avatar;
//                     if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
//                         commit('SET_ROLES', res.roles)
//                         commit('SET_PERMISSIONS', res.permissions)
//                     } else {
//                         commit('SET_ROLES', ['ROLE_DEFAULT'])
//                     }
//                     commit('SET_ID', user.userId)
//                     commit('SET_NAME', user.userName)
//                     commit('SET_AVATAR', avatar)
//                     resolve(res)
//                    }).catch(error => {
//                       reject(error)
//                   })
//                })
//            },
//             // 前端 登出
//             FedLogOut() {
//                 return new Promise(resolve => {
//                     removeToken()
//                     logout()
//                     resolve()
//                 })
//             }
//     },
// })

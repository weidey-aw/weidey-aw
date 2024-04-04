import {createRouter, createWebHistory} from 'vue-router'
import {verifyToken} from "@/api/client/user";
import {ElMessage} from "element-plus";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/index/home'
    },
    {
      path: '/index',
      name: 'index',
      redirect: '/index/home',
      component: () => import(/* webpackChunkName: "about" */ '../views/Index.vue'),
      meta:{"title":"阿伟博客"},
      children:[
        { path:'/index/home',name: 'Home',component: () => import(/* webpackChunkName: "about" */ '../views/page/Container.vue'),meta:{title:"首页"}},
        { path: '/index/personal',name: 'Personal',component: () => import(/* webpackChunkName: "about" */ '../views/page/Personal.vue'),meta:{title:"个人中心"}}
      ]
    },
    {
      path: '/404',
      name: '404',
      component: () => import(/* webpackChunkName: "about" */ '../views/404.vue'),
      meta:{
        title:'找不到网页啦'
      }
    },
    {
      path: '/empty',
      name: 'empty',
      component: () => import(/* webpackChunkName: "about" */ '../views/Empty.vue'),
      meta:{
        title:'开发中...'
      }
    },
  ]
})

const whiteList = ['/index',"/login","/empty","/index/home"]
router.beforeEach((to, from, next) =>{
  // 设置页面标题
  document.title = (to.meta.title ? to.meta.title : '');
  // 判断是否访问白名单
  if (whiteList.includes(to.path)){
    next()
  }else {
    // 判断是否登录
    let token = localStorage.getItem('token');
    if (token !=null){
      verifyToken().then(res=>{
        if (res.code==='200'){
          next()
        }else {
          localStorage.removeItem('token');
          localStorage.removeItem('user');
          localStorage.removeItem('role');
          ElMessage.warning("登录已过期，请重新登录")
          next({
            path:'/index'
          })
        }
      })
    } //没有token
    else {
      ElMessage.info("请先登录")
      next({
        path:'/index'
      })
    }
  }
})

export default router

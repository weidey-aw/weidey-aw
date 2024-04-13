import {createRouter, createWebHistory} from 'vue-router'
import {ElMessage} from "element-plus";
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {getToken} from "@/utils/tools/auth";
import {isRelogin} from "@/utils/request";
import {useUserStore} from "@/store/modules/user";

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


NProgress.configure({ showSpinner: false })
const whiteList = ['/index',"/login","/empty","/index/home","/404"]
router.beforeEach((to, from, next) =>{
  const store = useUserStore();
  // 设置页面标题
  document.title = (to.meta.title ? to.meta.title : '');
  //设置页面切换进度条
  NProgress.start();
  // 如果没有匹配到任何路由，重定向到404页面
  if (to.matched.length === 0) {
    next('/404');
  }
  // 判断是否访问白名单
  if (whiteList.includes(to.path)){
    NProgress.done()
    next()
  }else {
    // 判断是否登录
    if (getToken()){
         //是否出现登录的弹窗
        isRelogin.show = true;
        //拿登录状态,判断有没有路由
        if (store.roles.length===0){
          store.GetInfo().then(()=>{
            isRelogin.show = false
            next();
            NProgress.done()
          }).catch(err=>{
            isRelogin.show = true;
            store.LogOut().then(()=>{
              next({
                path:'/index'
              })
              ElMessage.info("登录过期");
              NProgress.done()
            });
          })
        }else {
          NProgress.done()
          next();
        }
    }
    //没有token
    else {
      store.FedLogOut().then(()=>{
        ElMessage.info("请先登录");
      })
      NProgress.done()
      next({
        path:'/index'
      })
    }
  }
})

export default router

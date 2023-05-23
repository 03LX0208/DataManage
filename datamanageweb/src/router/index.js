import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "@/views/HomeView";
import Login from '../views/LoginView.vue'
import Register from '../views/RegisterView'
import NotFound from "@/components/error_page/NotFound";
import TimeTable from "@/views/Student/TimeTableView";
import NotPermit from "@/components/error_page/NotPermit";
import CourseGraph from "@/components/CourseGraph";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/login/',
    name: 'login',
    component: Login
  },
  {
    path: '/register/',
    name: 'register',
    component: Register
  },
  {
    path: '/404/',
    name: '404',
    component: NotFound
  },
  {
    path: '/403/',
    name: '403',
    component: NotPermit
  },
  {
    path: '/course/graph/:id',
    name: 'course-graph',
    component: CourseGraph,
  },
  {
    path: '/student/time-table/',
    name: 'time-table',
    component: TimeTable
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404",
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

import store from '@/store/index'

router.beforeEach((to, from, next) => {
  // 持久化登录 权限管理
  const jwt_token = localStorage.getItem("jwt_token");
  if (jwt_token !== "") {
    store.commit("updateToken", jwt_token);
    store.dispatch("getInfo", {
      success() {
        next();
      },
      error() {
        localStorage.setItem("jwt_token", "");
        setTimeout(() => {router.push({name: 'login'});}, 500);
      }
    });
  } else if (to.name === 'login'|| to.name === '404' || to.name === 'register') {
    next()
  } else {
    router.push({name: 'login'});
  }
});

export default router

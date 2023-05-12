import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import("@/views/login.vue")
  },
  {
    path: '/empList',
    name: 'empList',
    component: () => import("@/views/empList.vue")
  },
  // {
  //   path: '/Login',
  //   name: 'LoginLayout',
  //   component: LoginLayout,
  //   // redirect: "/Login/SeekerLogin",
  //   children:[
  //     {
  //       path: '/Login/SeekerLogin',
  //       name: 'SeekerLogin',
  //       component: () => import("@/views/Login/Seeker_Login.vue"),
  //     },
  //     {
  //       path: '/Login/SeekerRegister',
  //       name: 'SeekerRegister',
  //       component: () => import("@/views/Register/Seeker_Register.vue"),
  //     },
  //   ]
  // }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router

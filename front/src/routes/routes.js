import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";

import Dashboard from "@/pages/Dashboard.vue";
import UserProfile from "@/pages/UserProfile.vue";
import TableList from "@/pages/TableList.vue";
import Typography from "@/pages/Typography.vue";
import Icons from "@/pages/Icons.vue";
import Maps from "@/pages/Maps.vue";
import UserList from "@/pages/UserList.vue";
import Main from "@/pages/Main.vue";
import Register from "@/pages/Login/Register.vue";
import Login from "@/pages/Login/Login.vue";
import NoticeWrite from "@/pages/Notice/NoticeWrite.vue";
import NoticeDetail from "@/pages/Notice/NoticeDetail.vue";
import NoticeDelete from "@/pages/Notice/NoticeDelete.vue";
import NoticeModify from "@/pages/Notice/NoticeModify.vue";
import HouseDetail from "@/pages/House/HouseDetail.vue";
import store from "../store";

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/notice",
    name: "Notice",
    component: DashboardLayout,
    children: [
      {
        path: "noticewrite",
        name: "noticewrite",
        component: NoticeWrite,
      },
      {
        path: "noticedetail",
        name: "noticedetail",
        component: NoticeDetail,
      },
      {
        path: "noticedelete",
        name: "noticedelete",
        component: NoticeDelete,
      },
      {
        path: "noticemodify",
        name: "noticemodify",
        component: NoticeModify,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    component: DashboardLayout,
    children: [
      {
        path: "housedetail",
        name: "housedetail",
        component: HouseDetail,
      },
    ],
  },
  {
    path: "/dashboard",
    component: DashboardLayout,

    beforeEnter: (to, from, next) => {
      // ...
      if (to.name == "Dashboard" && from.name == "Main") {
        store.state.visible = true;
      }
      next();
    },
    children: [
      {
        path: "/dashboard",
        name: "Dashboard",
        component: Dashboard,
      },
      {
        path: "user",
        name: "User Profile",
        component: UserProfile,
      },
      {
        path: "table",
        name: "Table List",
        component: TableList,
      },
      {
        path: "typography",
        name: "Typography",
        component: Typography,
      },
      {
        path: "icons",
        name: "실제 매물 정보",
        component: Icons,
      },
      {
        path: "maps",
        name: "Maps",
        meta: {
          hideFooter: true,
        },
        component: Maps,
      },
      {
        path: "userlist",
        name: "UserList",
        component: UserList,
      },
    ],
  },
];

export default routes;

<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar
      :sidebar-item-color="sidebarBackground"
      :sidebar-background-image="sidebarBackgroundImage"
    >
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/dashboard">
        <md-icon>dashboard</md-icon>
        <p>소개</p>
      </sidebar-link>
      <sidebar-link to="/dashboard/user">
        <md-icon>person</md-icon>
        <p>마이페이지</p>
      </sidebar-link>
      <sidebar-link to="/dashboard/table">
        <md-icon>content_paste</md-icon>
        <p>자유게시판</p>
      </sidebar-link>
      <sidebar-link to="/dashboard/typography">
        <md-icon>library_books</md-icon>
        <p>아파트 매매 정보</p>
      </sidebar-link>
      <sidebar-link to="/dashboard/icons">
        <md-icon>bubble_chart</md-icon>
        <p>실제 매물 정보</p>
      </sidebar-link>
      <sidebar-link
        to="/dashboard/userlist"
        v-if="userInfo != null && userInfo.email === 'admin@admin.com'"
      >
        <md-icon>sticky_note_2</md-icon>
        <p>유저 리스트</p>
      </sidebar-link>
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content></dashboard-content>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>

<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";
import { mapState, mapGetters } from "vuex";
// import FixedPlugin from "./Extra/FixedPlugin.vue";

export default {
  computed: {
    ...mapState(["isLogin", "userInfo"]),
  },
  components: {
    TopNavbar,
    DashboardContent,
    ContentFooter,
    MobileMenu,
  },
  data() {
    return {
      sidebarBackground: "green",
      sidebarBackgroundImage: require("@/assets/img/sidebar.png"),
    };
  },
};
</script>

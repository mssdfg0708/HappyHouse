<template>
  <md-toolbar md-elevation="0" class="md-transparent">
    <div class="md-toolbar-row">
      <div class="md-toolbar-section-start">
        <h3 class="md-title">{{ $route.name }}</h3>
      </div>
      <div class="md-toolbar-section-end">
        <div class="md-collapse">
          <md-list>
            <md-list-item href="/dashboard">
              <i class="material-icons">dashboard</i>
              <p class="hidden-lg hidden-md">Dashboard</p>
            </md-list-item>

            <li class="md-list-item">
              <div class="md-list-item-content">
                <drop-down>
                  <md-button
                    slot="title"
                    class="md-button md-just-icon md-simple"
                    data-toggle="dropdown"
                  >
                    <i class="material-icons">person</i>
                  </md-button>
                  <ul class="dropdown-menu dropdown-menu-right">
                    <li>
                      <a>{{ userInfo.userName }} 님</a>
                    </li>
                    <hr />
                    <li><a href="/dashboard/user">마이페이지</a></li>
                    <li><a href="#" @click="onClickLogout()">로그아웃</a></li>
                  </ul>
                </drop-down>
              </div>
            </li>
          </md-list>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
import { mapState, mapGetters } from "vuex";
export default {
  computed: {
    ...mapState(["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  data() {
    return {};
  },
  methods: {
    onClickLogout() {
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      this.$store.dispatch("userLogout", this.userInfo.email);
      sessionStorage.removeItem("accessToken"); //저장된 토큰 없애기
      sessionStorage.removeItem("refreshToken"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "Main" });
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
  },
  created() {
    if (this.userInfo == null) {
      alert("로그인 해주세요!");
      this.$router.push({ path: "/" });
    }
  },
  components: {},
};
</script>

<style lang="css"></style>

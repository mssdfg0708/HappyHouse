<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-size-55">
        <md-card style="width: 80%; position: absolute; top: 10%; left: 10%">
          <md-card-header data-background-color="blue">
            <h4 class="title">💀 해피 데스 하우스 🏠</h4>
            <p class="category">[광주 4반 10팀] 김유완, 서동완, 최강</p>
          </md-card-header>
          <md-card-content>
            <div id="typography">
              <div class="title">
                <h3>최 강 동 완</h3>
                <br /><br />
              </div>
              <div class="row">
                <div class="tim-typo">
                  <h4>
                    <span class="tim-note">로그인 화면</span>회원가입 | 로그인 | 비밀번호 찾기
                  </h4>
                </div>
                <div class="tim-typo">
                  <h4>
                    <span class="tim-note">마이 페이지</span>사용자 정보 [수정,탈퇴] | 아파트
                    관심정보 [조회,삭제]
                  </h4>
                </div>
                <div class="tim-typo">
                  <h4>
                    <span class="tim-note">자유 게시판</span>게시글[등록,수정,삭제,조회] 댓글
                    [작성,수정,삭제]
                  </h4>
                </div>
                <div class="tim-typo">
                  <h4>
                    <span class="tim-note">아파트 매매 정보</span>아파트 조회[등록된 지역,원하는
                    지역], 카카오 맵API[아파트 위치, 상권 정보], 관심정보 등록
                  </h4>
                </div>
                <div class="tim-typo">
                  <h4><span class="tim-note">실제 매물 정보</span>직방 페이지 크롤링</h4>
                </div>
              </div>
            </div>
          </md-card-content>
          <div style="margin-top: 5%">
            <user-card></user-card>
          </div>
        </md-card>
      </div>
      <!-- 카드 A -->
    </div>
  </div>
</template>

<script>
import { UserCard } from "@/pages";
import store from "../store";
export default {
  components: {
    UserCard,
  },
  created() {
    if (store.state.visible) {
      // alert("처음 입장");
      this.notifyVue();
      store.state.visible = false;
    }
  },
  data() {
    return {
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  methods: {
    notifyVue() {
      var color = Math.floor(Math.random() * 4 + 1);
      console.log(store.state.userInfo);
      this.$notify({
        message: `${store.state.userInfo.userName}님 어서오세요!`,
        icon: "add_alert",
        horizontalAlign: "left",
        verticalAlign: "top",
        type: this.type[color],
      });
    },
  },
};
</script>

<template>
  <!-- 아파트 세부정보 입력하는 것 -->
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <div
          class="md-card md-theme-default"
          style="width: 80%; position: absolute; top: 10%; left: 10%"
        >
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">아파트 세부 정보</h4>
            <p class="category" style="text-align: center">
              {{ house.apartmentName }}아파트 세부 정보입니다.
            </p>
          </md-card-header>
          <!-- 아파트 매매 세부 내용 보여지는 부분 -->
          <md-table>
            <md-table-row slot="md-table-row" class="md-layout-item" style="font-weight: bold">
              <md-table-cell style="width: 6%"></md-table-cell>
              <md-table-cell>준공연도</md-table-cell>
              <md-table-cell>아파트 이름</md-table-cell>
              <md-table-cell>상세주소</md-table-cell>
              <md-table-cell>거래금액</md-table-cell>
              <md-button class="md-button md-just-icon md-simple" @click="checkFavorite()">
                <i class="material-icons" id="favoriteBtn">favorite_border</i>
              </md-button>
            </md-table-row>

            <md-table-row slot="md-table-row">
              <md-table-cell></md-table-cell>
              <md-table-cell>{{ house.buildYear }}</md-table-cell>
              <md-table-cell>{{ house.apartmentName }}</md-table-cell>
              <md-table-cell> {{ house.sidoName }}</md-table-cell>
              <md-table-cell>{{ house.recentPrice }}만원</md-table-cell>
            </md-table-row>
          </md-table>

          <!-- 카카오 맵 -->
          <KakaoMap />
          <!-- 돌아가는 버튼 만들기  -->
          <div class="md-layout-item md-size-100 text-right">
            <sidebar-link to=""
              ><md-button class="md-raised md-success" @click="back"
                >목록으로</md-button
              ></sidebar-link
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import KakaoMap from "../../components/KakaoMap.vue";
export default {
  components: {
    KakaoMap,
  },
  data() {
    return {
      aptInfo: [],
      isFavorite: true,
    };
  },
  name: "HouseDetail",
  computed: {
    ...mapState(["house", "userInfo"]),
  },
  mounted() {
    this.showApt();
  },
  methods: {
    checkFavorite() {
      if (this.isFavorite) {
        this.isFavorite = false;
        this.addApt();
      } else {
        this.isFavorite = true;
        this.deleteApt();
      }
    },
    async showApt() {
      //관심 목록 조회
      await axios
        .get(
          `http://localhost:9999/map/favorite/${this.userInfo.email}/${sessionStorage.getItem(
            "refreshToken"
          )}`
        )
        .then(({ data }) => {
          this.aptInfo.length = 0; //배열 초기화
          for (let i = 0; data.length; i++) {
            if (data[i].aptCode == this.house.aptCode) {
              // 버튼 변경
              document.getElementById("favoriteBtn").innerHTML = "favorite";
              this.isFavorite = false;
            }
          }
        });
    },
    addApt() {
      //관심 목록 등록
      console.log("add");
      axios
        .post("http://localhost:9999/map/favorite", {
          email: this.userInfo.email,
          aptCode: this.house.aptCode,
        })
        .then(({ data }) => {
          if (data == 1) {
            this.notifyVue("관심정보에 추가되었습니다.");
            document.getElementById("favoriteBtn").innerHTML = "favorite";
          }
        });
    },
    deleteApt() {
      axios
        .delete("http://localhost:9999/map/favorite", {
          data: {
            email: this.userInfo.email,
            aptCode: this.house.aptCode,
          },
        })
        .then(({ data }) => {
          console.log(this.house.aptCode);
          if (data == 1) {
            this.notifyVue("관심정보에서 삭제되었습니다.");
            this.aptInfo.splice(this.house.idx, 1);

            document.getElementById("favoriteBtn").innerHTML = "favorite_border";
          }
        });
    },
    notifyVue(msg) {
      // 알림 바
      var color = Math.floor(Math.random() * 4 + 1);
      var type = ["", "info", "success", "warning", "danger"];
      this.$notify({
        message: msg,
        icon: "add_alert",
        horizontalAlign: "left",
        verticalAlign: "top",
        type: type[color],
      });
    },
    back() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>

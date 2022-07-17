<template>
  <form>
    <md-card style="width: 80%; position: absolute; top: 10%; left: 10%">
      <md-card-header>
        <h4 class="title">마이페이지</h4>
        <p class="category">Edit your profile</p>
      </md-card-header>
      <md-card-content>
        <!-- 사용자 개인정보 --><br />
        <p class="category">개인정보 수정</p>

        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-33">
            <md-field>
              <label>{{ userInfo.email }}</label>
              <md-input type="email" :disabled="true"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-33">
            <md-field>
              <label>{{ userInfo.userName }}</label>
              <md-input type="text" id="userName"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-100">
            <md-field> <md-button @click="showApi()">우편번호 찾기</md-button><br /> </md-field>
            <md-field>
              <md-input type="text" id="sample4_postcode" placeholder="우편코드" :disabled="true" />
            </md-field>

            <md-field>
              <md-input
                type="text"
                id="sample4_roadAddress"
                placeholder="신 주소"
                :disabled="true"
              />
              <span>수정 전 데이터 : {{ userInfo.address }}</span>
            </md-field>
            <md-field>
              <md-input
                type="text"
                id="sample4_jibunAddress"
                placeholder="구 주소"
                :disabled="true"
              />
              <span id="guide" style="color: #999; display: none"></span>
            </md-field>
          </div>
          <!-- 아파트 관심정보 -->
          <p class="category"><br />아파트 정보</p>
          <div class="md-layout-item md-size-100">
            <md-table v-model="aptInfo" @md-selected="onSelect">
              <md-table-row slot="md-table-row" slot-scope="{ item }" md-auto-select>
                <md-table-cell>{{ item.name }}</md-table-cell>
                <md-table-cell>
                  <md-button class="md-just-icon md-simple md-danger" @click="deleteApt(item)">
                    <md-icon>close</md-icon>
                    <md-tooltip md-direction="top">삭제</md-tooltip>
                  </md-button>
                </md-table-cell>
              </md-table-row>
            </md-table>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button class="md-raised md-danger" @click="userDelete()">회원 탈퇴</md-button>
            <md-button class="md-raised md-success" @click="userUpdate()">정보 수정</md-button>
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>
<script>
import { mapState, mapGetters } from "vuex";
import axios from "axios";

export default {
  computed: {
    ...mapState(["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  name: "nav-tabs-table",
  data() {
    return {
      notifications: {
        topCenter: false,
      },
      user: {
        email: "",
        userName: "",
        address: "",
        password: "",
      },
      selected: [],
      aptInfo: [],
    };
  },
  mounted() {
    this.email = this.userInfo.email;
    this.userName = this.userInfo.userName;
    this.address = this.userInfo.address;
    this.showApt();
  },
  mutations: {
    SET_USER_INFO: (state, userInfo) => {
      //로그인 정보 셋팅
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  methods: {
    async showApt() {
      await axios
        .get(
          `http://localhost:9999/map/favorite/${this.email}/${sessionStorage.getItem(
            "refreshToken"
          )}`
        )
        .then(({ data }) => {
          this.aptInfo.length = 0; //배열 초기화
          for (let i = 0; data.length; i++) {
            let apt = {
              idx: i,
              name: data[i].apartmentName,
              aptCode: data[i].aptCode,
            };
            this.aptInfo.push(apt);
          }
        });
    },
    deleteApt(selecetApt) {
      axios
        .delete("http://localhost:9999/map/favorite", {
          data: {
            email: this.email,
            aptCode: selecetApt.aptCode,
          },
        })
        .then(({ data }) => {
          if (data == 1) {
            this.notifyVue("관심정보에서 삭제되었습니다.");
            this.aptInfo.splice(selecetApt.idx, 1);
            for (let i = selecetApt.idx; i < this.aptInfo.length; i++) {
              this.aptInfo[i].idx = this.aptInfo[i].idx - 1;
            }
            console.log(this.aptInfo);
          }
        });
    },
    userUpdate() {
      let inputUserName = document.getElementById("userName").value;
      let inputUserAddress = document.getElementById("sample4_jibunAddress").value;
      if (inputUserName == "") {
        inputUserName = this.userName;
      }
      if (inputUserAddress == "") {
        inputUserAddress = this.address;
      }
      axios
        .put("http://localhost:9999/user", {
          email: this.email,
          userName: inputUserName,
          address: inputUserAddress,
        })
        .then(({ data }) => {
          let vuex = JSON.parse(sessionStorage.getItem("vuex"));
          vuex.userInfo = data;
          console.log(vuex);
          sessionStorage.setItem("vuex", JSON.stringify(vuex));
          this.notifyVue("수정이 완료되었습니다.");
          this.$router.go();
        });
    },
    userDelete() {
      var userPw = prompt("탈퇴하시겠습니까?", "비밀번호를 입력해주세요");
      console.log(this.email);
      console.log(userPw);
      if (userPw != null) {
        axios
          .delete("http://localhost:9999/user", {
            data: {
              email: this.email,
              password: userPw,
            },
          })
          .then(({ data }) => {
            if (data.message === "success") {
              this.$store.dispatch("userLogout", this.userInfo.email);
              sessionStorage.removeItem("accessToken"); //저장된 토큰 없애기
              sessionStorage.removeItem("refreshToken"); //저장된 토큰 없애기
              if (this.$route.path != "/") this.$router.push({ name: "Main" });
            } else {
              alert("비밀번호가 다릅니다.");
            }
          });
      }
    },
    notifyVue(msg) {
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
    showApi() {
      new daum.Postcode({
        oncomplete: function (data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var roadAddr = data.roadAddress; // 도로명 주소 변수
          var extraRoadAddr = ""; // 참고 항목 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== "" && data.apartment === "Y") {
            extraRoadAddr += extraRoadAddr !== "" ? ", " + data.buildingName : data.buildingName;
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraRoadAddr !== "") {
            extraRoadAddr = " (" + extraRoadAddr + ")";
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById("sample4_postcode").value = data.zonecode;
          document.getElementById("sample4_roadAddress").value = roadAddr;
          document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

          var guideTextBox = document.getElementById("guide");
          // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
          if (data.autoRoadAddress) {
            var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
            guideTextBox.innerHTML = "(예상 도로명 주소 : " + expRoadAddr + ")";
            guideTextBox.style.display = "block";
          } else if (data.autoJibunAddress) {
            var expJibunAddr = data.autoJibunAddress;
            document.getElementById("sample4_jibunAddress").value = expJibunAddr;
            guideTextBox.innerHTML = "(예상 지번 주소 : " + expJibunAddr + ")";
            guideTextBox.style.display = "block";
          } else {
            guideTextBox.innerHTML = "";
            guideTextBox.style.display = "none";
          }
        },
      }).open();
    },
    onSelect: function (items) {
      this.selected = items;
    },
  },
};
</script>
<style></style>

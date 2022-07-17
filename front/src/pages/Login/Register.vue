<template>
  <form style="width: 60%; position: absolute; top: 30%; left: 20%">
    <md-card>
      <md-card-header>
        <h4 class="title">회원가입</h4>
        <p class="category">Complete your profile</p>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>User Email</label>
              <md-input v-model="email" type="email"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>User Name</label>
              <md-input v-model="userName" type="text"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-80">
            <md-field>
              <label>User Password</label>
              <md-input v-model="password" type="password"></md-input>
            </md-field>
            <span>* 8자리 이상 20자리 이하, 영문 숫자 혼합</span>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-100">
            <md-field>
              <md-input type="text" id="sample4_postcode" placeholder="우편코드" :disabled="true" />
            </md-field>
            <md-field> <md-button @click="showApi()">우편번호 찾기</md-button><br /> </md-field>
            <md-field>
              <md-input
                type="text"
                id="sample4_roadAddress"
                placeholder="신 주소"
                :disabled="true"
              />
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
          <div class="md-layout-item md-size-100 text-right">
            <md-button class="md-raised md-success" @click="checkValue()">회원가입</md-button>
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
  data() {
    return {
      email: "",
      password: "",
      userName: "",
      address: "",
    };
  },
  methods: {
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

    checkValue() {
      let err = true;
      let msg = "";
      this.address = document.getElementById("sample4_jibunAddress").value;
      !this.email && ((msg = "이메일을 입력해주세요"), (err = false), this.email);
      err && !this.userName && ((msg = "이름을 입력해주세요"), (err = false), this.description);
      err && !this.password && ((msg = "비밀번호를 입력해주세요"), (err = false), this.password);
      err && !this.address && ((msg = "주소를 입력해주세요"), (err = false), this.address);
      var regExp =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (this.email.match(regExp) == null) {
        err = false;
        msg = "이메일 형식으로 작성해주세요";
      }
      regExp = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,20}$/;
      if (err && this.password.match(regExp) == null) {
        err = false;
        msg = "패스워드 형식으로 작성해주세요";
      }

      if (!err) alert(msg);
      else this.registArticle();
    },

    registArticle() {
      axios
        .post("http://localhost:9999/user", {
          email: this.email,
          password: this.password,
          userName: this.userName,
          address: document.getElementById("sample4_jibunAddress").value,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data == 1) {
            msg = "등록이 완료되었습니다.";
            this.moveList();
          }
          alert(msg);
        });
    },
    // 등록하고 이동하는곳
    moveList() {
      this.$router.push({ path: "/" });
    },
  },
};
</script>

<style></style>

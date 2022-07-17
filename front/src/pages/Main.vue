<template>
  <form style="width: 60%; position: absolute; top: 30%; left: 20%">
    <md-card>
      <md-card-header>
        <h4 class="title">💀 HappyHouse 💀</h4>
        <p class="category">초대받은 사람만 입장할 수 있습니다.</p>
      </md-card-header>
      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 text-center">
            <md-field>
              <label>User Email</label>
              <md-input v-model="user.email" type="email"></md-input>
            </md-field>
            <md-field>
              <label>User Password</label>
              <md-input v-model="user.password" type="password"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-center">
            <sidebar-link to="/register" class="md-raised md-success"
              ><md-button class="md-raised md-success"
                >회원가입</md-button
              ></sidebar-link
            >
            <sidebar-link to=""
              ><md-button class="md-raised md-success" @click="checkValue()"
                >로그인</md-button
              ></sidebar-link
            ><sidebar-link to="/login"
              ><md-button class="md-raised md-success"
                >비밀번호 초기화</md-button
              ></sidebar-link
            >
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Login",
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapActions(["userConfirm"]),
    async checkValue() {
      let err = true;
      let msg = "";
      !this.user.email &&
        ((msg = "이메일을 입력해주세요"), (err = false), this.user.email);
      err &&
        !this.user.password &&
        ((msg = "비밀번호를 입력해주세요"), (err = false), this.user.password);
      if (!err) alert(msg);
      else {
        // this.login();
        await this.userConfirm(this.user); //acations의 로그인 메소드 실행
        let loginOk = JSON.parse(sessionStorage.getItem("vuex"));
        if (loginOk.isLogin) {
          this.$router.push({ path: "Dashboard" });
        }
      }
    },
  },
};
</script>
<style>
.md-list-item {
  display: inline-block;
}
</style>

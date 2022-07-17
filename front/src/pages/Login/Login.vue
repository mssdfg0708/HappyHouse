<template>
  <form style="width: 60%; position: absolute; top: 30%; left: 20%">
    <md-card>
      <md-card-header>
        <h4 class="title">💀 비밀번호 초기화 💀</h4>
        <p class="category">초기화 할 이메일을 입력해주세요.</p>
      </md-card-header>
      <md-card-content>
        <md-field>
          <label>Input Email</label>
          <md-input type="text" name="email" id="email" />
        </md-field>
        <md-button class="md-raised md-success" @click="sendEmail">send</md-button>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
import emailjs from "emailjs-com";
import axios from "axios";
export default {
  methods: {
    sendEmail() {
      var user = {
        email: document.getElementById("email").value,
        password: Math.floor(Math.random() * 100000000 + 1),
      };
      emailjs.send("service_ptwgbnv", "template_yvgvdsl", user, "QJJqNKQnLuqMT0E1k").then(
        (result) => {
          if (result.status == 200) {
            axios
              .put("http://localhost:9999/user/newpassword", {
                email: user.email,
                password: user.password,
              })
              .then(({ data }) => {
                console.log(data);
                if (data.message == "success") {
                  this.$router.push({ path: "/" });
                  alert("비밀번호 초기화 성공!");
                } else {
                  alert("비밀번호 초기화 실패!");
                }
              });
          }
        },
        (error) => {
          console.log("FAILED...", error);
        }
      );
    },
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

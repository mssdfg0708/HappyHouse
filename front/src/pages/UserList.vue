<template>
  <div class="content">
    <form>
      <md-card style="width: 80%; position: absolute; top: 10%; left: 10%">
        <md-card-header>
          <h4 class="title">유저 리스트</h4>
          <p class="category">유저를 추방할 수 있습니다.</p>
        </md-card-header>
        <md-card-content>
          <div class="md-layout">
            <!-- 아파트 관심정보 -->
            <p class="category"><br />유저 정보</p>
            <div class="md-layout-item md-size-100">
              <md-table v-model="userList">
                <md-table-row slot="md-table-row" slot-scope="{ item }" md-auto-select>
                  <md-table-cell>{{ item.email }}</md-table-cell>
                  <md-table-cell>{{ item.userName }}</md-table-cell>
                  <md-table-cell>
                    <md-button class="md-just-icon md-simple md-danger" @click="userDelete(item)">
                      <md-icon>close</md-icon>
                      <md-tooltip md-direction="top">추방</md-tooltip>
                    </md-button>
                  </md-table-cell>
                </md-table-row>
              </md-table>
            </div>
          </div>
        </md-card-content>
      </md-card>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  mounted() {
    this.showUserList();
  },
  data() {
    return {
      refreshToken: sessionStorage.getItem("refreshToken"),
      userList: [
        { email: "test@ac.com", userName: "테스트" },
        { email: "test@ac.com", userName: "테스트" },
        { email: "test@ac.com", userName: "테스트" },
        { email: "test@ac.com", userName: "테스트" },
        { email: "test@ac.com", userName: "테스트" },
      ],
    };
  },
  onSelect: function (items) {
    this.selected = items;
  },
  methods: {
    async showUserList() {
      await axios
        .get(`http://localhost:9999/user/userlist/${sessionStorage.getItem("refreshToken")}`)
        .then(({ data }) => {
          console.log(data);
          this.userList.length = 0; //배열 초기화
          for (let i = 0; data.length; i++) {
            let user = {
              idx: i,
              email: data[i].email,
              userName: data[i].userName,
            };
            this.userList.push(user);
          }
        });
    },
    userDelete(user) {
      var userDelOk = confirm("추방 시키겠습니까?");
      if (userDelOk == true) {
        axios
          .delete("http://localhost:9999/user/exile", {
            data: {
              email: user.email,
              refreshToken: this.refreshToken,
            },
          })
          .then(({ data }) => {
            if (data.message === "success") {
              this.notifyVue(user.userName + "사용자가 추방되었습니다.");
              this.userList.splice(user.idx, 1);
              for (let i = user.idx; i < this.userList.length; i++) {
                this.userList[i].idx = this.userList[i].idx - 1;
              }
              console.log(this.userList);
            } else {
              alert("오류가 발생했습니다.");
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
  },
};
</script>

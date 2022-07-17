<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <div
          class="md-card md-theme-default"
          style="width: 80%; position: absolute; top: 10%; left: 10%"
        >
          <!-- 글상세조회 헤더부분 -->
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">자유게시판 상세 조회</h4>
            <p class="category" style="text-align: center">내용을 읽어보세요</p>
          </md-card-header>
          <md-card-content>
            <simple-table table-header-color="green"></simple-table>
          </md-card-content>
          <!-- 내용 보여지는 부분 -->
          <div>
            <md-table>
              <md-table-row slot="md-table-row" class="md-layout-item" style="font-weight: bold">
                <md-table-cell style="width: 8%"></md-table-cell>
                <md-table-cell style="width: 20%">Title</md-table-cell>
                <md-table-cell style="width: 23%">Writer</md-table-cell>
                <md-table-cell style="width: 23%">Description</md-table-cell>
                <md-table-cell>Date</md-table-cell>
              </md-table-row>

              <md-table-row slot="md-table-row">
                <md-table-cell></md-table-cell>
                <md-table-cell>{{ user.title }}</md-table-cell>
                <md-table-cell>{{ user.writerName }}</md-table-cell>
                <md-table-cell>{{ user.description }}</md-table-cell>
                <md-table-cell>{{ user.dateTime | formatDate }}</md-table-cell>
              </md-table-row>
            </md-table>
          </div>
          <md-card-content>
            <div class="md-layout"></div>
          </md-card-content>
          <!-- 버튼 있는 곳 -->
          <div class="md-layout-item md-size-100 text-right">
            <sidebar-link :to="{ name: 'noticemodify', params: { userNo: user.noticeId } }"
              ><md-button class="md-raised md-success">글 수정</md-button></sidebar-link
            >
            <sidebar-link :to="{ name: 'noticedelete', params: { userNo: user.noticeId } }"
              ><md-button class="md-raised md-success">글 삭제</md-button></sidebar-link
            >
            <sidebar-link to="/dashboard/table"
              ><md-button class="md-raised md-success">목록으로</md-button></sidebar-link
            >
          </div>
          <!-- 여기가 댓글 부분 -->
          <div id="form-commentInfo">
            <div id="comment-count">댓글</div>
            <input id="comment-input" placeholder="댓글을 입력해 주세요." />
            <md-button @click="addReply" id="submit">등록</md-button>
          </div>
          <div
            class="div"
            style="
              overflow: auto;
              height: 400px;
              overflow-x: hidden;
              margin-left: 10%;
              margin-right: 10%;
            "
          >
            <md-table v-model="replys" style="text-align: center">
              <md-table-row slot="md-table-row" slot-scope="{ item }" md-auto-select>
                <md-table-cell>{{ item.writerName }}</md-table-cell>
                <md-table-cell style="width: 320px; padding: 0px">{{
                  item.replyDescription
                }}</md-table-cell>
                <md-table-cell>{{ item.dateTime }}</md-table-cell>
                <md-table-cell style="width: 30px; padding: 0px">
                  <md-button class="md-just-icon md-simple md-danger" @click="updateReply(item)">
                    <md-icon>edit</md-icon>
                    <md-tooltip md-direction="top">수정</md-tooltip>
                  </md-button>
                </md-table-cell>
                <md-table-cell style="width: 30px; padding: 0px">
                  <md-button class="md-just-icon md-simple md-danger" @click="deleteReply(item)">
                    <md-icon>close</md-icon>
                    <md-tooltip md-direction="top">삭제</md-tooltip>
                  </md-button>
                </md-table-cell>
              </md-table-row>
            </md-table>
            <!-- <ul>
              <li v-for="item in replys" v-bind:key="item.writerName">{{ item.idx }}</li>
            </ul> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
import { mapState } from "vuex";
export default {
  watch: {
    replys: function () {
      // this.$router.go();
    },
  },
  name: "NoticeDetail",
  data() {
    return {
      user: {},
      replys: [],
      reply: {
        replyId: 0,
        noticeId: 0,
        writerId: 0,
        writerName: "",
        replyDescription: "",
        dateTime: "",
        email: "",
      },
    };
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  async created() {
    await axios
      .post("http://localhost:9999/notice/hits", {
        refreshToken: sessionStorage.getItem("refreshToken"),
        noticeId: this.$route.params.userNo,
      })
      .then(({ data }) => {});
    await axios
      .get(`http://localhost:9999/notice/${this.$route.params.userNo}`)
      .then(({ data }) => {
        this.user = data;
      });
    // 화면에 보여주기
    await axios.get(`http://localhost:9999/reply/${this.$route.params.userNo}`).then(({ data }) => {
      for (let i = 0; i < data.length; i++) {
        data[i].idx = i;
        this.replys.push(data[i]);
      }
    });
  },
  filters: {
    formatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");
    },
  },
  methods: {
    // 버튼 눌러서 등록 하기
    addReply() {
      axios
        .post("http://localhost:9999/reply", {
          noticeId: this.$route.params.userNo,
          writerId: this.userInfo.loginId,
          writerName: this.userInfo.userName,
          replyDescription: document.getElementById("comment-input").value,
          email: this.userInfo.email,
        })
        .then(({ data }) => {
          if (data.message == "success") {
            axios
              .get(`http://localhost:9999/reply/${this.$route.params.userNo}`)
              .then(({ data }) => {
                this.replys.length = 0;
                for (let i = 0; i < data.length; i++) {
                  data[i].idx = i;
                  this.replys.push(data[i]);
                }
              });
            document.getElementById("comment-input").value = null;
          }
        });
    },

    // 삭제
    deleteReply(selectReply) {
      axios
        .delete("http://localhost:9999/reply", {
          data: {
            replyId: selectReply.replyId,
            email: this.userInfo.email,
          },
        })
        .then(({ data }) => {
          if (data.message == "success") {
            this.notifyVue("댓글이 삭제되었습니다.");
            this.replys.splice(selectReply.idx, 1);
            for (let i = selectReply.idx; i < this.replys.length; i++) {
              this.replys[i].idx = this.replys[i].idx - 1;
            }
          } else {
            this.notifyVue("댓글삭제가 불가능합니다.");
          }
        });
    },
    // 수정
    updateReply(selectReply) {
      let updateText = prompt("내용을 입력해주세요");
      console.log(selectReply);
      axios
        .put("http://localhost:9999/reply", {
          replyId: selectReply.replyId,
          replyDescription: updateText,
          email: this.userInfo.email,
        })
        .then(({ data }) => {
          console.log(data);
          if (data.message == "success") {
            axios
              .get(`http://localhost:9999/reply/${this.$route.params.userNo}`)
              .then(({ data }) => {
                this.replys.length = 0;
                for (let i = 0; i < data.length; i++) {
                  data[i].idx = i;
                  this.replys.push(data[i]);
                }
              });
          } else {
            this.notifyVue("댓글수정이 불가능합니다.");
          }
        });
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

<style scoped>
#form-commentInfo {
  width: 100%;
}
#comments {
  margin-top: 10px;
}
#comment-count {
  margin-left: 10%;
  margin-bottom: 10px;
}

#comment-input {
  margin-left: 10%;
  margin-top: 1%;
  margin-right: 5%;
  width: 70%;
  height: 3.5em;
}
#submit {
  color: rgb(86, 173, 80);
  width: 5.5em;
  height: 3.2em;
  font-size: 15px;
  font-weight: bold;
  color: aliceblue;
}
</style>

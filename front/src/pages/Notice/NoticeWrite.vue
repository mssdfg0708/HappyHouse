<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
        <md-card>
          <!-- 글작성 헤더부분 -->
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">공지사항 글 작성</h4>
            <p class="category" style="text-align: center">공지사항을 작성하세요</p>
          </md-card-header>
          <md-card-content>
            <simple-table></simple-table>
          </md-card-content>
          <!-- 글작성 내용 쓰는 부분 -->
          <md-card-content>
            <div class="md-layout">
              <div class="md-layout-item md-small-size-100 md-size-100">
                <md-field>
                  <label>작성자</label>
                  <md-input
                    ref="writerName"
                    v-model="writerName"
                    type="text"
                    :disabled="true"
                  ></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-small-size-100 md-size-100">
                <md-field>
                  <label>제목</label>
                  <md-input ref="title" v-model="title" type="text"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-size-100">
                <md-field maxlength="5">
                  <label>내용</label>
                  <md-textarea ref="description" v-model="description"></md-textarea>
                </md-field>
              </div>
            </div>
          </md-card-content>
          <!-- 버튼 있는 곳 -->
          <div class="md-layout-item md-size-100 text-right">
            <sidebar-link to=""
              ><md-button class="md-raised md-success" @click="checkValue"
                >글 등록</md-button
              ></sidebar-link
            >
            <sidebar-link to="/dashboard/table">
              <md-button class="md-raised md-success">목록으로</md-button></sidebar-link
            >
          </div>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  mounted: function () {
    this.writerName = this.userInfo.userName;
  },
  computed: {
    ...mapState(["isLogin", "userInfo"]),
  },
  name: "NoticeWrite",
  data() {
    return {
      writerName: "",
      title: "",
      description: "",
    };
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = "";
      !this.writerName && ((msg = "작성자 입력해주세요"), (err = false), this.writerName);
      err && !this.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.description && ((msg = "내용 입력해주세요"), (err = false), this.description);
      if (!err) alert(msg);
      else this.registArticle();
    },

    registArticle() {
      console.log("userInfo : ", this.userInfo);
      axios
        .post("http://localhost:9999/notice", {
          writerName: this.writerName,
          writerId: this.userInfo.loginId,
          title: this.title,
          description: this.description,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          this.moveList();
        });
    },
    // 등록하고 이동하는것
    moveList() {
      this.$router.push({ path: "/dashboard/table" });
    },
  },
};
</script>

<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <div
          class="md-card md-theme-default"
          style="width: 80%; position: absolute; top: 10%; left: 10%"
        >
          <!-- 글수정 헤더부분 -->
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">공지사항 글수정</h4>
            <p class="category" style="text-align: center">공지사항을 수정하세요</p>
          </md-card-header>
          <md-card-content>
            <simple-table table-header-color="green"></simple-table>
          </md-card-content>
          <!-- 글 수정 부분 -->
          <!-- 공지사항은 관리자만 쓰니까 작성자 없이 제목, 내용만  -->
          <md-card-content>
            <md-table>
              <md-table-row slot="md-table-row">
                <th><md-table-cell></md-table-cell></th>
                <th><md-table-cell></md-table-cell></th>
                <th><md-table-cell>Title</md-table-cell></th>
                <th><md-table-cell>Description</md-table-cell></th>
              </md-table-row>

              <md-table-row slot="md-table-row">
                <md-table-cell></md-table-cell>
                <md-table-cell></md-table-cell>
                <md-table-cell>
                  <input
                    type="text"
                    id="title"
                    name="title"
                    v-model="title"
                    ref="title"
                    class="form-control" /><br
                /></md-table-cell>
                <md-table-cell
                  ><input
                    id="description"
                    name="description"
                    v-model="description"
                    ref="description"
                    class="form-control" /><br
                /></md-table-cell>
              </md-table-row>
            </md-table>
          </md-card-content>
          <!-- 버튼 있는 곳 -->
          <div class="md-layout-item md-size-100 text-right">
            <sidebar-link to=""
              ><md-button class="md-raised md-success" @click="checkValue"
                >글 수정</md-button
              ></sidebar-link
            >
            <sidebar-link to="/dashboard/table"
              ><md-button class="md-raised md-success"> 목록으로</md-button></sidebar-link
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "NoticeModify",
  data() {
    return {
      noticeId: 0,
      wirterName: "",
      title: "",
      description: "",
    };
  },
  created() {
    axios.get(`http://localhost:9999/notice/${this.$route.params.userNo}`).then(({ data }) => {
      this.userNo = data.noticeId;
      this.wirterName = data.wirterName;
      this.title = data.title;
      this.description = data.description;
    });
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = "";
      !this.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.description &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.description.focus());

      if (!err) alert(msg);
      else this.modifyBook();
    },

    modifyBook() {
      axios
        .put(`http://localhost:9999/notice/${this.$route.params.userNo}`, {
          noticeId: this.userNo,
          wirterName: this.wirterName,
          title: this.title,
          description: this.description,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          console.log(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "Table List" });
    },
  },
};
</script>

<style scoped>
.logo {
  text-decoration: none;
}
</style>

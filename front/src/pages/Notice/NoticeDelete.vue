<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <div
          class="md-card md-theme-default"
          style="width: 80%; position: absolute; top: 10%; left: 10%"
        >
          <!-- 글삭제 헤더부분 -->
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">자유게시판 글 삭제</h4>
            <p class="category" style="text-align: center">선택한 글을 삭제완료했습니다.</p>
          </md-card-header>
          <md-card-content>
            <simple-table></simple-table>
          </md-card-content>
          <div class="md-layout-item md-size-100 text-right">
            <sidebar-link to="/dashboard/table"
              ><md-button class="md-raised md-success">목록으로</md-button></sidebar-link
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
  name: "NoticeDelete",
  created() {
    console.log(this.$route.params.userNo);
    axios.delete(`http://localhost:9999/notice/${this.$route.params.userNo}`).then(({ data }) => {
      let msg = "삭제 처리시 문제가 발생했습니다";
      if (data === "success") {
        msg = "삭제가 완료되었습니다.";
      }
      console.log(msg);
    });
  },
};
</script>

<style></style>

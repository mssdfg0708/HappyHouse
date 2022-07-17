<template>
  <div>
    <md-table>
      <md-table-row slot="md-table-row">
        <md-table-cell style="width: 7%"></md-table-cell>
        <md-table-cell style="width: 15%">{{ user.index }}</md-table-cell>
        <md-table-cell style="width: 18%"
          ><router-link :to="{ name: 'noticedetail', params: { userNo: user.noticeId } }">{{
            user.title
          }}</router-link></md-table-cell
        >
        <md-table-cell style="width: 19%">{{ user.writerName }}</md-table-cell>
        <md-table-cell style="width: 22%">{{ user.dateTime | formatDate }}</md-table-cell>
        <!-- 일단 임시 조회수 추가 -->
        <md-table-cell>{{ user.hits }}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import moment from "moment";
import axios from "axios";
export default {
  name: "NoticeListItem",
  props: {
    user: Object,
  },
  filters: {
    formatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
  },
};
</script>

<style></style>

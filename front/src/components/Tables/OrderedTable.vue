<template>
  <div>
    <md-table>
      <md-table-row slot="md-table-row">
        <th class="md-table-head"></th>
        <th><md-table-cell></md-table-cell></th>
        <th><md-table-cell>No.</md-table-cell></th>
        <th><md-table-cell>Title</md-table-cell></th>
        <th><md-table-cell>Writer</md-table-cell></th>
        <th><md-table-cell>Date</md-table-cell></th>
        <th><md-table-cell>Views</md-table-cell></th>
      </md-table-row>
    </md-table>
    <notice-list-item v-for="(user, index) in users" :key="index" :user="user"></notice-list-item>
    <div class="md-layout-item md-size-100 text-right">
      <sidebar-link to="/notice/noticewrite"
        ><md-button class="md-raised md-success">글쓰기</md-button></sidebar-link
      >
    </div>
  </div>
</template>

<script>
import NoticeListItem from "@/pages/Notice/NoticeListItem.vue";
import axios from "axios";

export default {
  name: "ordered-table",
  components: {
    NoticeListItem,
  },
  data() {
    return {
      users: [],
    };
  },
  created() {
    axios.get("http://localhost:9999/notice/noticelist").then(({ data }) => {
      for (let i = data.length; i > 0; i--) {
        data[data.length - i].index = i;
      }
      this.users = data;
    });
  },
};
</script>

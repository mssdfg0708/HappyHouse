<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <div
          class="md-card md-theme-default"
          style="width: 80%; position: absolute; top: 10%; left: 10%"
        >
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">자유게시판</h4>
            <p class="category" style="text-align: center">
              {{ userInfo.userName }}님 자유 게시판입니다.
            </p>
          </md-card-header>
          <md-card-content>
            <ordered-table></ordered-table>
          </md-card-content>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import OrderedTable from "@/components/Tables/OrderedTable.vue";
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["userInfo"]),
  },
  components: {
    OrderedTable,
  },
};
</script>

<template>
  <!-- 시도, 구군, 선택하는 바 있는 곳 -->
  <div class="content">
    <div style="text-align: center">
      <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
      <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
      <b-form-select v-model="dongCode" :options="dongs" @change="searchAptList"></b-form-select>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchAptList() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
  },
};
</script>

<style></style>

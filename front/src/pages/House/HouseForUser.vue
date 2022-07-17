<template>
  <!-- 사용자 주변 아파트 정보 목록을 불러오는것 -->
  <div></div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "HouseForUser",
  data() {
    return {
      // 일단 추가
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  props: {
    house: Object,
  },
  // 일단 추가 한 것들 확인 해보기 - user것 주소로 값 넣어주기
  computed: {
    ...mapActions(["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapState(["sidos", "guguns", "dongs", "houses"]),
  },
  methods: {
    selectHouse() {
      console.log("listRow : ", this.house);
      this.detailHouse(this.house);
      this.$router.push({ name: "housedetail" });
    },
  },
};
</script>

<style></style>

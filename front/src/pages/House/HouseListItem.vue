<template>
  <div>
    <md-table>
      <md-table-row slot="md-table-row">
        <md-table-cell style="width: 6%"></md-table-cell>
        <md-table-cell style="width: 30%">{{ house.apartmentName }}</md-table-cell>
        <md-table-cell style="width: 42%">
          {{ house.sidoName }} {{ house.gugunName }} {{ house.dong }}
          {{ house.jibun }}</md-table-cell
        >
        <md-table-cell
          ><md-button class="md-raised md-success" @click="selectHouse"
            >상세보기</md-button
          ></md-table-cell
        >
      </md-table-row>
    </md-table>
  </div>
  <!-- <div>
    <md-table : =" ">
      <md-table-row slot="md-table-row">
        <sidebar-link to="" @click="selectHouse"
          ><md-table-cell md-label="아파트 이름">{{
            house.apartmentName
          }}</md-table-cell></sidebar-link
        >
        <md-table-cell md-label="주소">{{ house.sidoName }}</md-table-cell>
       <md-table-cell md-label="City">{{ item.city }}</md-table-cell>
        <md-table-cell md-label="Salary">{{ item.salary }}</md-table-cell>
  </md-table-row>
    </md-table>
  </div> -->
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.house);
      this.detailHouse(this.house);
      this.$router.push({ name: "housedetail" });
    },
  },
};
</script>

<style></style>

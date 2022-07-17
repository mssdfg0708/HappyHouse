<template>
  <md-table v-if="flag == null">
    <div class="div" style="overflow: auto; height: 400px; overflow-x: hidden">
      <house-list-item v-for="(house, index) in houses" :key="index" :house="house" />
    </div>
  </md-table>
  <md-table v-else
    ><house-for-user v-for="(house, index) in houses" :key="index" :house="house"
  /></md-table>
</template>

<script>
import HouseForUser from "@/pages/House/HouseForUser.vue";
import HouseListItem from "@/pages/House/HouseListItem.vue";
import { mapState } from "vuex";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
    HouseForUser,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["houses"]),
  },
};
</script>

<style></style>

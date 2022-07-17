<template>
  <!-- 아파트 매매정보 보여주는 곳 -->
  <!-- 부트스트랩 뷰 설치 -->
  <!-- npm install vue bootstrap-vue bootstrap -->
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <div
          class="md-card md-theme-default"
          style="width: 80%; position: absolute; top: 10%; left: 10%"
        >
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">아파트 정보</h4>
            <p class="category" style="text-align: center">{{ aptInfo }} 매매 상세정보입니다.</p>
          </md-card-header>
          <md-card-content>
            <!-- 원하는 정보 -->
            <house-search-bar></house-search-bar>
            <br />
            <house-list></house-list>
          </md-card-content>
          <!-- 카카오 맵 -->
          <KakaoMapList />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HouseList from "@/pages/House/HouseList.vue";
import HouseSearchBar from "@/pages/House/HouseSearchBar.vue";
import { mapState, mapActions } from "vuex";
import store from "@/store/index.js";
import axios from "axios";
import KakaoMapList from "@/components/kakaoMapList.vue";

export default {
  data() {
    return {
      aptInfo: this.$store.state.userInfo.address,
    };
  },
  mounted() {
    this.$store.state.houses = null;
    this.$store.state.flag = 1;
    // var add = this.$store.state.userInfo.address;
    axios.get(`http://localhost:9999/map/useraddress/${this.aptInfo}`).then(({ data }) => {
      this.$store.state.houses = data;
      console.log(this.$store.state);
    });
  },
  components: { HouseList, HouseSearchBar, KakaoMapList },
  props: {
    dataBackgroundColor: {
      type: String,
      default: "",
    },
  },
  computed: {
    ...mapState(["flag", "houses", "aptInfo"]),
  },
  methods: {},
};
</script>

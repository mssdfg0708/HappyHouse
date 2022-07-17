<template>
  <div class="map_wrap">
    <div id="map" style="width: 100%; height: 90%; position: relative; overflow: hidden"></div>
  </div>
</template>

<script>
import store from "@/store/index.js";
import { mapState } from "vuex";
export default {
  watch: {
    houses: function () {
      this.initMap();
    },
  },
  computed: {
    ...mapState(["houses", "marks"]),
  },
  name: "kakaoMapList",
  data() {
    return {
      marks: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=b4c5c098c0050477ff4f52938185c95a&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      this.marks = [];
      const container = document.getElementById("map");
      const latt = this.$store.state.houses[0].lat;
      const langg = this.$store.state.houses[0].lng;
      const options = {
        center: new kakao.maps.LatLng(latt, langg),
        level: 7,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // 마커가 표시될 위치입니다

      for (var i = 0; i < this.$store.state.houses.length; i++) {
        this.marks.push({
          title: this.$store.state.houses[i].apartmentName,
          latlng: new kakao.maps.LatLng(
            this.$store.state.houses[i].lat,
            this.$store.state.houses[i].lng
          ),
        });
      }

      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      for (var i = 0; i < 10; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        console.log("마킹", this.marks[i]);
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          title: this.marks[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          position: this.marks[i].latlng, // 마커를 표시할 위치
          image: markerImage, // 마커 이미지
        });
      }

      // 마커가 지도 위에 표시되도록 설정합니다
    },
  },
};
</script>

<style scoped>
.map_wrap {
  width: 100%;
  height: 450px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>

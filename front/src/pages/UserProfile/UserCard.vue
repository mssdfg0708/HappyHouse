<template>
  <div>
    <md-card class="md-card-profile" style="width: 33%">
      <div class="md-card-avatar">
        <img class="img" :src="uwanImage" />
      </div>

      <md-card-content>
        <h6 class="category text-gray">팀장 / Front-End</h6>
        <h4 class="card-title">김 유 완</h4>
        1997.10.18 INFP
        <p class="card-description">
          [GitHub] <a href="https://github.com/dscwan">https://github.com/dscwan</a><br />
        </p>
      </md-card-content>
    </md-card>

    <md-card class="md-card-profile" style="width: 33%">
      <div class="md-card-avatar">
        <img class="img" :src="dongwanImage" />
      </div>

      <md-card-content>
        <h6 class="category text-gray">설계 / Back-End</h6>
        <h4 class="card-title">서 동 완</h4>
        1997.01.07 ISTJ<br />
        <p class="card-description">
          [GitHub] <a href="https://github.com/mssdfg0708">https://github.com/mssdfg0708</a><br />
        </p>
      </md-card-content>
    </md-card>
    <md-card class="md-card-profile" style="width: 33%">
      <div class="md-card-avatar">
        <img class="img" :src="gangImage" />
      </div>

      <md-card-content>
        <h6 class="category text-gray">발표자 / Front-End</h6>
        <h4 class="card-title">최 강</h4>
        1996.01.25 ENFJ<br />
        <p class="card-description">
          [Blog] <a href="https://blog.naver.com/cg1735">https://blog.naver.com/cg1735</a><br />
          [GitHub] <a href="https://github.com/lion1735">https://github.com/lion1735</a><br />
          [Notion]
        </p>
      </md-card-content>
    </md-card>
  </div>
</template>
<script>
export default {
  name: "user-card",
  props: {
    gangImage: {
      type: String,
      default: require("@/assets/img/gang.jpg"),
    },
    dongwanImage: {
      type: String,
      default: require("@/assets/img/dongwan.jpg"),
    },
    uwanImage: {
      type: String,
      default: require("@/assets/img/uwan.jpg"),
    },
  },
  data() {
    return {};
  },
};
</script>
<style></style>
<template>
  <div style="margin-left: 20%">
    <md-card class="md-card-profile">
      <div class="md-card-avatar">
        <img class="img" :src="uwanImage" />
      </div>

      <md-card-content>
        <h6 class="category text-gray">팀장 / Front-End</h6>
        <h4 class="card-title">김 유 완</h4>
        1997.10.18 INFP
        <p class="card-description">
          [GitHub] <a href="https://github.com/dscwan">https://github.com/dscwan</a><br />
        </p>
      </md-card-content>
    </md-card>

    <md-card class="md-card-profile">
      <div class="md-card-avatar">
        <img class="img" :src="dongwanImage" />
      </div>

      <md-card-content>
        <h6 class="category text-gray">설계 / Back-End</h6>
        <h4 class="card-title">서 동 완</h4>
        1997.01.07 ISTJ<br />
        <p class="card-description">
          [GitHub] <a href="https://github.com/mssdfg0708">https://github.com/mssdfg0708</a><br />
        </p>
      </md-card-content>
    </md-card>
    <md-card class="md-card-profile">
      <div class="md-card-avatar">
        <img class="img" :src="gangImage" />
      </div>

      <md-card-content>
        <h6 class="category text-gray">발표자 / Front-End</h6>
        <h4 class="card-title">최 강</h4>
        1996.01.25 ENFJ<br />
        <p class="card-description">
          [Blog] <a href="https://blog.naver.com/cg1735">https://blog.naver.com/cg1735</a><br />
          [GitHub] <a href="https://github.com/lion1735">https://github.com/lion1735</a><br />
          <!-- [Notion]
          <a
            href="https://selective-spectrum-c0a.notion.site/Who-Am-I-9f81af71c25545328e54db3b2e207c90"
            >https://selective-spectrum-c0a.notion.site/Who-Am-I-9f81af71c25545328e54db3b2e207c90</a
          ><br /> -->
        </p>
      </md-card-content>
    </md-card>
  </div>
</template>
<script>
export default {
  name: "user-card",
  props: {
    gangImage: {
      type: String,
      default: require("@/assets/img/gang.jpg"),
    },
    dongwanImage: {
      type: String,
      default: require("@/assets/img/dongwan.jpg"),
    },
    uwanImage: {
      type: String,
      default: require("@/assets/img/uwan.jpg"),
    },
  },
  data() {
    return {};
  },
};
</script>
<style></style>

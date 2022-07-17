<template>
  <div class="content">
    <div
      class="md-layout"
      style="width: 80%; position: absolute; top: 10%; left: 10%"
    >
      <div class="md-layout-item">
        <md-card class="md-card-plain" style="width: 1600px">
          <md-card-header data-background-color="green">
            <h4 class="title">실제 매물 정보</h4>
            <p class="category">
              참고 사이트
              <a
                target="_blank"
                href="https://www.zigbang.com/home/oneroom/map?mkt_source=google_sa_search_pc&keyword=%EC%A7%81%EB%B0%A9&gclid=CjwKCAjw4ayUBhA4EiwATWyBrvmot1FXnVfW590GDKkU93qvmcHwIUA8yKUUhp7s_zFpcmlgVWKoTBoCOl0QAvD_BwE"
                >직방</a
              >
            </p>
          </md-card-header>

          <md-card-content class="md-layout-item md-small-size-100 md-size-100">
            <div class="iframe-container hidden-sm">
              <iframe src="https://www.zigbang.com/home">
                <p>Your browser does not support iframes.</p>
              </iframe>
            </div>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
//npm install jwt-decode
//필수
import jwt_decode from "jwt-decode";
//npm install --save vuex-persistedstate
// 화면 새로고침시 상태 초기화 방지 플러그인
// 저장 storage 변경도 가능
import createPersistedState from "vuex-persistedstate";
import openKakaoMap from "../components/kakaoMapList.vue";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [
    //vuex-persistent가 설치되어 있어야함
    //vuex에 저장되는 값들을 사용하는 웹브라우저의 localstorage에 저장하며, 새로고침시 그 값이 있다면 localstorage의 값을 가져와 사용한다는 원리
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
  state: {
    isLogin: false, //로그인 여부
    isLoginError: false, //로그인 중 에러 발생 여부
    userInfo: null, //로그인 정보
    visible: false,
    // 아파트 정보 불러오기 위한 것
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    aptInfo: null,
    flag: null,
    marks: [{ lat: null, lng: null, name: null }],
  },
  getters: {
    checkUserInfo: function (state) {
      //로그인 정보 가져오기
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      // 로그인 여부 셋팅
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      //로그인 에러 여부 셋팅
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      //로그인 정보 셋팅
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    // 시도,구군,동 내용 가져오기
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dong });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      console.log("Mutations", house);
      state.house = house;
    },
    SET_APTINFO(state, aptInfo) {
      state.aptInfo = aptInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      //id,pw로 로그인 처리
      const instance = axios.create({
        baseURL: "http://localhost:9999",
        headers: {
          "Content-type": "application/json",
        },
      });
      await instance
        .post("/user/login", JSON.stringify(user))
        .then((response) => {
          console.log(response);
          console.log(user);
          //정상 응답 받으면,
          if (response.data.password === "secretPassword") {
            let acToken = response.data["accessToken"]; //access 토큰 정보(jwt)
            let refToken = response.data["refreshToken"]; //refreshToken 정보
            let user = {
              loginId: response.data["id"],
              email: response.data["email"],
              userName: response.data["userName"],
              address: response.data["address"],
            };
            commit("SET_IS_LOGIN", true); //로그인 여부 셋팅
            commit("SET_IS_LOGIN_ERROR", false); // 에러 없음 셋팅
            commit("SET_USER_INFO", user); //유저 정보 셋팅
            sessionStorage.setItem("accessToken", acToken); //세션 스토리지에 토큰 저장해두기
            sessionStorage.setItem("refreshToken", refToken);
          } else {
            commit("SET_IS_LOGIN", false); //로그인 실패 저장
            commit("SET_IS_LOGIN_ERROR", true); //로그인 에러 셋팅
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async userLogout({ commit }, email) {
      const instance = axios.create({
        baseURL: "http://localhost:9999",
        headers: {
          "Content-type": "application/json",
        },
      });
      await instance.put(`/user/logout/${email}`).then((response) => {
        //정상 응답 받으면,
        if (response.data.message === "success") {
          console.log("리프레시 토큰 제거 성공");
          commit("SET_IS_LOGIN", false);
          commit("SET_USER_INFO", null);
        } else {
          console.log("리프레시 토큰 제거 실패");
        }
      });
    },
    async refresh({ commit }, user) {
      const instance = axios.create({
        baseURL: "http://localhost:9999",
        headers: {
          "Content-type": "application/json",
        },
      });
      instance.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refreshToken 셋팅
      console.log("토큰 재발급 시작");
      console.log("기존 토큰 : {}", sessionStorage.getItem("accessToken"));

      await instance
        .post(`user/refresh`, JSON.stringify(user))
        .then((response) => {
          //발급 성공
          if (response.data.password === "secretPassword") {
            let acToken = response.data["accessToken"]; //응답 데이터 중 accessToken 꺼내기
            console.log("새로 발급 : {}", acToken);
            sessionStorage.setItem("accessToken", acToken); //sessionStorage에 꺼낸 accessToken 저장
          }
        })
        .catch((err) => {
          if (err.response.status === 401) {
            //401받으면? 리스페시 토큰도 기간 만료!! --> 다시 로그인 필수
            console.log("갱신 실패");
            //다시 로그인 전에 DB에 저장된 리프레시 토큰 제거 수행
            instance
              .put(`/user/logout/${user.userid}`)
              .then((response) => {
                if (response.data.password === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("리프레시 토큰도 만료되었습니다. 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false); //유저 로그인 상태 바꾸기
                commit("SET_USER_INFO", null); // 셋팅된 유저 정보 없애기
              })
              .catch((err) => {
                alert("뭔가 잘못 됐어요...");
                commit("SET_IS_LOGIN", false); //유저 로그인 상태 바꾸기
                commit("SET_USER_INFO", null); // 셋팅된 유저 정보 없애기
              });
          }
        });
    },
    // 가져오기
    getSido({ commit }) {
      axios
        .get(`http://localhost:9999/map/sido`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      axios
        .get(`http://localhost:9999/map/gugun/${sidoCode}`)
        .then(({ data }) => {
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      axios
        .get(`http://localhost:9999/map/dong/${gugunCode}`)
        .then(({ data }) => {
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseList({ commit }, dongCode) {
      axios
        .get(`http://localhost:9999/map/apt/${dongCode}`)
        .then(({ data }) => {
          commit("SET_HOUSE_LIST", data);
          console.log("하우스 정보 호출 : ", this);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      commit("SET_DETAIL_HOUSE", house);
    },
  },
});

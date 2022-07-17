# HappyHouse


## 팀 구성

총 인원 : 3명   
프론트 엔드 : 2명 &nbsp; 백엔드 : 1명    
나의 역할 : 백엔드 개발

<br>

## 사용 기술

프론트 엔드 : Vue    
백엔드 : SpringBoot, MyBatis, MySql

<br>

## 프로젝트 기능 개요

1. Rest API 서버 개발을 통해 프론트(Vue) 와 백(Spring) 사이의 데이터 통신 구현   
2. JWT를 사용한 로그인, 로그아웃 구현   
3. 게시판 기능을 통해 게시판 CRUD 기능 구현   
4. 아파트 실거래가 정보를 가공, DB에 저장하여 아파트 매매 정보 제공   
5. KakaoMap API 를 활용하여 아파트 위치 정보를 지도에 마킹하여 제공    
6. 각 사용자마다 아파트 관심정보 저장 기능   

<br>

## 기능 명세서

| 순번 | 요구사항 ID | 대분류 | 소분류 | 요구사항 상세 | 구현 |
| --- | --- | --- | --- | --- | --- |
| A | A-01 | 사용자인증 | 회원가입 |  | 완료 |
|  | A-02 |  | 로그인 | JWT 토큰을 발급하여 사용자 로그인 처리 | 완료 |
|  | A-03 |  | 로그아웃 | JWT 토큰을 삭제하여 사용자 로그아웃 처리 | 완료 |
|  | A-04 |  | 비밀번호 초기화 | 메일인증으로 구현 | 완료 |
|  | A-05 |  | 회원탈퇴 | 비밀번호 입력해서 맞는지 체크 | 완료 |
|  | A-06 |  | 관리자 | 사용자 조회, 추방 | 완료 |
| B | B-01 | 자유 게시판 | 등록 |  | 완료 |
|  | B-02 |  | 삭제 | 선택한 공지 내용 삭제 | 완료 |
|  | B-03 |  | 수정 |  | 완료 |
|  | B-04 |  | 조회 |  | 완료 |
|  | B-05 |  | 세부 조회 |  | 완료 |
|  | B-06 |  | 좋아요 |  | 완료 |
|  | B-07 |  | 조회수 |  | 완료 |
|  | B-08 |  | 댓글 |  | 완료 |
| C | C-01 | 아파트 매매 정보 | 사용자 주변 아파트 | 사용자 주소 주변 아파트 리스트 | 완료 |
|  | C-02 |  | 읍면동 조회 |  | 완료 |
|  | C-03 |  | 아파트 리스트 조회 |  | 완료 |
|  | C-04 |  | 아파트 세부 조회 |  | 완료 |
|  | C-05 |  | 지도 마킹 |  | 완료 |
|  | C-06 |  | 아파트 정보 직방 |  | 완료 |
| F | F-01 | 마이페이지 | 사용자 조회 |  | 완료 |
|  | F-02 |  | 사용자 수정 |  | 완료 |
|  | F-02 |  | 사용자 탈퇴 |  | 완료 |
|  | F-03 |  | 관심 정보 등록 |  | 완료 |
|  | F-04 |  | 관심 정보 조회 | 사용자 별로 DB에 테이블에 등록 | 완료 |
|  | F-05 |  | 관심 정보 삭제 |  | 완료 |
| E | E-01 | 엔터테이먼트 | 알림창 | 알림 팝업 | 완료 |
| F | F-01 | 메인화면 | 좌측, 상단, 하단 바 |  | 완료 |
|  | F-02 |  | 팀 소개 |  | 완료 |

<br>
<br>
<br>

## API 명세서

| Index | Method | URL | 기능 | 요청 Body | 설명 |
| --- | --- | --- | --- | --- | --- |
| 1 | POST | /user | 회원가입 | O |  |
|  | POST | /user/login | 로그인 | O |  |
|  | PUT | /user/logout/{email} | 로그아웃 | X |  |
|  | POST | /user/refresh | accessToken refresh API | O |  |
|  | PUT | /user | 정보수정 | O |  |
|  | GET | /user/userlist/{refreshToken} | 전체 회원 리스트 조회 | X |  |
|  | DELETE | /user | 회원 탈퇴 | O |  |
|  | DELETE | /user/exile | 회원 추방 | O |  |
|  | PUT | /user/newpassword | 비밀번호 변경 | O |  |
| 2 | GET | /notice/noticelist | 글 목록 | X | 게시판 |
|  | PUT | /notice/{noticeId} | 글 수정 | O | noticeId |
|  | DELETE | /notice/{noticeId} | 글 삭제 | X |  |
|  | POST | /notice | 글 작성 | O |  |
|  | GET | /notice/{noticeId} | 글 상세조회 | X |  |
|  | POST | /notice/hits | 글 방문 여부 확인, 글 조회수 불러오기 | O |  |
|  | GET | /reply/{noticeId} | 댓글 목록 조회 | X | 게시판 댓글 |
|  | POST | /reply | 댓글 작성 | O |  reply |
|  | PUT | /reply | 댓글 수정 | O |  |
|  | DELETE | /reply | 댓글 삭제 | O |  |
| 3 | GET | /map/sido | 시/도 선택 | X | 아파트 정보 |
|  | GET | /map/gugun/{sido} | 구/군 선택 | X |  |
|  | GET | /map/dong/{gugun} | 동 선택 | X |  |
|  | GET | /map/apt/{dong} | 아파트 정보 출력 | X |  |
|  | GET | /map/useraddress/{address} | 사용자 주소를 통해 아파트 정보 목록 출력 | X |  |
|  | POST | /map/favorite | 아파트 관심정보 추가 | O |  |
|  | DELETE | /map/favorite | 아파트 관심정보 제거 | O |  |
|  | GET | /map/favorite/{email}/{refreshToken} | 아파트 관심목록 조회 | X |  |


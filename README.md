# Mission 1: 데이터 베이스/자바 기반 Pure 자바 프로젝트 과제

## Sqlite 사용

### 총 3 페이지로 구현되어있습니다.
- savePage.jsp: open api 정보 불러오기를 클릭했을때 넘어가는 페이지
- index.jsp: main page 이며 테이블을 보여주는 페이지
- history.jsp: 히스토리정보를 보여주는 페이지

### 서블렛
- index sevlet: open api 정보 불러오기를 처리
- history sevlet: 히스토리 삭제를 처리

### 모델
- history
- wifi

### 컨트롤러
- historyDbController: 히스토리 데이터의 db 저장/읽기/삭제/테이블생성
- wifiDbController: 와이파이 데이터의 db 저장/읽기/검색/테이블생성
- ApiExplorer: Open Api를 로드


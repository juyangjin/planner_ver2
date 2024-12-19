
Lv 0. API 명세 및 ERD 작성 필수
## API 명세서

|기능|Method|URL|request|response|상태코드|
|------|---|---|---|---|---|
|유저 생성 API|POST|/members/signup|요청 body|등록 정보|200:CREATED|
|로그인 API|GET|/auth/login|요청 param|단건응답 정보|200:OK|
|유저선택조회 API|GET|/members/{id}|요청 param|단건응답 정보|200:OK|
|유저목록조회 API|GET|/members|요청 param|다건응답 정보|200:OK|
|유저 수정 API|PUT|/members/{id}|요청 body|수정 정보|200:OK|
|유저 삭제 API|DEL|/members/{id}|요청 param| - |200:OK|
|일정 생성 API|POST|/plan|요청 body|등록 정보|200:CREATED|
|일정선택조회 API|GET|/plan/{id}|요청 param|단건응답 정보|200:OK|
|일정목록조회 API|GET|/plan|요청 param|다건응답 정보|200:OK|
|일정 수정 API|PUT|/plan/{id}|요청 body|수정 정보|200:OK|
|일정 삭제 API|DEL|/plan/{id}|요청 param| - |200:OK|
|댓글 생성 API|POST|/comment/{id}|요청 body|등록 정보|200:CREATED|
|댓글선택조회 API|GET|/comment/{id}|요청 param|단건응답 정보|200:OK|
|댓글목록조회 API|GET|/comment|요청 param|다건응답 정보|200:OK|
|댓글 수정 API|PUT|/comment/{id}|요청 body|수정 정보|200:OK|
|댓글 삭제 API|DEL|/comment/{id}|요청 param| - |200:OK|


## ERD

![image](https://github.com/user-attachments/assets/bc5e9807-a537-4e8f-8e8c-4470d12881da)


## 구현 

<details>
<summary>Lv 1</summary>  

### Lv 1. 일정 생성 및 조회  `필수`
Lv 1. 일정 CRUD 필수
- 작성 유저명, 제목, 내용, 작성일, 수정일을 작성하였고 작성일과 수정일은 추상클래스로 분리하여 추후에 멤버 entity에서도 쓸 수 있게하였다.
- 컨트롤러를 이용하여 일정을 생성, 조회, 수정, 삭제 가능케 하였다. 컨트롤러에서 명령을 내리면 서비스에서 이행되게 하였다.
</details>

<details>
<summary>Lv 2</summary>  
  
### Lv 2. 유저 CRUD 필수
- 유저 entity에 유저명, 이메일을 작성하고 1단계에서 작성한 추상클래스로 작성일과 수정일도 자동생성되게 하였다.
- 일정에 있었던 유저명을 지우고 유저에서 사용하는 고유 식별자를 가질 수 있게 하였다. 이 부분을 구현하기 위해 일정에서 유저 테이블에 있는 값을 가져와서 쓸 수 있게 구현하였다.

</details>

<details>
<summary>Lv 3, 4, 5</summary>  
  
### Lv 3. 회원가입 필수
유저에 비밀번호 필드를 추가하였다. equals를 사용하여 비교만 넣으면 되는 부분이었기 때문에 오래 걸리지 않았다.

### Lv 4. 로그인(인증) 필수
- 로그인 필터를 사용하여 로그인하지 않은 사용자에게는 생성 수정 조회 삭제기능들을 적용할 수 없게 하였다. 적용되지 않는 이유를 구분할 수 있게 로그가 생성되게 설정하였다.
- 커스텀 필터도 작성하여 어떤 주소값을 받았는지 로그가 찍힐 수 있게 하였다.
- 회원가입, 로그인은 위에 쓰여있는 API 명세서에 따라 URL를 다르게 작성하여 로그인 필터가 적용되지 않게 하였다.

### Lv 5. 다양한 예외처리 적용하기 도전
Validation을 활용해 다양한 예외처리 적용하기
정해진 예외처리 항목이 있는것이 아닌 프로젝트를 분석하고 예외사항을 지정

</details>

<details>
<summary>Lv 6, 7</summary>  

### Lv 6. 비밀번호 암호화 도전
Lv.3에서 추가한 비밀번호 필드에 들어가는 비밀번호를 암호화합니다.
-암호화를 위한 PasswordEncoder를 직접 만들어 사용합니다.
- PasswordEncoder 참고 코드

### Lv 7. 댓글 CRUD 도전
생성한 일정에 댓글을 남길 수 있습니다.
댓글과 일정은 연관관계를 가집니다. → 3주차 연관관계 매핑 참고!
댓글을 저장, 조회, 수정, 삭제할 수 있습니다.
댓글은 아래와 같은 필드를 가집니다.
댓글 내용, 작성일, 수정일, 유저 고유 식별자, 일정 고유 식별자 필드

</details>

Lv8 페이징 부분 구현 x

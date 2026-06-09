# 📚 [실습 Ex01] Book REST CRUD — 정답

`Memo_addRest_Controller` 구조를 본떠 **Book(도서)** 을 REST 방식으로 구현하는 실습의 **정답본**입니다.

## 🗂 구성 방식
- **문제(TODO)** : 실제 소스 패키지 안에 `Ex01_` 접두사로 배치되어 있습니다.
  학생이 직접 채워 넣습니다.
- **정답(이 폴더)** : 완성 코드만 모아둔 참고용 폴더입니다. (빌드 대상 아님 / 비교용)

## ✅ Entity 는 기존 것을 그대로 사용
새 엔티티를 만들지 않고 기존 [Book.java](../src/main/java/com/example/demo/Domain/Common/Entity/Book.java) 를 재사용합니다.

| 필드 | 타입 | 비고 |
|---|---|---|
| bookCode | Long | **PK (수동 입력, 자동증가 아님)** |
| bookName | String | |
| publisher | String | |
| isbn | String | |
- 테이블: `book` / `hibernate.hbm2ddl.auto = update`
- bookCode 에 `@GeneratedValue` 가 없으므로 **등록 시 코드를 직접 입력**해야 합니다.

## 📁 문제 파일 위치 (실제 패키지, Ex01_ 접두사)
| 계층 | 파일 |
|---|---|
| DTO | `Domain/Common/Dtos/Ex01_BookDTO.java` |
| Repository | `Domain/Common/Repository/Ex01_BookRepository.java` |
| Service | `Domain/Common/Service/Ex01_BookService.java` |
| ServiceImpl | `Domain/Common/Service/Ex01_BookServiceImpl.java` |
| Controller | `Controller/Ex01_Book_addRest_Controller.java` |
| View | `resources/templates/book/rest/{xhr,fetch,ajax,axios}.html` (4종) |

> Entity 는 `Ex01_` 없이 기존 `Book.java` 사용. 페이징은 기존 `PageDTO`/`PageBlock` 재사용.

## 🌐 REST API 명세
| 메서드 | URL | 설명 | 요청 | 응답 |
|---|---|---|---|---|
| GET | `/book/rest/xhr` | 화면(XMLHttpRequest) | - | HTML |
| GET | `/book/rest/fetch` | 화면(Fetch API) | - | HTML |
| GET | `/book/rest/ajax` | 화면(jQuery $.ajax) | - | HTML |
| GET | `/book/rest/axios` | 화면(axios) | - | HTML |
| GET | `/book/rest/list` | 목록(페이징) | `pageNo,amount` | `{page,list,pageBlock}` |
| POST | `/book/rest/add` | 등록 | Ex01_BookDTO(JSON) | `{message}` / 400 `{field:msg}` |
| PUT | `/book/rest/update` | 수정 | Ex01_BookDTO(JSON) | `{message}` |
| DELETE | `/book/rest/delete` | 삭제 | `{bookCode}` | `{message}` |

## 📝 TODO 목록 (총 18개)
| # | 파일 | 할 일 |
|---|---|---|
| 1~3 | Ex01_BookDTO | 검증 어노테이션 / toEntity() / from() |
| 4 | Ex01_BookRepository | `extends JpaRepository<Book, Long>` |
| 5 | Ex01_BookService | 5개 메서드 시그니처 |
| 6~10 | Ex01_BookServiceImpl | Repository 주입 + 5개 구현 + @Transactional |
| 11~16 | Ex01_Book_addRest_Controller | 예외처리 + axios뷰 + list/add/update/delete |
| 17~18 | axios.html | 폼/목록 + axios CRUD 연결 |

## 🧪 완료 기준 (자가검증)
- [ ] `GET /book/rest/axios` 화면 출력
- [ ] 등록 → 목록 즉시 반영(새로고침 없이)
- [ ] bookName 공백 등록 시 400 + "BOOKNAME 는 필수 항목입니다."
- [ ] 수정/삭제 정상 동작 + "성공!" 메시지
- [ ] 목록 페이징 10건/페이지, bookCode 내림차순

## ⚙️ 학생 작업 순서 팁
1. `Ex01_BookService` (TODO-5) 메서드 정의
2. `Ex01_BookServiceImpl` 에 `implements Ex01_BookService` 붙이고 본문 구현(TODO-6~10)
3. `Ex01_Book_addRest_Controller` 의 service import 주석 해제 + 핸들러 작성(TODO-11~16)
4. `axios.html` 화면 연결(TODO-17~18)

> ⚠️ 골격 상태에서는 `Ex01_BookServiceImpl` 의 `implements` 가 주석 처리되어 있어
> 컴파일/구동에 지장이 없습니다. TODO 를 완성하면서 단계적으로 활성화하세요.

## 🚀 도전 과제 (선택)
1. `xhr / fetch / ajax` 방식 뷰 추가 구현
2. `PageBlock` 으로 페이지 블럭 네비게이션 표시
3. `bookName` 키워드 검색 기능 추가 (Repository 쿼리 메서드)

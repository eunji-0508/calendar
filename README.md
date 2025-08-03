# **API 명세서**

---
### [ 일정을 생성하는 API ]

* URL : POST /calendars


* 인증/인가 : 없음


* 데이터 형식 : JSON

## Requset

```json
{
  "title": "프로그래밍 기초 세션",
  "contents": "프로그래밍 이해하는 시간",
  "name": "김튜터님",
  "passwd": "1234"
}
```

## Response

200 OK
``` json
{
"id": 1,
"title": "프로그래밍 기초 세션",
"contents": "프로그래밍 이해하는 시간",
"name": "김튜터님",
"createdAt": "2025-08-03T19:29:51.8646464",
"modifiedAt": "2025-08-03T19:29:51.8646464"
}
```
<br  >

---
### [ 전체 일정을 조회하는 API ]

* URL : GET /calendar?name={작성자명}


* 인증/인가 : 없음


* 데이터 형식 : JSON

## Requset

* Query Parameter : name

## Response
* 200 OK (Query Parameter가 존재하는 경우)
``` json
[
    {
        "id": 3,
        "title": "자바의 메모리 세션",
        "contents": "메모리가 무엇인지 알아보는 시간",
        "createdAt": "2025-08-03T19:45:05.142999",
        "modifiedAt": "2025-08-03T19:45:05.142999"
    },
    {
        "id": 1,
        "title": "프로그래밍 기초 세션",
        "contents": "프로그래밍 이해하는 시간",
        "createdAt": "2025-08-03T19:29:51.864646",
        "modifiedAt": "2025-08-03T19:29:51.864646"
    }
]
```
<br  >

* 200 OK (Query Parameter가 존재하지 않는 경우)
``` json
[
    {
        "id": 3,
        "title": "자바의 메모리 세션",
        "contents": "메모리가 무엇인지 알아보는 시간",
        "createdAt": "2025-08-03T19:45:05.142999",
        "modifiedAt": "2025-08-03T19:45:05.142999"
    },
    {
        "id": 2,
        "title": "처음 배우는 웹개발 용어 세션",
        "contents": "웹 개발 용어를 배우는 시간",
        "createdAt": "2025-08-03T19:41:30.771506",
        "modifiedAt": "2025-08-03T19:41:30.771506"
    },
    {
        "id": 1,
        "title": "프로그래밍 기초 세션",
        "contents": "프로그래밍 이해하는 시간",
        "createdAt": "2025-08-03T19:29:51.864646",
        "modifiedAt": "2025-08-03T19:29:51.864646"
    }
]
```
<br  >

---
### [ 선택 일정을 조회하는 API ]

* URL : GET /calendar/{calendarId}


* 인증/인가 : 없음


* 데이터 형식 : JSON

## Requset

* 경로 변수로 id를 전달함

## Response
* 200 OK (Query Parameter가 존재하는 경우)
``` json
{
    "id": 1,
    "title": "프로그래밍 기초 세션",
    "contents": "프로그래밍 이해하는 시간",
    "createdAt": "2025-08-03T19:29:51.864646",
    "modifiedAt": "2025-08-03T19:29:51.864646"
}
```
<br  >

---
### [ 일정을 수정하는 API ]

* URL : PUT /calendars/{calendarId}


* 인증/인가 : 비밀번호로 검증


* 데이터 형식 : JSON

## Requset
```json
{
  "title" : "변경된 세션",
  "name" : "김아무개",
  "passwd" : 1111
}
```

## Response

200 OK
``` json
{
    "id": 2,
    "title": "변경된 세션",
    "contents": "웹 개발 용어를 배우는 시간",
    "name": "김아무개",
    "createdAt": "2025-08-03T19:41:30.771506",
    "modifiedAt": "2025-08-03T20:12:26.611988"
}
```
<br  >

---
### [ 일정을 삭제하는 API ]

* URL : DELETE /calendar/{calendarId}


* 인증/인가 : 비밀번호로 검증


* 데이터 형식 : JSON

## Requset
``` json
{
    "passwd" : "1234"
}
```

## Response
* 200 OK
``` json
1
```
<br >

---
## [ 전체 API 요약 ]
| API명  | HTTP 메서드 | URL                       | 인증/검증   | 주요 설명                                             |
| ----- |----------| ------------------------- |---------|---------------------------------------------------|
| 일정 생성 | POST     | `/calendars`              | 없음      | `title`, `name` 필수.                               |
| 전체 조회 | GET      | `/calendars?name={작성자명}`  | 없음      | 작성자명 필터 가능. 없으면 전체 조회. `modifiedAt` 내림차순 정렬.      |
| 단일 조회 | GET      | `/calendars/{calendarId}` | 없음      | id에 해당하는 일정 조회.                                   |
| 일정 수정 | PUT      | `/calendars/{calendarId}` | 비밀번호 검증 | 제목, 작성자명만 수정 가능. `createdAt` 유지, `modifiedAt` 갱신. |
| 일정 삭제 | DELETE   | `/calendars/{calendarId}` | 비밀번호 검증 | 해당 id의 비밀번호 일치 시 일정 삭제.                           |

<br  >

---
## [ ERD ]
[ERD 확인하기](https://www.erdcloud.com/d/sZawiBW3rY8jcnCrm)


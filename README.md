# 일정 관리 앱 API 명세서
---

## 일정 생성 (Create)

* **Endpoint**: `POST /schedules`
* 일정 생성

### Request

| 필드       | 타입     | 설명      |
| -------- | ------ | ------- |
| todo     | string | 할 일     |
| author   | string | 작성자명    |
| password | string | 일정 비밀번호 |

```json
{
  "todo": "스터디하기",
  "author": "user",
  "password": "pw123"
}
```

### Success Response

* **Status**: `201 Created`
 
| 필드      | 타입     | 설명                             |
|-----------|----------|----------------------------------|
| id        | integer  | 생성된 일정의 고유 ID            |
| todo      | string   | 저장된 할 일                     |
| author    | string   | 저장된 작성자명                  |
| createdAt | string   | 생성 시각 (YYYY-MM-DDThh\:mm\:ss)  |
| updatedAt | string   | 수정 시각                        |

```json
{
  "id": 1,
  "todo": "스터디하기",
  "author": "user",
  "createdAt": "2025-05-24T16:00:00",
  "updatedAt": "2025-05-24T16:00:00"
}
```

### Error Response

* **Status**: `400 Bad Request`

```json
{
  "timestamp": "2025-05-24T08:17:47.261+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/schedules"
}
```

---

## 전체 일정 조회 (Read)

* **Endpoint**: `GET /schedules`
* 등록된 모든 일정 조회.

### Request

**Query Parameters (optional)**

| 파라미터       | 타입     | 설명                          |
| ---------- | ------ | --------------------------- |
| modifiedAt | string | `updatedAt` 필터 (YYYY-MM-DD) |
| author     | string | 작성자명 필터                     |

### Success Response

* **Status**: `200 OK`
 
| 필드      | 타입     | 설명                       |
|-----------|----------|----------------------------|
| id        | integer  | 일정의 고유 ID            |
| todo      | string   | 할 일                      |
| author    | string   | 작성자명                   |
| createdAt | string   | 생성 시각                  |
| updatedAt | string   | 수정 시각                  |

```json
[
  {
    "id": 1,
    "todo": "스터디하기",
    "author": "user",
    "createdAt": "2025-05-24T16:00:00",
    "updatedAt": "2025-05-24T16:00:00"
  },
  {
    "id": 2,
    "todo": "운동가기",
    "author": "user",
    "createdAt": "2025-05-24T17:00:00",
    "updatedAt": "2025-05-24T17:00:00"
  }
]
```

---

## 단건 일정 조회 (Read One)

* **Endpoint**: `GET /schedules/{id}`
* 특정 ID의 일정 조회

### Request

**Path Variable**

| 파라미터 | 타입      | 설명            |
| ---- | ------- | ------------- |
| id   | integer | 조회할 일정의 고유 ID |

### Success Response

* **Status**: `200 OK`
 
| 필드      | 타입     | 설명                  |
|-----------|----------|-----------------------|
| id        | integer  | 일정의 고유 ID        |
| todo      | string   | 할 일                 |
| author    | string   | 작성자명              |
| createdAt | string   | 생성 시각             |
| updatedAt | string   | 수정 시각             |

```json
{
  "id": 1,
  "todo": "스터디하기",
  "author": "user",
  "createdAt": "2025-05-24T16:00:00",
  "updatedAt": "2025-05-24T16:00:00"
}
```

### Error Response

* **Status**: `404 Not Found`

```json
{
  "timestamp": "2025-05-24T08:20:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/schedules/999"
}
```

---

## 일정 수정 (Update)

* **Endpoint**: `PUT /schedules/{id}`
*  선택한 일정의 `todo`, `author`를 수정

### Request

**Path Variable**

| 파라미터 | 타입      | 설명            |
| ---- | ------- | ------------- |
| id   | integer | 수정할 일정의 고유 ID |

**Body** (JSON)

| 필드       | 타입     | 설명       |
| -------- | ------ | -------- |
| todo     | string | 수정할 할 일  |
| author   | string | 수정할 작성자명 |
| password | string | 일정 비밀번호  |

```json
{
  "todo": "스터디 복습",
  "author": "user",
  "password": "pw123"
}
```

### Success Response

* **Status**: `200 OK`
 
| 필드      | 타입     | 설명                  |
|-----------|----------|-----------------------|
| id        | integer  | 일정의 고유 ID        |
| todo      | string   | 수정된 할 일          |
| author    | string   | 수정된 작성자명       |
| createdAt | string   | 생성 시각             |
| updatedAt | string   | 수정 시각             |

```json
{
  "id": 1,
  "todo": "스터디 복습",
  "author": "user",
  "createdAt": "2025-05-24T16:00:00",
  "updatedAt": "2025-05-24T17:00:00"
}
```

### Error Response

* **Status**: `400 Bad Request`

```json
{
  "timestamp": "2025-05-24T08:25:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/schedules/1"
}
```

* **Status**: `404 Not Found`

```json
{
  "timestamp": "2025-05-24T08:26:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/schedules/999"
}
```

---

## 일정 삭제 (Delete)

* **Endpoint**: `DELETE /schedules/{id}`
* 선택한 일정 삭제

### Request

**Path Variable**

| 파라미터 | 타입      | 설명            |
| ---- | ------- | ------------- |
| id   | integer | 삭제할 일정의 고유 ID |

**Body** (JSON)

| 필드       | 타입     | 설명      |
| -------- | ------ | ------- |
| password | string | 일정 비밀번호 |

```json
{
  "password": "pw123"
}
```

### Success Response

* **Status**: `204 No Content`
* **Body**: 없음

### Error Response

* **Status**: `400 Bad Request`

```json
{
  "timestamp": "2025-05-24T08:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/schedules/1"
}
```

* **Status**: `404 Not Found`

```json
{
  "timestamp": "2025-05-24T08:31:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/schedules/999"
}
```


# DB ERD
<img width="311" alt="SCR-20250525-mrxu" src="https://github.com/user-attachments/assets/c64210f0-efaf-4835-b68f-edb22b427d1b" />

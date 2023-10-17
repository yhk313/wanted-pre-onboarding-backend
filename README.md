# wanted-pre-onboarding-backend

### 모델
채용공고(recruitment_notice)
- recruitment_notice_id
- company_id
- position
- compensation
- details
- technology_used

회사(company)
- company_id
- name
- country
- region

사용자(user)
- user_id
- name

---

### api 설계
1. 채용공고 등록
- [POST] api/vi/recruitment-notice

2. 채용공고 수정
- [POST] api/vi/recruitment-notice/{id}

3. 채용공고 삭제
- [DELETE] api/vi/recruitment-notice/{id}

4. 채용공고 전체조회
- [GET] api/vi/recruitment-notice/all

5. 채용공고 검색
- [GET] api/vi/recruitment-notice/url?search = 
6. 채용공고 상세 조회
- [GET] api/vi/recruitment-notice/{id}
7. 사용자 채용공고 지원
- [POST] api/vi/recruitment-application

--- 
### 기능 구현
1. 채용공고 등록
- request
```
[POST] api/vi/recruitment-notice
```
```
{
    "companyId": 1,
    "position": "웹개발자",
    "compensation": 100000,
    "details": "웹개발자 모집합니다.",
    "technologyUsed": "python"
}
```


2. 채용공고 수정 
- request
```
  [POST] api/vi/recruitment-notice/{id}
  ```
```
{
    "position": "앱개발자",
    "compensation": 100000,
    "details": "앱개발자 모집합니다.",
    "technologyUsed": "python"
}
```
3. 채용공고 삭제
- request
```
[DELETE] api/vi/recruitment-notice/1
```

4. 채용공고 전체조회
- request
```
[GET] api/vi/recruitment-notice/all
```
- response
```
[
    {
        "recruitmentNoticeId": 3,
        "companyName": "삼성",
        "country": "대한민국",
        "region": "서울",
        "position": "웹개발자",
        "compensation": 4000,
        "technologyUsed": "python"
    },
    {
        "recruitmentNoticeId": 4,
        "companyName": "현대자동차",
        "country": "대한민국",
        "region": "울산",
        "position": "앱개발자",
        "compensation": 5000,
        "technologyUsed": "python"
    },
    {
        "recruitmentNoticeId": 5,
        "companyName": "원티드",
        "country": "대한민국",
        "region": "서울",
        "position": "웹개발자",
        "compensation": 6000,
        "technologyUsed": "python"
    },
    {
        "recruitmentNoticeId": 6,
        "companyName": "현대자동차",
        "country": "대한민국",
        "region": "울산",
        "position": "웹개발자",
        "compensation": 7000,
        "technologyUsed": "python"
    }
]
```

5. 채용공고 검색
- request
```
[GET] api/vi/recruitment-notice/url?search = 삼성
```
- response
```
[
    {
        "recruitmentNoticeId": 3,
        "companyName": "삼성",
        "country": "대한민국",
        "region": "서울",
        "position": "웹개발자",
        "compensation": 4000,
        "technologyUsed": "python"
    }
]
```
6. 채용공고 상세 조회
- request
``` 
[GET] api/vi/recruitment-notice/4
```
- response
```
{
    "recruitmentNoticeId": 4,
    "companyName": "현대자동차",
    "country": "대한민국",
    "region": "울산",
    "position": "앱개발자",
    "compensation": 5000,
    "details": "앱개발자 모집합니다.",
    "technologyUsed": "python",
    "otherRecruitmentNotices": [
        6
    ]
}
```
7. 사용자 채용공고 지원
- request
```
[POST] api/vi/recruitment-application
```
```
{
    "userId": 1,
    "recruitmentNoticeId": 7
}
```

--- 

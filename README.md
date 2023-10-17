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
- 
- [DELETE] api/vi/recruitment-notice/{id}

4. 채용공고 전체조회
- [GET] api/vi/recruitment-notice/all

5. 채용공고 검색
- [GET] api/vi/recruitment-notice/url?search = {search}
6. 채용공고 상세 조회
- [GET] api/vi/recruitment-notice/{id}
7. 사용자 채용공고 지원
- [POST] api/vi/recruitment-application

--- 
### 기능 구현
1. 채용공고 등록
- 회사는 채용공고를 등록합니다.
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
- 해당 공고 id의 채용공고를 수정합니다.
- 회사 id 이외 모두 수정 가능합니다.
- request
```
  [POST] api/vi/recruitment-notice/1
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
- 해당 공고 id 를 삭제합니다.
- request
```
[DELETE] api/vi/recruitment-notice/1
```

4. 채용공고 전체조회
- 모든 채용공고를 리스트 형태로 출력하며, 채용 및 회사정보를 포함합니다.
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
- 회사 이름, 포지션, 사용기술의 텍스트로 검색이 가능하며, 리스트로 출력합니다.
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
- 공고 id의 상세 정보를 조회합니다.
- 회사의 다른 공고 id를 리스트에 담아 출력합니다.
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
- 사용자가 지원한 채용공고를 DB에 저장합니다.
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

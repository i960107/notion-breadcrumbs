
# 테이블 구조
page
- page_id
- title
- content
- parent_id : null가능.

# 응답 형식
```
{
  "pageId": 1,
  "title": "title1",
  "content": "content1",
  "subpages": [
    {
      "id": 8",
      "title": "title8"
    },
    {
      "id": 9",
      "title": "title9"
    }
  ],
  "breadcrumbs": [
    {
      "id": 10",
      "title": "title10"
    },
    {
      "id": 11",
      "title": "title11"
    }
  ]
}
```

# 주의
+ 자신 이름의 브랜치에서 작업한다.
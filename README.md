# teamfresh-backend
(주)팀프레시 [시스템개발 3 팀] 백엔드/풀스택 개발자 과제
<br/>
<br/>

안녕하세요! (주)팀프레시 백엔드/풀스택 개발자 과제를 제출합니다!

TDD, 도커, 프론트 등 다양한 관점에서 과제를 해보면 좋았을 테지만, 개인적인 사정으로 하루만에 프로젝트를 완성해야 했습니다.  
좀 더 역량을 펼치지 못해서 아쉬움이 남아있지만, 개인적으로 과제를 하면서 한 걸음 더 성장하는 기분이 들어서 좋았습니다.  
(주) 팀프레시에 입사하기 위한 과제 전형이지만, 부담갖지 않고 재미있게 진행할 수 있었고 좋은 기회를 주셔서 감사합니다! 😀   

개발하면서 느낀 점을 더해서 과제를 제출하고자 합니다. 

1. 요구사항 처음부터 정확하게 분석하기
- 요구사항을 제대로 파악하기 까지 오래 걸렸습니다. 정확하게 파악하지 못한다면 도중에 계속 바꿔야 하기 때문에 최대한 정확하게 이해하고 프로젝트를 시작하고 싶었습니다.
- 그리고 기존에는 기획 상 변경이 많이 발생하는 곳에서 근무해서 연관관계를 설정하지 않고 Id값으로만 테이블을 join해서 사용했는데, 이번 요구사항에서는 테이블 별 참조관계를 생각해야 해서 꽤 오랜 시간이 걸렸습니다.

2. 클린 코드 작성하기
- 저는 `클린 코드`를 중점으로 개발했습니다.
- SRP원칙에 맞게 최대한 `메소드를 분리`하고자 했으며, 가독성 향상을 위해 for문을 `stream`으로 변경했습니다.
- 팀원들이 같은 프로젝트를 진행하는 경우, 클린한 코드는 필수라고 생각합니다.
- 디자인 패턴을 다양하게 사용할 줄 은 모르지만 객체의 책임과 역할별로 고민해보면서 `static factory method`를 사용해서 다른 사람이 읽기에 이상하지 않은 코드를 작성하기 위해 노력했습니다.

3. 과제 진행시 문제점
- 기존에는 쿼리 위주의 프로젝트를 오랜기간 진행해서 JPA의 영속성 컨텍스트에 대해 잊고 살았습니다.
- 이번 과제를 하면서 `LazyInitializationException` 에러가 발생해서 2시간동안 고생했는데, 해당 문제는  지연 로딩(Lazy Loading) 관련 문제로 영속성 컨텍스트의 생명주기와 트랜잭션에 대해 배울 수 있었습니다.

## 기술 스택
```
Java 17.0.8  
Spring Boot 3.1.5  
MySQL 8.1 (도커 이미지로 로컬에서 사용)
```

## 커밋 컨벤션
```text
docs : 문서, 요구사항 수정
feat : 새로운 기능 추가
fix : 에러 수정
style : 코드 포멧팅 (코드의 들여쓰기, 공백, 줄바꿈, 변수 및 함수명의 명명 규칙을 따르는 등의 작업)
test : 테스트 추가
refactor : 로직을 변경하지 않으면서 코드 품질을 향상시키는 작업(변수명, 매직넘버, 함수 재사용성 높이기, 불필요한 중첩 제거)
```

## 요구 사항 정리
### [Issue #1](https://github.com/codesejin/teamfresh-backend/issues/1#issue-1959256285)  
![image](https://github.com/codesejin/teamfresh-backend/assets/101460733/04f67a53-f612-4892-a251-de7eddb37ac2)


  
## 테이블 스키마
![image](https://github.com/codesejin/teamfresh-backend/assets/101460733/95321df0-339e-40fb-ae75-91e0ad8aff6d)


## 클래스 다이어그램
![image](https://github.com/codesejin/teamfresh-backend/assets/101460733/1aa1fd3d-fec9-4402-b236-e7a875584e36)


## API 문서

### [Postman API docs](https://documenter.getpostman.com/view/19993324/2s9YRE1qq9)
![image](https://github.com/codesejin/teamfresh-backend/assets/101460733/b8ccf9c1-3c2c-4b7f-ad5b-8f2e98f725e7)


## 스프링 서버 시작 시 SQL 삽입 쿼리문 자동 실행
```sql
-- sql
INSERT INTO carrier (carrier_name)
VALUES ('CJ_LOGISTICS'),
       ('LOTTE_LOGISTICS'),
       ('HYUNDAI_LOGISTICS'),
       ('DHL'),
       ('KOREA_POST');
INSERT INTO customer (contact_number, contact_person, name)
VALUES ('John Doe','123-456-7890','ABC Company'),
       ('John','123-456-7890','choco Company'),
       ('Doe','123-456-7890','teamteam'),
       ('park','123-456-7890','rocket fresh'),
       ('seijin','123-456-7890','coupang');
INSERT INTO voc
(blame_type, voc_content, created_at, updated_at, claim_entry_type, claim_status, customer_id, carrier_id, is_compensation_requested)
VALUES ('CARRIER', 'WRONG_DELIVERY_LOCATION', CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6),'KAKAOTALK','INCOMING',1,1,false),
       ('CUSTOMER', 'DAMAGED_ITEM', CURRENT_TIMESTAMP(6),NOW(6),'NAVER_TALK','INCOMING',1,2,false),
       ('CARRIER', 'DELAYED_DELIVERY', CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6),'PHONE','INCOMING',2,3,false),
       ('CUSTOMER', 'MISSING_ITEM', CURRENT_TIMESTAMP(6),NOW(6),'KAKAOTALK','INCOMING',2,4,false),
       ('CARRIER', 'DELAYED_DELIVERY', CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6),'PHONE','INCOMING',3,5,false);
```


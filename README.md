# kakaopayProject
2021 카카오페이 과제 by 김소진 

# 실행 시 주의사항
1. DBConfiguration 의 계정 정보 변경해주세요.
2. 아래 script를 MySQL DB에 실행시켜 기본 TABLE 생성해주세요.
3. JSON 테스트를 용이하게 하기 위해 swagger 를 사용하였습니다. 서버 구동 후 "localhost:8080/swagger-ui.html"에서 확인해주세요.

```sql
-- KEY
CREATE DATABASE `KEY`;

-- KEY.NUMRNG_INFO
CREATE TABLE `NUMRNG_INFO` (
  `KEY_NAME` varchar(100) NOT NULL,
  `KEY_TYP_CD` varchar(2) NOT NULL,
  `BIZ_CLSS_CD` varchar(2) DEFAULT NULL,
  `EXNT` varchar(1000) DEFAULT NULL,
  `CHG_DTTM` datetime NOT NULL,
  `REG_DTTM` datetime NOT NULL,
  PRIMARY KEY (`KEY_NAME`)
) 
;
-- KEY.NUM_NUMRNG
CREATE TABLE `NUM_NUMRNG` (
  `NUM_KEY` int(10) NOT NULL AUTO_INCREMENT,
  `KEY_NAME` varchar(100) NOT NULL,
  `CHG_DTTM` datetime NOT NULL,
  `REG_DTTM` datetime NOT NULL,
  PRIMARY KEY (`NUM_KEY`,`KEY_NAME`)
) 
;

-- KEY.STR_NUMRNG
CREATE TABLE `STR_NUMRNG` (
  `STR_KEY` varchar(19) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `CHG_DTTM` datetime NOT NULL,
  `REG_DTTM` datetime NOT NULL,
  PRIMARY KEY (`STR_KEY`,`KEY_NAME`)
) 
;

```


# 구현 시 신경 쓴 부분
1. Spring boot 로 구현하여 스프링부트 기능을 최대한 많이 활용하였습니다.
2. Scale out 이 쉽도록 Key를 관리하는 Main Table과 Key 유형을 숫자(01) 문자(02) 형으로 구분하여 Table 3개만으로 key 를 관리할 수 있도록 구현하였습니다.
3. 최적의 Key 발급을 위해 숫자형은 MySQL Auto Increment 를 사용하고, 문자형은 랜덤 채번을 위해 UUID 를 활용하였습니다.
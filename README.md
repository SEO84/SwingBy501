# 세계 최고의 데이터베이스 조회 시스템

온갖 고난과 역경을 이겨내고 만든 자바 프로젝트입니다.

## 멤버

- 원종호: 조장, 메인 ui, Subquery 예제
- 서현우: Subquery 예제
- 이상화: Group Function 예제
- 김재한: Join 예제
- 박광호: Join 예제

## 참고사항

- Java 8 이상의 모든 환경에서 문제 없이 컴파일됩니다.
- `ojdbc8_g.jar` 파일을 임의로 이 리포지토리에 포함하면 오라클의 라이센스를 위반하게 되므로,  
  각자 가지고 있는 jar 파일이 잘 연결되어 있는지 다시 확인해 주세요.  



## 게시판 관련 쿼리

````sql

-- EMP 테이블을 복사하여 EMP2 테이블을 만들기
CREATE TABLE EMP2 AS SELECT * FROM EMP;



--복사된 테이블에 고유 키 제약조건 추가하기
ALTER TABLE EMP2 ADD CONSTRAINT EMP2_PK PRIMARY KEY (EMPNO);



-- BOARD 테이블 만들기
CREATE TABLE "BOARD" 
(	
    "BOARDNO" NUMBER(11,0) CONSTRAINT "BOARD_PK" PRIMARY KEY, 
	"TITLE" VARCHAR2(100) NOT NULL, 
	"CONTENT" VARCHAR2(1000) NOT NULL, 
	"WRITER" VARCHAR2(100) NOT NULL, 
	"EMPNO" NUMBER(4,0) NOT NULL CONSTRAINT "BOARD_FK1" REFERENCES "SCOTT"."EMP2" ("EMPNO") , 
	"REGDATE" DATE DEFAULT sysdate NOT NULL
);



-- BOARD 시퀀스 만들기
CREATE SEQUENCE  "SCOTT"."BOARD_SEQ"  MINVALUE 0 INCREMENT BY 1 START WITH 0 CACHE 20 NOORDER  NOCYCLE;



-- EMP2에서 삭제된 EMPLOYEE들을 다시 EMP에서 EMP2로 복사하는 쿼리
INSERT INTO EMP2 SELECT * FROM EMP WHERE EMP.EMPNO NOT IN (SELECT EMPNO FROM EMP2);


````

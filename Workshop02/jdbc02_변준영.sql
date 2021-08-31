-- -- ## 1page ##
-- 1. 급여가 1500 이상인 직원들의 이름, 급여, 부서번호를 조회
SELECT ename, sal, deptno FROM emp WHERE sal>=1500;

-- 2. 직원 중에서 연봉이 2000 이상인 직원들의 이름, 연봉을 조회
SELECT ename, (sal*12) + ifnull(comm,0) as 'ann_sal' FROM emp  where 'ann_sal' >= '2000';

-- 3. 직원 중에서 comm이 없는 직원의 이름과 급여, 업무, comm를 조회
SELECT ename, sal, job, comm from emp where comm is null;

-- 4. 입시한지 가장 오래된 직원순으로 5명 조회
SELECT * from emp order by hiredate desc limit 5 ;

-- 5. 1981년에 입사한 직원중 급여가 1500이상, 2500이하인 직원들의 이름, 급여, 부서번호, 입사일
select ename, sal, deptno, hiredate from emp where sal between 1500 ANd 2500;

-- 6. 이름이 A로 시작하는 직원의 이름, 급여, 입사일을 조회
select ename, sal, hiredate from emp where ename like "A%";

-- 7. 05월에 입사한 직원 이름, 급여, 입사일을 조회
select ename, sal, hiredate from emp where hiredate like "_____05%";

-- 8. 세 번째 이름에 A가 들어간 직원의 이름, 급여, 입사일을 조회
select ename, sal, hiredate from emp where ename like "__A%";

-- 9. 이름이 K로 끝나는 직원의 이름, 급여, 입사일을 조회
select ename, sal, hiredate from emp where ename like"%K";

-- 10. 커미션을 받는 직원의 이름, 커미션, 급여를 조회
select ename, comm, sal from emp where comm is not null;



-- ## 2page ## 
-- 1. dept 테이블 생성
create table dept(
dept_no char(8),
dept_name varchar(100),
dept_loc varchar(100),
dept_tel_no varchar(100));

-- 2. dept_no에 alter문 써서 primary key 생성
alter table dept add primary key(dept_no);

-- 3. dept테이블에 dept_teino 컬럼 추가
alter table dept add dept_teino varchar(100);

insert into dept values ('10', 'SALES', 'SEOUL', '02-671-1111');
insert into dept values ('20', 'FINANCE', 'SEOUL','02-862-2222');
insert into dept values ('30', 'HR', 'BUSAN', '051-111-1111');
insert into dept values ('40', 'PURCHASE', 'BUSAN', '051-222-2222');
insert into dept values ('50', 'MANAGEMENT', 'SEOUL', '02-383-3333');

-- 4. emp 테이블 생성
create table emp(
emp_no char(8),
emp_name varchar(100),
emp_mge varchar(100),
hiredate date,
sal double,
dept_no char(8));

-- 5. dept_no 외래키 설정
alter table emp add primary key(emp_no);
alter table emp add foreign key(dept_no) references dept (dept_no);

-- 6. 사진과 같이 데이터 넣기
insert into emp values ('1001', 'KIM', '1003', '2005-01-15', '4750', '20');
insert into emp values ('1002', 'LEE', '1003', '2008-06-05', '3000', '30');
insert into emp values ('1003', 'PARK', '1001', '2007-11-28', '3500', '10');

-- 7. HR부서가 MANAGEMENT부서로 통합되었다. HR부서 직원에 대한 소속 부서를 MANAGEMENT부서로 변경
UPDATE emp set dept_no = 50 where dept_no = 30;

-- 8. HR 부서를 DEPT 테이블에서 삭제
DELETE from dept where dept_name = 'HR';

-- 0. EMP테이블에 추가, 입사일은 시스템 현재일자로 기입
 insert into emp values('2001', 'CHUNG', '1001', now(), '3000', '50');







select * from dept;
select * from emp;

ALTER TABLE emp ADD foreign key(deptno) references dept(deptno);

-- 1. 'ACCOUNTING' 부서에서 근무하는 직원들의 이름, 급여, 입사일을 조회하시오 (join 사용)
SELECT e.ename, e.sal, e.hiredate from emp e, dept d where e.deptno = d.deptno AND d.DNAME = 'ACCOUNTING';

-- 2. 직원의 이름과 관리자 이름을 조회하시오.
-- 직원의 이름
SELECT e.empno AS '사원번호', e.ename AS '사원이름', m.ename AS '상사이름' 
FROM emp e, emp m
WHERE e.mgr = m.empno;

-- 3. 관리자 이름과  관리하는 직원의 수를 조회,  관리직원수가 3명이상인 관리자만 출력
select e.ename , count(*) 
from emp e, emp m 
where e.mgr = m.empno 
group by m.ENAME 
having count(*)>=3;

-- 4. emp, dept 테이블을 JOIN하여 사원이름, 급여, 부서명을 검색하시오.
select e.ename as '사원이름', e.sal as '급여', d.dname as'부서명'
from emp as e, dept as d
where e.DEPTNO = d.deptno;

-- 5. 이름이 KING사원의 부서명을 검색하시오
select emp.ename, dept.dname 
from emp, dept 
where emp.ename = 'KING'
AND emp.deptno = dept.deptno;

-- 6. emp테이블에 있는 empno와 mgr을 이용해서 서로의 관계를 다음과 같이 출력해라 ex) 'scott의 매니저는 jones이다'
select concat(e.ename,'의 매니저는 ' ,m.ename,'이다' ) as '출력'
from emp e, emp m
where e.mgr=m.empno;



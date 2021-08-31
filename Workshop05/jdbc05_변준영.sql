-- 1. 'ACCOUNTING' 부서에서 근무하는 직원들의 이름, 급여, 입사일을 조회
select ename, sal, hiredate from emp where job = 'ACCOUNTING';

-- 2. 'TURNER'와 같은 부서에서 근무하는 직원의 이름과 부서번호를 조회
select ename, deptno from emp where DEPTNO = (select deptno from emp where ename = 'TURNER');

-- 3. 10번 부서의 평균급여보다 많은 급여를 받는 직원의 이름, 부서번호, 급여를 조회
select ename, deptno, sal from emp where sal > (select avg(sal) from emp where deptno='10');

-- 4. king에게 보고하는 모든 사원의 이름과 급여를 표시 (사원의 이름은 직원으로 AS)
select ename as '사원', sal from emp where mgr = (select empno from emp where ename = 'KING');

-- 5. 평균 급여보다 많은 급여를 받고 이름에 u가 포함된 사원//과 같은 부서에서 근무하는 모든 사원의 사원번호, 이름 및 급여를 표시
select empno, ename, sal from emp 
where deptno = (select deptno from emp where ename like('%u%'))
AND sal > (select avg(sal) from emp);


-- 6. 평균 급여보다 높고 최대 급여보다 낮은 월급을 받는 사원의 정보를 조회
select * from emp 
where sal > (select avg(sal) from emp) 
AND sal < (select max(sal) from emp) ;

-- 7. 급여가 10번부서의 속한 어떤 사원의 급여보다 많은 급여를 받는사원 검색
-- 이때, 10번 부서에 속한 사원은 제외, 사원번호순 정렬
select * from emp 
where sal > any (select sal from emp where deptno = '10')
AND deptno <> '10'
order by empno;

-- 8. 30번 소속 사원들 중에서 급여를 가장 많이 받는 사원보다 더 많은 급여를 받는 사원의 이름과 급여
select ename, sal from emp where sal > (select max(sal) from emp where deptno = '30');

-- 9. 부하직원을 거느린 사원을 검색
select * from emp where empno in(select distinct mgr from emp);

-- 10. 부하직원이 없는 사원 검색
select * from emp where empno != any(select distinct mgr from emp);


select distinct mgr from emp;






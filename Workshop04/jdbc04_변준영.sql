-- 1. 이름이 'adam'인 직원의 급여와 입사일을 조회.(lower()사용)
select ename, sal, hiredate from emp where ename = lower('adam');

-- 2. 7년 이상 장기 근속한 직원들의 이름, 입사일, 급여, 근무년차를 조회하시오
select ename, hiredate, sal, (substr(now(),1,4)-substr(hiredate,1,4))as '년차' from emp;

-- 3. 각 부서별 인원수를 조회하되 인원수가 5명 이상인 부서만 출력
select deptno, count(*) from emp group by deptno having count(*)>=5;

-- 4. 각 부서별 최대급여와 최소급여 조회 , 만약 최대==최소면 제외
select deptno, max(sal), min(sal) from emp group by deptno having max(sal)!= min(sal);

-- 5. 10,20번 부서에 속해있으면서 급여가 2000이상인 직원의 이름,급여, 업무, 부선번호를 조회
select ename, sal, job, deptno from emp where (deptno=10 OR deptno=20) AND sal>2000;

-- 6. 1981년 입사 / 10,20,30 부서 / 급여 1500 이상, 3000이하 / 커미션 받지않는사람 제외, 먼저 입사한 직원 먼저출력, 입사일이 같으면 급여가 많은 직원을 먼저출력
select hiredate, ename, deptno, sal, comm 
from emp 
where substr(hiredate,1,4)=1981 AND DEPTNO in(10, 20, 30) AND (sal>=1500 AND sal<=3000) AND (comm is not null) 
order by hiredate asc, sal desc;

-- 7. 부서가 10,20,30 번인 직원 / 급여 1500~3000 / 부서별 평균 급여조회 / 평균급여 2000이상만 출력, 평균급여가 높은 순으로 출력
select deptno, avg(sal) as 평균급여 
from emp 
where deptno in(10,20,30) and sal between 1500 and 3000 
group by deptno
having avg(sal)>=2000
order by avg(sal)
;

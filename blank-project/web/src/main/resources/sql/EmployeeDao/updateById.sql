UPDATE
    emp
SET
    empno = :empno
    ,ename = :ename
    ,job = :job
    ,mgr = :mgr
    ,hiredate = :hiredate
    ,sal = :sal
    ,comm = :comm
    ,deptno= :deptno
WHERE
    empno = :empno

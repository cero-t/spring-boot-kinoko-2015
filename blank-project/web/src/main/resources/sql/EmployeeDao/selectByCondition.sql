SELECT
    *
FROM
    emp
WHERE
    1 = 1
<#if name??>
    AND ename like '%' || :name || '%'
</#if>
<#if hiredateFrom??>
    AND :hiredateFrom < hiredate
</#if>
<#if hiredateTo??>
    AND hiredate < :hiredateTo
</#if>
<#if deptno??>
    AND deptno = :deptno
</#if>


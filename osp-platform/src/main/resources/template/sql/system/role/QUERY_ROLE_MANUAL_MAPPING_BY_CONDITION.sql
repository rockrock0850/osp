SELECT
        T1.ROLE_ID     AS ROLE_ID,
        T1.ROLE_NAME   AS ROLE_NAME,
        T1.EMPNO       AS EMPNO,
        T1.EMPNAME     AS EMPNAME,
        T1.DEPTCODE    AS DEPTCODE,
        T2.DEPTCHINAME AS DEPTNAME
FROM
        SYS_ROLE_MANUAL_MAPPING T1
LEFT JOIN
        HRM_DEPT T2
ON
        T1.DEPTCODE = T2.DEPTCODE
WHERE
    $P{CONDITION}
    
    
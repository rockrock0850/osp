-- Get All Member
SELECT DISTINCT
    T1.EMPNO       AS EMPNO,
    T1.EMPNAME     AS EMPNAME,
    T1.ENGNAME     AS ENGNAME,
    T1.ALIASNAME   AS NT_ACCOUNT,
    T2.DEPTCODE    AS DEPTCODE,
    T2.DEPTCHINAME AS DEPTNAME
FROM
    HRM_MEMBER T1
LEFT JOIN
    HRM_DEPT T2
ON
    T1.DEPTCODE = T2.DEPTCODE
WHERE
    T1.DEPTCODE IN
    (
        -- Is Dept Head
        SELECT
            DEPTCODE AS DEPTCODE
        FROM
            HRM_DEPT
        WHERE
            HEAD = :USER_ID)
ORDER BY
    T1.EMPNO
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
JOIN
    (
        -- Is Dept Head
        SELECT
            DEPTCODE    AS DEPTCODE,
            DEPTCHINAME AS DEPTCHINAME
        FROM
            HRM_DEPT
        WHERE
            HEAD = :USER_ID ) T2
ON
    T1.DEPTCODE LIKE '%' || T2.DEPTCODE || '%'
ORDER BY
    T1.EMPNO
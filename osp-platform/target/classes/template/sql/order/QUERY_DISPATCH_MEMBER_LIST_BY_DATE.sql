SELECT
    DISP_MEMBER_ID                                    AS DISP_MEMBER_ID,
    EMPNO                                             AS EMPNO,
    EMPNAME                                           AS EMPNAME,
    TO_CHAR(PAUSE_START_TIME,'yyyy-MM-dd hh24:mi:ss') AS PAUSE_START_TIME,
    TO_CHAR(PAUSE_END_TIME,'yyyy-MM-dd hh24:mi:ss')   AS PAUSE_END_TIME,
    ACTIVE                                            AS ACTIVE
FROM
    DISPATCH_MEMBER_CONFIG
WHERE
    TO_DATE(:PAUSE_START_TIME,'yyyy-MM-dd hh24:mi:ss') <= PAUSE_END_TIME
AND TO_DATE(:PAUSE_END_TIME,'yyyy-MM-dd hh24:mi:ss') >= PAUSE_START_TIME
ORDER BY
    PAUSE_START_TIME
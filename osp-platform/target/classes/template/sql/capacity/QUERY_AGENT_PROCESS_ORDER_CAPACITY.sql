-- COLUMN.2 個人待處理產程
SELECT
    T1.PROCESS_USER_ID AS PROCESS_USER_ID,
    SUM(
        CASE
            WHEN T2.SUCCESS_SEC > T2.FAIL_SEC
            THEN T2.FAIL_SEC
            ELSE T2.SUCCESS_SEC
        END)                        AS KPI_SUM,
    TO_CHAR (SYSDATE, 'YYYY-MM-DD') AS KPI_DATE
FROM
    ORDER_MAIN_OSP T1
LEFT JOIN
    SYS_ORDER_TYPE_SETTING T2
ON
    T1.ORDER_TYPE_ID = T2.ORDER_TYPE_ID
WHERE
    T1.PROCESS_USER_ID IN ( $P{AGENT_IN} )
AND T1.ORDER_STATUS NOT IN ( '000',
                            '070',
                            '080' )
GROUP BY
    T1.PROCESS_USER_ID
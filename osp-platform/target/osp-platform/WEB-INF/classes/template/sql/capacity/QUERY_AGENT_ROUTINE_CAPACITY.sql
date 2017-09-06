-- COLUMN.1 實際完成產能
-- STEP.1 查詢ROUTINE產能
SELECT
    T1.PROCESS_USER_ID                     AS PROCESS_USER_ID,
    TO_CHAR (T1.PROCESS_DATE,'YYYY-MM-DD') AS KPI_DATE,
    SUM (T1.ITEM_VALUE * T2.RESERV1)       AS KPI_SUM
FROM
    BUZ_RECORD_ROUTINE T1
LEFT JOIN
    (
        SELECT
            ITEM_ID,
            RESERV1
        FROM
            BUZ_ITEM
        WHERE
            ITEM_TYPE ='ROUT') T2
ON
    T1.ITEM_ID = T2.ITEM_ID
WHERE
    T1.PROCESS_USER_ID IN ( $P{AGENT_IN} )
AND PROCESS_DATE BETWEEN TO_DATE (:START_DATE, 'YYYY-MM-DD') AND TO_DATE
    (:END_DATE, 'YYYY-MM-DD')
GROUP BY
    TO_CHAR (T1.PROCESS_DATE, 'YYYY-MM-DD') ,
    T1.PROCESS_USER_ID
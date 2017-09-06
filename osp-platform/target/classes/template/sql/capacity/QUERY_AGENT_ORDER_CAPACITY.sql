-- COLUMN.1 實際完成產能
-- STEP.2 查詢ORDER產能
SELECT
    SRC.KPI_OWNER_ID AS PROCESS_USER_ID,
    SRC.KPI_DATE     AS KPI_DATE,
    SUM(SRC.KPI_SEC) AS KPI_SUM
FROM
    (
        SELECT
            KPI_SRC.KPI_OWNER_ID                   AS KPI_OWNER_ID,
            TO_CHAR (T1.UPDATE_DATE, 'YYYY-MM-DD') AS KPI_DATE,
            CASE
                WHEN T1.ORDER_STATUS = '070'
                THEN T2.SUCCESS_SEC
                WHEN T1.ORDER_STATUS = '080'
                THEN T2.FAIL_SEC
            END AS KPI_SEC
        FROM
            ORDER_MAIN_OSP T1
        JOIN
            (
                SELECT
                    ORDER_M_ID,
                    KPI_OWNER_ID
                FROM
                    ORDER_KPI_OWNER
                WHERE
                    KPI_OWNER_ID IN ( $P{AGENT_IN} ) ) KPI_SRC
        ON
            T1.ORDER_M_ID = KPI_SRC.ORDER_M_ID
        LEFT JOIN
            SYS_ORDER_TYPE_SETTING T2
        ON
            T1.ORDER_TYPE_ID = T2.ORDER_TYPE_ID
        WHERE
            T1.UPDATE_DATE BETWEEN TO_DATE (:START_DATE, 'yyyy-MM-dd hh24:mi:ss') AND
            TO_DATE (:END_DATE, 'yyyy-MM-dd hh24:mi:ss')
        AND T1.ORDER_STATUS IN ('070',
                                '080') ) SRC
GROUP BY
    SRC.KPI_DATE,
    SRC.KPI_OWNER_ID
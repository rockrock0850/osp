SELECT
    T1.ORDER_M_ID                                                 AS ORDER_M_ID,
    T1.SOURCE_ORDER_ID                                            AS SOURCE_ORDER_ID,
    T1.SOURCE_PROD_TYPE_NAME                                      AS SOURCE_PROD_TYPE_NAME,
    T3.OPTIONS_TEXT                                               AS OPERATE_TYPE,
    T1.CRITICAL_FLAG                                              AS CRITICAL_FLAG,
    T1.MSISDN                                                     AS MSISDN,
    T1.CUST_NAME                                                  AS CUST_NAME,
    T1.PARTENT_ORDER_ID                                           AS PARTENT_ORDER_ID,
    T1.COUNTS                                                     AS COUNTS,
    T1.CUST_SPECIFY_DATE                                          AS CUST_SPECIFY_DATE,
    T1.PROCESS_USER_ID                                            AS PROCESS_USER_ID,
    T1.PROCESS_USER_NAME                                          AS PROCESS_USER_NAME,
    T1.ORDER_TYPE_ID                                              AS ORDER_TYPE_ID,
    T1.ORDER_TYPE_NAME                                            AS ORDER_TYPE_NAME,
    T1.FLOW_ID                                                    AS FLOW_ID,
    T2.STATUS_NAME                                                AS ORDER_STATUS,
    T1.OSP_CREATE_TIME                                            AS OSP_CREATE_TIME,
    T1.EXPECT_PROCESS_TIME                                        AS EXPECT_PROCESS_TIME,
    T1.EXPECT_COMPLETE_TIME                                       AS EXPECT_COMPLETE_TIME,
    T4.OPTIONS_TEXT                                               AS SOURCE_SYS_ID,
    (T1.EXPECT_COMPLETE_TIME - T5.OVERTIME / (24 * 60)) - SYSDATE AS OVERDUE_TIME,
    CASE
        WHEN (T1.EXPECT_COMPLETE_TIME - T5.OVERTIME / (24 * 60)) - SYSDATE < 0
        THEN 'Y'
        ELSE 'N'
    END            AS OVERDUE_FLAG,
    T2.LIGHT_COLOR AS LIGHT_COLOR
FROM
    ORDER_MAIN_OSP T1
JOIN
    SYS_ORDER_STATUS_SETTING T2
ON
    T1.ORDER_STATUS = T2.STATUS_ID
LEFT JOIN
    SYS_OPTIONS_REFERENCE T3
ON
    T1.OPERATE_TYPE = T3.OPTIONS_VALUE
LEFT JOIN
    SYS_OPTIONS_REFERENCE T4
ON
    T1.SOURCE_SYS_ID = T4.OPTIONS_VALUE
LEFT JOIN
    SYS_ORDER_TYPE_SETTING T5
ON
    T1.ORDER_TYPE_ID = T5.ORDER_TYPE_ID
JOIN
    (
        SELECT
            T2.ORDER_TYPE_ID
        FROM
            SYS_SKILL_MEMBER_REF T1
        JOIN
            SYS_SKILL_ORDER_TYPE_MAP T2
        ON
            T1.SKILL_ID = T2.SKILL_ID
        WHERE
            T1.EMPNO = '$P{USER_ID}' ) T6
ON
    T1.ORDER_TYPE_ID = T6.ORDER_TYPE_ID
WHERE
    $P{CONDITION}
AND T1.ORDER_STATUS = '000'
ORDER BY
    T1.EXPECT_COMPLETE_TIME
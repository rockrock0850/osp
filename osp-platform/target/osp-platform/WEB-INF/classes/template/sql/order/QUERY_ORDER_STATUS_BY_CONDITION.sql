SELECT
    T2.STATUS_NAME                                          AS ORDER_STATUS,
    TO_CHAR(T1.SOURCE_CREATE_TIME ,'yyyy-MM-dd hh24:mi:ss') AS SOURCE_CREATE_TIME,
    T1.ORDER_M_ID                                           AS ORDER_M_ID,
    T1.ORDER_TYPE_NAME                                      AS ORDER_TYPE_NAME,
    T1.SOURCE_ORDER_ID                                      AS SOURCE_ORDER_ID,
    T1.COUNTS                                               AS COUNTS,
    T1.PARTENT_ORDER_ID                                     AS PARTENT_ORDER_ID,
    T3.OPTIONS_TEXT                                         AS SOURCE_SYS_ID,
    T1.SOURCE_PROD_TYPE_NAME                                AS SOURCE_PROD_TYPE_NAME,
    T1.MSISDN                                               AS MSISDN,
    T1.CUST_NAME                                            AS CUST_NAME,
    T1.CUST_ID                                              AS CUST_ID,
    T4.OPTIONS_TEXT                                         AS OPERATE_TYPE,
    T1.IVR_CODE                                             AS IVR_CODE,
    T1.SALES_ID                                             AS SALES_ID,
    T1.PROCESS_USER_ID                                      AS PROCESS_USER_ID,
    T5.REASON_TEXT                                          AS PROCESS_REASON,
    T1.COMMMENT                                             AS COMMMENT,
    T1.EXPECT_COMPLETE_TIME                                 AS CUST_SPECIFY_DATE,
    T1.PROMOTION_ID                                         AS PROMOTION_ID,
    T6.ITEM_NAME                                            AS ITEM_NAME,
    (
        CASE
            WHEN T1.ORDER_STATUS = '070'
            THEN T1.UPDATE_DATE
            WHEN T1.ORDER_STATUS = '080'
            THEN T1.UPDATE_DATE
            ELSE NULL
        END ) AS UPDATE_DATE,
	T2.LIGHT_COLOR 											AS LIGHT_COLOR
FROM
    ORDER_MAIN_OSP T1
LEFT JOIN
    SYS_ORDER_STATUS_SETTING T2
ON
    T1.ORDER_STATUS = T2.STATUS_ID
LEFT JOIN
    (
        SELECT
            OPTIONS_VALUE,
            OPTIONS_TEXT
        FROM
            SYS_OPTIONS_REFERENCE
        WHERE
            OPTIONS_TYPE IN ('SYS_SOURCE_ID',
                             'MAN_SOURCE_ID') )T3
ON
    T1.SOURCE_SYS_ID = T3.OPTIONS_VALUE
LEFT JOIN
    (
        SELECT
            OPTIONS_VALUE,
            OPTIONS_TEXT
        FROM
            SYS_OPTIONS_REFERENCE
        WHERE
            OPTIONS_TYPE = 'OPERATE_TYPE') T4
ON
    T1.OPERATE_TYPE= T4.OPTIONS_VALUE
LEFT JOIN
    SYS_REASON T5
ON
    T1.PROCESS_REASON = T5.REASON_ID
LEFT JOIN
    (
        SELECT
            ORDER_M_ID,
            listagg(ITEM_NAME,',')within GROUP (ORDER BY ORDER_M_ID) AS ITEM_NAME
        FROM
            BUZ_RECORD_CONTENT
        WHERE
            ITEM_VALUE ='Y'
        GROUP BY
            ORDER_M_ID) T6
ON
    T1.ORDER_M_ID = T6.ORDER_M_ID
WHERE
    $P{CONDITION}
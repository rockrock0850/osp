SELECT
    T1.ORDER_TYPE_ID      AS ORDER_TYPE_ID,
    T1.ORDER_TYPE_NAME    AS ORDER_TYPE_NAME,
    T1.SUCCESS_SEC        AS SUCCESS_SEC,
    T1.FAIL_SEC           AS FAIL_SEC ,
    T1.KPI_DAY_TYPE       AS KPI_DAY_TYPE,
    T1.CHK_CREATE_DATE    AS CHK_CREATE_DATE,
    T1.BEFORE_CREATE_DATE AS BEFORE_CREATE_DATE,
    T1.IS_REGULAR_TIME    AS IS_REGULAR_TIME,
    T1.REGULAR_TIME       AS REGULAR_TIME,
    T1.START_COUNT_TIME   AS START_COUNT_TIME,
    T1.OVERTIME           AS OVERTIME,
    T1.EMAIL              AS EMAIL,
    T1.CRITICAL_COUNTS    AS CRITICAL_COUNTS,
    T1.OVERTIME_COUNTS    AS OVERTIME_COUNTS,
    T2.OPTIONS_TEXT       AS KPI_DAY_TYPE_TEXT,
    T1.BTN_CREATE_SINGLE  AS BTN_CREATE_SINGLE,
    T1.BTN_CREATE_BATCH   AS BTN_CREATE_BATCH,
    T1.BTN_NOTICE         AS BTN_NOTICE
FROM
    SYS_ORDER_TYPE_SETTING T1
JOIN
    SYS_OPTIONS_REFERENCE T2
ON
    T1.KPI_DAY_TYPE = T2.OPTIONS_VALUE
WHERE
    $P{CONDITION}
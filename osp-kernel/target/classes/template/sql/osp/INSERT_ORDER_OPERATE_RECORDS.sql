--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/INSERT_ORDER_DISPATCH_NOTIFY.sql
-- 功能：新增分派案件至資料表「ORDER_DISPATCH_NOTIFY」
--
INSERT
INTO
    ORDER_OPERATE_RECORDS
    (
        ORDER_OP_ID,
        ORDER_M_ID,
        MSISDN,
        ACTION_TYPE,
        EXECUTE_TIME,
        CREATE_DATE,
        CREATE_USER,
        ACTION_CONTENT
    )
    VALUES
    (
        :orderOpId,
        :orderMId,
        :msisdn,
        'U01',
        SYSDATE,
        SYSDATE,
        'OSP-Kernel',
        :status
    )

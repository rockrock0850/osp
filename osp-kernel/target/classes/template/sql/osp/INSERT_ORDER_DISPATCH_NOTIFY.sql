--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/INSERT_ORDER_DISPATCH_NOTIFY.sql
-- 功能：新增分派案件至資料表「ORDER_DISPATCH_NOTIFY」
--
INSERT
INTO
    ORDER_DISPATCH_NOTIFY
    (
        BATCH_NO,
        ORDER_M_ID,
        PROCESS_USER_ID,
        CREATE_DATE
    )
    VALUES
    (
        :batchNo,
        :orderMId,
        :employeeNo,
        SYSDATE
    )
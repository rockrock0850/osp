--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/GET_REDISPATCH_CHILD_ORDER_LIST.sql
-- 功能：取得母單已派件，但尚未被派件的母子單派件資訊
--
SELECT
    T2.POOL_KEY,
    T2.ORDER_M_ID, -- 子單的「主要進件編號」
    T2.MSISDN, -- 門號
    T1.PROCESS_USER_ID, -- 母單的「處理人員編號」
    T1.PROCESS_USER_NAME -- 母單的「處理人員姓名」
FROM
    (
        SELECT
            SOURCE_ORDER_ID, -- 非子單的「原始單號」
            PROCESS_USER_ID, -- 非子單的「處理人員編號」
            PROCESS_USER_NAME -- 非子單的「處理人員姓名」
        FROM
            ORDER_MAIN_OSP -- OSP 案件主要維護資料表
        WHERE
            ORDER_STATUS = '010' -- 案件狀態：已派件，未處理
        AND PROCESS_USER_ID IS NOT NULL
        AND PARTENT_ORDER_ID IS NULL 
    ) T1 -- 已派件的非子單
JOIN
    (
        SELECT
            POOL_KEY,
            ORDER_M_ID, -- 子單的「主要進件編號」
            PARTENT_ORDER_ID, -- 母單的「原始單號」
            MSISDN -- 門號
        FROM
            ORDER_MAIN_OSP -- OSP 案件主要維護資料表
        WHERE
            ORDER_STATUS = '000' -- 案件狀態：未派件
        AND PARTENT_ORDER_ID IS NOT NULL 
    ) T2 -- 未派件的子單
ON
    T2.PARTENT_ORDER_ID = T1.SOURCE_ORDER_ID

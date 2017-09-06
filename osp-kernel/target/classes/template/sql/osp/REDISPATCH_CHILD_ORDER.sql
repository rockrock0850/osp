--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/REDISPATCH_CHILD_ORDER.sql
-- 功能：將母單已派件，但尚未被派件的子單，補派件給母單的「處理人員」
--
UPDATE
    ORDER_MAIN_OSP -- OSP 案件主要維護資料表
SET
    PROCESS_USER_ID = :processUserId, -- 處理人員編號：母單的「處理人員編號」
    PROCESS_USER_NAME = :processUserName, -- 處理人員姓名：母單的「處理人員姓名」
    ORDER_STATUS = :status, -- 案件狀態
    UPDATE_DATE = SYSDATE, -- 更新時間：系統時間
    UPDATE_USER = 'OSP_BATCH' -- 更新人員：固定參數
WHERE
    ORDER_M_ID = :orderMId

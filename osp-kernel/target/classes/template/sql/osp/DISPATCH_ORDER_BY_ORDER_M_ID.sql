--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/DISPATCH_ORDER_BY_ORDER_M_ID.sql
-- 功能：更新資料表「ORDER_MAIN_OSP」, 分派案件給處理人員
--
UPDATE
    ORDER_MAIN_OSP -- OSP 案件主要維護資料表
SET
    PROCESS_USER_ID = :employeeNo, -- 處理人員編號：傳入參數
    PROCESS_USER_NAME = :employeeName, -- 處理人員姓名：傳入參數
    ORDER_STATUS = :status, -- 案件狀態
    UPDATE_DATE = SYSDATE, -- 更新時間：系統時間
    UPDATE_USER = 'OSP-Batch' -- 更新人員：固定參數
WHERE
    ORDER_M_ID = :orderMId -- 主要進件單號：目前應派件「主要進件單號」集合

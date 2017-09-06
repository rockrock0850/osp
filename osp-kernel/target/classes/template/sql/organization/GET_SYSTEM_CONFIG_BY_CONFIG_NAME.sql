-- 
-- SQL File : resources/template/sql/organization/GET_SYSTEM_CONFIG_BY_CONFIG_NAME.sql
-- 說明：根據系統參數名稱，取得系統參數設定值
-- 
SELECT
    CONFIG_NAME,
    CONFIG_VALUE
FROM
    SYSTEM_CONFIG
WHERE
    (1 = 1)

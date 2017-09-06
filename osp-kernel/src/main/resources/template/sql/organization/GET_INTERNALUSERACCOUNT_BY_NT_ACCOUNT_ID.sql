-- 
-- SQL File : resources/template/sql/organization/GET_INTERNALUSERACCOUNT_BY_NT_ACCOUNT_ID.sql
-- 說明：根據使用者 NT Acount ID，取得內部員工 Account 相關資訊
-- 
SELECT DISTINCT
    USERID,
    NAME,
    ENGLISHNAME,
    PASSWORD,
    EMAIL,
    STATUS,
    IS_PASSWORD_LOCKED,
    "POSITION",
    AGENTCODE
FROM
    (
        SELECT
            USERID,
            NAME,
            ENGLISHNAME,
            PASSWORD,
            EMAIL,
            STATUS,
            IS_PASSWORD_LOCKED,
            "POSITION",
            AGENTCODE
        FROM
            INTERNALUSERACCOUNT
        WHERE
            EMAIL = :ntAccountId || '@fareastone.com.tw'
        AND IS_SYSTEM_ACCOUNT = 'N'
        AND (
                DATEEXPIRE > SYSDATE
            OR  DATEEXPIRE IS NULL
            ) 
    )

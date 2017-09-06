-- 
-- SQL File : resources/template/sql/organization/GET_STAFF_BY_NT_ACCOUNT_ID.sql
-- 說明：根據使用者 NT Acount ID，取得 STAFF 相關資訊
-- 
SELECT DISTINCT
    NTDOMAIN_ACCOUNT_ID,
    AMDOCS_ID,
    STAFF_NAME,
    EMAIL_ADDRESS,
    GROUP_NUMBER,
    LOGNAME,
    PASSWORD,
    AREA_ID,
    STAFF_ID
FROM
    (
        SELECT
            NTDOMAIN_ACCOUNT_ID,
            AMDOCS_ID,
            STAFF_NAME,
            EMAIL_ADDRESS,
            GROUP_NUMBER,
            LOGNAME,
            PASSWORD,
            AREA_ID,
            STAFF_ID
        FROM
            STAFF
        WHERE
            NTDOMAIN_ACCOUNT_ID = :ntAccountId
        AND STATUS = 1 
    )

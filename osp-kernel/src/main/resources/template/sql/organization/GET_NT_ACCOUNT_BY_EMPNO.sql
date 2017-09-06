-- 
-- SQL File : resources/template/sql/organization/GET_NT_ACCOUNT_BY_EMPNO.sql
-- 說明：根據使用者員編，取得 NT Account
-- 
SELECT
    ALIASNAME
FROM
    REF_STAFF
WHERE
    EMPNO = :empNo

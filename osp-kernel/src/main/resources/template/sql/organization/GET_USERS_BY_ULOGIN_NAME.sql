-- 
-- SQL File : resources/template/sql/organization/GET_USERS_BY_ULOGIN_NAME.sql
-- 說明：根據使用者 Login Name，取得使用者相關資訊
-- 
SELECT DISTINCT
    USER_ID,
    U_LOGIN_NAME AS USER_LOGIN_NAME,
    U_PASSWORD AS USER_PASSWORD,
    U_EMPL_ID AS USER_EMPL_ID
FROM
    (
        SELECT
            USER_ID,
            U_LOGIN_NAME,
            U_PASSWORD,
            U_EMPL_ID
        FROM
            USERS
        WHERE
            U_LOGIN_NAME = :amdocsId 
    )

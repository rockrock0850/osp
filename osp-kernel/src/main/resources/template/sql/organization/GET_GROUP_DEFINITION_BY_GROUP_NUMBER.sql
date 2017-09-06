-- 
-- SQL File : resources/template/sql/organization/GET_GROUP_DEFINITION_BY_GROUP_NUMBER.sql
-- 說明：根據 GROUP NUMBER，取得 GROUP 相關資訊
-- 
SELECT DISTINCT
    GROUP_NUMBER,
    GROUP_NAME,
    MANAGER_ID,
    PRIVILEGE_LEVEL,
    DEFAULT_FLAG,
    STATUS,
    GROUP_DESC
FROM
    (
        SELECT
            GROUP_NUMBER,
            GROUP_NAME,
            MANAGER_ID,
            PRIVILEGE_LEVEL,
            DEFAULT_FLAG,
            STATUS,
            GROUP_DESC
        FROM
            GROUP_DEFINITION
        WHERE
            GROUP_NUMBER = :groupNumber 
    )

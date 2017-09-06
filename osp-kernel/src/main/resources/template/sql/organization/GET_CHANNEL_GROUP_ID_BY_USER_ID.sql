-- 
-- SQL File : resources/template/sql/organization/GET_CHANNEL_GROUP_ID_BY_USER_ID.sql
-- 說明：根據使用者員工編號 empId，取得通路群組 ID
-- 
SELECT
    CODECHAR AS CHANNEL_GROUP_ID
FROM
    (
        SELECT
            CODECHAR
        FROM
            VIEW_PROMOTION_GROUP
        WHERE
            USERID = :userEmplId
        AND CODECHAR IS NOT NULL
        UNION ALL
        SELECT
            CODECHAR
        FROM
            VIEW_SPECIAL_GROUP
        WHERE
            USERID = :userEmplId
        AND CODECHAR IS NOT NULL 
    )

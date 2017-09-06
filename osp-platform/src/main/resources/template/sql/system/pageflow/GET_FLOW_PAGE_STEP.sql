-- ==========================================
--   依據 STEP_ID 取得該步驟應有的內容
-- ==========================================
SELECT
    T1.FLOW_ID           AS FLOW_ID,
    T1.SORT_SEQUENCE     AS SORT_SEQUENCE,
    T1.STEP_ID           AS STEP_ID,
    T1.CONTENT_ID        AS CONTENT_ID,
    T1.CONTENT_NAME      AS CONTENT_NAME,
    T2.TEMPLATE_TYPE     AS TEMPLATE_TYPE,
    T2.LINK              AS LINK,
    T2.OPEN_BROWSER      AS OPEN_BROWSER,
    T2.PARAMETER         AS PARAMETER,
    T2.HTTP_METHOD		 AS HTTP_METHOD,
    T2.ENCRYPTION_METHOD AS ENCRYPTION_METHOD,
    T2.ENCRYPTION_KEY    AS ENCRYPTION_KEY,
    T2.SURROUNDING       AS SURROUNDING
FROM
    BUZ_STEP_PAGE T1
INNER JOIN
    BUZ_CONTENT T2
ON
    T1.CONTENT_ID = T2.CONTENT_ID
WHERE
    T1.FLOW_ID = :FLOW_ID
AND T1.STEP_ID = :STEP_ID
ORDER BY
    T1.SORT_SEQUENCE
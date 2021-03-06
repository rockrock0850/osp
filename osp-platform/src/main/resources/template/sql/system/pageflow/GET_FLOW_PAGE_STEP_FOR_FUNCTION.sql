SELECT
    T1.MENU_ID      		AS MENU_ID,
    T1.FLOW_ID      		AS FLOW_ID,
    T3.CONTENT_ID   		AS CONTENT_ID,
    T3.CONTENT_NAME 		AS CONTENT_NAME,
    T3.TEMPLATE_TYPE		AS TEMPLATE_TYPE,
    T3.LINK         		AS LINK,
    T3.OPEN_BROWSER      	AS OPEN_BROWSER,
    T3.PARAMETER    		AS PARAMETER,
    T3.HTTP_METHOD		 	AS HTTP_METHOD,
    T3.ENCRYPTION_METHOD 	AS ENCRYPTION_METHOD,
    T3.ENCRYPTION_KEY 		AS ENCRYPTION_KEY,
    T3.SURROUNDING 			AS SURROUNDING
FROM
    BUZ_FLOW T1
LEFT JOIN
    (
        SELECT
            STEP_ITEM.FLOW_ID      AS FLOW_ID,
            STEP_ITEM.STEP_ID      AS STEP_ID,
            PAGE_ITEM.CONTENT_ID   AS CONTENT_ID,
            PAGE_ITEM.CONTENT_NAME AS CONTENT_NAME
        FROM
            BUZ_FLOW_STEP STEP_ITEM
         LEFT JOIN
            BUZ_STEP_PAGE PAGE_ITEM
        ON
            STEP_ITEM.STEP_ID = PAGE_ITEM.STEP_ID ) T2
ON
    T1.FLOW_ID = T2.FLOW_ID
LEFT JOIN
    BUZ_CONTENT T3
ON
    T2.CONTENT_ID = T3.CONTENT_ID
WHERE
    T1.MENU_ID = :MENU_ID
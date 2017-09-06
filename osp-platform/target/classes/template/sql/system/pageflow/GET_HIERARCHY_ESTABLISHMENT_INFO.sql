SELECT
    T1.CONTENT_ID    AS CONTENT_ID,
    T1.ITEM_ID       AS ITEM_ID,
    T1.SORT_SEQUENCE AS SORT_SEQUENCE,
    T2.ITEM_TYPE     AS ITEM_TYPE,
    T2.ITEM_NAME     AS ITEM_NAME,
    T2.RESERV2    	 AS RESERV2,
    T2.RESERV3     	 AS RESERV3
FROM
    BUZ_CONTENT_ITEM_MAP T1
LEFT JOIN
    BUZ_ITEM T2
ON
    T1.ITEM_ID = T2.ITEM_ID

WHERE
    T1.CONTENT_ID = :CONTENT_ID
ORDER BY
    T1.SORT_SEQUENCE
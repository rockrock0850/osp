SELECT
    T1.TXID_STATUS,
    T1.ORDER_M_ID,
    T2.MSISDN
FROM
    SYS_TXID_MAP T1
LEFT JOIN
    ORDER_MAIN_OSP T2
ON
    T2.ORDER_M_ID = T1.ORDER_M_ID
WHERE
    T1.ORDER_M_ID =
    (
        SELECT
            ORDER_M_ID
        FROM
            SYS_TXID_MAP
        WHERE
            TXID = :txId
    )

UPDATE
    SYS_TXID_MAP
SET
    TXID_STATUS = 'Y',
    UPDATE_DATE = SYSDATE
WHERE
    TXID = :txId

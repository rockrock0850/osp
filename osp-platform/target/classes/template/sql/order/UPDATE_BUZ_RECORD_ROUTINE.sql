MERGE
INTO
    BUZ_RECORD_ROUTINE T1
USING
    (
        SELECT
            1
        FROM
            dual) T2
ON
    (
        T1.CREATE_DATE = :CREATE_DATE
    AND T1.CONTENT_ID = :CONTENT_ID
    AND T1.ITEM_ID = :ITEM_ID)
WHEN MATCHED
    THEN
UPDATE
SET
    T1.PROCESS_USER_ID = :PROCESS_USER_ID,
    T1.ITEM_VALUE = :ITEM_VALUE,
    T1.UPDATE_USER = :UPDATE_USER,
    T1.UPDATE_DATE = SYSDATE,
    T1.PROCESS_DATE = SYSDATE
WHEN NOT MATCHED
    THEN
INSERT
    (
        T1.PROCESS_USER_ID,
        T1.PROCESS_DATE,
        T1.CONTENT_ID,
        T1.ITEM_ID,
        T1.ITEM_NAME,
        T1.ITEM_VALUE,
        T1.SORT_SEQUENCE,
        T1.CREATE_DATE,
        T1.CREATE_USER,
        T1.UPDATE_DATE,
        T1.UPDATE_USER
    )
    VALUES
    (
        :PROCESS_USER_ID,
        SYSDATE,
        :CONTENT_ID,
        :ITEM_ID,
        :ITEM_NAME,
        :ITEM_VALUE,
        :SORT_SEQUENCE,
        SYSDATE,
        :CREATE_USER,
        SYSDATE,
        :UPDATE_USER
    )
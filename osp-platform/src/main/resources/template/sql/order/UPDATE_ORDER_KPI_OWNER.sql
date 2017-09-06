MERGE
INTO
    ORDER_KPI_OWNER T1
USING
    (
        SELECT
            1
        FROM
            dual) T2
ON
    (
        T1.ORDER_M_ID = :ORDER_M_ID)
WHEN NOT MATCHED
    THEN
INSERT
    (
        T1.ORDER_M_ID,
        T1.KPI_OWNER_ID,
        T1.CREATE_USER,
        T1.CREATE_DATE
    )
    VALUES
    (
        :ORDER_M_ID,
        :KPI_OWNER_ID,
        :CREATE_USER,
        SYSDATE
    )
UPDATE
    ORDER_MAIN_OSP
SET
    ORDER_STATUS = :ORDER_STATUS,
    UPDATE_USER  = :UPDATE_USER,
    UPDATE_DATE	 = :UPDATE_DATE,
    PROCESS_USER_ID = :PROCESS_USER_ID,
    PROCESS_USER_NAME = :PROCESS_USER_NAME
WHERE
    ORDER_M_ID = :ORDER_M_ID
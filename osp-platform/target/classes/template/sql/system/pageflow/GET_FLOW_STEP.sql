-- ==========================================
--   依據 FLOW_ID 取得該FLOW應有的頁籤
-- ==========================================
SELECT
    STEP_ID,
    STEP_NAME,
    SORT_SEQUENCE,
    FLOW_NAME,
    BTN_TEMP_SAVE,
	BTN_TEMP_LEAVE,
	BTN_SUCCESS,
	BTN_FAIL,
	BTN_SHOW_MESSAGE,
	BTN_SYS_REPLY,
	BTN_SHOW_SOURCE_DOC,
	BTN_CHANGE_ORDER_TYPE
FROM
    BUZ_FLOW_STEP T1
INNER JOIN
    (
        SELECT
            FLOW_ID,
            FLOW_NAME,
            BTN_TEMP_SAVE,
			BTN_TEMP_LEAVE,
			BTN_SUCCESS,
			BTN_FAIL,
			BTN_SHOW_MESSAGE,
			BTN_SYS_REPLY,
			BTN_SHOW_SOURCE_DOC,
			BTN_CHANGE_ORDER_TYPE
        FROM
            BUZ_FLOW 
    ) T2
ON
   T1.FLOW_ID = T2.FLOW_ID
WHERE
    T1.FLOW_ID = :FLOW_ID
ORDER BY
    SORT_SEQUENCE    
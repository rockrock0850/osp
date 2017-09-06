--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/GET_UNDISPATCHED_ORDER_LIST.sql
-- 功能：根據員工編號，取得尚未分派案件清單
--
WITH REFERENCE_ORDER_ID_SET AS -- 派件參照單號集合(非子單)
    (
        SELECT
            REFERENCE_ORDER_ID, -- 派件參照單號(非子單)
            NEXT_REFERENCE_ORDER_ID -- 下一個順位的派件參照單號(非子單)
        FROM
            (
                SELECT
                    REFERENCE_ORDER_ID, -- 派件參照單號(非子單)
                    LEAD(REFERENCE_ORDER_ID) OVER(ORDER BY ACC_SUM_SEC) AS NEXT_REFERENCE_ORDER_ID, -- 下一個順位的派件參照單號(非子單)
                    ACC_SUM_SEC -- 各進件的派件秒數累計總合(包含母子單的秒數)
                FROM
                    (
                        SELECT
                            REFERENCE_ORDER_ID, -- 派件參照單號(非子單)
                            SUM(PROCESS_SEC) OVER(ORDER BY ROWNUM) AS ACC_SUM_SEC -- 各進件的派件秒數累計總合(包含母子單的秒數)
                        FROM
                            (
                                SELECT
                                    T1.CRITICAL_FLAG, -- 是否為急件 (Y or N)
                                    T1.EXPECT_COMPLETE_TIME - T2.OVERTIME / ( 24 * 60 ) AS DUE_TIME, -- 即將逾期時間
                                    T1.REFERENCE_ORDER_ID, -- 派件參照單號(非子單)
                                    CASE
                                        WHEN T2.FAIL_SEC <= T2.SUCCESS_SEC
                                        THEN T2.FAIL_SEC
                                        ELSE T2.SUCCESS_SEC
                                    END AS PROCESS_SEC -- 案件處理秒數：從有效件與無效件的處理時間秒數取較小者，作為派件秒數加總時的計算單位
                                FROM
                                    (
                                        SELECT
                                            SOURCE_ORDER_ID, -- 來源原始單號
                                            CASE
                                                WHEN PARTENT_ORDER_ID IS NULL
                                                THEN SOURCE_ORDER_ID -- 來源原始單號
                                                ELSE PARTENT_ORDER_ID -- 母子單的「母單來源原始單號」
                                            END AS REFERENCE_ORDER_ID, -- 派件參照單號(非子單)
                                            CRITICAL_FLAG, -- 是否為急件 (Y or N)
                                            EXPECT_COMPLETE_TIME, -- 預計完成時間
                                            ORDER_TYPE_ID -- 案件類型代碼
                                        FROM
                                            ORDER_MAIN_OSP -- OSP 案件主要維護資料表
                                        WHERE
                                            ORDER_STATUS = '000' -- 案件狀態：未派件
                                        AND TEAM_GROUP = '$P{TEAM_GROUP}' -- 人員所屬進件組別：傳入參數
                                        AND PROCESS_USER_ID IS NULL -- 處理人員：尚未分配處理人員
                                    ) T1 -- OSP進/案件主要維護資料表
                                LEFT JOIN
                                    SYS_ORDER_TYPE_SETTING T2 -- 案件類型處理規則設定
                                ON
                                    T2.ORDER_TYPE_ID = T1.ORDER_TYPE_ID -- 關聯條件：案件類型代碼相同
                                WHERE
                                    T1.SOURCE_ORDER_ID = T1.REFERENCE_ORDER_ID
                                AND T1.ORDER_TYPE_ID IN ($P{ORDER_TYPE_ID}) -- 案件類型代碼集合：傳入參數
                                ORDER BY 
                                    CRITICAL_FLAG DESC, -- 是否為急件 (Y or N)：急件優先
                                    DUE_TIME, -- 即將逾期時間：時間較近者優先
                                    REFERENCE_ORDER_ID -- 派件參照單號(非子單)
                            )
                    )
            )
        WHERE
            ACC_SUM_SEC < 
            (
                SELECT
                    DISP_CONFIG_VALUE * 60 AS DISP_SEC -- 秒數
                FROM
                    DISPATCH_ENTITY_CONFIG -- 派件規則 - 組織設定資料表
                WHERE
                    TEAM_GROUP = '$P{TEAM_GROUP}' -- 人員所屬進件組別：傳入參數
                AND DISP_CONFIG_KEY = 'Quantity' -- 派件規則代碼：每次派件量，依據各案件類型的無效件秒數累加至此設定分鐘數
            ) -- 派件累積秒數：人員所屬進件組別的「每次派件量」
    )
-- 非子單的「主要進件單號」
SELECT
    SET2.POOL_KEY,
    SET2.ORDER_M_ID, -- 主要進件單號
    SET2.MSISDN -- 門號
FROM
    (
        SELECT 
            REFERENCE_ORDER_ID -- 派件參照單號(非子單)
        FROM 
            REFERENCE_ORDER_ID_SET -- 派件參照單號集合(非子單)
        UNION
        SELECT 
            NEXT_REFERENCE_ORDER_ID AS REFERENCE_ORDER_ID -- 下一個順位的派件參照單號(非子單)
        FROM 
            REFERENCE_ORDER_ID_SET -- 派件參照單號集合(非子單)
        WHERE
            NEXT_REFERENCE_ORDER_ID IS NOT NULL
    ) SET1
JOIN
    (
        SELECT
            POOL_KEY,
            ORDER_M_ID, -- 主要進件單號
            SOURCE_ORDER_ID, -- 母子單的「母單單號」
            MSISDN -- 門號
        FROM
            ORDER_MAIN_OSP -- OSP 案件主要維護資料表
        WHERE
            ORDER_STATUS = '000' -- 案件狀態：未派件
        AND TEAM_GROUP = '$P{TEAM_GROUP}' -- 人員所屬進件組別：傳入參數
        AND PROCESS_USER_ID IS NULL -- 處理人員：尚未分配處理人員
        AND PARTENT_ORDER_ID IS NULL -- 非子單
    ) SET2
ON
    SET1.REFERENCE_ORDER_ID = SET2.SOURCE_ORDER_ID

UNION

-- 子單的「主要進件單號」
SELECT
    SET3.POOL_KEY,
    SET3.ORDER_M_ID, -- 主要進件單號
    SET3.MSISDN -- 門號
FROM
    (
        SELECT
            POOL_KEY,
            ORDER_M_ID, -- 主要進件單號
            PARTENT_ORDER_ID, -- 母子單的「母單單號」
            MSISDN -- 門號
        FROM
            ORDER_MAIN_OSP -- OSP 案件主要維護資料表
        WHERE
            ORDER_STATUS = '000' -- 案件狀態：未派件
        AND TEAM_GROUP = '$P{TEAM_GROUP}' -- 人員所屬進件組別：傳入參數
        AND PROCESS_USER_ID IS NULL -- 處理人員：尚未分配處理人員
        AND PARTENT_ORDER_ID IS NOT NULL -- 子單
    ) SET3
JOIN
    (
        SELECT 
            REFERENCE_ORDER_ID -- 派件參照單號(非子單)
        FROM 
            REFERENCE_ORDER_ID_SET -- 派件參照單號集合(非子單)
        UNION
        SELECT 
            NEXT_REFERENCE_ORDER_ID AS REFERENCE_ORDER_ID -- 下一個順位的派件參照單號(非子單)
        FROM 
            REFERENCE_ORDER_ID_SET -- 派件參照單號集合(非子單)
        WHERE
            NEXT_REFERENCE_ORDER_ID IS NOT NULL
    ) SET4
ON
    SET3.PARTENT_ORDER_ID = SET4.REFERENCE_ORDER_ID

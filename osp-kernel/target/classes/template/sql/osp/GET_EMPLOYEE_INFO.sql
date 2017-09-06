--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/GET_EMPLOYEE_INFO.sql
-- 說明：
--      1. 檢查人員目前是否可被派件
--      2. 取得「人員姓名」
--      3. 查詢人員在目前班別中具有的相關 SKILL，並對應至相關的「案件類型」集合
-- 
SELECT
    EMPNAME,
    REGEXP_REPLACE(ORDER_TYPE_ID_SET, '([^,]+'')(,\1)+', '\1') AS ORDER_TYPE_ID_SET
FROM
    (
        SELECT
            SET1.EMPNAME,
            LISTAGG('''' || S5.ORDER_TYPE_ID || '''', ',') WITHIN GROUP (ORDER BY S5.ORDER_TYPE_ID) AS ORDER_TYPE_ID_SET
        FROM
            (
                SELECT
                    S1.EMPNO,
                    S1.EMPNAME,
                    S3.SKILL_ID
                FROM
                    (
                        SELECT
                            EMPNO,
                            EMPNAME,
                            SHIFT_TYPE_ID
                        FROM
                            SYS_SHIFT_CONTENT
                        WHERE
                            NOT EXISTS -- 排除暫停派件者
                                (
                                    SELECT
                                        1
                                    FROM
                                        DISPATCH_MEMBER_CONFIG
                                    WHERE
                                        ACTIVE = 'Y'
                                    AND EMPNO = :employeeNo -- 處理人員編號：傳入參數
                                    AND PAUSE_START_TIME <= SYSDATE
                                    AND PAUSE_END_TIME >= SYSDATE
                                )
                        AND EMPNO = :employeeNo -- 處理人員編號：傳入參數
                        AND WORK_DATE = TO_DATE(TO_CHAR(SYSDATE, 'YYYYMMDD'), 'YYYYMMDD') -- 當日有排班
                    ) S1 -- 班表
                JOIN
                    SYS_SHIFTTYPE_SETTING S2 -- 班別
                ON
                    S2.SHIFT_TYPE_ID = S1.SHIFT_TYPE_ID -- 班別代碼
                JOIN
                    SYS_SHIFTTYPE_SKILL_MAP S3 -- 班別與 SKILL 對照表
                ON
                    S3.SHIFT_TYPE_ID = S1.SHIFT_TYPE_ID 
                JOIN
                    (
                        SELECT
                            DISP_CONFIG_VALUE
                        FROM
                            DISPATCH_ENTITY_CONFIG
                        WHERE
                            DISP_CONFIG_KEY = 'LastAssign'
                        AND TEAM_GROUP = :teamGroup
                    )
                     S4
                ON
                    1 = 1
                WHERE
                    (
                        S2.START_TIME <= TO_CHAR(SYSDATE, 'HH24:MI') 
                    AND S2.BREAK_START_TIME > TO_CHAR(SYSDATE, 'HH24:MI')
                    )
                OR 
                    (
                        S2.BREAK_END_TIME <= TO_CHAR(SYSDATE, 'HH24:MI') 
                    AND SYSDATE < TO_DATE(TO_CHAR(SYSDATE, 'YYYYMMDD') || ' ' || S2.END_TIME, 'YYYYMMDD HH24:MI') - TO_NUMBER(S4.DISP_CONFIG_VALUE) / 60 / 24 
                    ) -- 系統時間介於上班時間範圍內
            ) SET1
        JOIN
            SYS_SKILL_MEMBER_REF S4 -- 人員 SKILL 參照
        ON
            S4.EMPNO = SET1.EMPNO
        AND S4.SKILL_ID = SET1.SKILL_ID
        JOIN
            SYS_SKILL_ORDER_TYPE_MAP S5 -- SKILL 與案件類型對照表
        ON
            S5.SKILL_ID = SET1.SKILL_ID
        GROUP BY
            SET1.EMPNAME
    )

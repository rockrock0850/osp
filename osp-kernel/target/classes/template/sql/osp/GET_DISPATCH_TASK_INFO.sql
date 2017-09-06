--
-- SQL：/osp-kernel/src/main/resources/template/sql/osp/GET_DISPATCH_TASK_INFO.sql
-- 功能：取得當日需進行自動派件的人員, 班表與派件間隔分鐘數等資訊
--
SELECT
    S1.EMPNO, -- 員工編號
    S1.START_TIME, -- 班表開始時間
    S1.END_TIME, -- 班表結束時間
    S1.TEAM_GROUP, -- 群組別
    S2.DISP_CONFIG_VALUE AS CYCLE_MINUTES, -- 自動派件間隔分鐘數
    S3.DISP_CONFIG_VALUE AS LAST_DISPATCH_MINUS_MINUTES -- 最後派件時間：以班別下班時間扣除此設定，即為最後派件時間
FROM
    (
        SELECT
            T1.EMPNO,
            T2.START_TIME,
            T2.END_TIME,
            NVL(T3.TEAM_GROUP, 'M') AS TEAM_GROUP
        FROM
            (
                SELECT
                    SHIFT_TYPE_ID,
                    EMPNO
                FROM
                    SYS_SHIFT_CONTENT
                WHERE
                    TO_CHAR(WORK_DATE, 'YYYYMMDD') = TO_CHAR(SYSDATE, 'YYYYMMDD') 
            ) T1
        JOIN
            SYS_SHIFTTYPE_SETTING T2
        ON
            T2.SHIFT_TYPE_ID = T1.SHIFT_TYPE_ID
        LEFT JOIN
            HRM_MEMBER T3
        ON
            T1.EMPNO = T3.EMPNO 
    ) S1
JOIN
    DISPATCH_ENTITY_CONFIG S2
ON
    S2.TEAM_GROUP = S1.TEAM_GROUP
JOIN
    DISPATCH_ENTITY_CONFIG S3
ON
    S3.TEAM_GROUP = S1.TEAM_GROUP
WHERE
    S2.DISP_CONFIG_KEY = 'Cycle'
AND S3.DISP_CONFIG_KEY = 'LastAssign'
    

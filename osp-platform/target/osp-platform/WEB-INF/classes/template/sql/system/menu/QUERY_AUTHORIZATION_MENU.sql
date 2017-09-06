SELECT
    SRC.MENU_ID         AS MENU_ID,
    SRC.MENU_TEXT       AS MENU_TEXT,
    SRC.MENU_LINK       AS MENU_LINK,
    SRC.MENU_TYPE       AS MENU_TYPE,
    SRC. MENU_OPEN_TYPE AS MENU_OPEN_TYPE,
    SRC.MENU_LEV        AS MENU_LEV
FROM
    (
        SELECT DISTINCT
            T2.MENU_ID        AS MENU_ID,
            T2.MENU_TEXT      AS MENU_TEXT,
            T2.MENU_LINK      AS MENU_LINK,
            T2.MENU_TYPE      AS MENU_TYPE,
            T2.MENU_OPEN_TYPE AS MENU_OPEN_TYPE,
            T2.MENU_LEV       AS MENU_LEV,
            T2.ACTIVE         AS ACTIVE
        FROM
            SYS_ROLE_MENU_REF T1
        JOIN
            SYS_MENU T2
        ON
            T1.MENU_ID = T2.MENU_ID
        WHERE
            T1.ROLE_ID IN
            (
                SELECT
                    ROLE_ID
                FROM
                    SYS_ROLE_MANUAL_MAPPING
                WHERE
                    EMPNO = :USER_ID )
        UNION
        SELECT DISTINCT
            MENU_ID        AS MENU_ID,
            MENU_TEXT      AS MENU_TEXT,
            MENU_LINK      AS MENU_LINK,
            MENU_TYPE      AS MENU_TYPE,
            MENU_OPEN_TYPE AS MENU_OPEN_TYPE,
            MENU_LEV       AS MENU_LEV,
            ACTIVE         AS ACTIVE
        FROM
            SYS_MENU
        WHERE
            SKILL_ID IN
            (
                SELECT
                    T1.SKILL_ID
                FROM
                    SYS_SKILL_MEMBER_REF T1
                JOIN
                    SYS_SKILL T2
                ON
                    T1.SKILL_ID = T2.SKILL_ID
                WHERE
                    T1.EMPNO = :USER_ID ) ) SRC
WHERE
    SRC.ACTIVE = 'Y'
ORDER BY
    SRC.MENU_ID
SELECT
        T1.MENU_ID AS MENU_ID,
        T1.MENU_TEXT AS MENU_TEXT
FROM
        SYS_MENU T1
WHERE
        T1.MENU_LEV IN('2')
AND
        T1.MENU_ID  IN (
                SELECT 
                        MENU_ID
                FROM
                         SYS_ROLE_MENU_REF
                WHERE
                        ROLE_ID = :ROLE_ID
                        )
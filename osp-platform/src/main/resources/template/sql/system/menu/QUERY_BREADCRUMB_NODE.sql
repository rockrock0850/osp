SELECT
	MENU_ID         AS MENU_ID
FROM
    SYS_MENU
WHERE
    MENU_LEV IS NOT NULL
AND MENU_LEV NOT IN ('1')
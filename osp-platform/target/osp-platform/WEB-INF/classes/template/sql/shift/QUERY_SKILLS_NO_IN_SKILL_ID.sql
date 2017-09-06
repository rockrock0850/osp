SELECT
	T1.SKILL_ID    AS SKILL_ID,
	T1.SKILL_NAME  AS SKILL_NAME
FROM
    SYS_SKILL T1
WHERE
    T1.SKILL_ID NOT IN (
            SELECT
                    SKILL_ID
            FROM 
                    SYS_SHIFTTYPE_SKILL_MAP
            WHERE
                    SHIFT_TYPE_ID = :SHIFT_TYPE_ID
                    )
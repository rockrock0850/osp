SELECT
        CONF_VALUE AS CONF_VALUE
FROM    
        SYS_GLOBAL_CONFIG
WHERE
        CONF_ID = :CONF_ID
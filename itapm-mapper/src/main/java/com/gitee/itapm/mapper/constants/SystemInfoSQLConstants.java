package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public final class SystemInfoSQLConstants {


    public static final String QUERY_TABLE_PARAMS =
            "id as id," +
                    "ch_name as chName, " +
                    "en_name as enName, " +
                    "description as description, " +
                    "status as status, " +
                    "create_time as createTime," +
                    "update_time as updateTime";


    public static final String INSERT_PARAMS =
            "id,ch_name,en_name, description," +
                    "status,create_time,update_time";


    public static final String INSERT_SQL =
            "insert into t_itapm_system_info(" + INSERT_PARAMS + ") " +
                    "values(#{id},#{chName},#{enName},#{description}" +
                    "#{status},now(),now())";


    public static final String QUERY_BY_ID =
            "select " + QUERY_TABLE_PARAMS + " t_itapm_system_info where id = " +
                    "(#{id}";
}
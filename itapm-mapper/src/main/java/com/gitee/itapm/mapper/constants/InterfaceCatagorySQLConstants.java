package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public class InterfaceCatagorySQLConstants {

    public static final String QUERY_TABLE_PARAMS=
            "id as id," +
                    "system_id as systemId, " +
                    "name as name, " +
                    "status as status, " +
                    "create_time as createTime," +
                    "update_time as updateTime";


    public static final String INSERT_PARAM=
                    "id ,system_id," +
                    "name,status," +
                    "create_time,update_time ";


    public static final String INSERT_SQL=
            "insert into t_itapm_interface_detail("+
                    INSERT_PARAM+") values("+
                    "#{id},#{systemId}," +
                    "#{name},#{status}," +
                    "now(),now())";
}

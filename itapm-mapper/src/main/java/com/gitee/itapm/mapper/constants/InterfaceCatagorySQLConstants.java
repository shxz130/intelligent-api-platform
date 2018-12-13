package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public class InterfaceCatagorySQLConstants {

    private static final String TABLE_NAME="t_itapm_interface_catagory";

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
            "insert into "+TABLE_NAME+"("+
                    INSERT_PARAM+") values("+
                    "#{id},#{systemId}," +
                    "#{name},#{status}," +
                    "now(),now())";


    public static final String QUERY_BY_SYSTEM_ID=
            "select "+QUERY_TABLE_PARAMS+" from "+TABLE_NAME+
                    "where system_id=#{systemId}";
}

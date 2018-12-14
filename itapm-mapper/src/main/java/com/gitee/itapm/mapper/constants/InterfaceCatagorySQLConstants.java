package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public class InterfaceCatagorySQLConstants {

    private static final String TABLE_NAME=" t_itapm_interface_catagory ";

    public static final String QUERY_TABLE_PARAMS=
            " id as id," +
                    "system_version_id as systemVersionId, " +
                    "name as name, " +
                    "status as status, " +
                    "create_time as createTime," +
                    "update_time as updateTime";


    public static final String INSERT_PARAM=
                    "system_version_id," +
                    "name," +
                    "status," +
                    "create_time," +
                    "update_time ";


    public static final String BASE_SELECT= "select "+QUERY_TABLE_PARAMS+" from "+TABLE_NAME+" ";


    public static final String INSERT_SQL=
            "insert into "+TABLE_NAME+"("+INSERT_PARAM+") values("+
                    "#{systemVersionId}," +
                    "#{name}," +
                    "#{status}," +
                    "now()," +
                    "now())";


    public static final String QUERY_BY_SYSTEM_VERSION_ID=
                    BASE_SELECT
                    +"where " +
                    "system_version_id=#{systemVersionId}";

    public static final String QUERY_BY_SYSTEM_VERSION_ID_NAME=BASE_SELECT+
            "where "+
            "system_version_id=#{systemVersionId} and "+
            "name=#{name}";

    public static final String DELETE_BY_ID="delete from "+TABLE_NAME+"where id=#{id}";
}

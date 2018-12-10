package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public class InterfaceDetailSQLConstants {

    public static final String QUERY_TABLE_PARAMS=
                    "id as id," +
                    "system_id as systemId, " +
                    "system_name as systemName, " +
                    "catagory_id as catagoryId, " +
                    "name as name, " +
                    "address as address, " +
                    "users as users, " +
                    "status as status, " +
                    "description as description"+
                    "create_time as createTime," +
                    "update_time as updateTime";


    public static final String INSERT_PARAM=
                    "id ,system_id, system_name,catagory_id," +
                    "name,address,users,status,description," +
                    "create_time,update_time ";


    public static final String INSERT_SQL=
            "insert into t_itapm_interface_detail("+
                    INSERT_PARAM+") values("+
                    "#{id},#{systemId},#{systemName},#{catagoryId}," +
                    "#{name},#{address},#{users},#{status}" +
                    ",#{description},now(),now())";






}

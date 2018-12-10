package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public class InterfaceDetailSQLConstants {

    public static final String TABLE_NAME="t_itapm_interface_detail";



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


    public static final String QUERY_BASE_SQL= "select "+QUERY_TABLE_PARAMS+" from "+TABLE_NAME+" ";


    public static final String INSERT_PARAM=
                    "id ,system_id, system_name,catagory_id," +
                    "name,address,users,status,description," +
                    "create_time,update_time ";


    public static final String INSERT_SQL=
            "insert into "+TABLE_NAME+"("+
                    INSERT_PARAM+") values("+
                    "#{id},#{systemId},#{systemName},#{catagoryId}," +
                    "#{name},#{address},#{users},#{status}" +
                    ",#{description},now(),now())";


    public static final String QUERY_BY_ID=QUERY_BASE_SQL+
            "where id=#{id}";

    public static final String QUERY_BY_SYSTEM_ID=QUERY_BASE_SQL+
            "where system_id=#{systemId} order by create_time desc";

    public static final String QUERY_BY_SYSTEM_ID_CATAGORY_ID=QUERY_BASE_SQL+
            "where system_id=#{systemId} and catagory_id = #{catagoryId} order by update_time desc";

    public static final String QUERY_BY_SYSTEM_ID_CATAGORY_ID_CONDITION=QUERY_BASE_SQL+
            "where system_id=#{systemId} " +
              "and catagory_id = #{catagoryId} " +
              "and (name like concat(concat('%',#{condition}),'%')  or address like concat(concat('%',#{condition}),'%'))"+
            "order by update_time desc";
}

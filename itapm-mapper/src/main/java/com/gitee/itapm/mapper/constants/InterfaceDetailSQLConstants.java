package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public class InterfaceDetailSQLConstants {

    public static final String TABLE_NAME=" t_itapm_interface_detail ";



    public static final String QUERY_TABLE_PARAMS=
                    "id as id," +
                    "system_version_id as systemVersionId, " +
                    "system_name as systemName, " +
                    "catagory_id as catagoryId, " +
                    "name as name, " +
                    "address as address, " +
                    "caller as caller, " +
                    "type as type, "+
                    "request_type as requestType, "+
                    "status as status, " +
                    "description as description,"+
                    "memo as memo,"+
                    "create_time as createTime," +
                    "update_time as updateTime ";


    public static final String QUERY_BASE_SQL= "select "+QUERY_TABLE_PARAMS+" from "+TABLE_NAME+" ";


    public static final String INSERT_PARAM=
                    "system_version_id," +
                    "system_name,"+
                    "catagory_id," +
                    "name," +
                    "address," +
                    "caller," +
                    "type, "+
                    "request_type, "+
                    "status," +
                    "description," +
                    "memo,"+
                    "create_time," +
                    "update_time ";


    public static final String INSERT_SQL=
            "insert into "+TABLE_NAME+"("+
                    INSERT_PARAM+") values("+
                    "#{systemVersionId}," +
                    "#{systemName}," +
                    "#{catagoryId}," +
                    "#{name}," +
                    "#{address}," +
                    "#{caller}," +
                    "#{type}," +
                    "#{requestType}," +
                    "#{status}," +
                    "#{description}," +
                    "#{memo}," +
                    "now()," +
                    "now())";


    public static final String QUERY_BY_ID=QUERY_BASE_SQL+
            "where " +
            "id=#{id}";

    public static final String QUERY_BY_SYSTEM_VERSION_ID=QUERY_BASE_SQL+
            "where " +
            "system_version_id=#{systemVersionId} order by create_time desc";

    public static final String QUERY_BY_SYSTEM_VERSION_ID_CATAGORY_ID=QUERY_BASE_SQL+
            "where " +
            "system_version_id=#{systemVersionId} and " +
            "catagory_id = #{catagoryId} order by update_time desc";

    public static final String QUERY_BY_SYSTEM_VERSION_ID_CONDITION=QUERY_BASE_SQL+
            "where system_version_id=#{systemVersionId} " +
              "and (name like concat(concat('%',#{condition}),'%')  or address like concat(concat('%',#{condition}),'%') or description like concat(concat('%',#{condition}),'%')) "+
            "order by update_time desc";


    public static final String QUERY_BY_SYSTEM_VERSION_ID_NAME =QUERY_BASE_SQL+
                    "where " +
                    "system_version_id=#{systemVersionId} and " +
                    " name = #{name} order by id desc";

    public static final String UPDATE_BY_ID = "update " +TABLE_NAME+"set "+
            "system_version_id = #{systemVersionId}, " +
            "system_name = #{systemName}, " +
            "catagory_id = #{catagoryId}, " +
            "name = #{name}, " +
            "address = #{address}, " +
            "caller = #{caller}, " +
            "type = #{type}, "+
            "request_type = #{requestType}, "+
            "status = #{status}, " +
            "description = #{description},"+
            "memo = #{memo},"+
            "update_time = now() "+
            "where id =#{id}";

    public static final String QUERY_BY_CATAGORY_ID = QUERY_BASE_SQL+"where catagory_id=#{catagoryId}";

    public static final String DELETE_BY_ID = "delete from "+TABLE_NAME+"where id =#{id}";
}

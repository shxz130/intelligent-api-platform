package com.gitee.itapm.mapper.constants;

import java.util.Date;

/**
 * Created by jetty on 2018/12/14.
 */


public class ParamTypeSQLConstants {


    private Integer id;

    private Integer interfaceDetailId;

    private String paramTypeName;
    /*** 参数类型 REQ，RESP */
    private String resource;

    private Date createTime;

    private Date updateTime;

    private static final String BASE_SELECT_FIELD=" " +
            "id as id," +
            "interface_detail_id as interfaceDetailId, " +
            "param_type_name = paramTypeName," +
            "resource as resource," +
            "create_time as createTime," +
            "update_time as updateTime ";

    private static final String BASE_UPDATE_FIELD=" " +
            "interface_detail_id = #{interfaceDetailId}, " +
            "param_type_name = #{paramTypeName}," +
            "resource = #{resource}," +
            "update_time = now() ";


    private static final String BASE_INSERT_FIELD=" " +
            "interface_detail_id, " +
            "param_type_name," +
            "resource," +
            "create_time,"+
            "update_time ";

    private static final String TABLE_NAME=" t_itapm_param_type ";

    private static final String BASE_QUERY_SQL="select "+BASE_SELECT_FIELD+" from "+TABLE_NAME;



    public static final String QUERY_BY_INTERFACE_DETAIL_ID_NAME_AND_RESOURCE = BASE_QUERY_SQL+
            "where interface_detail_id=#{interfaceDetailId} and " +
            "param_type_name =#{paramTypeName} and " +
            "resource =#{resource}";

    public static final String PERSIST = "insert into "+TABLE_NAME+"("+BASE_INSERT_FIELD+") "+
            "values(" +
            "#{interfaceDetailId}," +
            "#{paramTypeName}," +
            "#{resource}," +
            "now()," +
            "now())";
}

package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/14.
 */
public class ParamTypeRefGenericSQLConstants {

    private static final String TABLE_NAME=" t_itapm_param_type_ref_generic ";

    public static final String QUERY_BY_PARAM_AND_GENERIC_TYPE_ID =
            "select id as id,param_type_id as paramTypeId, generic_type_id as genericTypeId from "+TABLE_NAME
            +"where param_type_id=#{paramTypeId} and genericTypeId=#{genericTypeId}";


    public static final String PERSIST = "insert into "+TABLE_NAME +"(generic_type_id,generic_type_id,create_time,update_time)"+
            "values(#{paramTypeId},#{genericTypeId},now(),now());";

    public static final String DELETE_BY_ID = "delete from "+TABLE_NAME+"where id=#{id}";
}

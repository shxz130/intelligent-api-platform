package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/14.
 */
public class GenericParamTypeRefGenericSQLConstants {

    private static final String TABLE_NAME=" t_itapm_generic_param_field_ref_generic ";

    public static final String QUERY_BY_PARAM_AND_GENERIC_TYPE_ID =
            "select id as id,generic_param_field_id as genericParamFieldId, generic_type_id as genericTypeId from "+TABLE_NAME
            +"where generic_param_field_id=#{genericParamFieldId} and generic_type_id=#{genericTypeId}";


    public static final String PERSIST = "insert into "+TABLE_NAME +"(generic_param_field_id,generic_type_id,create_time,update_time)"+
            "values(#{genericParamFieldId},#{genericTypeId},now(),now());";

    public static final String DELETE_BY_ID = "delete from "+TABLE_NAME+"where id=#{id}";

    public static final String QUERY_BY_FIELD_ID =  "select id as id,generic_param_field_id as genericParamfieldId, generic_type_id as genericTypeId from "+TABLE_NAME
            +"where generic_param_field_id=#{genericParamFieldId}";

}

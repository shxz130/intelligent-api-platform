package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/14.
 */
public class GenericParamFieldSQLConstants {


    private static final String TABLE_NAME=" t_itapm_generic_param_field ";

    private static final String BASE_SELECT_FIELD_SQL=
                    " id as id," +
                    "generic_param_type_id as genericParamTypeId," +
                    "param_name as paramName," +
                    "param_type as paramType, " +
                    "param_length as paramLength," +
                    "required as required," +
                    "default_value as defaultValue," +
                    "param_description as paramDescription," +
                    "example as example ," +
                    "create_time as createTime," +
                    "update_time as updateTime ";

    private static final String BASE_INSERT_SQL=
                    " generic_param_type_id," +
                    "param_name," +
                    "param_type, " +
                    "param_length," +
                    "required," +
                    "default_value," +
                    "param_description ," +
                    "example ," +
                    "create_time," +
                    "update_time ";

    private static final String BASE_UPDATE=
                    " generic_param_type_id = #{genericParamTypeId}," +
                    "param_name = #{paramName}," +
                    "param_type = #{paramType}, " +
                    "param_length = #{paramLength}," +
                    "required = #{required}," +
                    "default_value = #{defaultValue}," +
                    "param_description = #{paramDescription}," +
                    "example = #{example} ," +
                    "update_time = now() ";


    private static final String BASE_QUERY_SQL="select "+BASE_SELECT_FIELD_SQL+"from"+TABLE_NAME;



    public static final String QUERY_BY_PARAM_TYPE_PARAM_NAME = BASE_QUERY_SQL+
            " where " +
            "generic_param_type_id = #{genericParamTypeId} and "+
            "param_name = #{paramName} order by id desc ";




    public static final String QUERY_BY_PARAM_TYPE_ID = BASE_QUERY_SQL+
            " where " +
            "generic_param_type_id = #{genericParamTypeId} order by id desc";;


    public static final String DELETE_BY_ID = "delete from "+TABLE_NAME+"where id =#{id} ";


    public static final String PERSIST = "insert into "+TABLE_NAME+"("+
            BASE_INSERT_SQL+")" +
            "values(" +
                "#{genericParamTypeId}," +
                "#{paramName}," +
                "#{paramType}," +
                "#{paramLength}," +
                "#{required},"+
                "#{defaultValue}," +
                "#{paramDescription}," +
                "#{example}," +
                "now(),now())";


    public static final String UPDATE_BY_ID ="update "+TABLE_NAME+" set "+BASE_UPDATE+" where id=#{id}";
}

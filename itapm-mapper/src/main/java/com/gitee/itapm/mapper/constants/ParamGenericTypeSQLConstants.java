package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/14.
 */
public class ParamGenericTypeSQLConstants {



    public static final String QUERY_BY_SYSTEM_VERSION_ID_AND_NAME = "select id as id, system_version_id as systemVersionId," +
            " name as name from t_itapm_generic_param_type where system_version_id=#{systemVersionId} and name =#{name}" ;


    public static final String PERSIST = "insert into t_itapm_generic_param_type(system_version_id,name,create_time,update_time) values (#{systemVersionId},#{name},now(),now())";
}

package com.gitee.itapm.mapper.constants;

/**
 * Created by jetty on 2018/12/9.
 */
public class ParamInfoSQLConstants {

    public static final String TABLE_NAME="t_itapm_param_info";

    public static final String QUERY_TABLE_PARAMS=
                    "id as id," +
                    "type as type," +
                    "parent_id as parentId, " +
                    "interface_detail_id as interfaceDetailId, " +
                    "param_name as paramName, " +
                    "param_type as paramType, " +
                    "param_length as paramLength, " +
                    "required as required, " +
                    "default_value as defaultValue, " +
                    "param_description as paramDescription"+
                    "example as example"+
                    "create_time as createTime," +
                    "update_time as updateTime";


    public static final String INSERT_PARAM=
            "id ,type, parent_id,interface_detail_id," +
                    "param_name,param_type,param_length,required,default_value," +
                    "param_description,example,create_time,update_time ";

    public static final String QUERY_BY_INTERFACE_DETAIL_ID_AND_PARENT_ID_IS_NULL="";

    public static final String INSERT_SQL=
            "insert into "+TABLE_NAME+"("+
                    INSERT_PARAM+") values("+
                    "#{id},#{type},#{parentId},#{interfaceDetailId}," +
                    "#{paramName},#{paramType},#{paramLength},#{required},#{defaultValue}" +
                    ",#{paramDescription},#{example},now(),now())";



}

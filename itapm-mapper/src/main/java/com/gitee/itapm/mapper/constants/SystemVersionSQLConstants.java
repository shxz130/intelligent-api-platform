package com.gitee.itapm.mapper.constants;

import java.util.Date;

/**
 * Created by jetty on 2018/12/14.
 */
public class SystemVersionSQLConstants {


    private Integer id;

    private Integer systemId;

    private String systemName;

    private String systemVersion;

    private Date createTime;

    private Date updateTime;

    private static final String BASE_QUERY_FIELD=
                    "id as id," +
                    "system_id as systemId," +
                    "system_name as systemName,"+
                    "system_version as systemVersion,"+
                    "create_time as create_time," +
                    "update_time as updateTime ";

    private static final String BASE_INSERT_FIELD=
                    "system_id ," +
                    "system_name," +
                    "system_version,"+
                    "create_time ," +
                    "update_time ";

    private static final String BASE_UPDATE_FIELD=
                    " system_id =#{systemId},system_name =#{systemName}, system_version_id =#{systemVersionId},update_time =now()";

    private static final String TABLE_NAME=" t_itapm_system_version ";



    public static final String QUERY_BASE_SQL="select "+BASE_QUERY_FIELD+" from " + TABLE_NAME;


    public static final String QUERY_BY_STSTEM_INFO_ID_AND_VERSION = QUERY_BASE_SQL+"where "+
            "system_id =#{systemInfoId} and system_version=#{systemVersion}";

    public static final String PERSIST ="insert into "+TABLE_NAME+"("+ BASE_INSERT_FIELD+") values ("+
            "#{systemId}," +
            "#{systemName},"+
            "#{systemVersion}," +
            "now()," +
            "now())";

    public static final String QUERY_LAST_ONE_BY_STSTEM_INFO_ID = QUERY_BASE_SQL+"where "+
            "system_id =#{systemInfoId} order by create_time desc limit 1";

}

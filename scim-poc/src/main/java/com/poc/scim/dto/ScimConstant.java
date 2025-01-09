package com.poc.scim.dto;

import java.text.SimpleDateFormat;

public class ScimConstant {
    public static final String APPLICATION_JSON = "application/scim+json; charset=UTF-8";
    public static final String URN_LIST_RESPONSE = "urn:ietf:params:scim:api:messages:2.0:ListResponse";
    public static final String URN_USER = "urn:ietf:params:scim:schemas:core:2.0:User";
    public static final String URN_GROUP = "urn:ietf:params:scim:schemas:core:2.0:Group";
    public static final String URN_ERROR = "urn:ietf:params:scim:api:messages:2.0:Error";
    public static final String URN_PATCH_OP = "urn:ietf:params:scim:api:messages:2.0:PatchOp";
    public static final String NAME_USER = "User";
    public static final String NAME_GROUP = "Group";
    public static final String PATH_USERS = "/Users";
    public static final String PATH_GROUPS = "/Groups";
    public static final SimpleDateFormat SCIM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public static final SimpleDateFormat READ_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        SCIM_DATE_FORMAT.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        READ_DATE_FORMAT.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }
}


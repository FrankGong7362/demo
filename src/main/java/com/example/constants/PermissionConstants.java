package com.example.constants;

import java.util.HashMap;
import java.util.Map;

public class PermissionConstants {
    // 权限代码
    public static final String ADMIN_CODE = "0";
    public static final String USER_CODE = "1";
    public static final String UNKNOWN_CODE = "-1";

    // 权限描述
    public static final String ADMIN_DESC = "管理员";
    public static final String USER_DESC = "普通用户";
    public static final String UNKNOWN_DESC = "未知用户";

    // 权限映射
    private static final Map<String, String> PERMISSION_MAP = new HashMap<>();

    static {
        PERMISSION_MAP.put(ADMIN_CODE, ADMIN_DESC);
        PERMISSION_MAP.put(USER_CODE, USER_DESC);
    }

    public static String getPermissionDesc(String code) {
        return PERMISSION_MAP.getOrDefault(code, UNKNOWN_DESC);
    }
}

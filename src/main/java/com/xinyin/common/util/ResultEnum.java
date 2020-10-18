package com.xinyin.common.util;

/**
 * 响应枚举
 */
public enum ResultEnum {

    SERVER_ERROR("500","服务端异常"),
    SERVER_SUCCESS("200","服务端成功"),
    USO_AUTH_ERROR("501","USO认证失败！！！"),
    PERMISSION_TOKEN_EXPIRED("502","TOKEN过期！！！"),
    PERMISSION_TOKEN_INVALID("503","TOKEN非法！！！"),
    PERMISSION_SIGNATURE_ERROR("504","签名错误！！！"),
    USER_NOT_REGISTER_ERROR("505","用户未注册！！！"),
    PERMISSION_JWT_VERIFY_ERROR("401","JWT认证失败！！！");

    private final String code;
    private final String desc;

    ResultEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

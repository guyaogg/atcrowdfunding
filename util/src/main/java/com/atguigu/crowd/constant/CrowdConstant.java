package com.atguigu.crowd.constant;


/**
 * 尚筹网项目常量
 *
 * @author guyao
 * @create 2021-08-01 21:30
 */
public enum CrowdConstant {
    // 不变信息存储
    ADMIN_TOM("顶级管理员"),

    ATTR_NAME_EXCEPTION("exception"),
    ATTR_NAME_INTERCEPT("interceptPath"),
    XML_JSON_HEADER("XMLHttpRequest"),
    APPLICATION_JSON_HEADER("application/json"),
    ACCEPT("Accept"),
    X_REQUESTED_WITH("X-Requested-With"),

    XML_ADMIN("/admin/to/"),
    XML_HTML("/page.html"),

    ERROR_PAGE("error/"),
    ADMIN_PAGE("admin/"),
    SYSTEM_ERROR("system-error"),
    LOGIN_PAGE("login"),
    MAIN_PAGE("main"),
    PAGE_PAGE("page"),
    ADD_PAGE("add"),
    EDIT_PAGE("edit"),
    REDIRECT("redirect:"),
    ATTR_NAME_MESSAGE("message"),
    ATTR_NAME_PORTAL_DATA("portalData"),
    ROLE_,
    REDIS_CODE_PREFIX_,


    MESSAGE_LOGIN_FAIL("抱歉！账号密码错误！请重新输入！"),
    MESSAGE_LOGIN_ACCT_ALREADY_IN_USE("抱歉！这个账号已经使用了"),
    MESSAGE_ACCESS_FORBIDDEN("请登录后再访问！"),
    MESSAGE_STRING_INVALIDATE("字符串不合法！请不要传入空字符串"),
    MESSAGE_OBJECT_INSERT_DATE("不可以新增空值！"),
    MESSAGE_OBJECT_MODIFY_DATE("不可以修改成空值！"),
    MESSAGE_OBJECT_DELETE_DATE("未选中删除角色！"),
    MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE("传入数据有异常"),
    MESSAGE_QUERY_FAIL("查无此人！"),
    MESSAGE_REMOVE_FAIL("兄弟想开点，删到自己了！"),
    MESSAGE_ASSIGN_FAIL("抱歉！你该看看你在什么位置憨憨"),
    MESSAGE_CODE_NOT_EXISTS("验证码已过期！请查看手机号是否正确或重新发送验证码"),
    MESSAGE_CODE_INVALID("验证码不正确！"),
    MESSAGE_SEVER_FAULT("服务器故障，请重新点击"),
    MESSAGE_HEADER_UPLOAD_FAULT("头图上传失败"),
    MESSAGE_HEADER_IS_EMPTY("头图不可为空"),
    MESSAGE_DETAIL_PIC_EMPTY("详情图集不可为空"),
    MESSAGE_HEADER_IS_TOO_BIG("头图不要超过5M"),
    MESSAGE_PICTURE_IS_TOO_BIG("图片不要超过5M"),
    MESSAGE_DETAIL_IS_TOO_BIG("详细图集不要超过10M 》-《"),
    MESSAGE_ORIGINAL_IS_NULL("图片名不能为空"),
    MESSAGE_TEMPORARY_PROJECT_MISSING("临时存储的项目对象丢失请返回上一步重新发起项目。"),


    MESSAGE_ENCRYPTION_MOD("md5"),
    ATTR_NAME_LOGIN_ADMIN("loginAdmin"),
    ATTR_NAME_LOGIN_MEMBER("loginMember"),
    ATTR_NAME_TEMPLE_PROJECT("tempProject"),
    ATTR_NAME_PAGE_INFO("pageInfo");
    private String str;

    CrowdConstant(String str) {
        this.str = str;
    }

    CrowdConstant() {
    }

    public String getStr() {
        return str;
    }

}

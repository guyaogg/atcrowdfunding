package com.atguigu.crowd.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 配置放行地址的类
 * @author guyao
 */
public class AccessPassResources {
    public static final String PROJECT_DETAIL = "/project/get/project/detail/";
    public static final Set<String> PASS_RES_SET = new HashSet<>();
    static {
        PASS_RES_SET.add("/crowd");
        PASS_RES_SET.add("/crowd/");
        PASS_RES_SET.add("/pay/notify");
        PASS_RES_SET.add("/crowd/auth/member/to/reg/page");
        PASS_RES_SET.add("/crowd/auth/member/to/login/page");
        PASS_RES_SET.add("/crowd/auth/member/logout");
        PASS_RES_SET.add("/crowd/auth/member/login");
        PASS_RES_SET.add("/crowd/auth/do/member/register");
        PASS_RES_SET.add("/crowd/auth/member/send/short/message.json");
        PASS_RES_SET.add("/crowd/auth/query/member/loginAcct/avail.json");
    }
    public static final Set<String> STATIC_RES_SET = new HashSet<>();
    static {
        STATIC_RES_SET.add("bootstrap");
        STATIC_RES_SET.add("crowd");
        STATIC_RES_SET.add("css");
        STATIC_RES_SET.add("fonts");
        STATIC_RES_SET.add("img");
        STATIC_RES_SET.add("jquery");
        STATIC_RES_SET.add("layer");
        STATIC_RES_SET.add("script");
        STATIC_RES_SET.add("ztree");
    }

    /**
     * 判断是否是静态资源
     * @return true：静态资源
     *          false ：不是静态资源
     */
    public static boolean judgeCurrentServletPathWhetherStaticResource(String servletPath){
        if(servletPath == null || servletPath.length() == 0){
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE.getStr());
        }
        String[] split = servletPath.split("/");
        String firstLevelPath = split[2];

//        int i= 1;
//        for (;i < servletPath.length(); i++) {
//            if(servletPath.charAt(i) == '/'){
//                break;
//            }
//        }
//        String firstLevelPath = servletPath.substring(1,i);
        return STATIC_RES_SET.contains(firstLevelPath);
    }

//    public static void main(String[] args) {
//        String servletPath = "/css/5465132ad/sa";
//        boolean result = judgeCurrentServletPathWhetherStaticResource(servletPath);
//        System.out.println(result);
//    }


}

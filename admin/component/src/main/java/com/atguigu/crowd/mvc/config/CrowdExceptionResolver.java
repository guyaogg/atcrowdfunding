package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.exception.*;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.atguigu.crowd.constant.CrowdConstant.*;

/**
 * @author guyao
 * @create 2021-08-01 21:00
 */
@ControllerAdvice//表示当前类是基于注解的异常处理类
public class CrowdExceptionResolver {
    /**
     * 处理JSON请求错误的方法（通用JSON
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(JSONException.class)
    public ModelAndView resolveJSONException(
            JSONException exception,// 捕获错误
            HttpServletRequest request,// 请求对象
            HttpServletResponse response) throws IOException {// 响应对象
        // 由于是ajax请求没有视图
        return commonResolve(null, null, exception, request, response);
    }
    @ExceptionHandler(RoleSaveException.class)
    public ModelAndView resolveRoleSaveException(
            RoleSaveException exception,// 捕获错误
            HttpServletRequest request,// 请求对象
            HttpServletResponse response) throws IOException {// 响应对象
        // 由于是ajax请求没有视图
        return commonResolve(null, null, exception, request, response);
    }

    // 处理更新或新增时账号重复
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception,// 捕获错误
            HttpServletRequest request,// 请求对象
            HttpServletResponse response) throws IOException {// 响应对象
        // 查看是更新页面还是新增页面

        return exception.getMessage().equals(MESSAGE_LOGIN_ACCT_ALREADY_IN_USE.getStr())?
                commonResolve(ADMIN_PAGE.getStr(), ADD_PAGE.getStr(), exception, request, response)
                : commonResolve(ERROR_PAGE.getStr(), SYSTEM_ERROR.getStr(), exception, request, response);
    }
    // 处理删除错误
    @ExceptionHandler(value = RemoveFailException.class)
    public ModelAndView resolveRemoveFailException(
            RemoveFailException exception,// 捕获错误
            HttpServletRequest request,// 请求对象
            HttpServletResponse response) throws IOException {// 响应对象
        return commonResolve(ADMIN_PAGE.getStr(), PAGE_PAGE.getStr(), exception, request, response);
    }
    /**
     *  处理查询错误处理
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = QueryFailedException.class)
    public ModelAndView resolveQueryFailedException(
            QueryFailedException exception,// 捕获错误
            HttpServletRequest request,// 请求对象
            HttpServletResponse response) throws IOException {// 响应对象
        return commonResolve(ADMIN_PAGE.getStr(), PAGE_PAGE.getStr(), exception, request, response);
    }
    @ExceptionHandler(value = {Exception.class})
    public ModelAndView resolveLoginFailedException(
            Exception exception,//捕获错误
            HttpServletRequest request,//请求对象
            HttpServletResponse response) throws IOException {//响应对象
        return commonResolve(ERROR_PAGE.getStr(), SYSTEM_ERROR.getStr(), exception, request, response);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView resolveNullPointerException(
            NullPointerException exception,//捕获错误
            HttpServletRequest request,//请求对象
            HttpServletResponse response) throws IOException {//响应对象
        return commonResolve(ERROR_PAGE.getStr(), SYSTEM_ERROR.getStr(), exception, request, response);

    }

    /**
     * 通用异常处理
     *
     * @param viewName  异常界面
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private ModelAndView commonResolve(String reView,
                                       String viewName,
                                       Exception exception,
                                       HttpServletRequest request,
                                       HttpServletResponse response
    ) throws IOException {
        //获取异常消息
        //判断类型
        boolean requestType = CrowdUtil.judgeRequestType(request);
        if (requestType) {//是ajax请求
            ResultEntity<Object> resultEntity = ResultEntity.fail(exception.getMessage());
            //创建json
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            response.getWriter().write(json);
            //ajax不返回视图模型
            return null;

        } else {
            //不是ajax
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ATTR_NAME_EXCEPTION.getStr(), exception);
            modelAndView.setViewName(reView + viewName);
            return modelAndView;
        }
    }
}

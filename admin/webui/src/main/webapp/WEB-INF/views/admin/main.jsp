<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/8/2
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <%@include file="../../common/appoint.jsp" %>

</head>


<body>

<%@include file="/WEB-INF/common/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/common/div-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">控制面板</h1>
<%--            <security:authentication property="principal.originalAdmin.userPswd"/>--%>


            <div class="row placeholders">
<%--                界面元素管理--%>
                <security:authorize access="hasAuthority('user:save') or hasRole('顶级管理员') or hasRole('经理')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                             alt="Generic placeholder thumbnail">
                        <h4>经 理</h4>
                        <span class="text-muted">经理</span>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('部长') or hasRole('顶级管理员')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                             alt="Generic placeholder thumbnail">
                        <h4>部 长</h4>
                        <span class="text-muted">部长</span>
                    </div>
                </security:authorize>

<security:authorize access="hasAuthority('user:save') or hasRole('顶级管理员')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>经 理 管 理 员</h4>
                    <span class="text-muted">经 理管理员</span>
                </div>
</security:authorize>
    <security:authorize access="hasRole('顶级管理员')">
        <div class="col-xs-6 col-sm-3 placeholder">
            <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                 alt="Generic placeholder thumbnail">
            <h4>顶 级 管 理 员</h4>
            <span class="text-muted">顶级管理员</span>
        </div>
    </security:authorize>
            </div>
        </div>
    </div>
</div>


</body>
</html>


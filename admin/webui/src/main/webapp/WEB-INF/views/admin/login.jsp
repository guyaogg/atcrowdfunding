<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/8/2
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="keys" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <meta name="author" content="">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/font-awesome.min.css">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="static/jquery/jquery-2.1.1.min.js"></script>

    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
    <%--引入layer--%>
    <script type="text/javascript" src="static/layer/layer.js"></script>
    <link rel="stylesheet" href="static/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <p>${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
    <p>${requestScope.exception.message}</p>
    <form action="${pageContext.request.contextPath }/do/login.html" method="post"  class="form-signin" role="form">
            <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 管理员登录
            </h2>
            <p>${requestScope.exception.message}</p>
            <div class="form-group has-success has-feedback">
                <input type="text" name="loginAcct" value="tom" class="form-control" id="inputSuccess4"
                       placeholder="请输入登录账号" autofocus>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-success has-feedback">
                <input type="text" name="userPassword" value="123456" class="form-control" id="inputSuccess5"
                       placeholder="请输入登录密码"
                       style="margin-top:10px;">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
        <div><input type="checkbox" name="remember-me"/><span> 记住我</span></div>
            <button type="submit" class="btn btn-lg btn-success btn-block">登 录</button>
        </form>
</div>

</body>
</html>

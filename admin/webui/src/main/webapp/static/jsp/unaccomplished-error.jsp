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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%@include file="../../WEB-INF/common/appoint.jsp" %>
    <script type="text/javascript">
        // $(function (){
        //     $("button").click(function (){
        //         //相当于浏览器的后退按钮
        //         // window.history.back();
        //
        //     })
        // })
        $(function () {
            $("#back").click(function () {
                window.history.back();
                return false;
            })
        })
    </script>
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
    <h2 class="form-signin-heading" style="text-align: center"><i class="glyphicon glyphicon-log-in"></i> 尚筹网系统消息
    </h2>
    <H3 style="text-align: center">这个分页还未完成呕！！亲！！！</H3>
    <button id="back" style="width: 170px;margin: 50px auto" class="btn btn-lg btn-primary btn-block">点我返回上一步</button>




</div>

</body>
</html>

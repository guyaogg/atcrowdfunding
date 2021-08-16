<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/8/1
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>nhao</title>
    <%@include file="WEB-INF/common/appoint.jsp" %>
    >

    <script type="text/javascript">
        $(function () {
            $("#btn1").click(function () {
                // $.get()
                // $.post()只能成功
                $.ajax({
                    "url": "send/array.html",//请求地址
                    "type": "post",          //请求方式
                    "data": {
                        "array": [5, 8, 12]
                    },  //请求数据
                    "dataType": "text",      //响应数据的处理
                    "success": function (result) {//处理成功响应
                        alert(result)
                    },
                    "error": function (result) {//处理失败响应
                        alert(result)
                    }
                });
            })
            $("#btn2").click(function () {
                // $.get()
                // $.post()只能成功
                let array = [5, 8, 12];
                console.log(array.length);
                let requestBody = JSON.stringify(array);
                console.log(requestBody.length);
                $.ajax({
                    "url": "send/array/two.json",//请求地址
                    "type": "post",          //请求方式
                    "data": requestBody,
                    "contentType": "application/json;charset=UTF-8",//设置请求头内容类型


                    "dataType": "json",      //响应数据的处理
                    "success": function (result) {//处理成功响应
                        console.log(result)
                    },
                    "error": function (result) {//处理失败响应
                        console.log(result)
                    }
                });
            })
            $("#btn5").click(function () {
                layer.msg("layer的弹框")
            })
            $("#btnA").click(function () {
                console.log("前");
                $.ajax({
                    "url": "text/ajax/async.html",
                    "type": "POST",
                    "dateType": "text",
                    "async": false,// 关闭异步工作模式（默认异步
                    "success": function (response) {
                        console.log("执行" + response);
                    }
                });
                console.log("后");

            })

        })
    </script>


</head>
<body>

<a href="ka.html">查看ssm环境</a><br>

<a href="admin/to/login/page.html"> 去后台</a><br>
<button id="btn1">Send [5,8,12] One</button>
<br>
<button id="btn2">Send [5,8,12] Two</button>
<br>
<button id="btn5">点我弹框</button>
<br>
<button id="btnA">发送Ajax请求</button>


</body>
</html>

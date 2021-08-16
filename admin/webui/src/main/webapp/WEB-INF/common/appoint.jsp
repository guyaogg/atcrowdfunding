<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/8/2
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--    http://localhost:8080/crowdfunding/ka.html--%>
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
<link rel="stylesheet" href="static/css/main.css">
<%--引入layer--%>
<script src="static/layer/layer.js"></script>
<script src="static/script/docs.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".back").click(function () {
            window.history.back();
            return false;
        })
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
</script>
<style>
    .tree li {
        list-style-type: none;
        cursor: pointer;
    }

    .tree-closed {
        height: 40px;
    }

    .tree-expanded {
        height: auto;
    }
</style>

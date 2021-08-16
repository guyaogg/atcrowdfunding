<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <%@include file="../../../common/appoint.jsp" %>
    <%--    // 引入ztree Css,js--%>
    <link rel="stylesheet" href="ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // 创建json对象存储zTree所作的设置
            let setting = {};
            // 准备生成树形结构的json数据(模型
            $.ajax({
                "url": "menu/get/whole/tree.json",
                "type": "post",
                "dataType": "json",
                "success": function (response){
                    let result = response.result;
                    // 如果成功表明有数据
                    if(result == "SUCCESS"){
                        // 得到属性数据
                        let zTreeNodes = response.data;
                        // 将树结构绑定静态资源
                        $.fn.zTree.init($("#treeDemo"),setting,zTreeNodes);

                    }
                    if(result == "FAIL"){
                        layer.msg(response.message);
                    }

                },
                "error":function (response) {
                    layer.msg(response.message);
                }
            })




        })

    </script>

</head>


<body>

<%@include file="/WEB-INF/common/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/common/div-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree">

                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>


</body>
</html>


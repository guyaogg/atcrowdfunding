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
    <%@include file="../../common/appoint.jsp" %>
    <%--    // 引入ztree Css,js--%>
    <link rel="stylesheet" href="static/ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="static/ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="static/crowd/handlerRequest.js"></script>
    <script type="text/javascript" src="static/crowd/my-menu.js"></script>
    <script type="text/javascript">
        $(function () {
            // 刷新页面
            refresh();
            // 给添加子节点按钮绑定单击响应函数
            $("#treeDemo").on("click",".addBtn",function (){
                // 将当前节点的id属性作为新节点的父节点pid
                window.pid = this.id;
                // 打开模态框
                $("#menuAddModal").modal("show");
                return false;
            })
            // 给添加子节点保存按钮绑定事件
            $("#menuSaveBtn").click(function (){
                // 直接调保存函数
                saveMenu("menu/save.json",null,window.pid,"menuAddModal");
                // 重置表单(保存需要重置)
                $("#menuResetBtn").click();

            })
            // 给编辑节点按钮绑定单击响应函数
            $("#treeDemo").on("click",".editBtn",function (){
                // 打开模态框
                $("#menuEditModal").modal("show");
                // 获取zTreeObj对象
                let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
                // console.log(zTreeObj);
                // 将id带入到全局变量中
                window.id = this.id;
                // 搜索节点的属性名
                let key = "id";
                // 搜索节点的属性值
                let value = window.id;

                // 获得当前节点
                let currentNode = zTreeObj.getNodesByParam(key,value)[0];// 数组不知道什么时候改动的
                // 得到数据
                // console.log(currentNode[0]);
                $("#menuEditModal [name=name]").val(currentNode.name);
                $("#menuEditModal [name=url]").val(currentNode.url);
                // 回显一致的选中
                $("#menuEditModal [name=icon]").val([currentNode.icon]);
                return false;
            })
            // 给修改按钮绑定事件
            $("#menuEditBtn").click(function (){
                // 直接调保存/修改函数
                saveMenu("menu/update.json",window.id,null,"menuEditModal");


            })
            // 绑定删除事件
            $("#treeDemo").on("click",".removeBtn",function (){
                // 显示模态框
                $("#menuConfirmModal").modal("show");
                // 将id值传到全局中以便删除
                window.id = this.id;
                // 回显数据
                // 得到名字
                let name = $(this).parent().prev().attr("title");
                $("#menuConfirmModal [id=removeNodeSpan]").text("  【  "+name+"  】 ");
                return false;

            })
            // 给删除按钮绑定事件
            $("#confirmBtn").click(function (){
                // 先保证id不为空
                if(window.id == null){
                    layer.msg("你并没有选中值！,删除失败");
                    return;
                }
                // 发送ajax请求
                $.ajax({
                    "url": "menu/remove.json",
                    "data": "id=" + window.id,
                    "dataType": "json",
                    "success": function (response){
                        successR(response);
                        // 刷新页面

                    },
                    "error": function (response){
                        errorR(response);
                    }
                })

                // 关闭模态框
                $("#menuConfirmModal").modal("hide");
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

<%-- 引入模态框--%>
<%@include file="/WEB-INF/views/menu/modal-menu-add.jsp"%>
<%@include file="/WEB-INF/views/menu/modal-menu-confirm.jsp"%>
<%@include file="/WEB-INF/views/menu/modal-menu-edit.jsp"%>
</body>
</html>


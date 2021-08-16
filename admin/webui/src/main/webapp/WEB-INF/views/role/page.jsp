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
<%--    引入环境--%>
    <link rel="stylesheet" href="static/ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="static/ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="static/crowd/handlerRequest.js"></script>
    <script type="text/javascript" src="static/crowd/my-role.js"></script>

    <script type="text/javascript" >

        $(function (){
            // 为分页操作做初始化
            window.pageNum = 1;// window全局变量
            window.pageSize = 8;
            window.keyword = "";
            window.pageCount = 5;

            // 调用js函数
            generatePage(window.pageNum);
            // 给查询按钮绑定单击响应函数
            $("#searchBtn").click(function () {
                // 获取关键词数据 给全局变量赋值
                window.keyword = $("#keywordInput").val();
                // 调用分页函数
                generatePage(window.pageNum);
            })
            // 点击打开新增模态框
            $("#showModelAdd").click(function (){
                $("#addModel").modal("show");
            })
            // 单击新增模态框保存按钮
            $("#saveRoleBtn").click(function (){
                // 获取参数
                let roleName = $.trim($("#addModel [name=roleName]").val());// 表示找后代元素中的name=？的元素
                // 向服务器发送ajax请求
                $.ajax({
                    "url": "role/save.json",
                    "type": "post",
                    "dataType": "json",
                    "data": "name="+roleName,
                    "success": function (response){
                        let result = response.result;
                        if(result == "SUCCESS"){
                            layer.msg("操作成功");
                            // 重新加载分页数据
                            generatePage(9999999);
                        }
                        if(result == "FAIL"){
                            layer.msg("操作失败," + response.message);
                        }
                    },
                    "error": function (response) {
                        layer.msg("操作失败," + response.message);
                    }
                })

                // 关闭模态框
                $("#addModel").modal("hide");
                // 清理模态框
                $("#addModel [name=roleName]").val("");

            })
            // 点击打开更新模态框(传统click不具有动态性
            // $(".pencilBtn").click(function (){
            //     $("#editModel").modal("show");
            // })
            // 使用on函数解决问题(
            // 首先找到动态元素附着的静态元素(绑定事件，选择器，响应函数
            $("#rolePageBody").on("click",".pencilBtn",function (){
                // 打开模态框
                $("#editModel").modal("show");

                // 获取表格中当前行的角色名称
                let roleName = $(this).parent().prev().text();// 通过关系拿到值

                // 获取当前角色的id
                let roleId = $(this).attr("roleId");// 通过属性值拿到，attr拿到自定属性值
                // 方便使用绑定到全局变量
                window.roleId = roleId;
                // console.log(roleId);
                // 回显数据
                $("#editModel [name=roleName]").val(roleName);

            });
            // 给更新按钮绑定事件
            $("#editRoleBtn").click(function (){
                // 从文本框中获取新的角色名称
                let roleName =  $("#editModel [name=roleName]").val();
                $.ajax({
                    "url": "role/update.json",
                    "type": "post",
                    "data": {
                        "id": window.roleId,
                        "name": roleName
                    },
                    "dataType": "json",
                    "success": function (response){
                        successR(response);
                    },
                    "error": function (response) {
                        errorR(response);

                    }
                })
                // 关闭模态框
                $("#editModel").modal("hide");

            })
            // 测试代码
            // let roleArray = [{roleId:5,roleName:"aaa"},{roleId:3,roleName:"www"}];
            // showConfirmMode(roleArray);

            // 点击确认模态框中确认按钮绑定事件
            $("#removeRoleBtn").click(function (){
                let requestBody = JSON.stringify(window.roleIdArray);
                $.ajax({
                    "url": "role/remove/array.json",
                    "type": "post",
                    "data": requestBody,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType": "json",
                    "success": function (response){
                        successR(response);
                    },
                    "error": function (response){
                        errorR(response);
                    }
                })
                // 关闭模态框
                $("#confirmModel").modal("hide");
            })
            // 为单例删除绑定事件
            $("#rolePageBody").on("click",".removeBtn",function (){
                // 创建role对象存入数组
                let roleName = $(this).parent().prev().text();
                let roleId = $(this).attr("roleId");
                let roleArray = [{
                    roleId: roleId,
                    roleName: roleName
                }]

                // 调用专门的函数打开模态框
                showConfirmMode(roleArray);

            })
            // 给全选绑定事件
            $("#summaryBox").click(function (){
                // 获取多选框自身的状态
                let currentStatus = this.checked;
                // 根据当选状态生成事件
                $(".itemBox").prop("checked", currentStatus);
            });
            // 单击按钮联动全选绑定事件
            $("#rolePageBody").on("click",".itemBox",function (){
                // 获取被选中的数量
                let itemCheckedCount = $(".itemBox:checked").length;
                // 获取全部数量
                let itemCount = $(".itemBox").length;
                $("#summaryBox").prop("checked", itemCheckedCount == itemCount);

            })
            // 给批量删除按钮绑定事件
            $("#batchRemoveBtn").click(function (){

                // 创建预备删除的数组
                let roleArray = [];
                // 遍历选中的多选框
                $(".itemBox:checked").each(function (){
                    // 使用this引用当前遍历的多选框
                    let roleId = $(this).attr("roleId");
                    let roleName = $(this).parent().next().text();
                    roleArray.push({
                        "roleId": roleId,
                        "roleName": roleName
                    });

                });
                // 检查roleArray长度是否为0
                if(roleArray.length == 0){
                    layer.msg("请至少选择一个执行删除");
                    return false;
                }
                // 执行
                showConfirmMode(roleArray);
            })
            // 为改变权限绑定事件
            $("#rolePageBody").on("click",".checkBtn",function (){
                // 打开模态框
                $("#assignAuth").modal("show");
                // 获取当前角色参数
                window.roleId = $(this).attr("roleId");
                // 装配AuthTree的模型结构
                fillAuthTree(window.roleId);
            })

            // 绑定权限重置事件
            $("#assignResetBtn").click(function (){
                // 重新装配AuthTree的模型结构
                fillAuthTree(window.roleId);
            })
            // 绑定权限提交事件
            $("#assignSubmitBtn").click(function (){
                // 收集
                // 声明存放id的数组
                let authIdArray = [];
                // zTreeObj获取
                let zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
                // 获取被勾选节点
                let checkedNodes = zTreeObj.getCheckedNodes();
                // 遍历checkedNodes
                for (let i = 0; i < checkedNodes.length; i++) {
                    let checkedNode = checkedNodes[i];
                    let authId = checkedNode.id;
                    authIdArray.push(authId);
                }
                // alert(authIdArray);
                // 发送保存请求
                let requestBody = {
                    "authIdArray": authIdArray,
                    "roleId": [window.roleId]
                }
                requestBody = JSON.stringify(requestBody);
                requestJSONString("assign/save/authIds/by/roleId.json",requestBody)
                // 关闭模态框
                $("#assignAuth").modal("hide");

            })





        });



    </script>

</head>


<body>


<%@include file="/WEB-INF/common/nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/common/div-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form  class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
<security:authorize access="hasRole('部长') or hasRole('顶级管理员')">
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" id="showModelAdd" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
</security:authorize>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">

                            </tbody>
                            <tfoot id="tFoot">
                            <tr><td colspan="6" align="center"><ul id="footUl" class="pagination"></ul></td></tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<%@include file="/WEB-INF/views/role/model-add.jsp"%>
<%@include file="/WEB-INF/views/role/model-edit.jsp"%>
<%@include file="/WEB-INF/views/role/model-confirm.jsp"%>
<%@include file="/WEB-INF/views/role/model-assign-auth.jsp"%>


</body>
</html>


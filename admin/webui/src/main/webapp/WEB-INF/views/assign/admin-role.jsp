<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript">
        $(function (){
            // 分配按钮绑定事件
            $("#toRightBtn").click(function (){
                // layer.msg("绑定了");
                // jquery强大的选择器
                $("select:eq(0)>option:selected").appendTo("select:eq(1)");
            })
            // 反分配按钮绑定事件
            $("#toLeftBtn").click(function (){
                $("select:eq(1)>option:selected").appendTo("select:eq(0)");
            })
            // 重置刷新
            $("#resetBtn").click(function () {
                location.reload()
            })
            // 提交按钮把以加入的全部选中
            $("#submitBtn").click(function (){
                $("select:eq(1)>option").prop("selected","selected");

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
            <%@include file="/WEB-INF/common/jump-head.jsp" %>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/role/assign.html" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                        <input type="hidden" name="keyword" value="${param.keyword}"/>
                        <div class="form-group">
                            <label>未分配角色列表</label><br>
                            <select  class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
<%--                            只保存已分配--%>
                            <label>已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.AssignedRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <p>
                            <button id="resetBtn" type="reset"  class="btn btn-primary">重 置</button>
                            <button id="submitBtn" type="submit" style="margin-left:166px;"  class="btn btn-success ">提 交</button>
                        </p>

                    </form>
                </div>

            </div>
        </div>

    </div>
</div>


</body>
</html>


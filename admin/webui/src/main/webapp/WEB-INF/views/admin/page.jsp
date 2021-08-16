<%@ page import="com.atguigu.crowd.constant.CrowdConstant" %>
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
        $(function () {
            let oldRemove = <%=session.getAttribute("oldRemove")%>;
            let remove = <%=session.getAttribute("remove")%>;
            let removeCount = <%=session.getAttribute("removeCount")%>;
            if (oldRemove != remove) {
                <%
                session.setAttribute("oldRemove",session.getAttribute("remove"));
                %>
                layer.msg("删除了" + removeCount + "条记录");
            }


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
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="admin/get/page.html" method="post" class="form-inline" role="form"
                          style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text"
                                       value="${param.keyword}"
                                       placeholder=${empty param.keyword ? "请输入查询条件" : param.keyword}>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
<security:authorize access="hasAuthority('user:save') or hasRole('顶级管理员')">
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>

                        <a href="admin/to/add/page.html" class="btn btn-primary" style="float:right;">
                            <i class="glyphicon glyphicon-plus"></i> 新增
                        </a>
                    </security:authorize>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
<security:authorize access="hasAuthority('user:save') or hasRole('顶级管理员')">

<th width="100">操作</th>
</security:authorize>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <c:if test="${empty requestScope.exception}">
                                        <td colspan="6" align="center">抱歉！暂时没有数据</td>
                                    </c:if>
                                    <c:if test="${!empty requestScope.exception}">
                                        <td colspan="6" align="center"><a class="back"
                                                                          href="#">${requestScope.exception.message}，返回上一步</a>
                                        </td>
                                    </c:if>

                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                                <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count}</td>
                                        <td><input type="checkbox"></td>
                                        <td>${admin.loginAcct}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <security:authorize access="hasAuthority('user:save') or hasRole('顶级管理员')">
                                        <td>
                                            <a href="assign/to/admin/role/page.html?adminId=${admin.id}&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}"
                                               class="btn btn-success btn-xs"><i
                                                    class=" glyphicon glyphicon-check"></i></a>
                                            <a href="admin/to/edit/page.html?adminId=${admin.id}&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}"
                                               class="btn btn-primary btn-xs"><i
                                                    class=" glyphicon glyphicon-pencil"></i></a>
                                            <a id="single_remove"
                                               href="admin/remove/${admin.id}/pageNum=${pageInfo.pageNum}&keyword=${param.keyword}.html"
                                               class="btn btn-danger btn-xs"
                                            ><i class=" glyphicon glyphicon-remove"></i></a>
                                        </td>
                                        </security:authorize>
                                    </tr>
                                </c:forEach>

                            </c:if>

                            </tbody>
                            <tfoot>
                            <tr>

                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <c:if test="${pageInfo.isFirstPage}">
                                            <li class="disabled"><a
                                                    href="admin/get/page.html?pageNum=1&keyword=${param.keyword}">上一页</a>
                                            </li>
                                            <li class="disabled"><a
                                                    href="admin/get/page.html?pageNum=1&keyword=${param.keyword}">首
                                                页</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pageInfo.hasPreviousPage}">
                                            <li><a
                                                    href="admin/get/page.html?pageNum=${pageInfo.pageNum - 1}&keyword=${param.keyword}">上一页</a>
                                            </li>
                                            <li><a
                                                    href="admin/get/page.html?pageNum=1&keyword=${param.keyword}">首
                                                页</a>
                                            </li>
                                        </c:if>


                                        <c:forEach items="${requestScope.pageInfo.navigatepageNums}" var="page">
                                            <c:if test="${page==requestScope.pageInfo.pageNum}">
                                                <li class="active"><a
                                                        href="admin/get/page.html?pageNum=${page}&keyword=${param.keyword}">${page}
                                                    <span
                                                            class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${page!=requestScope.pageInfo.pageNum}">
                                                <li>
                                                    <a href="admin/get/page.html?pageNum=${page}&keyword=${param.keyword}">${page}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>

                                        <c:if test="${pageInfo.isLastPage}">
                                            <li class="disabled"><a
                                                    href="admin/get/page.html?pageNum=${pageInfo.pages}&keyword=${param.keyword}">尾
                                                页</a>
                                            </li>
                                            <li class="disabled"><a
                                                    href="admin/get/page.html?pageNum=${pageInfo.pages}&keyword=${param.keyword}">下一页</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pageInfo.hasNextPage}">
                                            <li><a
                                                    href="admin/get/page.html?pageNum=${pageInfo.pages}&keyword=${param.keyword}">尾
                                                页</a>
                                            </li>
                                            <li>
                                                <a href="admin/get/page.html?pageNum=${pageInfo.pageNum + 1}&keyword=${param.keyword}">下一页</a>
                                            </li>
                                        </c:if>

                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>


</body>
</html>


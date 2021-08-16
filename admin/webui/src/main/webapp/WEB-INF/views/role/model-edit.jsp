<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<div id="editModel"
<%@include file="model-common-head.jsp"%>
                <input type="text" name="roleName" class="form-control" value="" placeholder="请输入角色名称" autofocus>
            </div>
            <div class="modal-footer">
                <button id="editRoleBtn" type="button" class="btn btn-success">修 改</button>
                <%@include file="model-common-last.jsp"%>


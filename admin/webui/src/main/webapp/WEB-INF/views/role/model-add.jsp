<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<div id="addModel"
<%@include file="model-common-head.jsp"%>
                <input type="text" name="roleName" class="form-control" placeholder="请输入角色名称" autofocus>
            </div>
            <div class="modal-footer">
                <button id="saveRoleBtn" type="button" class="btn btn-primary">保 存</button>
                <%@include file="model-common-last.jsp"%>


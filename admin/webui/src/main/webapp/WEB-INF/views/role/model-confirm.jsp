<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<div id="confirmModel"
<%@include file="model-common-head.jsp"%>
                <h4>请确认是否要删除下列的角色：</h4>
                <div id="roleNameDiv" style="text-align: center"><br/></div>
            </div>
            <div class="modal-footer">
                <button id="removeRoleBtn" type="button" class="btn btn-success">确认删除</button>
                <%@include file="model-common-last.jsp"%>


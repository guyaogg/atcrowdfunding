<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<div id="assignAuth"
<%@include file="model-common-head.jsp" %>

<div class="modal-body">
    <ul id="authTreeDemo" class="ztree">
    </ul>
</div>
<div class="modal-footer">
    <button id="assignResetBtn" type="reset"  class="btn btn-primary">重置</button>
<button id="assignSubmitBtn" type="submit"  class="btn btn-success">设置好了，请求分配！</button>
<%@include file="model-common-last.jsp" %>


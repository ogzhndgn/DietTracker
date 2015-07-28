<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../pages/header.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="includes/profile-header.jsp" %>
    <div class="row">
        <%@include file="includes/empty-col-2.jsp"%>
        <%@include file="includes/user-info.jsp" %>
        <%@include file="includes/meal-form.jsp" %>
        <%@include file="includes/empty-col-2.jsp"%>
    </div>
    <div class="row">
        <%@include file="includes/empty-col-2.jsp"%>
        <%@include file="includes/meal-history.jsp" %>
        <%@include file="includes/empty-col-2.jsp"%>
    </div>
</div>
</body>
</html>
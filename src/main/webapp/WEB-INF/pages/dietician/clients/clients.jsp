<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../header.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="../../includes/profile-header.jsp" %>
    <div class="row">
        <%@include file="../../includes/empty-col-2.jsp" %>
        <c:choose>
            <c:when test="${not empty clientList}">
                <%@include file="client-list.jsp"%>
            </c:when>
        </c:choose>
        <%@include file="../../includes/empty-col-2.jsp" %>
    </div>
    <%@include file="../../includes/footer.jsp" %>
</div>
</body>
</html>
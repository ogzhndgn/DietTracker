<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../pages/header.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="../profile/includes/profile-header.jsp" %>
    <div class="row">
        <%@include file="../profile/includes/empty-col-2.jsp"%>
        <div class="col-md-6"><caption><h3><spring:message code="text.MealHistory"/></h3></caption></div>
        <div class="col-md-2"><a href="${pageContext.request.contextPath}/profile" class="btn btn-primary" role="button"><spring:message code="text.ReturnToProfile"/></a></div>
        <%@include file="../profile/includes/empty-col-2.jsp"%>
    </div>
    <div class="row">
        <%@include file="../profile/includes/empty-col-2.jsp"%>
        <%@include file="../profile/includes/meal-history.jsp" %>
        <%@include file="../profile/includes/empty-col-2.jsp"%>
    </div>
    <%@include file="../profile/includes/footer.jsp"%>
</div>
</body>
</html>
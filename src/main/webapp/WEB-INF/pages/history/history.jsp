<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../pages/header.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="../includes/profile-header.jsp" %>
    <div class="row">
        <%@include file="../includes/empty-col-2.jsp"%>
        <div class="col-md-6"><caption><h3><spring:message code="text.MealHistory"/></h3></caption></div>
        <div class="col-md-2"><a href="${pageContext.request.contextPath}/profile" class="btn btn-primary" role="button"><spring:message code="text.ReturnToProfile"/></a></div>
        <%@include file="../includes/empty-col-2.jsp"%>
    </div>
    <div class="row">
        <%@include file="../includes/empty-col-2.jsp"%>
        <%@include file="../includes/meal-search-form.jsp"%>
        <%@include file="../includes/empty-col-2.jsp"%>
    </div>
    <div class="row">
        <%@include file="../includes/empty-col-2.jsp"%>
        <c:choose>
            <c:when test="${not empty historyList}">
                <%@include file="../includes/meal-history.jsp" %>
            </c:when>
            <c:otherwise>
                <div class="col-md-8 alert alert-danger" role="alert"><h4 align="center"><spring:message code="text.NoHistoryToShow"/></h4></div>
            </c:otherwise>
        </c:choose>
        <%@include file="../includes/empty-col-2.jsp"%>
    </div>
    <%--<%@include file="../includes/footer.jsp"%>--%>
</div>
</body>
</html>
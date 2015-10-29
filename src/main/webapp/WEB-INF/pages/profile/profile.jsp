<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../pages/header.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="../includes/profile-header.jsp" %>
    <div class="row">
        <%@include file="../includes/empty-col-2.jsp" %>
        <%@include file="../includes/user-info.jsp" %>
        <%@include file="../includes/meal-form.jsp" %>
        <%@include file="../includes/empty-col-2.jsp" %>
    </div>
    <c:choose>
        <c:when test="${not empty historyList}">
            <div class="row">
                <%@include file="../includes/empty-col-2.jsp" %>
                <div class="col-md-6"><caption><h3><spring:message code="text.MealHistory"/></h3></caption>
                </div><div class="col-md-2"><a href="${pageContext.request.contextPath}/history" class="btn btn-primary"role="button"><spring:message code="text.SeeMore"/></a></div>
                <%@include file="../includes/empty-col-2.jsp" %>
            </div>
            <div class="row">
                <%@include file="../includes/empty-col-2.jsp" %>
                <%@include file="../includes/meal-history.jsp" %>
                <%@include file="../includes/empty-col-2.jsp" %>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <%@include file="../includes/empty-col-2.jsp" %>
                <div class="col-md-8 alert alert-danger" role="alert"><h4 align="center"><spring:message code="text.NoMealInfo"/></h4></div>
                <%@include file="../includes/empty-col-2.jsp" %>
            </div>
        </c:otherwise>
    </c:choose>
    <%--<%@include file="../includes/footer.jsp"%>--%>
</div>
</body>
</html>
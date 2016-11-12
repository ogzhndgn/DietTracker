<div class="row">
  <%@include file="empty-col-2.jsp" %>
  <div class="col-md-5">
    <div class="page-header" style="height: 8%;">
      <h1>
        <spring:message code="text.Title"/>
      </h1>
    </div>
  </div>
  <div class="col-md-3">
    <div class="page-header text-right" style="height: 8%;">
      <spring:message code="text.Dear"/>
      <h4>
      <c:choose>
        <c:when test="${not empty user.name}">
          <c:out value="${user.name}"/>
        </c:when>
        <c:otherwise>
          <c:out value="${user.email}"/>
        </c:otherwise>
      </c:choose>
    </h4>
    </div>
  </div>
  <%@include file="empty-col-2.jsp" %>
</div>
<div class="row">
  <%@include file="empty-col-2.jsp"%>
  <div class="col-md-8">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <ul class="nav navbar-nav">
          <li><a href="${pageContext.request.contextPath}/profile"><spring:message code="text.MenuItem1"/></a></li>
          <li><a href="${pageContext.request.contextPath}/history"><spring:message code="text.MenuItem2"/></a></li>
          <li><a href="${pageContext.request.contextPath}/weighttrack"><spring:message code="text.MenuItem3"/></a></li>
          <li class="navbar-right"><a href="${pageContext.request.contextPath}/logout"><spring:message code="text.LogOut"/></a></li>
        </ul>
      </div>
    </nav>
    <c:if test="${showErrorMessage}">
      <div class="alert alert-danger fade in" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error</span><spring:message code="error.${errorMessage}"/>
      </div>
    </c:if>
    <c:if test="${showSuccessMessage}">
      <div class="alert alert-success" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
        <span class="sr-only">Success!</span><spring:message code="success.${successMessage}"/>
      </div>
    </c:if>
  </div>
  <%@include file="empty-col-2.jsp"%>
</div>
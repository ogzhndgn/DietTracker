<div class="row">
  <%@include file="empty-col-2.jsp"%>
  <div class="col-md-8">
    <div class="page-header">
      <h1>
        <spring:message code="text.Title"/>
      </h1>
    </div>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <ul class="nav navbar-nav">
          <li><a href="${pageContext.request.contextPath}/profile"><spring:message code="text.MenuItem1"/></a></li>
          <li><a href="${pageContext.request.contextPath}/history"><spring:message code="text.MenuItem2"/></a></li>
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
<div class="row">
  <%@include file="empty-col-2.jsp" %>
  <div class="col-md-5">
    <div class="page-header" style="height: 10%;">
      <h1>
        <spring:message code="text.Title"/>
      </h1>
    </div>
  </div>
  <div class="col-md-3">
    <div class="page-header text-right" style="height: 10%;">
      <spring:message code="text.Dear"/>
        <c:choose>
          <c:when test="${not empty user.name}">
            <c:out value="${user.name}"/>
          </c:when>
          <c:otherwise>
            <c:out value="${user.email}"/>
          </c:otherwise>
        </c:choose>
      <br>
      <a href="${pageContext.request.contextPath}/logout" class="btn btn-link"><spring:message code="text.LogOut"/></a>
    </div>
  </div>
  <%@include file="empty-col-2.jsp" %>
</div>
<div class="row">
  <%@include file="empty-col-2.jsp"%>
  <div class="col-md-8">
    <c:choose>
      <c:when test="${user.role eq 'CLIENT'}">
        <%@include file="../client/menu.jsp" %>
      </c:when>
      <c:otherwise>
        <%@include file="../dietician/menu.jsp" %>
      </c:otherwise>
    </c:choose>
    <c:if test="${showErrorMessage}">
      <div class="alert alert-danger fade in" role="alert" id="error-message-div">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error</span><spring:message code="error.${errorMessage}"/>
      </div>
    </c:if>
    <c:if test="${showSuccessMessage}">
      <div class="alert alert-success fade in" role="alert" id="success-message-div">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
        <span class="sr-only">Success!</span><spring:message code="success.${successMessage}"/>
      </div>
    </c:if>
  </div>
  <%@include file="empty-col-2.jsp"%>
</div>
<script>
  $(document).ready(function () {
    $("#error-message-div").fadeTo(2000, 500).slideUp(500, function () {
      $("#error-message-div").slideUp(500);
    });
    $("#success-message-div").fadeTo(2000, 500).slideUp(500, function () {
      $("#success-message-div").slideUp(500);
    });
  });
</script>
<html lang="en">
<%@include file="../../pages/header.jsp" %>
<link href="${pageContext.request.contextPath}/css/login.page.css" rel="stylesheet">
<body bgcolor="#faebd7">
<div class="container">
  <div class="row">
    <div class="col-md-6 col-md-offset-3">
      <h3><spring:message code="text.Title"/></h3>
    </div>
    <div class="row">
      <div class="col-md-6 col-md-offset-3">
        <c:if test="${showErrorMessage}">
          <div class="alert alert-danger" role="alert">
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
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-12">
                <a class="active" id="login-form-link"><spring:message code="text.ForgotPassword"/></a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                <form id="forgotpassword-form" action="${pageContext.request.contextPath}/forgotpassword" method="post" role="form">
                  <div class="form-group">
                    <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="<spring:message code="text.Email"/>" value="" autocomplete="off" required="">
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                        <input type="submit" name="forgotpassword-submit" id="forgotpassword-submit" tabindex="4" class="form-control btn btn-login" value="<spring:message code="text.ForgotPassword"/>">
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-lg-12">
                        <div class="text-center">
                          <a href="${pageContext.request.contextPath}/" tabindex="5" class="forgot-password"><spring:message code="text.Login"/></a>
                        </div>
                        <div class="text-center">
                          <br/>
                          <span class="version-name">v1.5.0 Florence</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
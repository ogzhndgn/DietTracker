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
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-12">
                <a class="active" id="login-form-link"><spring:message code="text.PasswordRecovery"/></a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                <form id="passwordrecovery-form" action="${pageContext.request.contextPath}/passwordrecovery/${hash}" method="post" role="form">
                  <div class="form-group">
                    <input type="password" name="password" id="password" tabindex="1" class="form-control" placeholder="<spring:message code="text.Password"/>" value="" autocomplete="off" required="">
                  </div>
                  <div class="form-group">
                    <input type="password" name="confirm-password" id="confirm-password" tabindex="1" class="form-control" placeholder="<spring:message code="text.ConfirmPassword"/>" value="" autocomplete="off" required="">
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                        <input type="submit" name="forgotpassword-submit" id="forgotpassword-submit" tabindex="4" class="form-control btn btn-login" value="<spring:message code="text.PasswordRecovery"/>">
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
                          <span class="version-name">v1.4.0 Edinburgh</span>
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
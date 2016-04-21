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
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-12">
              </div>
            </div>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                  <div class="form-group">
                    <div class="row">
                      <div class="col-lg-12">
                        <div class="text-center">
                            <h1>Page Not Found!</h1>
                        </div>
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
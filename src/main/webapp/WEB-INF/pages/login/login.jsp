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
                <c:if test="${showLogOutMessage}">
                    <div class="alert alert-success" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
                        <span class="sr-only"></span><spring:message code="success.${successMessage}"/>
                    </div>
                </c:if>
                <div class="panel panel-login">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <a href="#" <c:if test="${showLoginForm}"> class="active"</c:if> id="login-form-link"><spring:message code="text.Login"/></a>
                            </div>
                            <div class="col-xs-6">
                                <a href="#" <c:if test="${not showLoginForm}"> class="active"</c:if> id="register-form-link"><spring:message code="text.Register"/></a>
                            </div>
                        </div>
                        <hr>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form id="login-form" action="${pageContext.request.contextPath}/" method="post" role="form" <c:if test="${not showLoginForm}"> style="display: none;"</c:if> >
                                    <div class="form-group">
                                        <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="<spring:message code="text.Email"/>" value="" autocomplete="off" required="">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<spring:message code="text.Password"/>" autocomplete="off"  required="">
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="<spring:message code="text.Login"/>">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="text-center">
                                                    <%--<a href="${pageContext.request.contextPath}/forgotpassword" tabindex="5" class="forgot-password"><spring:message code="text.ForgotPassword"/></a>--%>
                                                    <span class="version-name">v1.2.0 Copenhagen</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <form id="register-form" action="${pageContext.request.contextPath}/register" method="post" role="form" <c:if test="${not showRegisterForm}"> style="display: none;" </c:if> >
                                    <div class="form-group">
                                        <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="<spring:message code="text.Email"/>" value="" autocomplete="off" required="">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<spring:message code="text.Password"/>" autocomplete="off" required="">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="<spring:message code="text.ConfirmPassword"/>" autocomplete="off" required="">
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="<spring:message code="text.Register"/>">
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.page.js"></script>
</body>
</html>
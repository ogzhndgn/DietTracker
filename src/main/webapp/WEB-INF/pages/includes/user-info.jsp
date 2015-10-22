<div class="col-md-4">
  <h3><spring:message code="text.ProfileUpdate"/></h3>
  <form class="form-horizontal" role="form" id="user-update" action="${pageContext.request.contextPath}/profile" method="post">
    <input type="hidden" name="user_update"/>
    <div class="form-group">
      <label class="control-label col-sm-4" for="name"><spring:message code="text.Name"/></label>
      <div class="col-sm-8">
        <input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="<spring:message code="text.Name"/>" value="${user.name}" required="">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-4"><spring:message code="text.Email"/></label>
      <div class="col-sm-8">
        <p class="form-control-static">${user.email}</p>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-4" for="password"><spring:message code="text.Password"/></label>
      <div class="col-sm-8">
        <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<spring:message code="text.Password"/>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-4" for="password"><spring:message code="text.ConfirmPassword"/></label>
      <div class="col-sm-8">
        <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="<spring:message code="text.ConfirmPassword"/>">
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-4"></div>
      <div class="col-sm-8">
        <button type="submit" class="btn btn-default"><spring:message code="text.Update" /></button>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary"><span class="glyphicon glyphicon-log-out"></span><spring:message code="text.LogOut"/></a>
      </div>
    </div>
  </form>
</div>
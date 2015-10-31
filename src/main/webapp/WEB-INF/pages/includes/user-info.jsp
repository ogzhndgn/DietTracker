<div class="col-md-4">
  <h3><spring:message code="text.ProfileUpdate"/></h3>
  <form class="form-horizontal" role="form" id="user-update" action="${pageContext.request.contextPath}/profile" method="post">
    <input type="hidden" name="user_update"/>
    <div class="form-group">
      <label class="control-label col-sm-4" for="name"><spring:message code="text.Name"/></label>
      <div class="col-sm-8">
        <input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="<spring:message code="text.Name"/>" value="${user.name}" required="" autocomplete="off">
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
        <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<spring:message code="text.Password"/>" autocomplete="off">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-4" for="password"><spring:message code="text.ConfirmPassword"/></label>
      <div class="col-sm-8">
        <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="<spring:message code="text.ConfirmPassword"/>" autocomplete="off">
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-4"></div>
      <div class="col-sm-8">
        <button type="submit" class="btn btn-default"><spring:message code="text.Update" /></button>
        <button type="button" class="confirm-logout btn btn-primary" data-toggle="modal" data-target="#confirmLogoutModal">
          <span class="glyphicon glyphicon-log-out"></span><spring:message code="text.LogOut"/>
        </button>
      </div>
    </div>
  </form>
</div>
<div class="modal fade" id="confirmLogoutModal" tabindex="-1" role="dialog" aria-labelledby="confirmLogoutModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="confirmLogoutModalLabel"><spring:message code="text.AreYouSureTitle"/></h4>
      </div>
      <div class="modal-body">
        <spring:message code="text.AreYouSureLogout"/>
      </div>
      <div class="modal-footer">
        <a data-dismiss="modal" class="btn secondary"><span class="glyphicon glyphicon-remove"></span><spring:message code="text.No"/></a>
        <a id="confirmLogoutButton" href="${pageContext.request.contextPath}/logout" role="button" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-ok"></span><spring:message code="text.Yes"/>
        </a>
      </div>
    </div>
  </div>
</div>
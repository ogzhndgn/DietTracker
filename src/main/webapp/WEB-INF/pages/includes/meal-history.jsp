<div class="col-md-8">
  <table class="table table-striped table-bordered">
    <thead>
    <tr>
      <th><spring:message code="text.Meal"/></th>
      <th><spring:message code="text.Food"/></th>
      <th><spring:message code="text.Time"/></th>
      <th><spring:message code="text.Actions"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="history" items="${historyList}">
      <tr>
        <td><spring:message code="text.${history.code}"/></td>
        <td>${history.foodList}</td>
        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${history.eatingTime}"/></td>
        <td>
          <a role="button" class="confirm-delete btn btn-primary btn-sm" data-id="${history.id}">
            <span class="glyphicon glyphicon-remove"></span><spring:message code="text.Delete"/>
          </a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="confirmModalLabel"><spring:message code="text.AreYouSureTitle"/></h4>
      </div>
      <div class="modal-body">
        <spring:message code="text.AreYouSureDeleteMeal"/>
      </div>
      <div class="modal-footer">
        <a data-dismiss="modal" class="btn secondary"><spring:message code="text.Close"/></a>
        <a id="deleteButton" role="button" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-remove"></span><spring:message code="text.Delete"/>
        </a>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="errorModalLabel"><spring:message code="text.ErrorInformation"/></h4>
            </div>
            <div class="modal-body">
                <spring:message code="error.ERR_MEAL_NOT_DELETED"/>
            </div>
            <div class="modal-footer">
                <a data-dismiss="modal" class="btn secondary"><spring:message code="text.Close"/></a>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.confirm-delete').on('click', function () {
            var id = $(this).data('id');
            $('#confirmModal').data('id', id).modal('show');
        });

        $('#deleteButton').click(function () {
            var id = $('#confirmModal').data('id');
            $.ajax({
                type: 'DELETE',
                url: '${pageContext.request.contextPath}/delete/' + id,
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (result) {
                    if (result == true) {
                        $('[data-id=' + id + ']').parents('tr').remove();
                        $('#confirmModal').modal('hide');
                    } else {
                        $('#confirmModal').modal('hide');
                        $('#errorModal').modal('show');
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#confirmModal').modal('hide');
                    $('#errorModal').modal('show');
                }
            })
        });
    });
</script>
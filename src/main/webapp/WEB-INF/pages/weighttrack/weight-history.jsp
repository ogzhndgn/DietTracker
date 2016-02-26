<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-4">
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th><spring:message code="text.Date"/></th>
            <th><spring:message code="text.Weight"/></th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="weight" items="${weightList}">
            <tr>
                <td><fmt:formatDate type="both" dateStyle="short" value="${weight.weightDate}"/></td>
                <td>${weight.weight}</td>
                <c:if test="${weight.status eq '+'}">
                    <td><span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span></td>
                </c:if>
                <c:if test="${weight.status eq '-'}">
                    <td><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span></td>
                </c:if>
                <c:if test="${weight.status eq '0'}">
                    <td><span class="glyphicon glyphicon-resize-horizontal" aria-hidden="true"></span></td>
                </c:if>
                <td>
                    <a role="button" class="confirm-delete btn btn-primary btn-sm" data-id="${weight.id}">
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
                <spring:message code="text.AreYouSureDeleteWeight"/>
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
                <spring:message code="error.ERR_WEIGHT_NOT_DELETED"/>
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
                url: '${pageContext.request.contextPath}/weighttrack/delete/' + id,
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
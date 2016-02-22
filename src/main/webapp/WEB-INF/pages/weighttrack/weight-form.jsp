<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker.min.css" rel="stylesheet">
<div class="col-md-2">
    <h3><spring:message code="text.TrackYourWeight"/></h3>
    <form role="form" id="new-weight" class="form-horizontal" action="${pageContext.request.contextPath}/weighttrack" method="post">
        <input type="hidden" name="add_weight"/>
        <div class="form-group">
            <label class="control-label col-sm-4" for="weight-date"><spring:message code="text.Date"/></label>
            <div class="col-sm-8">
                <div class="input-group date" data-provide="datepicker" id="weight-date-div">
                    <input type="text" name="weight-date" id="weight-date" class="form-control" placeholder="<spring:message code="text.Date"/>" autocomplete="off"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4" for="weight"><spring:message code="text.Weight"/></label>
            <div class="col-sm-8">
                <input type="number" min="0" step="0.1" name="weight" id="weight" class="form-control" placeholder="<spring:message code="text.Weight"/>" autocomplete="off"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4"></div>
            <div class="col-sm-8">
                <button type="submit" class="btn btn-default"><spring:message code="text.Save"/></button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {
        $('#weight-date-div').datepicker({
            format: 'dd.mm.yyyy'
        });
    });
</script>
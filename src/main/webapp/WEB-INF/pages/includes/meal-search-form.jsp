<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker.min.css" rel="stylesheet">
<div class="col-md-8">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title"><spring:message code="text.MealSearchFormTitle"/></div>
        </div>
        <div class="panel-body">
            <form role="form" id="meal-search" class="form-horizontal" action="${pageContext.request.contextPath}/history" method="post">
                <div class="form-group">
                    <label class="col-sm-2" for="meal-select"><spring:message code="text.Meal"/></label>
                    <label class="col-sm-3" for="meal-time-begin"><spring:message code="text.Time"/></label>
                    <label class="col-sm-3" for="meal-time-end"><spring:message code="text.Time"/></label>
                    <label class="col-sm-3" for="food-search"><spring:message code="text.Food"/></label>
                    <label class="col-sm-1">&nbsp;</label>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">
                        <select class="form-control" id="meal-select" name="meal">
                            <option value="0"><spring:message code="text.PleaseSelect"/></option>
                            <c:forEach var="meal" items="${mealList}">
                                <c:choose>
                                    <c:when test="${mealId == meal.id}">
                                        <option value="${meal.id}" selected>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${meal.id}">
                                    </c:otherwise>
                                </c:choose>
                                <spring:message code="text.${meal.code}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group date" data-provide="datepicker" id="meal-time-begin-div">
                            <input type="text" name="meal-time-begin" id="meal-time-begin" class="form-control" placeholder="<spring:message code="text.Time"/>" value="${mealTimeBegin}" autocomplete="off"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group date" data-provide="datepicker" id="meal-time-end-div">
                            <input type="text" name="meal-time-end" id="meal-time-end" class="form-control" placeholder="<spring:message code="text.Time"/>" value="${mealTimeEnd}" autocomplete="off"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="food-search" id="food-search" class="form-control" placeholder="<spring:message code="text.Food"/>" value="${foodSearch}" autocomplete="off"/>
                    </div>
                    <div class="col-sm-1">
                        <button type="submit" class="btn btn-default"><spring:message code="text.Search"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#meal-time-begin-div').datepicker({
            format: 'dd.mm.yyyy'
        });
        $('#meal-time-end-div').datepicker({
            format: 'dd.mm.yyyy'
        });
    });
</script>
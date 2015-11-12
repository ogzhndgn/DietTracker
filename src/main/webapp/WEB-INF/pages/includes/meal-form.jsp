<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/typeahead.bundle.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/autocomplete.css" rel="stylesheet">
<div class="col-md-4">
  <h3><spring:message code="text.LogYourMeal"/></h3>
  <form role="form" id="new-meal" class="form-horizontal" action="${pageContext.request.contextPath}/profile" method="post">
      <input type="hidden" name="add_meal"/>
      <div class="form-group">
          <label class="control-label col-sm-2" for="meal"><spring:message code="text.Meal"/></label>
          <div class="col-sm-10">
              <select class="form-control" id="meal" name="meal" required="">
                  <option><spring:message code="text.PleaseSelect"/></option>
                  <c:forEach var="meal" items="${mealList}">
                      <option value="${meal.id}"><spring:message code="text.${meal.code}"/></option>
                  </c:forEach>
              </select>
          </div>
      </div>
      <div id="food-list-div">
          <div id="food-div-id-1" class="form-group">
              <label class="control-label col-sm-2" for="foodid"><spring:message code="text.Food"/></label>
              <div class="col-sm-10">
                  <div class="input-group">
                      <input type="text" name="food" id="foodid" class="form-control" placeholder="<spring:message code="text.Food"/>" value="" required="" autocomplete="off"/>
                      <span class="input-group-addon"><span class="addtextbox glyphicon glyphicon-plus-sign"></span></span>
                  </div>
              </div>
          </div>
          <div id="food-div-id-2" class="form-group">
              <div class="col-sm-2"></div>
              <div class="col-sm-10">
                    <input type="text" name="food" id="foodid" class="form-control" placeholder="<spring:message code="text.Food"/>" value="" autocomplete="off"/>
              </div>
          </div>
          <div id="food-div-id-3" class="form-group">
              <div class="col-sm-2"></div>
              <div class="col-sm-10">
                    <input type="text" name="food" id="foodid" class="form-control" placeholder="<spring:message code="text.Food"/>" value="" autocomplete="off"/>
              </div>
          </div>
      </div>
      <div class="form-group">
          <label class="control-label col-sm-2" for="time"><spring:message code="text.Time"/></label>
          <div class="col-sm-10">
              <div class="input-group date" id="foodtimediv" >
                  <input type="text" name="time" id="time" class="form-control" placeholder="<spring:message code="text.Time"/>" value="" required="" autocomplete="off"/>
                  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
              </div>
          </div>
      </div>
      <div class="form-group">
          <div class="col-sm-2"></div>
          <div class="col-sm-10">
              <button type="submit" class="btn btn-default"><spring:message code="text.Save"/></button>
          </div>
      </div>
    </form>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/food.time-tr.js"></script>
</div>
<script>
    $(document).ready(function () {
        var counter = 4;
        $('.addtextbox').on('click', function() {
            if(counter > 7) {
                $("#limitExceedInfo").modal('show');
                return false;
            }
            var newFoodDiv = $(document.createElement('div')).attr("id", 'food-div-id-' + counter).attr("class", 'form-group');
            newFoodDiv.after().html('<div class="col-sm-2"></div><div class="col-sm-10">'+
                    '<input type="text" name="food" id="foodid" class="form-control" placeholder="<spring:message code="text.Food"/>" value="" autocomplete="off"/></div>');
            newFoodDiv.appendTo("#food-list-div");
            counter++;
        });
    });
</script>
<div class="modal fade" id="limitExceedInfo" tabindex="-1" role="dialog" aria-labelledby="limitExceedInfoLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="limitExceedInfoLabel"><span class="glyphicon glyphicon-info-sign"></span><spring:message code="text.Information"/></h4>
            </div>
            <div class="modal-body">
                <spring:message code="text.FoodDivLimitExceed"/>
            </div>
            <div class="modal-footer">
                <a data-dismiss="modal" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-ok"></span><spring:message code="text.Ok"/></a>
            </div>
        </div>
    </div>
</div>
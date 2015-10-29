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
      <div class="form-group">
          <label class="control-label col-sm-2" for="foodid"><spring:message code="text.Food"/></label>
          <div class="col-sm-10">
              <input type="text" name="food" id="foodid" class="form-control" placeholder="<spring:message code="text.Food"/>" value="" required=""/>
          </div>
      </div>
      <div class="form-group">
          <div class="col-sm-2"></div>
          <div class="col-sm-10">
              <input type="text" name="food" id="foodid" class="form-control" placeholder="<spring:message code="text.Food"/>" value=""/>
          </div>
      </div>
      <div class="form-group">
          <div class="col-sm-2"></div>
          <div class="col-sm-10">
              <input type="text" name="food" id="foodid" class="form-control" placeholder="<spring:message code="text.Food"/>" value=""/>
           </div>
      </div>
      <div class="form-group">
          <label class="control-label col-sm-2" for="time"><spring:message code="text.Time"/></label>
          <div class="col-sm-10">
              <div class="input-group date" id="foodtimediv" >
                  <input type="text" name="time" id="time" class="form-control" placeholder="<spring:message code="text.Time"/>" value="" required=""/>
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
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/autocomplete.js"></script>--%>
    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--var foods = new Bloodhound({--%>
                <%--datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),--%>
                <%--queryTokenizer: Bloodhound.tokenizers.whitespace,--%>
                <%--remote: {--%>
                    <%--url: '${pageContext.request.contextPath}/getfood?prefix=%QUERY',--%>
                    <%--wildcard: '%QUERY'--%>
                <%--}--%>
            <%--});--%>

            <%--// passing in `null` for the `options` arguments will result in the default--%>
            <%--// options being used--%>
            <%--foods.initialize();--%>
            <%--$('#food').typeahead(null, {--%>
                <%--name: 'foods',--%>
                <%--display: 'value',--%>
                <%--source: foods--%>
            <%--});--%>
        <%--});--%>
    <%--</script>--%>
</div>
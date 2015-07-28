<div class="col-md-8">
  <caption><h3><spring:message code="text.MealHistory"/></h3></caption>
  <table class="table table-striped table-bordered">
    <thead>
    <tr>
      <th><spring:message code="text.Meal"/></th>
      <th><spring:message code="text.Food"/></th>
      <th><spring:message code="text.Time"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="history" items="${historyList}">
      <tr>
        <td><spring:message code="text.${history.code}"/></td>
        <td>${history.foodList}</td>
        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${history.eatingTime}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="${pageContext.request.contextPath}/history" class="btn btn-info" role="button"><spring:message code="text.SeeMore"/></a>
</div>
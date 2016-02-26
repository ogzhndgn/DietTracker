<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../pages/header.jsp" %>
<body>
<div class="container-fluid">
  <%@include file="../includes/profile-header.jsp" %>
  <div class="row">
    <%@include file="../includes/empty-col-2.jsp" %>
    <%@include file="weight-form.jsp"%>
    <%@include file="weight-chart.jsp"%>
    <%@include file="../includes/empty-col-2.jsp" %>
  </div>
  <div class="row">
    <%@include file="../includes/empty-col-2.jsp"%>
    <c:choose>
      <c:when test="${not empty weightList}">
          <%@include file="weight-history.jsp"%>
      </c:when>
    </c:choose>
  </div>
  <%@include file="../includes/footer.jsp"%>
</div>
</body>
</html>
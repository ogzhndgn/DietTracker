<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-8">
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th><spring:message code="text.Name"/></th>
            <th><spring:message code="text.Email"/></th>
            <th><spring:message code="text.Age"/></th>
            <th><spring:message code="text.Details"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="client" items="${clientList}">
            <spring:url var="detailUrl" value="/dietician/clientdetail/{parameter}">
                <spring:param name="parameter" value="${client.id}"/>
            </spring:url>
            <tr>
                <td>${client.name}</td>
                <td>${client.email}</td>
                <td>${client.age}</td>
                <td><a href="<c:out value='${detailUrl}'/>"><spring:message code="text.Detail"/> </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
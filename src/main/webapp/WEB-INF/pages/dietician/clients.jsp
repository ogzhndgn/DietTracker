<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../pages/header.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="../includes/profile-header.jsp" %>
    <div class="row">
        <%@include file="../includes/empty-col-2.jsp" %>
        <table>
            <c:forEach var="client" items="${clientList}">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.name}</td>
                </tr>
            </c:forEach>
        </table>
        <%@include file="../includes/empty-col-2.jsp" %>
    </div>
    <%@include file="../includes/footer.jsp" %>
</div>
</body>
</html>
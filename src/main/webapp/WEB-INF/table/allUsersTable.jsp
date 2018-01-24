<%@ include file="../util/addition.jsp" %>
<c:set var="count" value="${firstIndex}" scope="page"/>
<table id="allUsersTable" class="table table-condensed">
    <tr>
        <th><fmt:message key="number"/></th>
        <th><fmt:message key="user"/></th>
        <th><fmt:message key="role"/></th>
        <th><fmt:message key="phone.number"/></th>
        <th><fmt:message key="email"/></th>
    </tr>
    <c:forEach var="client" items="${users}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td><c:out value="${count}"/></td>
            <td><c:out value="${client.firstName} ${client.lastName}"/></td>
            <td><c:out value="${client.role}"/></td>
            <td><c:out value="${client.phoneNumber}"/></td>
            <td><c:out value="${client.email}"/></td>
        </tr>
    </c:forEach>
</table>

<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${numberOfPages}">

            <c:set var="pageCounter" value="${pageCounter + 1}" scope="page"/>
            <form action="${pageContext.request.contextPath}/my-car/all_users" method="post">
                <input type="hidden" value="${pageCounter}" name="currentPage">
                <input style="margin: 2px" type="submit" class="btn btn-primary btn-lg" value="${pageCounter}">
            </form>

        </c:forEach>
    </ul>
</nav>

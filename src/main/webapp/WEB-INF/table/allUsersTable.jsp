<%@ include file="../util/addition.jsp" %>
<c:set var="counter" value="0" scope="page"/>
<table id="allUsersTable" class="table table-condensed">
    <tr>
        <th><fmt:message key="number"/></th>
        <th><fmt:message key="user"/></th>
        <th><fmt:message key="role"/></th>
        <th><fmt:message key="phone.number"/></th>
        <th><fmt:message key="email"/></th>
        <th><fmt:message key="password"/></th>
    </tr>
    <c:forEach var="client" items="${users}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td><c:out value="${count}"/></td>
            <td><c:out value="${client.firstName} ${client.lastName}"/></td>
            <td><c:out value="${client.role}"/></td>
            <td><c:out value="${client.phoneNumber}"/></td>
            <td><c:out value="${client.email}"/></td>
            <td><c:out value="${client.password}"/></td>
        </tr>
    </c:forEach>
</table>

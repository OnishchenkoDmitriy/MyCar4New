<%@ include file="../util/addition.jsp" %>

<c:set var="counter" value="0" scope="page"/>
<table class="table table-condensed" id="myOrdersTable">

    <c:if test="${informationMessage != null}">
        <div class="alert alert-info">${informationMessage}</div>
    </c:if>
    <tr>
        <th><fmt:message key="number"/></th>
        <th><fmt:message key="price"/></th>
        <th><fmt:message key="user"/></th>
        <th><fmt:message key="departure.address"/></th>
        <th><fmt:message key="arrival.address"/></th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td><c:out value="${count}"/></td>
            <td><c:out value="${order.price / 100}"/> grn.</td>
            <td><c:out value="${order.client.firstName} ${order.client.lastName}"/></td>
            <td><c:out value="${order.departureAddress.street} ${order.departureAddress.number}"/></td>
            <td><c:out value="${order.arrivalAddress.street} ${order.arrivalAddress.number}"/></td>
        </tr>
    </c:forEach>
</table>
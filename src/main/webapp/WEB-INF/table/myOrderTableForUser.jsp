<%@ include file="../util/addition.jsp" %>

<c:set var="counter" value="0" scope="page"/>
<table class="table table-condensed" id="myOrdersTable">
    <c:if test="${informationMessage eq not null}">
        <div class="alert alert-info">${informationMessage}</div>
    </c:if>
    <tr>
        <th><fmt:message key="number"/></th>
        <th><fmt:message key="price"/></th>
        <th><fmt:message key="driver"/></th>
        <th><fmt:message key="driver.phone.number"/></th>
        <th><fmt:message key="car"/></th>
        <th><fmt:message key="departure.address"/></th>
        <th><fmt:message key="arrival.address"/></th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td><c:out value="${count}"/></td>
            <td><c:out value="${order.price}"/> grn.</td>
            <td><c:out value="${order.driver.firstName} ${order.driver.lastName}"/></td>
            <td><c:out value="${order.driver.phoneNumber}"/></td>
            <td><c:out value="${order.car.brand} ${order.car.model} ${order.car.number}"/></td>
            <td><c:out value="${order.departureAddress.street} ${order.departureAddress.number}"/></td>
            <td><c:out value="${order.arrivalAddress.street} ${order.arrivalAddress.number}"/></td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../util/addition.jsp" %>

<c:set var="counter" value="0" scope="page"/>
<table id="allCarsTable" class="table table-condensed">
    <tr>
        <th><fmt:message key="number"/></th>
        <th><fmt:message key="driver"/></th>
        <th><fmt:message key="phone.number"/></th>
        <th><fmt:message key="car.number"/></th>
        <th><fmt:message key="car.brand"/></th>
        <th><fmt:message key="car.model"/></th>
        <th><fmt:message key="car.color"/></th>
        <th><fmt:message key="state"/></th>
        <th><fmt:message key="car.type"/></th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td><c:out value="${count}"/></td>
            <td><c:out value="${car.driver.firstName} ${car.driver.lastName}"/></td>
            <td><c:out value="${car.driver.phoneNumber}"/></td>
            <td><c:out value="${car.number}"/></td>
            <td><c:out value="${car.brand}"/></td>
            <td><c:out value="${car.model}"/></td>
            <td id = "color" bgcolor="${car.color}"></td>
            <td>${car.carState}</td>
            <td><fmt:message key="car.type.${car.carType}"/></td>
        </tr>
    </c:forEach>
</table>

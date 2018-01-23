<%@ include file="../util/addition.jsp" %>
<table id = "currentOrderTable" class="table table-bordered">
    <tr>
        <th><fmt:message key="departure.address"/></th>
        <td>${currentOrder.departureAddress.street} ${currentOrder.departureAddress.number}</td>
    </tr>
    <tr>
        <th><fmt:message key="arrival.address"/></th>
        <td>${currentOrder.arrivalAddress.street} ${currentOrder.arrivalAddress.number}</td>
    </tr>
    <tr>
        <th><fmt:message key="driver.name"/></th>
        <td>${currentOrder.driver.firstName} ${currentOrder.driver.lastName}</td>
    </tr>
    <tr>
        <th><fmt:message key="driver.phone.number"/></th>
        <td>${currentOrder.driver.phoneNumber}</td>
    </tr>
    <tr>
        <th><fmt:message key="car"/></th>
        <td>${currentOrder.car.brand} ${currentOrder.car.model} ${currentOrder.car.number}</td>
    </tr>
    <tr>
        <th><fmt:message key="car.color"/></th>
        <th bgcolor="${currentOrder.car.color}"></th>
    </tr>
</table>
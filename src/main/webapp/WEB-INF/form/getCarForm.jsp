<%@ include file="../util/addition.jsp"%>

<form id="getCarForm" action="${pageContext.request.contextPath}/my-car/make_order" method="post">

    <h3><fmt:message key="get.car"/></h3>

    <c:if test="${informationMessage != null}">
        <div class="alert alert-info">${informationMessage}</div>
    </c:if>
    <c:if test="${errorDataMessage != null}">
        <div class="alert alert-danger">${errorDataMessage}</div>
    </c:if>

    <div class="form-group">
        <label for="cityId1"><fmt:message key="city"/><br/></label>
        <select name="city" class="form-control" id="cityId1">
            <option value="Kiev"><fmt:message key="kiev.city"/></option>
        </select>
    </div>

    <div class="form-group">
        <label for="departureStreetId1"><fmt:message key="departure.street"/></label>
        <input name="departureStreet" type="text" class="form-control" id="departureStreetId1" placeholder="<fmt:message key="example"/>: <fmt:message key="address.street.example"/>">
    </div>
    <div class="form-group">
        <label for="departureNumberId1"><fmt:message key="depature.number"/></label>
        <input name="departureNumber" type="text" class="form-control" id="departureNumberId1" placeholder="<fmt:message key="example"/>: <fmt:message key="address.number.example"/>">
    </div>
    <div class="form-group">
        <label for="arrivalStreetId1"><fmt:message key="arrival.street"/></label>
        <input name="arrivalStreet" type="text" class="form-control" id="arrivalStreetId1">
    </div>
    <div class="form-group">
        <label for="arrivaleNumberId1"><fmt:message key="arrival.number"/></label>
        <input name="arrivalNumber" type="text" class="form-control" id="arrivaleNumberId1">
    </div>
    <div class="form-group">
        <label for="selCarType"><fmt:message key="car.type"/><br/></label>
        <select name="carType" class="form-control" id="selCarType">
            <option value="classic"><fmt:message key="car.type.CLASSIC"/></option>
            <option value="premium"><fmt:message key="car.type.PREMIUM"/></option>
        </select>
    </div>
    <button type="submit" class="btn btn-default"><fmt:message key="get.car"/></button>

</form>


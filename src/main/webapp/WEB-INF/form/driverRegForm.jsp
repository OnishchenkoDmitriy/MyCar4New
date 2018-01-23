<%@ include file="../util/addition.jsp" %>

<form id="driverRegistrationForm" action="${pageContext.request.contextPath}/my-car/driver_registration" method="post">

    <br>
    <h3><fmt:message key="driver.registration"/></h3>
    <c:if test="${errorDataMessage != null}">
        <div class="alert alert-danger">${errorDataMessage}</div>
    </c:if>

    <table id="driverRegistrationForm">
        <tr>
            <th>
                <fmt:message key="driver"/>
            </th>
            <th>
                <fmt:message key="car"/>
            </th>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="emailId2"><fmt:message key="email"/></label>
                    <input name="email" type="email" class="form-control" id="emailId2" placeholder="<fmt:message key="example"/>: <fmt:message key="email.example"/>">
                </div>
            </td>
            <td>
                <div class="form-group">
                    <label for="carNumberId"><fmt:message key="car.number"/></label>
                    <input name="carNumber" type="text" class="form-control" id="carNumberId" placeholder="<fmt:message key="example"/>: <fmt:message key="car.number.example"/>">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="nameId2"><fmt:message key="name"/></label>
                    <input name="firstName" type="text" class="form-control" id="nameId2" placeholder="<fmt:message key="example"/>: <fmt:message key="name.example"/>">
                </div>
            </td>
            <td>
                <div class="form-group">
                    <label for="carBrandId"><fmt:message key="car.brand"/></label>
                    <input name="carBrand" type="text" class="form-control" id="carBrandId" placeholder="<fmt:message key="example"/>: <fmt:message key="car.brand.example"/>">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="lastNameId2"><fmt:message key="second.name"/></label>
                    <input name="lastName" type="text" class="form-control" id="lastNameId2" placeholder="<fmt:message key="example"/>: <fmt:message key="second.name.example"/>">
                </div>
            </td>
            <td>
                <div class="form-group">
                    <label for="carModelId"><fmt:message key="car.model"/></label>
                    <input name="carModel" type="text" class="form-control" id="carModelId" placeholder="<fmt:message key="example"/>: <fmt:message key="car.model.example"/>">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="phoneNumberId2"><fmt:message key="phone.number"/></label>
                    <input name="phoneNumber" type="text" class="form-control" id="phoneNumberId2" placeholder="<fmt:message key="example"/>: <fmt:message key="phone.number.example"/>">
                </div>
            </td>
            <td>
                <div class="form-group">
                    <label for="selCarType"><fmt:message key="car.type"/><br/></label>
                    <select name="" class="form-control" id="selCarType">
                        <option value="classic"><fmt:message key="car.type.CLASSIC"/></option>
                        <option value="premium"><fmt:message key="car.type.PREMIUM"/></option>
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="inputPassword1Id2"><fmt:message key="password"/></label>
                    <input name="password" type="password" class="form-control" id="inputPassword1Id2" placeholder="<fmt:message key="password.example"/>">
                </div>
            </td>
            <td>
                <div class="form-group">
                    <label for="selCarColor"><fmt:message key="car.color"/></label><br>
                    <input type="color" id="selCarColor" name="carColor"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="inputPassword2Id2"><fmt:message key="confirm.password"/></label>
                    <input name="password2" type="password" class="form-control" id="inputPassword2Id2" placeholder="<fmt:message key="password.example"/>">
                </div>
            </td>
            <td>
                <button type="submit" class="btn btn-default"><fmt:message key="registration"/></button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/my-car/index_page" role="button"><fmt:message key="back"/></a>
            </td>
        </tr>

    </table>

</form>

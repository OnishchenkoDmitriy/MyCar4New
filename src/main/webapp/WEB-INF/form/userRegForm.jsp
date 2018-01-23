<%@ include file="../util/addition.jsp" %>

<form id="userRegistrationForm" action="${pageContext.request.contextPath}/my-car/user_registration" method="post">

  <h3><fmt:message key="user.registration"/></h3>

  <c:if test="${errorDataMessage != null}">
    <div class="alert alert-danger">${errorDataMessage}</div>
  </c:if>

  <div class="form-group">
    <label for="emailId1"><fmt:message key="email"/></label>
    <input name="email" type="email" class="form-control" id="emailId1" placeholder="<fmt:message key="example"/>: <fmt:message key="email.example"/>">
  </div>
  <div class="form-group">
    <label for="nameId1"><fmt:message key="name"/></label>
    <input name="firstName" type="text" class="form-control" id="nameId1" placeholder="<fmt:message key="example"/>: <fmt:message key="name.example"/>">
  </div>
  <div class="form-group">
    <label for="lastNameId1"><fmt:message key="second.name"/></label>
    <input name="lastName" type="text" class="form-control" id="lastNameId1" placeholder="<fmt:message key="example"/>: <fmt:message key="second.name.example"/>">
  </div>
  <div class="form-group">
    <label for="phoneNumberId1"><fmt:message key="phone.number"/></label>
    <input name="phoneNumber" type="text" class="form-control" id="phoneNumberId1" placeholder="<fmt:message key="example"/>: <fmt:message key="phone.number.example"/>">
  </div>
  <div class="form-group">
    <label for="inputPassword1Id1"><fmt:message key="password"/></label>
    <input name="password" type="password" class="form-control" id="inputPassword1Id1" placeholder="<fmt:message key="password.example"/>">
  </div>
  <div class="form-group">
    <label for="inputPassword2Id1"><fmt:message key="confirm.password"/></label>
    <input name="password2" type="password" class="form-control" id="inputPassword2Id1" placeholder="<fmt:message key="password.example"/>">
  </div>

  <button type="submit" class="btn btn-default"><fmt:message key="registration"/></button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/my-car/index_page" role="button"><fmt:message key="back"/></a>
</form>

<%@ include file="../util/addition.jsp"%>

<form class="form-horizontal" id = "signInForm" action="${pageContext.request.contextPath}/my-car/login" method="post">
    <c:if test="${informationMessage eq not null}">
        <div class="alert alert-info">${informationMessage}</div>
    </c:if>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <div class="form-group">
        <label for="emailId3" class="col-sm-2 control-label"><fmt:message key="email"/></label>
        <div class="col-sm-10">
            <input name="email" type="email" class="form-control" id="emailId3" placeholder="<fmt:message key="email"/>">
        </div>
    </div>
    <div class="form-group">
        <label for="passwordId3" class="col-sm-2 control-label"><fmt:message key="password"/></label>
        <div class="col-sm-10">
            <input name="password" type="password" class="form-control" id="passwordId3" placeholder="<fmt:message key="password"/>">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default"><fmt:message key="login"/></button>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/my-car/user_registration_page" role="button"><fmt:message key="registration"/></a>
        </div>
    </div>
</form>
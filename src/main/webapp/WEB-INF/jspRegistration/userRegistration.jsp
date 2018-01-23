<%@ include file="../util/addition.jsp"%>
<jsp:include page="../util/head.jsp"/>
<jsp:include page="../util/nav.jsp"/>

<section class="py-5">
    <div class="container">

        <ul class="nav nav-tabs">
            <li role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/my-car/user_registration_page"><fmt:message key="user.registration"/></a></li>
            <li role="presentation"><a class="nav-link"  href="${pageContext.request.contextPath}/my-car/driver_registration_page"><fmt:message key="driver.registration"/></a></li>
        </ul>

        <jsp:include page="../form/userRegForm.jsp"/>
    </div>
</section>

<jsp:include page="../util/footer.jsp"/>

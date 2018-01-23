<%@ include file="util/addition.jsp"%>
<jsp:include page="util/head.jsp"/>
<jsp:include page="util/nav.jsp"/>
<jsp:include page="util/header.jsp"/>
<%@ include file="form/loginForm.jsp"%>

<section class="py-5">
    <div class="container">
        <h1><fmt:message key="welcome.to.mycar"/></h1>
        <p><fmt:message key="my.car.service.welcome.text"/></p>
    </div>
</section>
<section class="py-5">
    <div class="container">
        <h1><fmt:message key="our.discounts"/></h1>
        <p><fmt:message key="my.car.service.discount.text"/></p>
    </div>
</section>

<jsp:include page="util/footer.jsp"/>



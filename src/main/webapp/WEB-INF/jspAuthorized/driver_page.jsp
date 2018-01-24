<%@ include file="../util/addition.jsp"%>
<jsp:include page="../util/head.jsp"/>
<jsp:include page="../util/nav.jsp"/>
<jsp:include page="../util/driverNavigation.jsp"/>

<div class="text-center" class="text-to-uppercase" class="text-uppercase" style="font-size: 26px">
    <fmt:message key="welcome.to.my.car.for.driver"/>
</div>

<section class="py-5 bg-image-full" style="background-image: url('../../resources/img/driver.jpg'); margin: 5%">
    <c:if test="${informationMessage eq not null}">
        <div class="alert alert-info">${informationMessage}</div>
    </c:if>
    <div style="height:500px;"></div>
</section>

<jsp:include page="../util/footer.jsp"/>

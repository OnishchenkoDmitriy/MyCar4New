<%@ include file="util/addition.jsp"%>
<jsp:include page="util/head.jsp"/>
<jsp:include page="util/nav.jsp"/>

<section class="py-5 bg-image-full">

    <div class="alert alert-danger" style="height: 200px; width: 100%"><fmt:message key="oops.something.wrong"/></div>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/my-car/index_page" role="button" style="margin-left: 5%">
        <fmt:message key="back"/></a>

    <c:if test="${informationMessage eq not null}">
        <div class="alert alert-info">${informationMessage}</div>
    </c:if>

    <div style="height: 100px;"></div>
</section>

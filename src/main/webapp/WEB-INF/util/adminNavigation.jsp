<%@ include file="addition.jsp" %>

<nav class="nav">
    <ul class="nav nav-tabs">
        <li role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/my-car/index_page"><fmt:message key="get.car"/></a></li>
        <li role="presentation"><a class="nav-link"  href="${pageContext.request.contextPath}/my-car/my_orders"><fmt:message key="my.orders"/></a></li>
        <li role="presentation"><a class="nav-link"  href="${pageContext.request.contextPath}/my-car/all_users"><fmt:message key="all.users"/></a></li>
        <li role="presentation"><a class="nav-link"  href="${pageContext.request.contextPath}/my-car/all_cars"><fmt:message key="all.cars"/></a></li>
        <li role="presentation"><a class="nav-link"  href="${pageContext.request.contextPath}/my-car/my_profile_page"><fmt:message key="my.profile"/></a></li>
    </ul>
</nav>

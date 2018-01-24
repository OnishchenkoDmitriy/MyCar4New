<%@ include file="../util/addition.jsp" %>
<jsp:include page="../util/head.jsp"/>
<jsp:include page="../util/nav.jsp"/>


<c:if test="${role eq 'CLIENT'}">
    <jsp:include page="../util/userNavigation.jsp"/>
</c:if>
<c:if test="${role eq 'DRIVER'}">
    <jsp:include page="../util/driverNavigation.jsp"/>
</c:if>
<c:if test="${role eq 'ADMIN'}">
    <jsp:include page="../util/adminNavigation.jsp"/>
</c:if>

<c:if test="${role eq 'CLIENT' or role eq 'ADMIN'}">
    <jsp:include page="../table/myOrderTableForUser.jsp"/>
</c:if>
<c:if test="${role eq 'DRIVER'}">
    <jsp:include page="../table/myOrderTableForDriver.jsp"/>
</c:if>

<jsp:include page="../util/footer.jsp"/>

<%@ include file="../util/addition.jsp"%>
<jsp:include page="../util/head.jsp"/>
<jsp:include page="../util/nav.jsp"/>
<jsp:include page="../util/adminNavigation.jsp"/>
<jsp:include page="../table/allCarsTable.jsp"/>

<c:if test="${informationMessage eq not null}">
    <div class="alert alert-info">${informationMessage}</div>
</c:if>

<jsp:include page="../util/footer.jsp"/>

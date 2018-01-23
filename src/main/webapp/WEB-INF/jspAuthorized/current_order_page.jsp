<%@ include file="../util/addition.jsp"%>
<jsp:include page="../util/head.jsp"/>
<jsp:include page="../util/nav.jsp"/>
<c:if test="${role eq 'CLIENT'}">
    <jsp:include page="../util/userNavigation.jsp"/>
</c:if>
<c:if test="${role eq 'ADMIN'}">
    <jsp:include page="../util/adminNavigation.jsp"/>
</c:if>
<section class="py-5">
    <div class="container">
        <h3>
            <fmt:message key="order.accepted"/> ${currentOrder.price / 100} grn.
        </h3>
        <jsp:include page="../table/currentOrderTable.jsp"/>
        <br/>

        <a class="btn btn-default" href="${pageContext.request.contextPath}/my-car/confirm_order" role="button"><fmt:message key="confirm.order"/></a>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/my-car/cancel_order" role="button"><fmt:message key="cancel.order"/></a>
    </div>
</section>
<jsp:include page="../util/footer.jsp"/>


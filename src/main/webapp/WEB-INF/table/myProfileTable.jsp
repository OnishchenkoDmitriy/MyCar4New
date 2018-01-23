<%@ include file="../util/addition.jsp"%>

<table class="table table-condensed" id = "myProfileTable" style="margin: 5%">
    <tr>
        <td><fmt:message key="name"/></td>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <td><fmt:message key="second.name"/></td>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <td><fmt:message key="email"/></td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td><fmt:message key="phone.number"/></td>
        <td>${user.phoneNumber}</td>
    </tr>
    <tr>
        <td><fmt:message key="role"/></td>
        <td>${user.role}</td>
    </tr>
</table>
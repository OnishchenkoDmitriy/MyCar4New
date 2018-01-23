<%@ include file="util/addition.jsp"%>
<jsp:include page="util/head.jsp"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/my-car/index_page"><fmt:message
                            key="home"/>

                    </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/my-car/contact_page"><fmt:message
                            key="contact"/>
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <c:if test="${isLanguage eq null}">
                        <form>
                            <select class="nav-select" id="language" name="language" onchange="submit()">
                                <option value="en_US" ${language == 'en_US' ? 'selected' : ''}><fmt:message key="language.en"/></option>
                                <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}><fmt:message key="language.ru"/></option>
                            </select>
                        </form>
                    </c:if>
                </li>
                <li class="nav-item" style="margin-left: 20px;">
                    <p class="navbar-text">${userName}</p>
                </li>
                <li class="nav-item">
                    <c:if test="${logOut == true}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/my-car/logout"><fmt:message
                                key="logout"/></a>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
</nav>

<section class="py-5 bg-image-full">
    <div id = "contact_information">
        <h2><fmt:message key="contact"/></h2>
        <table id = contactTable>
            <tr>
                <td><fmt:message key="developer"/>: </td>
                <td><fmt:message key="developer.name"/></td>
            </tr>
            <tr>
                <td><fmt:message key="phone.number"/>: </td>
                <td><fmt:message key="phone.number.value"/></td>
            </tr>
            <tr>
                <td><fmt:message key="email"/>  </td>
                <td><fmt:message key="email.value"/></td>
            </tr>
        </table>
        <br>
        <h2><fmt:message key="about.project"/>:</h2>
        <p id = "aboutProject">
            <fmt:message key="about.project.text"/>
        </p>
    </div>
    <div style="height: 40px;"></div>
</section>

<jsp:include page="util/footer.jsp"/>
<%@ include file="addition.jsp"%>
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
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/my-car/contact_page"><fmt:message
                            key="contact"/></a>
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
                    <p class="navbar-text" style="color: lightskyblue;">${userName}</p>
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
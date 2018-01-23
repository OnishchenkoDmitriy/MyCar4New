<%@ include file="addition.jsp" %>

<!DOCTYPE html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MyCar</title>

    <style type="text/css">
        <jsp:include page="../../resources/css/bootstrap.min.css"/>
        <jsp:include page="../../resources/css/style.css"/>
    </style>

    <script>
        if (${registrationIsSuccessful eq true}) {
            alert("<fmt:message key="registration.is.successful"/>");
        }
    </script>

</head>

<body>



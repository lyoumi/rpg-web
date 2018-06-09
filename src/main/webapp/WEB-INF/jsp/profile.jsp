<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/23/17
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CrossWorld</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <link href="/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <link href="/resources/css/jumbotron-narrow.css" rel="stylesheet">

    <script src="/resources/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->
    <script src="/resources/js/ie-emulation-modes-warning.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<form action="<c:url value="profile" />" method="POST">

    <div class="container">
        <div class="header clearfix">
            <nav>
                <ul class="nav nav-pills pull-right">
                    <li role="presentation" class="active"><a href="/menu">Home</a></li>
                    <sec:authorize access="isAuthenticated()">
                        <li role="presentation"><a href="/profile">Profile</a></li>
                        <li role="presentation"><a href="/logout">Logout</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li role="presentation"><a href="/admin/menu">Admin menu</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <li role="presentation"><a href="/login">Login</a></li>
                    </sec:authorize>
                </ul>
            </nav>
            <h3 class="text-muted">CROSS(X)WORLD</h3>
        </div>


        <div class="jumbotron">

            <form:form modelAttribute="account">
                <sec:csrfInput/>
                <fieldset>
                    <h1>
                        Login: ${account.username}
                    </h1>
                    <h2>
                        Name: ${account.userInfo.name}
                    </h2>
                    <h2>
                        Surname: ${account.userInfo.surName}
                    </h2>
                    <h2>
                        Email: ${account.userInfo.email}
                    </h2>
                    <h2>
                        Role: ${account.role}
                    </h2>
                </fieldset>
            </form:form>
        </div>


    </div>


</form>
<script src="/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

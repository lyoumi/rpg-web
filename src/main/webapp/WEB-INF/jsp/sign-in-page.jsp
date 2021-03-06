<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/26/17
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <sec:csrfInput/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>SignIn</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%--<link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">--%>

    <!-- Custom styles for this template -->
    <link href="/resources/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/resources/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/resources/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>


<div class="container">


    <form action="<c:url value="login" />" method="POST" class="form-signin">
        <form:form modelAttribute="userData">
            <sec:csrfInput/>
            <h2 class="form-signin-heading">Please sign in</h2>
            <form:input path="id" style="display: none;"/>
            <label for="username" class="sr-only">Login</label>
            <form:input path="username" type="text" id="username" name="username" class="form-control" placeholder="username"/>
            <label for="password" class="sr-only">Password</label>
            <form:input path="password"  type="password" id="password" name="password" class="form-control" placeholder="password"/>
            <label for="role" class="sr-only">Password</label>
            <form:input path="role" type="text" class="form-control" style="display:none;" placeholder="role"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="SignIn">Sign in</button>
            <p><a class="btn btn-lg btn-primary btn-block" role="button" type="submit" href="/signup">Sign up</a></p>
        </form:form>
    </form>
</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

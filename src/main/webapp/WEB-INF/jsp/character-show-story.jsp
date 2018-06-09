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
    <sec:csrfInput/>
    <sec:csrfMetaTags/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Game</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <link href="/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <%--<link href="/resources/css/jumbotron-narrow.css" rel="stylesheet">--%>

    <script src="/resources/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->
    <script src="/resources/js/ie-emulation-modes-warning.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

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

<div class="container">

    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            get_fb();

            function get_fb() {
                var feedback = $.ajax({
                    url: "get",
                    headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
                    dataType: "text",
                    timeout: 3000,
                    error: function () {
                        //do something
                    },
                    success: function (result) {
                        r = JSON.parse(result);

                        document.getElementById('generalInfo').innerHTML = "Name: " + r.name + ", " + r.level + "lvl";
                        document.getElementById('hp').innerHTML = r.hitPoints;
                        document.getElementById('mp').innerHTML = r.manaPoints;
                        if (r.currentAction != null) {
                            document.getElementById('characterStory').innerHTML = r.currentAction;
                        }
                        document.getElementById('exp').innerHTML = r.expToNextLevel;
                        if (r.targetCity != null) {
                            document.getElementById('city').innerHTML = "Current city: " + r.targetCity.name;
                        } else if (r.currentCity != null) {
                            document.getElementById('city').innerHTML = "Goes to city: " + r.currentCity.name;
                        } else {
                            document.getElementById('city').innerHTML = "Thinks where to go";
                        }
                        if (r.quest != null) {
                            document.getElementById('quest').innerHTML = r.quest.title;
                        }

                        console.log("Success!")
                        setTimeout(function () {
                            get_fb();
                        }, 3000);
                    }
                });
            }

            $('#healhp').click(function () {
                var saveData = $.ajax({
                    type: 'POST',
                    url: "healhp",
                    headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
                    data: null,
                    dataType: "text",
                    errors: function (error) {
                        console.log(error);
                    },
                    success: function (result) {
                        document.getElementById('hp').innerHTML = result;
                    }
                });
            })
            $('#healmp').click(function () {
                var saveData = $.ajax({
                    type: 'POST',
                    url: "healmp",
                    headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
                    data: null,
                    dataType: "text",
                    success: function (result) {
                        document.getElementById('mp').innerHTML = result;
                        console.log("Save Complete")
                    }
                });
            })
            $('#damage').click(function () {
                var saveData = $.ajax({
                    type: 'POST',
                    url: "skill",
                    headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
                    data: null,
                    dataType: "text",
                    success: function (result) {
                        r = JSON.parse(result);

                        document.getElementById('hp').innerHTML = r.hitPoints;
                        document.getElementById('mp').innerHTML = r.manaPoints;
                        if (r.currentAction != null) {
                            document.getElementById('characterStory').innerHTML = r.currentAction;
                        }

                        console.log("Save Complete")
                    }
                });
            })
        })
    </script>

    <div class="jumbotron">

        <form>
            <h1>
                <label id="generalInfo"/>
            </h1>
            <h2>
                Now: <label id="characterStory"/>
            </h2>
            <p>Hit Points: <label id="hp"/></p>
            <p>Mana Points: <label id="mp"/></p>
            <p>Exp to NextLevel: <label id="exp"/></p>
            <p><label id="city"/></p>
            <p>Equipment:
            <ul>
                <c:forEach items="${character.items}" var="item">
                    <li>${item.slot}: ${item.name}</li>
                </c:forEach>
            </ul>
            <p>Quest: <label id="quest"/></p>
        </form>
    </div>
    <button id="healhp" name="healhp" class="btn btn-lg btn-success" role="button">Heal HP</button>
    <button id="damage" name="damage" class="btn btn-lg btn-success" role="button">Damage</button>
    <button id="healmp" name="healmp" class="btn btn-lg btn-success" role="button">Heal MP</button>


</div>

<script src="/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

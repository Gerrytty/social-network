<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><@spring.message "auth.page.title"/></title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="/static/css/auth.css" rel="stylesheet">

</head>

<body>

<div class="row">

    <br>

</div>

<div class="row">

    <div class="col"></div>
    <div class="col"></div>

    <div class="col">

        <div class="container">

            <form class="form-signin" role="form" action="/auth/process" method="post">

                <h2 class="form-signin-heading"><@spring.message "auth.page.title"/></h2> <br>

                <input type="email" name="email" class="form-control" placeholder="<@spring.message "auth.page.message.email"/>" required> </br>
                <input type="password" name="password" class="form-control" placeholder="<@spring.message "auth.page.message.password"/>" required> </br>
                <input type="checkbox" name="remember-me"> <@spring.message "remember-me"/> <br><br>
                <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
                <input type="submit"  class="btn btn-lg btn-dark btn-block" value=<@spring.message "auth.page.sign-in"/>>

            </form>

            <br>
            <button class="btn btn-lg btn-dark btn-block" onclick="location.href='/reg'"><@spring.message "reg.page.title"/></button>

        </div>

    </div>

    <div class="col"></div>
    <div class="col"></div>

</div>

</body>
</html>
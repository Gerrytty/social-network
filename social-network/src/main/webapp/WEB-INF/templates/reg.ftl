<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message "reg.page.title"/></title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="../static/css/reg.css" rel="stylesheet">

    <style>
        .error {
            color: #ff0000;
        }
    </style>

</head>
<body>

<br>
<header>

    <div class="row">

        <div class="col"></div>
        <div class="col"></div>
        <div class="col">
            <h2><@spring.message "reg.page.title"/></h2>

        </div>
        <div class="col">
        </div>
        <div class="col"></div>

    </div>

</header>

<br>

    <div class="row">

        <div class="col"></div>
        <div class="col"></div>
        <div class="col">

            <div>
                <@spring.bind "registrationDto"/>
                <form class="form-signup" action="/reg" method="post">

                    <@spring.message "firstName"/>: <br>
                    <@spring.formInput "registrationDto.firstName"/> <br>
                    <@spring.showErrors "<br>", "error"/>
                    <br><@spring.message "secondName"/>: <br>
                    <@spring.formInput "registrationDto.secondName"/> <br>
                    <@spring.showErrors "<br>", "error"/>
                    <br><@spring.message "email"/>: <br>
                    <@spring.formInput "registrationDto.email"/> <br>
                    <@spring.showErrors "<br>", "error"/>
                    <br><@spring.message "password"/>: <br>
                    <@spring.formPasswordInput "registrationDto.password"/> <br>
                    <@spring.showErrors "<br>","error"/> <br>
                    <br>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input class="btn btn-lg btn-dark btn-block" type="submit" name="submit" id="submit"
                           value=<@spring.message "reg.page.sign-up"/>>
                </form>
            </div>

        </div>
        <div class="col">

        </div>

        <div class="col"></div>

    </div>

</body>
</html>
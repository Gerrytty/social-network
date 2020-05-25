<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>

    <style>
        .error {
            color: #ff0000;
        }
    </style>

</head>
<body>

<#--<form method="post" action="reg">-->

<#--    <input type="text" name="firstName" placeholder="First name..."> </br> </br>-->
<#--    <input type="text" name="secondName" placeholder="Second name..."> </br> </br>-->
<#--    <input type="email" name="email" placeholder="Email..."> </br> </br>-->
<#--    <input type="password" name="password" placeholder="Password..."> </br> </br>-->
<#--    <input type="submit" placeholder="Register">-->

    <div>
        <@spring.bind "registrationDto"/>
        <form class="form-signup" action="reg" method="post">

            First name: <br>
            <@spring.formInput "registrationDto.firstName"/>
            <@spring.showErrors "<br>", "error"/>
            <br>Second name: <br>
            <@spring.formInput "registrationDto.secondName"/>
            <@spring.showErrors "<br>", "error"/>
            <br>Email: <br>
            <@spring.formInput "registrationDto.email"/>
            <@spring.showErrors "<br>", "error"/>
            <br>Password: <br>
            <@spring.formPasswordInput "registrationDto.password"/>
            <@spring.showErrors "<br>","error"/> <br>
            <br><br>
            <input class="btn btn-lg btn-dark btn-block" type="submit" name="submit" id="submit" value="Sign up">
        </form>
    </div>


<#--</form>-->

</body>
</html>
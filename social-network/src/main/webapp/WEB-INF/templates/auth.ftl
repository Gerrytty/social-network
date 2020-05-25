<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Authentication</title>
</head>
<body>

<form method="post" action="/auth/process">

    <input type="email" name="email" placeholder="Enter your email..."> </br> </br>
    <input type="password" name="password" placeholder="Enter your password..."></br> </br>
    <input type="checkbox" name="remember-me"> Remember me <br><br>
    <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
    <input type="submit" placeholder="Sign in">

</form>

<br>
<button onclick="location.href='/reg'">Registration</button>

</body>
</html>
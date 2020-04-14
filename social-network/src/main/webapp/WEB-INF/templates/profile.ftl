<#ftl encoding="utf-8">

<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>${user.firstName} ${user.secondName}</title>

    <script>
        function previewFile() {
            var preview = document.getElementById('photo');
            var file    = document.querySelector('input[type=file]').files[0];
            var reader  = new FileReader();
            reader.onloadend = function () {
                preview.src = reader.result;
            };
            if (file) {
                reader.readAsDataURL(file);
            } else {
                preview.src = "";
            }
        }
    </script>

</head>
<body>

<br>

<div class="row">

</div>

<div class="row">

    <div class="col">

    </div>

    <div class="col-sm">

        <a href="/profile?id=${authUserId}">My profile</a> <br>
        <a href="">Fied</a> <br>
        <a href="/chat">Forum</a> <br>
        <a href="/users">Users</a> <br>
        <a href="">Photos</a> <br>
        <a href="">Settings</a> <br>
        <a href="/logout">Logout</a> <br>

    </div>


    <div class="col">
<#--        <img id="photo" src="/static/ava.jpg" alt="Ava" width="220" height="220"/>-->

        <img id="photo" src="${user.pathToAva}" alt="Ava" width="220" height="220"/>

        <#if authUserId == id>

            <form method="post" action="/avaUpload" enctype="multipart/form-data">

                <input type="file" id="file" name="photo" multiple accept="image/*,image/jpeg" onchange="previewFile()">
                <input type="submit" value="add">

            </form>

        </#if>

    </div>



    <div class="col">

    </div>

    <div class="col">

        <div class="container">

            <div class="row">
                <h3>${user.firstName} ${user.secondName}</h3>
            </div>

            <div class="row">
                <p>Birth date: birthDate</p> <br>
            </div>

            <div class="row">
                <p>Town: town</p> <br>
            </div>

            <div class="row">
                <#if authUserId != id>
                    <a href="">Add as Friend</a>
                </#if>
            </div>

        </div>

    </div>

    <div class="col">

    </div>

</div>

<br><br><br><br>

<div class="row">

    <div class="col">

    </div>

    <div class="col">
        <h1>Stena</h1>
    </div>

    <div class="col">

    </div>

</div>

<br>

<div class="row">
    <div class="col">

    </div>

    <div class="col">

        <form method="post" action="/addPost">

            <input type="text" name="text" placeholder="What do you want to post?"> <br>
            <input type="submit" value="Add">

        </form>

    </div>

    <div class="col">

    </div>
</div>

<br>

<#list posts as post>

    <div class="row">

        <div class="col"></div>

        <div class="col">

            <div class="container">

                <p>${post.date}</p>

                <p>${post.text}</p>

            </div>

        </div>

        <div class="col"></div>

    </div>

    <hr style="color: black"><br>

</#list>

</body>
</html>
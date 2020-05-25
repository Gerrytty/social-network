<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chat</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <script>

        function sendMessage(reciverId, text) {
            var body = {
                reciverId: reciverId,
                text: text
            };

            $.ajax({
                url: "/messages",
                method: "POST",
                data: JSON.stringify(body),
                contentType: "application/json",
                dataType: "json",
                complete: function () {
                    if (text === 'Login') {
                        receiveMessage(reciverId)
                    }
                }
            });
        }

        // LONG POLLING
        function receiveMessage(reciverId) {
            $.ajax({
                url: "/messages?reciverId=" + reciverId,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
                    receiveMessage(reciverId);
                }
            })
        }

    </script>

</head>
<body onload="sendMessage(${senderId}, 'Login')">
<h1>You'r id - ${senderId}</h1>
<div>
    <input id="message" placeholder="Your message">
    <button onclick="sendMessage('${reciverId}',
            $('#message').val())">Send</button>
</div>
<div>
    <ul id="messages">

    </ul>
</div>

<#list allMessages as message>

    <div class="row">

        <div class="col"></div>

        <div class="col">

            <div class="container">

                <p>${message.text}</p>

            </div>

        </div>

        <div class="col"></div>

    </div>

    <hr style="color: black"><br>

</#list>

</body>
</html>
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
    <script src="../static/chat.js"></script>

    <script>

        function sendMessage(pageId, text) {
            var body = {
                pageId: pageId,
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
                        receiveMessage(pageId)
                    }
                }
            });
        }

        // LONG POLLING
        function receiveMessage(pageId) {
            $.ajax({
                url: "/messages?pageId=" + pageId,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
                    receiveMessage(pageId);
                }
            })
        }

    </script>

</head>
<body onload="sendMessage('${pageId}', 'Login')">
<h1>You'r id - ${pageId}</h1>
<div>
    <input id="message" placeholder="Your message">
    <button onclick="sendMessage('${pageId}',
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
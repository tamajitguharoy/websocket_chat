<!DOCTYPE html>
<html>
<head>
<title>Testing websockets</title>
</head>
<body>
    <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    	<div>
		<input id="toUser" type="text" />
	</div>
	<div>
		<input id="inputmessage" type="text" />
	</div>
	<div>
		<input type="submit" value="Send message" onclick="send()" />
	</div>
	<div id="messages"></div>
	<script type="text/javascript">
		var webSocket = 
			new WebSocket('ws://192.168.1.115:8080/websocket4/websocket');

		webSocket.onerror = function(event) {
			onError(event)
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		function onMessage(event) {
			document.getElementById('messages').innerHTML 
				+= '<br />&nbsp;&nbsp;&nbsp;: ' + event.data;
		}

		function onOpen(event) {
			document.getElementById('messages').innerHTML 
				= 'Connection established';
		}

		function onError(event) {
			alert(event.data);
		}

		function send() {
			var txt = document.getElementById('inputmessage').value;
                       
			webSocket.send($("#toUser").val()+";;;"+txt);
			return false;
		}
	</script>
</body>
</html>
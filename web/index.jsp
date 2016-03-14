<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div
		style="width: 500px; height: 200px; border: 1px solid black; overflow-y: scroll;">
		<ul id="chatMessageContainer">
		</ul>
	</div>

	<input id="messageInput" style="width:500px;"type="text" placeholder="Entrez votre pseudo">
	<!-- <input id="messageInput" type="text" placeholder="Entrez votre message..."> -->
</body>
<script type="text/javascript">
	var connectionChat = new WebSocket("ws://" + location.host
			+ "/Tchat/testSocket");
	document.getElementById("messageInput").addEventListener("keydown",
			function(e) {
				if (e.keyCode === 13 && this.value !== "") {
					connectionChat.send(this.value);
					if (this.placeholder === "Entrez votre pseudo") {
						this.placeholder = "Entrer votre message...";
					}
					this.value = "";
				}
			});
	connectionChat.onmessage = function(event) {
		var li = document.createElement("li");
		li.innerHTML = event.data;
		li.setAttribute("style","color: #7C6F6F; font-size: 70%;margin: 5px;");
		document.getElementById("chatMessageContainer").appendChild(li);
	}
</script>
</html>
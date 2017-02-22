<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {

	});
</script>

<script type="text/javascript">
	var socket;
	if (!window.WebSocket) {
		window.WebSocket = window.MozWebSocket;
	}
	if (window.WebSocket) {
		socket = new WebSocket("ws://localhost:8086/websocket");
		socket.onmessage = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = ta.value + '\n' + event.data
		};
		socket.onopen = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = "Web Socket已打开,开始你的互动之旅吧!";
		};
		socket.onclose = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = ta.value + "无法打开Web Socket连接";
		};
		socket.onerror = function(event) {
			console.log("发生错误 uid: " + event.target.uid);
		};
	} else {
		alert("你的浏览器还不支持Web Socket.请下载以下之一试之：\n\n\nSafari 5+ \nChrome 6-19\nFirefox 7+  ");
	}

	function send(message) {
		if (!window.WebSocket) {
			return;
		}
		if (socket.readyState == WebSocket.OPEN) {
			socket.send(message);
		} else {
			alert("socket还没有打开或已关闭，请刷新当前页面");
		}
	}
</script>
<div class="pageContent">
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/user/save" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="text" name="message" value="您好，欢迎使用诺德兰电子点餐系统!" />
		<br />
		<input type="button" value="发送数据" onclick="send(this.form.message.value)" />
		<h3>Output</h3>
		<textarea id="responseText" style="width: 500px; height:300px;"></textarea>
		<br />
		<input type="button" onclick="$('#responseText').val('');" value="清理" />
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form:form>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/statics/saas/inc/head.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function enterEnvent() { //相应回车事件
		var key = window.event.keyCode;
		if (key == 0xD) {
			document.forms[0].submit();
		}
	}
	function login() {
		if (document.getElementById("remember").checked) {
			SetCookie(document.getElementById("username").value, document
					.getElementById("password").value);
		} else {
			DelCookie();
		}
		document.forms[0].submit();
	}
	function GetCookie(name) {
		var arg = name + "=";
		var alen = arg.length;
		var clen = window.document.cookie.length;
		var i = 0;
		while (i < clen) {
			var j = i + alen;
			if (window.document.cookie.substring(i, j) == arg)
				return getCookieVal(j);
			i = window.document.cookie.indexOf(" ", i) + 1;
			if (i == 0)
				break;
		}
		return null;
	}
	function getCookieVal(offset) {
		var endstr = window.document.cookie.indexOf(";", offset);
		if (endstr == -1)
			endstr = window.document.cookie.length;
		return unescape(window.document.cookie.substring(offset, endstr));
	}
	function SetCookie(name, value) {
		var exp = new Date();
		exp.setTime(exp.getTime() + (30 * 24 * 60 * 60 * 1000));
		window.document.cookie = name + "=" + escape(value) + "; expires="
				+ exp.toGMTString() + ";path=/";
	}
	function DeleteCookie(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 100);
		var cval = GetCookie(name);
		window.document.cookie = name + "=" + cval + "; expires="
				+ exp.toGMTString() + ";path=/";
	}

	function DelCookie() {
		DeleteCookie(document.getElementById("username").value);
	}
	function remember1() {
		if (document.getElementById("remember").checked) {
			SetCookie(document.getElementById("username").value, document
					.getElementById("password").value);
			alert("Saved!");
		}
	}
	function showpassword() {
		var p = GetCookie(document.getElementById("username").value);
		if (p != null)
			document.getElementById("password").value = p;
	}
</script>
</head>
<body>
	<div class="header_warp">
		<div class="header">
			<h1>
				<span>聚客宝云平台</span>
			</h1>
			<div class="login_list">
				<a>欢迎使用聚客宝云平台！</a>
			</div>
		</div>
	</div>
	<div class="login_body">
		<div class="login_box">
			<form action="<%=contextPath%>/doLogin" method="post">
				<p>
					<strong>用户名</strong><span><input name="username"
						id="username" type="text" onkeydown="enterEnvent();"
						onblur="showpassword()" id="textfield" type="text"
						class="form-control input" placeholder="用户名" value="ipple1986"></span>
				</p>
				<p class="longin_ppp">
					<strong>密码</strong><span><input name="password"
						id="password" type="password" onkeydown="enterEnvent();"
						id="textfield2" class="form-control" placeholder="密　码"  value="ipple1986"></span>
				</p>
				<p class="longin_ppp">
					<label><input name="remember" id="remember" type="checkbox"
						value="" checked="true">&nbsp;记住密码</label>
				</p>
				<input type="button" onclick="login();" value="登录" class="login_btn">

				<div align="center">${errorMsg}</div>
			</form>
		</div>
	</div>
	<div class="login_footer">&copy;广州聚房宝网络科技有限公司&nbsp;|&nbsp;商务合作热线：400-066-3988</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WORK FLOW</title>
<link href="./css/reset.css" rel="stylesheet" type="text/css">
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
	<%
		String id = (String)session.getAttribute("id");
		if(id!=null){
			response.sendRedirect("./Main.me");
		}
	%>
	<body>
		<!-- 헤더영역 -->

			<div id="loginBox">
			<h2>WORK FLOW</h2>
			<!-- 회원가입을 할 때는 itwill_member 테이블을 사용할 거야. -->
			<fieldset>
				<form action="./MemberLoginAction.me" method="post">
					<input type="text" name="id" placeholder="id"><br>
					<input type="text" name="pw" placeholder="password"><br>
					<input type="submit" value="로그인" class="submit"><br>
					<input type="button" value="회원가입" class="button" onclick="location.href='./MemberJoin.me'">
				</form>
				
			</fieldset>
		</div>
</body>
</html>
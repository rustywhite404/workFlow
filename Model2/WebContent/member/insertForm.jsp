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

	<body>

			<div id="joinBox">
			<h2>회원가입</h2>
			<hr>
			<!-- 회원가입을 할 때는 itwill_member 테이블을 사용할 거야. -->
			<fieldset>
				<form action="./MemberJoinAction.me" method="post">
					<input type="text" name="id" placeholder="id"><br>
					<input type="text" name="pw" placeholder="password"><br>
					<input type="text" name="name" placeholder="name"><br>
					<input type="text" name="age" placeholder="age"><br>
					남: <input type="radio" name="gender" value="man"> 여: <input type="radio" name="gender" value="woman"><br>
					<input type="text" name="email" placeholder="email"><br>
					<input type="text" name="grade" placeholder="grade"><br>
					<input type="submit" value="회원가입" class="submit"><br>
					<input type="button" value="로그인" onclick="location.href='./MemberLogin.me'">
				</form>
				
			</fieldset>
		</div>

</body>
</html>
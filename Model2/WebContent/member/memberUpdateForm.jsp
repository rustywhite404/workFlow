<%@page import="net.ivyro.zian.member.db.MemberDTO"%>
<%@page import="java.util.Calendar"%>
<%@page import="net.ivyro.zian.member.db.MemberDAO"%>
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
		// 로그인 없이 접근할 경우 로그인 페이지로 이동 처리
		if(id==null){
			response.sendRedirect("./MemberLogin.me");
		}
		
		// 아이디에 해당하는 정보 불러오기
		// MemberDAO 객체를 생성해서 SelectInfo(id)를 사용할 수도 있지만
		// 그렇게 하지 않고 MemberUpdateAction에서 저장한 회원정보를 받아와서 사용해보자.
		// request.setAttribute("mdto", mdto); 로 저장해뒀었어.

		MemberDTO mdto = (MemberDTO)request.getAttribute("mdto");		
		System.out.println("[액션 페이지에서 뷰 페이지로 정보를 전달 받았습니다.]");
		System.out.println("[전달받은 정보 :"+mdto+"]");
		
	%>
	<body>
		<!-- 헤더영역 -->
		<jsp:include page="../inc/header.jsp"/>
		<!-- 헤더영역 -->
		
		<div id="content">
			<div id="main_container">
			
			<h2><%=mdto.getName()%>님의 회원 정보</h2>
			<hr>
			<div class="userInfo">
				<form action="./MemberUpdatePro.me" method="post">
					ID :<br>
					<input type="text" name="id" value="<%=mdto.getId() %>"><br>
					비밀번호 :<br>
					<input type="password" name="pw"><br>
					이름 :<br> 
					<input type="text" name="name" value="<%=mdto.getName() %>"><br>
					직급 :<br> 
					<input type="text" name="grade" value="<%=mdto.getGrade() %>"><br>
					성별 : 남 <input type="radio" name="gender" value="man" 
					<%
					if(mdto.getGender().equals("man")){
					%>
					checked
					<% 
					}
					%>
					> 여: <input type="radio" name="gender" value="woman" 
					<%
					if(mdto.getGender().equals("woman")){
					%>
					checked
					<%
					}
					%>
					>
					<br>
					나이 : <br>
					<input type="text" name="age" value="<%=mdto.getAge() %>"><br>
					이메일 : <br>
					<input type="text" name="email" value="<%=mdto.getEmail() %>"><br>
					<input type="submit" value="수정하기" class="submit">
				</form>
			</div>
		</div>
		</div>
	<!-- 헤더영역 -->
	<jsp:include page="../inc/footer.jsp"/>
	<!-- 헤더영역 -->
</body>
</html>
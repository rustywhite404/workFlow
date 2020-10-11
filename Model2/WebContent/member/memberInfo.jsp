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
		request.setCharacterEncoding("UTF-8");
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.selectInfo(id);		

		
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
				<span class="subject">ID : </span><span class="info"><%=mdto.getId() %></span>
				<br>
				<span class="subject">이름 : </span><span class="info"><%=mdto.getName() %></span>
				<br>
				<span class="subject">직급 : </span><span class="info"><%=mdto.getGrade() %></span>
				<br>
				<span class="subject">성별 : </span><span class="info"><%=mdto.getGender() %></span>
				<br>
				<span class="subject">나이 : </span><span class="info"><%=mdto.getAge() %></span>
				<br>
				<span class="subject">이메일 : </span><span class="info"><%=mdto.getEmail() %></span>
				<br>
				<span class="subject">가입일 : </span><span class="info"><%=mdto.getReg_date() %></span>
			</div>
		</div>
		</div>
	<!-- 헤더영역 -->
	<jsp:include page="../inc/footer.jsp"/>
	<!-- 헤더영역 -->
</body>
</html>
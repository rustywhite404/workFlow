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
		 String id = (String) session.getAttribute("id");
	if(id==null){
		response.sendRedirect("./MemberLogin.me");
		System.out.println("[아이디가 없어서 로그인창으로 이동합니다.]");
		return;
	}
	  

		// 아이디에 해당하는 이름 찾기
		request.setCharacterEncoding("UTF-8");
		MemberDAO mdao = new MemberDAO();
		String name = mdao.selectName(id);		
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
	%>
	<body>
		<!-- 헤더영역 -->
		<jsp:include page="../inc/header.jsp"/>
		<!-- 헤더영역 -->
		
		<div id="content">
			<div id="main_container">
			
			<h2><%=name%>님, 환영합니다!</h2>
			<hr>
			<div class="welcome">
				<p>오늘은 <%=year%>년 <%= month%>월 <%=day%>일 입니다.</p>
			</div>
		</div>
		</div>
	<!-- 헤더영역 -->
	<jsp:include page="../inc/footer.jsp"/>
	<!-- 헤더영역 -->
</body>
</html>
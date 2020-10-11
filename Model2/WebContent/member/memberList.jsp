<%@page import="java.util.List"%>
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
		// 로그인 없이 접근할 경우, 로그인 한 사람이 admin이 아닐 경우 처리 
		if(id==null || !id.equals("admin")){
			response.sendRedirect("./MemberLogin.me");
		}
		
		// 회원정보를 저장해서 가져오기 
		List<MemberDTO> memberList = (List<MemberDTO>)request.getAttribute("memberList");
		
	%>
	<body>
		<!-- 헤더영역 -->
		<jsp:include page="../inc/header.jsp"/>
		<!-- 헤더영역 -->
		
		<div id="content">
			<div id="main_container">
			
			<h2>회원 목록</h2>
			<hr>
			<div class="userInfo">
				<table border="1">
					<tr>
						<td>ID</td>
						<td>NAME</td>
						<td>AGE</td>
						<td>GENDER</td>
						<td>EMAIL</td>
						<td>GRADE</td>
						<td>REG DATE</td>
					</tr>
					
					<%
						// ArrayList - size() : 배열의 요소 개수 리턴
						for(int i=1;i<memberList.size();i++){
							MemberDTO mdto = (MemberDTO)memberList.get(i);
					%>
					<tr>
						<td><%=mdto.getId()%></td>
						<td><%=mdto.getName()%></td>
						<td><%=mdto.getAge()%></td>
						<td><%=mdto.getGender()%></td>
						<td><%=mdto.getEmail()%></td>
						<td><%=mdto.getGrade()%></td>
						<td><%=mdto.getReg_date() %></td>
					</tr>
					<%
						}
					%>
				</table>				
			</div>
		</div>
		</div>
	<!-- 헤더영역 -->
	<jsp:include page="../inc/footer.jsp"/>
	<!-- 헤더영역 -->
</body>
</html>
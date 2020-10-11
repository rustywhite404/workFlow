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
			
			<h2>글쓰기</h2>
			<hr>
			<form action="./BoardWriteAction.bo" method="post">
				<table class="boardWrite">
						<tr>
							<input type="hidden" name="id" value="<%=mdto.getId()%>">
							<input type="hidden" name="name" value="<%=mdto.getName()%>">
							<th>작성자</th>
							<td><%=mdto.getName()%></td>
							<th>비밀번호</th>
							<td><input type="password" name="pw"></td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="3"><input type="text" name="subject" class="sub_input"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3"><textarea cols="92" rows="20" name="content"></textarea></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3"><input type="file" name="file" class="file_input"></td>
						</tr>

				</table>
				<div class="btn_set"> 
					<input type="button" class="list_btn" onclick="location.href='./BoardList.bo'" value="취소">
					<input type="submit" class="submit" value="글쓰기">
				</div>
			</form>
		</div>
		</div>
	<!-- 헤더영역 -->
	<jsp:include page="../inc/footer.jsp"/>
	<!-- 헤더영역 -->
</body>
</html>
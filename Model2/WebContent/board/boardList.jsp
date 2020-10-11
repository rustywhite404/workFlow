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
			<div class="boardList">
					<form> 
						<table class="boardListTable">
							<colgroup>
								<col width="110px">
								<col width="auto">
								<col width="130px">
								<col width="130px">
							</colgroup>
							<thead>
								<tr>
									<th><span class="lineR">번호</span></th>
									<th><span class="lineR">제목</span></th>
									<th><span class="lineR">작성자</span></th>
									<th>등록일</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>번호</td>
									<td>제목</td>
									<td>작성자</td>
									<td>등록일</td>
								</tr>	
							</tbody>
						</table>
						<input type="button" class="" onclick="location.href='./BoardList.bo'" value="목록">
						<input type="button" class="submit" onclick="location.href='./BoardWrite.bo'" value="글쓰기">
					</form>
			</div>
		</div>
		</div>
	<!-- 헤더영역 -->
	<jsp:include page="../inc/footer.jsp"/>
	<!-- 헤더영역 -->
</body>
</html>
<%@page import="net.ivyro.zian.member.db.MemberDTO"%>
<%@page import="net.ivyro.zian.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/header.css" rel="stylesheet" type="text/css">
<link href="./css/reset.css" rel="stylesheet" type="text/css">
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<%
	//한글화
	String id = (String)session.getAttribute("id");
	System.out.println("[메인페이지입니다.]"+id+"[가 id입니다.]");

	if(id==null){
		response.sendRedirect("./MemberLogin.me");
		System.out.println("[아이디가 없어서 로그인창으로 이동합니다.]");
		return;
	}
	
	
	//MemberDAO 호출
	MemberDAO mdao = new MemberDAO();
	// 아이디에 해당하는 개인정보 찾기
	request.setCharacterEncoding("UTF-8");
	String name = mdao.selectName(id);
	
	MemberDTO mdto = new MemberDTO();
	mdto = mdao.selectInfo(id);		

%>

<section id="wrap">	
		<div id="left_menu">
			<div class="profile"> 
				<ul>
					<li><div class="propic"></div></li>
					<li><span class="name"><%=mdto.getName()%></span> <%=mdto.getGrade()%>님</li>
					<li><a href="./Logout.me">로그아웃</a></li>
				</ul>
			</div>
			<div class="userPage"> 
				<ul>
					<li><h4>USER INFO</h4></li>
					<li><a href="./MemberInfo.me">회원 정보</a></li>
					<li><a href="./MemberUpdate.me">회원 정보 수정</a></li>
					<li><a href="./MemberDelete.me">회원 탈퇴</a></li>
					<!-- 관리자 기능 -->
					<% 
						if(id.equals("admin")){
					%>
					<li><a href="./AllMemberList.me">전체 회원 목록</a></li>
					<%
						}
					%>
					<!-- 관리자 기능 -->
				</ul>
			</div>
			<div class="boardPage"> 
				<ul>
					<li><h4>BOARD</h4></li>
					<li><a href="./BoardList.bo">공지게시판</a></li>
					<li>문의게시판</li>
					<!-- 관리자 기능 -->
				</ul>
			</div>
			<div class="shopPage"> 
				<ul>
					<li><h4>SHOP</h4></li>
					<li>제품 리스트</li>
					<li>장바구니</li>
					<!-- 관리자 기능 -->
				</ul>
			</div>
		</div>
		
		
package net.ivyro.zian.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("[MemberLogoutAction 페이지를 실행합니다.]");
		System.out.println("[세션 객체를 초기화 시키겠습니다.]");
		
		HttpSession session = request.getSession();
		// 받아온 세션 정보 저장 
		
		session.invalidate(); // 세션 정보 삭제
		
		System.out.println("[세션 정보를 모두 지웠습니다.]");
		System.out.println("[자바스크립트를 통해 메인으로 갑니다.]");
		// 자바스크립트 사용 페이지 이동하기 
		
		// 응답정보의 내용의 형식을 지정(mime타입)
		response.setContentType("text/html; charset=UTF-8");
		
		// 출력 스트림준비
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print(" location.href='./MemberLogin.me'; ");
		out.print("</script>");
		
		out.close();		
		
		return null;
	}

	
	
}

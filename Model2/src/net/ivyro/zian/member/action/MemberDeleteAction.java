package net.ivyro.zian.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ivyro.zian.member.db.MemberDAO;

public class MemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 넘어온 정보 확인(세션 제어)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// id는 세션값 사용, pw는 입력받은 파라미터값 사용
		String pw = request.getParameter("pw");
						
		// MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		
		// deleteMember(id, pw) 메서드 호출 
		int check = mdao.deleteMember(id, pw);
		
		
		// 페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(check == 0){
			out.print("<script>");
			out.print(" alert('비밀번호 오류'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			return null;
		}else if(check == -1){
			out.print("<script>");
			out.print(" alert('아이디 없음'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			return null;
		}
		
		// check == 1
		// 삭제처리 하면 됨!
		session.invalidate();
		out.print("<script>");
		out.print(" alert('회원 삭제(탈퇴완료!)'); ");
		out.print(" location.href='./MemberLogin.me'; ");
		out.print("</script>");
	
		
		return null;
	}

}

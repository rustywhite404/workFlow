package net.ivyro.zian.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ivyro.zian.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//넘어온 정보 저장(id, pw)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		
		// 아이디, 로그인 정보가 있으면 로그인 처리
		int check = mdao.loginMember(id, pw);
		System.out.println("[ID,PW가 DB에 있는지 확인 후 처리]");
		
		// check == 0 (비밀번호 오류)
		if(check == 0){
			// javascript 사용 alert()
			response.setContentType("text/html; charset=UTF-8");
			
			// 화면에 출력하는 출력스트림 생성
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print(" alert('비밀번호 오류!!');");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			
			return null;
		}
		// check == -1 (아이디 없음)
		else if( check == -1 ){
			// javascript 사용 alert()
			response.setContentType("text/html; charset=UTF-8");
			
			// 화면에 출력하는 출력스트림 생성
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print(" alert('아이디 없음!!');");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			// controller에서 이동X
			return null;
		}
		
		// check == 1 (정상처리)
		if(check == 1){
			// 아이디값을 세션객체에 저장
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			// main페이지 -> ./Main.me 이동
		}
		
		// 페이지 이동 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);		
		return forward;
	}

}

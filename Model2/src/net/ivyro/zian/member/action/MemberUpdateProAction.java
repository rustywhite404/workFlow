package net.ivyro.zian.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ivyro.zian.member.db.MemberDAO;
import net.ivyro.zian.member.db.MemberDTO;

public class MemberUpdateProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션값 저장(id)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		// 비 로그인 접근시 로그인 페이지로 이동시키기
		ActionForward forward = new ActionForward();
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글화
		request.setCharacterEncoding("UTF-8");
		
		// 입력받은 정보들 저장
		MemberDTO mdto = new MemberDTO();
		mdto.setAge(Integer.parseInt(request.getParameter("age")));
		mdto.setEmail(request.getParameter("email"));
		mdto.setGender(request.getParameter("gender"));
		mdto.setGrade(request.getParameter("grade"));
		mdto.setId(request.getParameter("id"));
		mdto.setName(request.getParameter("name"));
		mdto.setPw(request.getParameter("pw"));

		// MemberDAO 생성
		MemberDAO mdao = new MemberDAO();
		
		// MemberDAO 에서 정보들 업데이트 처리(MemberUpdate(MemberDTO)
		int check = mdao.MemberUpdate(mdto);
		
		// 페이지 이동		
		if(check==0){ // 비밀번호가 다름
			// 응답 결과를 html 형식으로 처리
			response.setContentType("text/html; charset=UTF-8"); 
			
			// 출력스트림
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back()");
			out.print("</script>");
			out.close();
			
			// 자바스크립트로 이동을 완료했기 때문에 
			// 컨트롤러에서 이동하지 않게 하기 위해서
			return null; // 처리를 해준다.
			
		}else if(check==-1){ // 회원이 없음  
			// 응답 결과를 html 형식으로 처리
			response.setContentType("text/html; charset=UTF-8"); 
			
			// 출력스트림
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('없는 회원입니다!');");
			out.print("history.back()");
			out.print("</script>");
			out.close();
			
			// 자바스크립트로 이동을 완료했기 때문에 
			// 컨트롤러에서 이동하지 않게 하기 위해서
			return null; // 처리를 해준다.

		}
		
		// check == 1; 아이디와 비밀번호가 일치할 때 

		// 응답 결과를 html 형식으로 처리
		response.setContentType("text/html; charset=UTF-8"); 
		
		// 출력스트림
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('수정완료!');");
		out.print("location.href='./Main.me'");
		out.print("</script>");
		out.close();
		
		// 자바스크립트로 이동을 완료했기 때문에 
		// 컨트롤러에서 이동하지 않게 하기 위해서
		return null; // 처리를 해준다.

	}

}

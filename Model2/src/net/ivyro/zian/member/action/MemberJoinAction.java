package net.ivyro.zian.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ivyro.zian.member.db.MemberDAO;
import net.ivyro.zian.member.db.MemberDTO;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[MemberJoinAction을 실행합니다.]");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달정보(회원정보) 저장 - MemberDTO
		MemberDTO mdto = new MemberDTO();
		mdto.setAge(Integer.parseInt(request.getParameter("age")));
		mdto.setEmail(request.getParameter("email"));
		mdto.setGender(request.getParameter("gender"));
		mdto.setId(request.getParameter("id"));
		mdto.setName(request.getParameter("name"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setGrade(request.getParameter("grade"));
		
		System.out.println("[전달받은 정보들을 확인합니다.]");
		System.out.println("[Action: MemberDTO :]"+mdto);
		
		// DB 처리 - MemberDAO
		MemberDAO mdao = new MemberDAO();
		
		// 회원가입 처리
		mdao.insertMember(mdto);
		System.out.println("[DB에 신규 회원 정보 등록 완료]");
		System.out.println("[컨트롤러에서 페이지를 이동시킵니다.]");
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		return forward;
	}

}

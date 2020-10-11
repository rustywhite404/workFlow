package net.ivyro.zian.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ivyro.zian.member.db.MemberDAO;
import net.ivyro.zian.member.db.MemberDTO;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("[MemberUpdateAction 페이지로 왔습니다.]");
		// 넘어온 id를 세션값에 저장해서 사용해야해.
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		
		if(id==null){ // 넘어온 아이디가 없을 경우
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(false);
		}
		
		// 넘어온 아이디가 있을 경우 계속 실행
		// MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		
		// 회원 정보를 가져오는 메서드 selectInfo(id)를 다시 사용해도 되겠다.
		MemberDTO mdto = mdao.selectInfo(id);
		
		// 회원 정보를 request 영역에 저장해두자. 
		request.setAttribute("mdto", mdto);
		
		// 페이지 이동(./member/MemberupdateForm.jsp)
		forward.setPath("./member/memberUpdateForm.jsp");
		forward.setRedirect(false);
		System.out.println("[DB에서 회원 정보를 가져와서 저장했습니다.]");
		System.out.println("[페이지를 이동합니다.]");
				
		return forward;
	}

}

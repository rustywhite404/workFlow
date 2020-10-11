package net.ivyro.zian.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ivyro.zian.member.db.MemberDAO;
import net.ivyro.zian.member.db.MemberDTO;

public class AllMemberListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션 제어(로그인 여부, 관리자인지 확인)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		
		// 전체 회원 목록 조회 메서드 :AllMemberList()
		List<MemberDTO> memberList = mdao.AllMemberList();
		
		// 회원 정보를 저장해서 view 페이지로 이동 
		request.setAttribute("memberList", memberList);
		System.out.println(memberList);
		
		// 페이지이동
		forward.setPath("./member/memberList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

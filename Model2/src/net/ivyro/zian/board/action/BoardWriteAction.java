package net.ivyro.zian.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ivyro.zian.board.db.BoardDAO;
import net.ivyro.zian.board.db.BoardDTO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : BoardWriteAction_execute() 호출");

		// 전달하는 정보 저장( 파라미터값 저장 )
		// -> BoardDTO 객체 생성 -> DB테이블 생성
		BoardDTO bdto = new BoardDTO();
		request.setCharacterEncoding("UTF-8");
		bdto.setId(request.getParameter("id"));
		bdto.setName(request.getParameter("name"));
		bdto.setPw(request.getParameter("pw"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		
		
		// BoardDAO 객체 생성
		BoardDAO bdao = new BoardDAO();
		// 글쓰기 동작(insertBoard())
		int result = bdao.insertBoard(bdto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		
        if(result == 1){ // 글하나 작성완료
        	forward.setPath("./BoardList.bo");
        	forward.setRedirect(true);
        }else{
        	//에러 상황( 자바스크립트 / 페이지 이동 )
        	forward.setPath("./Main.me");
        	forward.setRedirect(true);
        }
		
		return forward;
	}

}

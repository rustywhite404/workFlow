package net.ivyro.zian.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[doProcess 호출합니다.]");
		// 가상주소를 가져오기 -------------------------------------------------------------------------------------------
		// 기본적으로 가상주소의 URL은 http://localhost:8088/Model2/test.me 이런 식이야.
		// 여기서 프로젝트명+주소 부분만 잘라서 가지고 다니기로 하자.
		// 즉, requestURI : /Model2/test.me 처럼 되게 하자는 거지.
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI(프로젝트명+주소) :"+requestURI);
		
		//contextPath : /Model2 = 프로젝트명
		String contextPath = request.getContextPath();
		System.out.println("contextPath(프로젝트명) :"+contextPath);
		
		//이제 필요한 걸 준비했으니까 가상주소를 만들어보자.
		// /test.me
		String command = requestURI.substring(contextPath.length());
		System.out.println("command(가상주소):"+command);
		
		// 가상주소를 가져오기 -------------------------------------------------------------------------------------------
		System.out.println("--------------------------------------------------------------------------------");
		
		// 특정 주소에 대한 처리---------------------------------------------------
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")){
			System.out.println("[BoardWrite : 게시판 글쓰기를 시작합니다.]");
			forward = new ActionForward();
			forward.setPath("./board/boardWrite.jsp");
			forward.setRedirect(false);			
			System.out.println("[boardWrite.jsp로 이동 준비 완료.]");
			
		}else if(command.equals("/BoardWriteAction.bo")){
			
			System.out.println("[BoardWrite : 게시판 글쓰기 PRO 페이지 동작]");
						
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardList.bo")){
			System.out.println("[BoardList 페이지를 출력합니다.]");
			forward = new ActionForward();
			forward.setPath("./board/boardList.jsp");
			forward.setRedirect(false);
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 특정 주소에 대한 처리 ------------------------------------------------------------------------------------------
		
		System.out.println("--------------------------------------------------------------------------------");
		
		// 페이지 이동 처리 ------------------------------------------------------------------------------------------
		// 이동 정보(ActionForward)가 있을 때, 여러번의 페이지 처리를 한 번에 효율적으로 처리하기 위해서 미리 만들어 두는 거야.
		// isRedirect가 true면 response, false면 forward 방식으로 이동시키기로 했지? (f-f)로 외우면 좀 더 편할거야.
		if(forward != null){ // forward가 존재할 때 
			System.out.println("[페이지를 이동합니다.]");
			if(forward.isRedirect()){ // response - true;
				System.out.println("이동할 페이지: "+forward.getPath());
				System.out.println("sendRidirect 방식으로 이동합니다.");
				response.sendRedirect(forward.getPath());
				
			}else{ // forward - false;
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				System.out.println("이동할 페이지:"+forward.getPath());
				System.out.println("forward 방식으로 이동합니다.");
				dis.forward(request, response);
			}
		}	
		// 페이지 이동 처리 ------------------------------------------------------------------------------------------
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
}

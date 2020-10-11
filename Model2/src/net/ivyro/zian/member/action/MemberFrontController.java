package net.ivyro.zian.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get/post 방식 상관없이 특정 주소에 따른 처리를 할 수 있게 해야해.
		// 그래서 doProcess를 실행시켜 주는거야.
		System.out.println("doProcess()호출");
		
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
		
		// 특정 주소에 대한 처리 ------------------------------------------------------------------------------------------
		Action action = null;
		ActionForward forward = null;
		
		//http://localhost:8088/Model2/MemberJoin.me - 회원가입 창을 만들거야.
		if(command.equals("/MemberJoin.me")){
			System.out.println("/MemberJoin.me를 호출합니다.");
			// 회원 가입 처리를 하려면 회원 정보 입력창(view)이 있어야겠지?
			// WebContent/member/insertForm.jsp 페이지로 이동해보자. 
			
			// 페이지로 이동하려면 => ActionForward 객체를 사용해야겠지.
			forward = new ActionForward();
			
			//이동할 주소는 어디일까?
			forward.setPath("./member/insertForm.jsp");
			
			//이동할 방법은? - MVC Model2 구조에서는 jsp 페이지가 주소줄에 드러나면 안 돼.
			// 그럼 주소가 변경되지 않고 이동하는 forward 방식을 사용해야겠지.
			forward.setRedirect(false);
		
		}else if(command.equals("/MemberJoinAction.me")){
			System.out.println("[MemberJoinAction.me의 주소를 호출합니다.]");
			
			// 전달되는 정보를 받아서 DB에 저장해야 하겠지?
			// Action 페이지에서 처리를 할 거야.
			// 즉, Action 페이지 -> Pro 페이지의 역할을 해 줄 거야.
			// MemberJoinAction 객체가 필요할 거고.
			// MemberJoinAction action = new MemberJoinAction(); 으로 쓰면 되지만,
			// MemberJoinAction 은 Action을 상속받으니까
			action = new MemberJoinAction(); // 이렇게도 쓸 수 있지.
			System.out.println("[컨트롤러에서 MemberJoinAction 객체를 생성합니다.]");
			
			// 객체를 생성했으면 페이지 이동을 해야겠지?
			
			try {
				System.out.println("[컨트롤러가 execute메서드를 호출합니다. ]");
				forward = action.execute(request, response);
				System.out.println("[처리 완료 후 페이지 이동] :" +forward);				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberLogin.me")){
			System.out.println("[MemberLogin.me의 주소를 호출합니다.]");
			System.out.println("[=./member/loginForm.jsp]");
			
			// 페이지 이동(ActionForward 객체)
			forward = new ActionForward();
			// 페이지 이동주소
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberLoginAction.me")){
			System.out.println("/MemberLoginAction.me의 주소를 호출합니다.");
			
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
				System.out.println("[처리 완료 후 페이지 이동] :" +forward);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Main.me")){
			System.out.println("[메인페이지를 호출합니다.]");
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberInfo.me")){
			forward = new ActionForward();
			forward.setPath("./member/memberInfo.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberUpdate.me")){
			// DB 정보를 가지고 와서 JSP 페이지로 이동해야 하니까
			// MemberUpdateAction 객체가 필요해.
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Logout.me")){
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}else if(command.equals("/MemberUpdatePro.me")){
			action = new MemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberDelete.me")){
			// 회원 삭제는 DB 정보가 필요없기 때문에 바로 VIEW 페이지로 이동
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
			
			
		}else if(command.equals("/MemberDeleteAction.me")){
			
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/AllMemberList.me")){
			action = new AllMemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
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
		System.out.println("doGet()호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()호출");
		doProcess(request, response);
		
	}

	
}

package net.ivyro.zian.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	// 추상메서드는 서브 클래스들에게 강제성을 부여해.
	// 그래서 개발 형식이 통일되고 틀이 정해지지.
	// 뿐만 아니라 객체간의 관계가 약회되어서 각각의 객체가 해당 동작만 처리할 수 있게 돼. 
	
	// 인터페이스로 Action을 정의해서, 
	// Action 페이지의 동작을 미리 선언해서 사용해보자.
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
	
}

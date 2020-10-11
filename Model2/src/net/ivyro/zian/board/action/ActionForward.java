package net.ivyro.zian.board.action;

public class ActionForward {
	
	// ActionForward는 이동할 정보를 저장하는 객체야.
	// 이동할 페이지와 방식을 저장해서 담아갈거야.
	
	private String path;
	private boolean isRedirect;
	// isRedirect가 true인지, false인지에 따라 연결 방식을 바꾸기로 하자.
	// true면 sendRedirect 방식으로 이동하고,
	// false면 forward 방식으로 이동하기로 해.
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}
	
	
	
	
}

package net.ivyro.zian.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	// DB 연결 메서드
	public Connection getCon() throws Exception{
		// 커넥션 풀을 사용해서 DB를 연결해보자.
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/shopdb");
		con = ds.getConnection();
		
		return con; // return ds.getConnection(); 		
	}
	
	// DB 자원해제 메서드
	public void closeDB(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	//insertMember(mdto) : 회원가입 처리 
	public void insertMember(MemberDTO mdto){
		
		try {
			getCon();
			sql = "insert into shop_member(id, pw, name, age, gender, email, grade, reg_date) "
					+ "values(?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getName());
			pstmt.setInt(4, mdto.getAge());
			pstmt.setString(5, mdto.getGender());
			pstmt.setString(6, mdto.getEmail());
			pstmt.setString(7, mdto.getGrade());
			pstmt.executeUpdate();			
			System.out.println("[회원가입 처리 완료!]");			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[!회원가입 처리 중 예외가 발생했습니다!]");
		}finally{
			closeDB();
		}
		
	}//insertMember(mdto) : 회원가입 처리
	
	
	// loginMember(id,pw) : 로그인 처리
	public int loginMember(String id, String pw){
		int check = -1;
		try {
			getCon();
			sql="select pw from shop_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(pw.equals(rs.getString("pw"))){
					check = 1;
				}else{ // 비밀번호 다름
					check = 0;
				}
			}else{ // 아이디가 없음
				check = -1;
			}
			System.out.println("[!로그인 처리 완료!]");
			System.out.println(check);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[!로그인 처리 중 예외가 발생했습니다!]");
		}finally{
			closeDB();
		}
		
		return check;
	}// loginMember(id,pw) : 로그인 처리
	
	// selectName(id) : 아이디에 해당하는 이름 찾기
	public String selectName(String id){
		String name = null;
		try {
			getCon();
			sql = "select name from shop_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				name = rs.getString("name");
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			closeDB();
		}
		return name;
	}// selectName(id) : 아이디에 해당하는 이름 찾기
	
	// selectInfo(id) : 아이디에 해당하는 회원정보 전체 찾기
	public MemberDTO selectInfo(String id){
		MemberDTO mdto = null;
		try {
			getCon();
			sql = "select * from shop_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				mdto = new MemberDTO();
				mdto.setAge(rs.getInt("age"));
				mdto.setEmail(rs.getString("email"));
				mdto.setGender(rs.getString("gender"));
				mdto.setGrade(rs.getString("grade"));
				mdto.setId(rs.getString("id"));
				mdto.setName(rs.getString("name"));
				mdto.setPw(rs.getString("pw"));
				mdto.setReg_date(rs.getDate("reg_date"));
			}
			System.out.println("회원정보 전체 조회 완료!");
			System.out.println("회원정보:"+mdto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}		
		return mdto;

	}	// selectInfo(id) : 아이디에 해당하는 회원정보 전체 찾기
	// MemberUpdate(mdto) : 회원정보 수정
	public int MemberUpdate(MemberDTO mdto){
		int check = 0;
	
		try {
			getCon();
			sql ="select pw from shop_member where id=?"; // 회원 존재 여부부터 확인
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()){ // 아이디 있음
				if(mdto.getPw().equals(rs.getString("pw"))){
					// 비밀번호가 일치(본인) -> 회원정보 수정
					
					sql="update shop_member set name=?, age=?, gender=?, email=?, grade=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mdto.getName());
					pstmt.setInt(2, mdto.getAge());
					pstmt.setString(3, mdto.getGender());
					pstmt.setString(4, mdto.getEmail());
					pstmt.setString(5, mdto.getGrade());
					pstmt.setString(6, mdto.getId());
					
					pstmt.executeUpdate();
					check = 1; 
					System.out.println("[회원정보 수정 완료]");
					
				}else{ // 비밀번호가 다름
					check = 0;
					System.out.println("[비밀번호가 달라서 회원정보 수정이 어렵습니다.]");
				}
				
			}else{ // 아이디 없음
				check = -1;
				System.out.println("[해당 아이디가 없어서 회원정보 수정이 불가능합니다.]");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}		
		return check;
	}// MemberUpdate(mdto) : 회원정보 수정
	
	// deleteMember(id, pw) : 회원 탈퇴
	public int deleteMember(String id, String pw){
		int check = 0;
		try {
			getCon();
			sql = "select pw from shop_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){ // 아이디가 있을 경우
				if(pw.equals(rs.getString("pw"))){
					sql = "delete from shop_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					System.out.println("[회원 탈퇴가 완료되었습니다.]");
					check = 1;
				}else{ // 비밀번호가 다를 경우
					check = 0;
					System.out.println("[회원 탈퇴 처리 중 비밀번호가 다릅니다.]");
				}
				
			}else{ // 아이디가 없을 경우
				check = -1;
				System.out.println("[회원 탈퇴 처리 중 아이디가 없습니다.]");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return check;
	}// deleteMember(id, pw) : 회원 탈퇴 
	
	//AllMemberList() : 전체회원보기
	public List<MemberDTO> AllMemberList(){
		List<MemberDTO> memberList =
				new ArrayList<MemberDTO>();
		
		try {
			getCon();
			sql="select * from shop_member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 데이터 처리
			while(rs.next()){
				// 회원 전체 정보를 리스트에 저장
				// 리스트 한 칸에 회원 1명(mdto) 정보만 저장
				MemberDTO mdto = new MemberDTO();
				mdto.setAge(rs.getInt("age"));
				mdto.setEmail(rs.getString("email"));
				mdto.setGrade(rs.getString("grade"));
				mdto.setId(rs.getString("id"));
				mdto.setName(rs.getString("name"));
				mdto.setGender(rs.getString("gender"));
				mdto.setAge(Integer.parseInt(rs.getString("age")));
				mdto.setReg_date(rs.getDate(("reg_date")));
				
				//리스트 한 칸에 정보 저장
				memberList.add(mdto);
			}
			System.out.println("[회원 전체목록 저장 완료!]");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return memberList;

	}//AllMemberList() : 전체회원보기
	
	
	
}

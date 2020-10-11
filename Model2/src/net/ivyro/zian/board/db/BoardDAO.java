package net.ivyro.zian.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
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
	
	public int insertBoard(BoardDTO bdto){
		int check = 0;
		int bno = 0;
		try {
			getCon();
			// 글번호 계산
			sql="select max(bno) from shop_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			if(rs.next()){
				bno = rs.getInt(1)+1; // 인덱스를 사용해서 호출
			}
			System.out.println("[글번호:"+bno+"]");
			
			////////////////////////////////////////////////////////////////////
			sql="insert into shop_board(bno, id, pw, name, subject, content, "
					+ "re_lev, re_seq, re_ref, date, readcount, file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, bdto.getId());
			pstmt.setString(3, bdto.getPw());
			pstmt.setString(4, bdto.getName());
			pstmt.setString(5, bdto.getSubject());
			pstmt.setString(6, bdto.getContent());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, bno);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			pstmt.setString(11, bdto.getFile());
			
			check = pstmt.executeUpdate();
			System.out.println("[글쓰기 완료!]"+check);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[글쓰기 중 예외가 발생했습니다.]");
		}finally{
			closeDB();
		}	
		
		return check;
	}
	
	
	
}

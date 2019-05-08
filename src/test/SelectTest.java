package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC Driver(MariaDB) 로딩 
			//직접 코드로 메소드Area에 로딩할 때 
			Class.forName("org.mariadb.jdbc.Driver");
		
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.1.21:3307/webdb";
			// DriverManager.getConnection을 하면 Connection 인터페이스의 conn이 구현된다. 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 연결이 성공되는 지 확인
			System.out.println("연결 성공");
			
			//3. statement 객체 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "select no, name from department";
			rs =stmt.executeQuery(sql);
			
			//5. 결과 가져오기
			//rs 값 중 다음 값이 없다면 false이므로 while문이 멈춘다.
			while(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error"+e);
		} finally {
			try {
				
				if( rs!= null) {
					rs.close();
				}if(stmt!=null) {
					stmt.close();
				}if( conn != null) {
					// 연결 종료	
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

}

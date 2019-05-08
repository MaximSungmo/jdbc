package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		
		Connection conn = null;
				
		try {
			//1. JDBC Driver(MariaDB) 로딩 
			//직접 코드로 메소드Area에 로딩할 때 
			Class.forName("driver.MyDriver");
		
			//2. 연결하기
			String url = "jdbc:mydb://192.168.1.21:3307/webdb";
			// DriverManager.getConnection을 하면 Connection 인터페이스의 conn이 구현된다. 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 연결이 성공되는 지 확인
			System.out.println("연결 성공"+ conn);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if( conn != null) {
					// 연결 종료
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		
	}

}

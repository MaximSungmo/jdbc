package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

	public List<EmployeeVo> getList(String keyword){

		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC Driver(MariaDB) 로딩 
			//직접 코드로 메소드Area에 로딩할 때 
			Class.forName("org.mariadb.jdbc.Driver");
		
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.1.21:3307/employees";
			// DriverManager.getConnection을 하면 Connection 인터페이스의 conn이 구현된다. 
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. SQL 준비 
			//JDBC에게 덜 완성된(PreparedStatement) 상태를 준비시켜서 sql을 바인딩
			String sql = 
					" select emp_no, first_name, last_name, hire_date \r\n" + 
					" from employees" + 
					" where first_name like ?" + 
					" or last_name like ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩 
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			
			rs =pstmt.executeQuery();
			
			//Createstatement와 preparedStatement의 차이점은 sql을 적재하는 타이밍이다.
			
			
			//5. 결과 가져오기
			//rs 값 중 다음 값이 없다면 false이므로 while문이 멈춘다.
			while(rs.next()) {
				long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				
				result.add(vo);
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error"+e);
		} finally {
			try {
				
				if( rs!= null) {
					rs.close();
				}if(pstmt!=null) {
					pstmt.close();
				}if( conn != null) {
					// 연결 종료	
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}
	
	
}

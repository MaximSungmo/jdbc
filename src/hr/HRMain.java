package hr;

import java.util.List;
import java.util.Scanner;

public class HRMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("검색어>");
		String keyword = sc.nextLine();
		
		// DAO 만들어서 
		// DB에 있는 조회되는 정보를 VO에 담을 예정 
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVo> list = dao.getList(keyword);
		
		for(EmployeeVo vo: list) {
			System.out.println(vo);
		}
		
		
	}

}

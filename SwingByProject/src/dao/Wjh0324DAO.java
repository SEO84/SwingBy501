package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import dto.Wjh0324DTO;

public class Wjh0324DAO {
	String driver = "oracle.jdbc.driver.OracleDriver"; // 12행 ~ 15행 데이터베이스 접속을 위한 4가지 정보를 String 변수에 저장.
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";
	
	public List<Wjh0324DTO> get() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String query =
				    "WITH " +
				    "E10 AS (SELECT * FROM EMP WHERE DEPTNO = 10), " +
				    "D AS (SELECT * FROM DEPT) " +
				    "SELECT E10.EMPNO, E10.ENAME, E10.DEPTNO, D.DNAME, D.LOC " +
				    "FROM E10, D " +
				    "WHERE E10.DEPTNO = D.DEPTNO";
				pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			ArrayList<Wjh0324DTO> ls = new ArrayList<>();
			while (rs.next()) {
				
				StringBuilder sb = new StringBuilder();
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				ls.add(new Wjh0324DTO(empno, ename, deptno, dname, loc));
			}
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	public static void main(String[] args) {
		Object result = new Wjh0324DAO().get();
		System.out.println(result);
	}
	
}

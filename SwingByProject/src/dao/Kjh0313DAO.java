package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import dto.Kjh0313DTO;

public class Kjh0313DAO {
	String driver = "oracle.jdbc.driver.OracleDriver"; // 12행 ~ 15행 데이터베이스 접속을 위한 4가지 정보를 String 변수에 저장.
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";
	
	public List<Kjh0313DTO> get() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String query =
				    "WITH " +
				    "E AS (SELECT * FROM EMP), " +
				    "D AS (SELECT * FROM DEPT) " +
				    "SELECT E.EMPNO, E.ENAME, E.JOB, E.DEPTNO, D.DNAME, D.LOC " +
				    "FROM E, D " +
				    "WHERE E.DEPTNO = D.DEPTNO";
				pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			ArrayList<Kjh0313DTO> ls = new ArrayList<>();
			while (rs.next()) {
				
				StringBuilder sb = new StringBuilder();
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				ls.add(new Kjh0313DTO(empno, ename,job, deptno, dname, loc));
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
		Object result = new Kjh0313DAO().get();
		System.out.println(result);
	}
	
}
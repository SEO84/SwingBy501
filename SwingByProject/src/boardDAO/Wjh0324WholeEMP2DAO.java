package boardDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import boardDTO.Wjh0324Emp2DTO;


public class Wjh0324WholeEMP2DAO {
	static String driver = "oracle.jdbc.driver.OracleDriver"; // 12행 ~ 15행 데이터베이스 접속을 위한 4가지 정보를 String 변수에 저장.
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String userid = "scott";
	static String passwd = "tiger";
	
	public List<Wjh0324Emp2DTO> getWholeEmp2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM EMP2";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			ArrayList<Wjh0324Emp2DTO> ls = new ArrayList<>();
			while (rs.next()) {
				ls.add(new Wjh0324Emp2DTO(
						rs.getInt("empno"),
						rs.getString("ename"),
						rs.getString("job"),
						rs.getObject("mgr", Integer.class),
						rs.getDate("hiredate"),
						rs.getObject("sal", Integer.class),
						rs.getObject("comm", Integer.class),
						rs.getObject("deptno", Integer.class)
					)
				);
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
}

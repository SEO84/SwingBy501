package boardDAO;

import boardDTO.Wjh0324Emp2DTO;
import common.Scott;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Wjh0324WholeEMP2DAO {
	
	public List<Wjh0324Emp2DTO> getWholeEmp2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(Scott.driver);
			con = DriverManager.getConnection(Scott.url, Scott.userid, Scott.passwd);
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
						rs.getObject("sal", Double.class),
						rs.getObject("comm", Double.class),
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

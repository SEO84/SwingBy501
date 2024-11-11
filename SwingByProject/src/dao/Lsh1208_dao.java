package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Lsh1208_dto;

public class Lsh1208_dao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public Lsh1208_dao() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Lsh1208_dto> Lsh1208_select() {
		ArrayList<Lsh1208_dto> lsh1208_list = new ArrayList<Lsh1208_dto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select job as job, round(avg(sal)) as sal\r\n" + 
					 "from emp\r\n" + 
					 "group by job\r\n"+ 
					 "order by sal desc";

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Lsh1208_dto Dto = new Lsh1208_dto();
				Dto.setJob(rs.getString("job"));
				Dto.setSal(rs.getInt("sal"));
				lsh1208_list.add(Dto);
			}
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
		return lsh1208_list;
	}

	public static void main(String[] args) {
		Lsh1208_dao daoInstance = new Lsh1208_dao();
		ArrayList<Lsh1208_dto> results = daoInstance.Lsh1208_select();

		for (Lsh1208_dto record : results) {
			System.out.println("직업:" + record.getJob() + "평균 급여:" + record.getSal());
		}
	}
}
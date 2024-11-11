package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.pkh0827dto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class pkh0827dao {
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String JDBC_USER = "scott";
	private static final String JDBC_PASSWORD = "tiger";
	private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver"; // JDBC 드라이버 클래스
	
	

	// SQL 쿼리
	private static final String SELECT_EMP_DEPT_SALGRADE_QUERY = "SELECT e.ename, e.job, e.sal, d.dname, d.loc, s.grade, s.losal, s.hisal "
			+ "FROM emp e " + "JOIN dept d ON e.deptno = d.deptno "
			+ "JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal " + "ORDER BY e.ename";

	// 데이터베이스 연결 및 쿼리 실행
	public List<pkh0827dto> getEmployeeDetails() throws SQLException {
		List<pkh0827dto> employeeList = new ArrayList<>();

		// JDBC 드라이버 로딩
		try {
			Class.forName(JDBC_DRIVER); // JDBC 드라이버 로드
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("JDBC Driver not found.");
		}

		// 1. 데이터베이스 연결
		try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_EMP_DEPT_SALGRADE_QUERY);
				ResultSet rs = stmt.executeQuery()) {

			// 2. ResultSet에서 데이터를 읽고 DTO로 변환
			while (rs.next()) {
				pkh0827dto employee = new pkh0827dto();
				employee.setEname(rs.getString("ename"));
				employee.setJob(rs.getString("job"));
				employee.setSal(rs.getDouble("sal"));
				employee.setDname(rs.getString("dname"));
				employee.setLoc(rs.getString("loc"));
				employee.setGrade(rs.getInt("grade"));
				employee.setLosal(rs.getDouble("losal"));
				employee.setHisal(rs.getDouble("hisal"));

				// 리스트에 추가
				employeeList.add(employee);
			}
		}

		// 3. 결과 반환
		return employeeList;
	}
}

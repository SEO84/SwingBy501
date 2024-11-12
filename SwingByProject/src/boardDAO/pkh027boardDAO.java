package boardDAO;

import boardDTO.shw1013BoardDTO;
import java.sql.*;

public class pkh027boardDAO {

	// 게시글 추가 메서드
	public int addBoard(shw1013BoardDTO board, String driver, String url, String userid, String passwd)
			throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// 데이터베이스 연결
			connection = DriverManager.getConnection(url, userid, passwd);

			// 작성자 이름 조회 (empNo를 이용하여 EMP 테이블에서 조회)
			String writerQuery = "SELECT ENAME FROM EMP WHERE EMPNO = ?";
			pstmt = connection.prepareStatement(writerQuery);
			pstmt.setInt(1, board.getEmpNo());
			rs = pstmt.executeQuery();

			String writer = null;
			if (rs.next()) {
				writer = rs.getString("ENAME"); // 직원 이름 (ENAME)
			}

			// 만약 작성자가 없으면 기본값 설정 (예: "Unknown")
			if (writer == null) {
				writer = "Unknown"; // EMP 테이블에서 해당 empNo에 맞는 작성자를 찾지 못하면 "Unknown"을 사용
			}

			// 게시글 추가 SQL (WRITER 포함)
			String sql = "INSERT INTO BOARD (BOARDNO, TITLE, CONTENT, WRITER, EMPNO, REGDATE) "
					+ "VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";

			pstmt = connection.prepareStatement(sql);

			// PreparedStatement에 값 설정
			pstmt.setString(1, board.getTitle()); // 제목
			pstmt.setString(2, board.getContent()); // 내용
			pstmt.setString(3, writer); // 작성자 이름 (EMP 테이블에서 조회한 ENAME 또는 "Unknown")
			pstmt.setInt(4, board.getEmpNo()); // EMPNO (외래키)

			// SQL 실행
			result = pstmt.executeUpdate(); // 실행 후 영향받은 행 수 반환

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (connection != null)
				connection.close();
		}

		return result; // 삽입 성공 시 1, 실패 시 0 반환
	}
}

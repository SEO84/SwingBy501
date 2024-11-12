package boardDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class shw1013deleteEmpDAO {

    // 데이터베이스 연결 정보
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
    private static final String DB_USER = "scott"; // 사용자 ID
    private static final String DB_PASSWORD = "tiger"; // 사용자 비밀번호

    // DELETE 메서드 (boolean 형식)
    public boolean deleteEmployee(int empNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;

        try {
            // 1. 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 3. SQL 작성
            String sql = "DELETE FROM BOARD WHERE empno = ?";
            String sql2 = "DELETE FROM emp2 WHERE empno = ?";

            // 4. PreparedStatement 생성 및 파라미터 설정
            pstmt = conn.prepareStatement(sql);
            pstmt2 = conn.prepareStatement(sql2);

            pstmt.setInt(1, empNo);   
            pstmt2.setInt(1, empNo);  

            // 5. SQL 실행
            int result1 = pstmt.executeUpdate();
            int result2 = pstmt2.executeUpdate();

            // 6. 결과 반환 (둘 중 하나라도 성공하면 true)
            return result1 > 0 || result2 > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false; // 오류 발생 시 false 반환
        } finally {
            // 7. 리소스 정리
            try {
                if (pstmt != null) pstmt.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
//
//    // 테스트용 main 메서드
//    public static void main(String[] args) {
//        shw1013deleteEmpDAO dao = new shw1013deleteEmpDAO();
//        int empNoToDelete = 101; // 삭제할 empno
//
//        boolean isDeleted = dao.deleteEmployee(empNoToDelete);
//        if (isDeleted) {
//            System.out.println("EmpNo " + empNoToDelete + " 삭제 성공!");
//        } else {
//            System.out.println("EmpNo " + empNoToDelete + " 삭제 실패! 존재하지 않는 EmpNo입니다.");
//        }
//    }
//}

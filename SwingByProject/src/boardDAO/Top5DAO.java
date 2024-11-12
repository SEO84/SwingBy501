package boardDAO;

import java.sql.*;
import java.util.ArrayList;
import boardDTO.Top5DTO;

public class Top5DAO {
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String userid = "scott";
    private String passwd = "tiger";

    public Top5DAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 급여 상위 5명 조회
    public ArrayList<Top5DTO> getTop5() {
        ArrayList<Top5DTO> top5List = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * "
                   + "FROM ("
                   + "    SELECT empno, ename, job, sal "
                   + "    FROM emp "
                   + "    ORDER BY sal DESC"
                   + ") "
                   + "WHERE ROWNUM <= 5";

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Top5DTO dto = new Top5DTO();
                dto.setEmpno(rs.getInt("empno"));
                dto.setEname(rs.getString("ename"));
                dto.setJob(rs.getString("job"));
                dto.setSal(rs.getDouble("sal"));
                top5List.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return top5List;
    }
}

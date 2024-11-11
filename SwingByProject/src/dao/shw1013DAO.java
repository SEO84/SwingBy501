package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.shw1013DTO;

public class shw1013DAO {
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String userid = "scott";
    String passwd = "tiger";

    public ArrayList<shw1013DTO> select() {
        ArrayList<shw1013DTO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            String query = "SELECT empno, ename, job, sal FROM emp " +
                           "WHERE sal < ALL (SELECT sal FROM emp WHERE job = 'MANAGER') " +
                           "AND job <> 'MANAGER' ORDER BY sal DESC";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                shw1013DTO dto = new shw1013DTO();
                dto.setEmpno(rs.getInt("empno"));
                dto.setEname(rs.getString("ename"));
                dto.setJob(rs.getString("job"));
                dto.setSal(rs.getDouble("sal"));
                list.add(dto);
            }
        } catch (Exception e) {
            System.out.println("데이터베이스 조회 중 오류 발생: " + e.getMessage());
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
        return list;
    }

	public List<shw1013DTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}

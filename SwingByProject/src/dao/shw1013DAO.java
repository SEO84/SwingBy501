package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.shw1013DTO;

public class shw1013DAO {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String userid = "scott";
    String passwd = "tiger";

    public shw1013DAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<shw1013DTO> get() {
        List<shw1013DTO> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement("SELECT empno, ename, job, sal FROM emp");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                shw1013DTO dto = new shw1013DTO();
                dto.setEmpno(rs.getInt("empno"));
                dto.setEname(rs.getString("ename"));
                dto.setJob(rs.getString("job"));
                dto.setSal(rs.getDouble("sal"));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list; // 실패 시 빈 리스트 반환
    }


}

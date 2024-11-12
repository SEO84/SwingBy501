package boardDAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import boardDTO.shw1013BoardDTO;

public class kjh0313boardDAO {
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String userid = "scott";
    private String passwd = "tiger";
    
    public List<shw1013BoardDTO> getBoardList() {
        List<shw1013BoardDTO> boardList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userid, passwd);
            String sql = "SELECT boardNo, title, writer FROM board";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int boardNo = rs.getInt("boardNo");
                String title = rs.getString("title");
                String writer = rs.getString("writer");

                shw1013BoardDTO board = new shw1013BoardDTO();
                board.setBoardNo(boardNo);
                board.setTitle(title);
                board.setWriter(writer);
                boardList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return boardList;
    }
}
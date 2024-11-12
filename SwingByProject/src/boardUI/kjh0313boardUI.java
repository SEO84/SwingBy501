package boardUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import boardDAO.kjh0313boardDAO;
import boardDTO.*;

public class kjh0313boardUI extends JPanel {
    private JTable boardTable;
    private DefaultTableModel tableModel;

    public kjh0313boardUI() {
        setLayout(new BorderLayout());

        // 테이블 모델 설정 (컬럼명 지정)
        String[] columnNames = {"번호", "제목", "작성자"};
        tableModel = new DefaultTableModel(columnNames, 0);
        boardTable = new JTable(tableModel);

        // 스크롤 패널에 테이블 추가
        JScrollPane scrollPane = new JScrollPane(boardTable);
        add(scrollPane, BorderLayout.CENTER);

        // 데이터 로드 및 테이블에 추가
        loadData();
    }

    private void loadData() {
    	kjh0313boardDAO dao = new kjh0313boardDAO();
        List<shw1013BoardDTO> boardList = dao.getBoardList();

        for (shw1013BoardDTO board : boardList) {
            Object[] rowData = {
                board.getBoardNo(),
                board.getTitle(),
                board.getWriter()
            };
            tableModel.addRow(rowData);
        }
    }

    // 메인 메서드 (단독 실행을 위한 테스트)
    public static void main(String[] args) {
        JFrame frame = new JFrame("게시판 목록");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new kjh0313boardUI());
        frame.setVisible(true);
    }
}

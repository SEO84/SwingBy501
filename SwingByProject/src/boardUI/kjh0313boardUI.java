package boardUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import boardDAO.kjh0313boardDAO;
import boardDTO.shw1013BoardDTO;

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
        
        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        JButton dummyButton = new JButton("더미 버튼");
        buttonPanel.add(dummyButton);

        // 버튼 패널을 하단에 추가
        add(buttonPanel, BorderLayout.SOUTH);

        // 테이블에 클릭 이벤트 추가
        boardTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = boardTable.getSelectedRow(); // 클릭된 행의 인덱스를 가져옴
                if (selectedRow != -1) {
                    Object boardNo = tableModel.getValueAt(selectedRow, 0);
                    Object title = tableModel.getValueAt(selectedRow, 1);
                    Object writer = tableModel.getValueAt(selectedRow, 2);

                    // 더미 기능: 클릭된 데이터 출력
                    JOptionPane.showMessageDialog(null, 
                        "선택된 행의 정보:\n번호: " + boardNo + "\n제목: " + title + "\n작성자: " + writer,
                        "행 선택",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

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

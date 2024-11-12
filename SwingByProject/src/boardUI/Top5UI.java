package boardUI;

import boardDAO.Top5DAO;
import boardDTO.Top5DTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Top5UI extends JPanel {
    private static final String[] columnNames = {"EMPNO", "ENAME", "JOB", "SAL"};
    private Top5DAO dao = new Top5DAO();
    private final JTable table;

    public Top5UI() {
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columnNames);
        this.table = new JTable(model);

        // 급여 상위 5명 조회
        List<Top5DTO> result = dao.getTop5();
        for (Top5DTO dto : result) {
            model.addRow(dto.getRow());
        }

        // 테이블을 스크롤 패널에 추가
        this.add(new JScrollPane(table));
    }
}

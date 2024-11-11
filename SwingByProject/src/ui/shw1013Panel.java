package ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.shw1013DAO;
import dto.shw1013DTO;

public class shw1013Panel extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private shw1013DAO employeeDAO;

    public shw1013Panel() {
        // DAO 객체 생성
        employeeDAO = new shw1013DAO();

        // 데이터 조회
        List<shw1013DTO> employeeList = employeeDAO.get();

        // 테이블 데이터 준비
        String[] columns = {"EMPNO", "ENAME", "JOB", "SAL"};
        Object[][] data = new Object[employeeList.size()][columns.length];

        // 데이터 배열에 값 넣기
        for (int i = 0; i < employeeList.size(); i++) {
            shw1013DTO employee = employeeList.get(i);
            data[i][0] = employee.getEmpno();
            data[i][1] = employee.getEname();
            data[i][2] = employee.getJob();
            data[i][3] = employee.getSal();
        }

        // JTable에 데이터 설정
        table = new JTable(data, columns);
        table.setFillsViewportHeight(true);

        // JTable을 JScrollPane에 넣어 스크롤 가능하게 설정
        scrollPane = new JScrollPane(table);

        // JPanel에 JScrollPane 추가
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }
}

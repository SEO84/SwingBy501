package ui;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.pkh0827dao;
import dto.pkh0827dto;

public class Pkh0827Panel extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	private pkh0827dao employeeDAO;
	public Pkh0827Panel() {

		// DAO 객체 생성
		employeeDAO = new pkh0827dao();
		// 데이터 조회
		List<pkh0827dto> employeeList = null;
		try {
			employeeList = employeeDAO.getEmployeeDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 테이블 데이터 준비
		String[] columns = { "ENAME", "JOB", "SAL", "DNAME", "LOC", "GRADE", "LOSAL", "HISAL" };
		Object[][] data = new Object[employeeList.size()][columns.length];

		// 데이터 배열에 값 넣기
		for (int i = 0; i < employeeList.size(); i++) {
			pkh0827dto employee = employeeList.get(i);
			data[i][0] = employee.getEname();
			data[i][1] = employee.getJob();
			data[i][2] = employee.getSal();
			data[i][3] = employee.getDname();
			data[i][4] = employee.getLoc();
			data[i][5] = employee.getGrade();
			data[i][6] = employee.getLosal();
			data[i][7] = employee.getHisal();
		}

		// JTable에 데이터 설정
		table = new JTable(data, columns);
		table.setFillsViewportHeight(true);

		// JTable을 JScrollPane에 넣어 스크롤 가능하게 설정
		scrollPane = new JScrollPane(table);

		// JPanel에 JScrollPane 추가
//		JPanel panel = new JPanel();
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
	}
}

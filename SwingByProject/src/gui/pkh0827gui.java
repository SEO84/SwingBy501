package gui;

import javax.swing.*;

import dao.pkh0827dao;
import dto.pkh0827dto;

import java.awt.*;
import java.sql.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;

public class pkh0827gui extends JFrame {
	private JTable table;
	private JScrollPane scrollPane;
	private pkh0827dao employeeDAO;

	public pkh0827gui() {
		// JFrame 설정
		setTitle("Employee Data");
		setSize(800, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);

		// JFrame에 JPanel 추가
		add(panel);

		// 창 보이기
		setVisible(true);
	}

	public static void main(String[] args) {
		// GUI 실행
		SwingUtilities.invokeLater(() -> new pkh0827gui());
	}
}

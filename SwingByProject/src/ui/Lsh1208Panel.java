package ui;


import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Lsh1208_dao;
import dto.Lsh1208_dto;

public class Lsh1208Panel extends JPanel{
	private static final String[] columnNames = {"JOB","SAL"};
	private Lsh1208_dao dao = new Lsh1208_dao();
	private final JTable table;
	public Lsh1208Panel () {
		var model = new DefaultTableModel(new Object[][] {}, columnNames);
		this.table = new JTable(model);
		
		List<Lsh1208_dto> result = dao.Lsh1208_select();
		for (Lsh1208_dto dto: result) {
			model.addRow(dto.getRow());
		}
		
		this.add(new JScrollPane(table));
	}
}



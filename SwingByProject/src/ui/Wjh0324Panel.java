package ui;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Wjh0324DAO;
import dto.Wjh0324DTO;

public class Wjh0324Panel extends JPanel {
	private static final String[] columnNames = {"EMPNO", "ENAME", "DEPTNO", "DNAME", "LOC"};
	private Wjh0324DAO dao = new Wjh0324DAO();
	private final JTable table;
	public Wjh0324Panel () {
		var model = new DefaultTableModel(new Object[][] {}, columnNames);
		this.table = new JTable(model);
		
		List<Wjh0324DTO> result = dao.get();
		for (Wjh0324DTO dto: result) {
			model.addRow(dto.getRow());
		}
		
		this.add(new JScrollPane(table));
	}
}

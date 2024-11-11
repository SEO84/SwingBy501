package ui;

import javax.swing.JPanel;
import javax.swing.JTable;

import dao.Wjh0324DAO;

public class Wjh0324Panel extends JPanel {
	private static final String[] columnNames = {"EMPNO", "ENAME", "DEPTNO", "DNAME", "LOC"};
	private Wjh0324DAO dao = new Wjh0324DAO();
	private final JTable table;
	public Wjh0324Panel () {
		this.table = new JTable(new Object[][] {}, columnNames); 
		this.add(table);
	}
}

package boardUI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import boardDAO.Wjh0324WholeEMP2DAO;
import boardDTO.Wjh0324Emp2DTO;

public class Wjh0324WholeEmp2Panel extends JPanel {
	private final JTable table;
	private TableModel tableModel;
	private static String[] cols = {
			"empno",
			"ename",
			"job",
			"mgr",
			"hireDate",
			"sal",
			"comm",
			"deptno"
	};
	public Wjh0324WholeEmp2Panel() {
		super();
		this.setLayout(new BorderLayout());
		tableModel = new DefaultTableModel(cols, 0);
		table = new JTable(tableModel);
		
		JScrollPane sp = new JScrollPane(table);
		
		JButton editEmp2Button = new JButton("사원정보 수정");
		JButton signupButton = new JButton("추가하기");
		
		JPanel bottomButtonGroup = new JPanel(new FlowLayout());
		bottomButtonGroup.add(editEmp2Button);
		bottomButtonGroup.add(signupButton);
		
		JPanel borderPanel = new JPanel(new BorderLayout());
		borderPanel.add(sp, BorderLayout.CENTER);
		borderPanel.add(bottomButtonGroup, BorderLayout.SOUTH);
		
		sp.setSize(600, 400);
		sp.setLocation(0, 0);
		
		this.add(borderPanel, BorderLayout.CENTER);
		
		SwingUtilities.invokeLater(() -> {
			loadData();
		});
	}
	
	void loadData() {
		Wjh0324WholeEMP2DAO dao = new Wjh0324WholeEMP2DAO();
		List<Wjh0324Emp2DTO> emp2s = dao.getWholeEmp2();
		
		DefaultTableModel tm = new DefaultTableModel(cols, 0);
		for (Wjh0324Emp2DTO emp2 : emp2s) tm.addRow(emp2.getRowFor(cols));
		
		tableModel = tm;
		table.setModel(tm);
	}
	
}

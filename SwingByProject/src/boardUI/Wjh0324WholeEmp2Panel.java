package boardUI;

import boardDAO.Wjh0324WholeEMP2DAO;
import boardDTO.Wjh0324Emp2DTO;
import common.UI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ui.SignupFrame;

public class Wjh0324WholeEmp2Panel extends JPanel {

	private final JTable table;
	private TableModel tableModel;
	private List<Wjh0324Emp2DTO> emp2List;

	private static String[] cols = { "empno", "ename", "job", "mgr", "hireDate", "sal", "comm", "deptno" };

	private final WindowAdapter reloader = new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent ev) {
			Wjh0324WholeEmp2Panel.this.reloadData();
		}
	};

	public Wjh0324WholeEmp2Panel() {
		super();
		this.setLayout(new BorderLayout());
		tableModel = new DefaultTableModel(cols, 0);
		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) { return false; }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane sp = new JScrollPane(table);
		
		
		JPanel bottomButtonGroup = new JPanel(new FlowLayout());

		bottomButtonGroup.add(UI.button("새로고침", _l -> reloadData()));

		bottomButtonGroup.add(UI.button("사원정보 수정", _l -> {
			int rowIndex = table.getSelectedRow();
			if (rowIndex < 0) {
				JOptionPane.showMessageDialog(this, "회사원을 선택해주세요.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Wjh0324Emp2DTO emp2 = emp2List.get(rowIndex);

			// JFrame win = new JFrame("사원정보 수정");
			// SignupUI signupUI = new SignupUI(win);
			// signupUI.setEditMode(true);
			// signupUI.loadEmployeeData( emp2.empno, emp2.ename, emp2.job, emp2.mgr, emp2.hireDate, emp2.sal, emp2.comm, emp2.deptno );
			
			
			// win.add(signupUI);
			// win.setLocationRelativeTo(this);
			// win.setVisible(true);
			// win.addWindowListener(reloader);
			JFrame win = new SignupFrame("사원정보 수정");
			win.setLocationRelativeTo(this);
			win.addWindowListener(reloader);
			win.setVisible(true);
		}));

		bottomButtonGroup.add(UI.button("등록하기", _l -> {
			// JFrame win = new JFrame("사원정보 등록");
			// SignupUI signupUI = new SignupUI(win);
			// signupUI.setEditMode(false);
			
			// win.add(signupUI);
			// win.setLocationRelativeTo(this);
			// win.setVisible(true);
			// win.addWindowListener(reloader);
			JFrame win = new SignupFrame("사원정보 등록");
			win.setLocationRelativeTo(this);
			win.addWindowListener(reloader);
			win.setVisible(true);
		}));

		bottomButtonGroup.add(UI.button("탈퇴시키기", l -> {
			JFrame jf = new shw1013DeleteUI();
			jf.addWindowListener(reloader);
		}));
		
		JPanel borderPanel = new JPanel(new BorderLayout());
		borderPanel.add(sp, BorderLayout.CENTER);
		borderPanel.add(bottomButtonGroup, BorderLayout.SOUTH);
		
		sp.setSize(600, 400);
		sp.setLocation(0, 0);
		
		this.add(borderPanel, BorderLayout.CENTER);
		
		SwingUtilities.invokeLater(() -> {
			reloadData();
		});
	}

	private void reloadData() {
		Wjh0324WholeEMP2DAO dao = new Wjh0324WholeEMP2DAO();
		emp2List = dao.getWholeEmp2();
		
		DefaultTableModel tm = new DefaultTableModel(cols, 0);
		for (Wjh0324Emp2DTO emp2 : emp2List) tm.addRow(emp2.getRowFor(cols));
		
		tableModel = tm;
		table.setModel(tm);
	}
	
}

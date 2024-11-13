package ui;
import boardUI.Top5UI;
import boardUI.Wjh0324WholeEmp2Panel;
import boardUI.kjh0313boardUI;
import common.UI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame {
		private JPanel current;
	
	private void setPanel(JPanel panel) {
			if (current != null) this.remove(current);
			this.add(current = panel, BorderLayout.CENTER);
			this.revalidate();
			this.repaint();
	}
	
	public MainApp() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 450);
		
		this.setLayout(new BorderLayout());
		
		
		JPanel buttons = new JPanel();
		

		buttons.add(UI.button("서현우", l -> setPanel(new shw1013Panel())));
		buttons.add(UI.button("이상화", l -> setPanel(new Lsh1208Panel())));
		buttons.add(UI.button("김재한", l -> setPanel(new Kjh0313Jpanel())));
		buttons.add(UI.button("박광호", l -> setPanel(new Pkh0827Panel())));
		buttons.add(UI.button("Top5", l -> setPanel(new Top5UI())));
		buttons.add(UI.button("회원", l -> setPanel(new Wjh0324WholeEmp2Panel())));
		buttons.add(UI.button("게시판", l -> setPanel(new kjh0313boardUI())));
		
		buttons.setLayout(new FlowLayout());
		this.add(buttons, BorderLayout.NORTH);

		this.setTitle("세계 최고의 데이터베이스 프로그램!!!!!!!!!!");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		new MainApp();
	}

}

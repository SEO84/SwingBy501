package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Jpanel.Kjh0313Jpanel;

public class MainApp extends JFrame {
	
	private JPanel current;
	private static JButton button(String label, ActionListener l) {
		JButton button = new JButton(label);
		if (l != null) button.addActionListener(l);
		return button;
	}
	
	public MainApp() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 450);
		
		this.setLayout(new BorderLayout());
		
		
		JPanel buttons = new JPanel();
		
		buttons.add(button("원종호", l -> {
			if (current != null) MainApp.this.remove(current);
			MainApp.this.add(current = new Wjh0324Panel(), BorderLayout.CENTER);
            MainApp.this.revalidate();
            MainApp.this.repaint();
		}));
		
		buttons.add(button("서현우", l -> {
			if (current != null) MainApp.this.remove(current);
			MainApp.this.add(current = new shw1013Panel(), BorderLayout.CENTER);
            MainApp.this.revalidate();
            MainApp.this.repaint();
		}));
		buttons.add(button("이상화", null));
		buttons.add(button("김재한", l -> {
			if (current != null) MainApp.this.remove(current);
			MainApp.this.add(current = new Kjh0313Jpanel(), BorderLayout.CENTER);
            MainApp.this.revalidate();
            MainApp.this.repaint();
		}));
		buttons.add(button("박광호", l -> {
			if (current != null) MainApp.this.remove(current);
			MainApp.this.add(current = new Pkh0827Panel(), BorderLayout.CENTER);
            MainApp.this.revalidate();
            MainApp.this.repaint();
		}));
		
		
		buttons.setLayout(new FlowLayout());
		
		this.add(buttons, BorderLayout.NORTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}

	public static void main(String[] args) {
		new MainApp();
	}

}

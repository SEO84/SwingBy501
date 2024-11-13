package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import boardUI.Top5UI;
import boardUI.kjh0313boardUI;

public class MainApp2 extends JFrame {

    private JPanel current;

    private static JButton button(String label, ActionListener l) {
        JButton button = new JButton(label);
        if (l != null) button.addActionListener(l);
        return button;
    }

    private void setPanel(JPanel panel) {
        if (current != null) MainApp2.this.remove(current);
        MainApp2.this.add(current = panel, BorderLayout.CENTER);
        MainApp2.this.revalidate();
        MainApp2.this.repaint();
    }

    public MainApp2() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 450);

        this.setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        buttons.add(button("원종호", l -> {
            setPanel(new Wjh0324Panel());
        }));
        buttons.add(button("서현우", l -> {
            setPanel(new shw1013Panel());
        }));
        buttons.add(button("이상화", l -> {
            setPanel(new Lsh1208Panel());
        }));
        buttons.add(button("김재한", l -> {
            setPanel(new Kjh0313Jpanel());
        }));
        buttons.add(button("박광호", l -> {
            setPanel(new Pkh0827Panel());
        }));
        buttons.add(button("Top5", l -> {
            setPanel(new Top5UI());
        }));
        buttons.add(button("게시판!", l -> {
            setPanel(new kjh0313boardUI());
        }));

        this.add(buttons, BorderLayout.NORTH);

        // EMP 리스트 버튼을 하단에 추가
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(button("회원조회", l -> {
            setPanel(new EmpListPanel());
        }));
        bottomPanel.add(button("회원가입", l -> {
            SignupUI signupUI = new SignupUI();
            signupUI.setEditMode(false);
            setPanel(signupUI);
        }));

        // 회원정보 수정 버튼 추가
        bottomPanel.add(button("회원정보수정", l -> {
            SignupUI signupUI = new SignupUI();
            signupUI.setEditMode(true);
            // 예시: 기존 회원 정보를 불러와서 설정
            signupUI.loadEmployeeData(1234, "새 이름", "새 직업", 0, "2023-10-01", 5000.0, 500.0, 10);
            setPanel(signupUI);
        }));

        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setTitle("세계 최고의 데이터베이스 프로그램!!!!!!!!!!");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainApp2();
    }
}
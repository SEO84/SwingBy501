package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import boardDAO.shw1013deleteEmpDAO;

public class MainApp2 extends JFrame {

    private JPanel current;

    private static JButton button(String label, java.awt.event.ActionListener l) {
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

        // 상단 버튼 패널
        JPanel buttons = new JPanel();
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

        buttons.setLayout(new FlowLayout());
        this.add(buttons, BorderLayout.NORTH);

        // 하단 탈퇴 버튼 패널
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("탈퇴");
        deleteButton.addActionListener(e -> showDeleteDialog());
        bottomPanel.add(deleteButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setTitle("세계 최고의 데이터베이스 프로그램!!!!!!!!!!");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // 탈퇴 창 띄우기
    private void showDeleteDialog() {
        // 탈퇴 창 생성
        JFrame deleteFrame = new JFrame("회원 탈퇴");
        deleteFrame.setSize(300, 150);
        deleteFrame.setLayout(new BorderLayout());
        deleteFrame.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new FlowLayout());
        javax.swing.JLabel label = new javax.swing.JLabel("EmpNo 입력: ");
        javax.swing.JTextField empNoField = new javax.swing.JTextField(15);
        inputPanel.add(label);
        inputPanel.add(empNoField);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("탈퇴");
        JButton cancelButton = new JButton("취소");

        confirmButton.addActionListener(e -> {
            String empNo = empNoField.getText().trim();
            if (empNo.isEmpty()) {
                JOptionPane.showMessageDialog(deleteFrame, "EmpNo를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int empNoInt = Integer.parseInt(empNo); // EmpNo를 정수로 변환
                deleteEmployee(empNoInt); // 실제 삭제 메서드 호출
                deleteFrame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(deleteFrame, "EmpNo는 숫자여야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> deleteFrame.dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        deleteFrame.add(inputPanel, BorderLayout.CENTER);
        deleteFrame.add(buttonPanel, BorderLayout.SOUTH);
        deleteFrame.setVisible(true);
    }

    // DAO를 사용한 탈퇴 로직
    private void deleteEmployee(int empNo) {
    	shw1013deleteEmpDAO dao = new shw1013deleteEmpDAO(); // DAO 객체 생성
        try {
            dao.deleteEmployee(empNo); // DAO의 삭제 메서드 호출
            JOptionPane.showMessageDialog(this, "EmpNo " + empNo + " 삭제 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "EmpNo " + empNo + " 삭제 실패: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MainApp2();
    }
}

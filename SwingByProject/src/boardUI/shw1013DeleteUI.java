package boardUI;

import boardDAO.shw1013deleteEmpDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

public class shw1013DeleteUI extends JFrame {

    public shw1013DeleteUI() {
        super("회원 탈퇴");
        this.setSize(300, 150);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("EmpNo 입력: ");
        JTextField empNoField = new JTextField(15);
        inputPanel.add(label);
        inputPanel.add(empNoField);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("탈퇴");
        JButton cancelButton = new JButton("취소");

        confirmButton.addActionListener(e -> {
            String empNo = empNoField.getText().trim();
            if (empNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "EmpNo를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int empNoInt = Integer.parseInt(empNo); // EmpNo를 정수로 변환
                deleteEmployee(empNoInt); // 실제 삭제 메서드 호출
                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "EmpNo는 숫자여야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> this.dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    // DAO를 사용한 탈퇴 로직
    private void deleteEmployee(int empNo) {
        shw1013deleteEmpDAO dao = new shw1013deleteEmpDAO(); // DAO 객체 생성
        boolean deleteResult = dao.deleteEmployee(empNo); // DAO의 삭제 메서드 호출
        if (deleteResult) {
          JOptionPane.showMessageDialog(this, "EmpNo " + empNo + " 삭제 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(this, "EmpNo " + empNo + " 삭제 실패, EmpNo가 존재하지 않거나, 오류가 발생했습니다." , "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}

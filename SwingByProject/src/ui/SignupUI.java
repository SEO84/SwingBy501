package ui;

import javax.swing.*;
import java.awt.*;
import service.EmpUpdateService;

public class SignupUI extends JPanel {

    private JTextField empNoField;
    private JTextField enameField;
    private JTextField jobField;
    private JTextField mgrField;
    private JTextField hiredateField;
    private JTextField salField;
    private JTextField commField;
    private JTextField deptnoField;
    private JButton submitButton;
    private boolean isEditMode = false;
    private EmpUpdateService updateService;

    public SignupUI() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 컴포넌트 간의 간격 설정
        gbc.fill = GridBagConstraints.HORIZONTAL;

        empNoField = new JTextField(15);
        enameField = new JTextField(15);
        jobField = new JTextField(15);
        mgrField = new JTextField(15);
        hiredateField = new JTextField(15);
        salField = new JTextField(15);
        commField = new JTextField(15);
        deptnoField = new JTextField(15);

        addLabelAndField("사원 번호:", empNoField, gbc, 0);
        addLabelAndField("이름:", enameField, gbc, 1);
        addLabelAndField("직업:", jobField, gbc, 2);
        addLabelAndField("매니저:", mgrField, gbc, 3);
        addLabelAndField("입사일:", hiredateField, gbc, 4);
        addLabelAndField("급여:", salField, gbc, 5);
        addLabelAndField("커미션:", commField, gbc, 6);
        addLabelAndField("부서 번호:", deptnoField, gbc, 7);

        submitButton = new JButton("제출");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(submitButton, gbc);

        updateService = new EmpUpdateService();

        submitButton.addActionListener(e -> {
            int empNo = Integer.parseInt(empNoField.getText());
            String ename = enameField.getText();
            String job = jobField.getText();
            int mgr = Integer.parseInt(mgrField.getText());
            String hiredate = hiredateField.getText();
            double sal = Double.parseDouble(salField.getText());
            double comm = Double.parseDouble(commField.getText());
            int deptno = Integer.parseInt(deptnoField.getText());

            if (isEditMode) {
                boolean success = updateService.updateEmployee(empNo, ename, job, mgr, hiredate, sal, comm, deptno);
                if (success) {
                    JOptionPane.showMessageDialog(this, "회원정보가 성공적으로 수정되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(this, "회원정보 수정에 실패했습니다.");
                }
            } else {
                // 회원가입 로직 추가
            }
        });
    }

    private void addLabelAndField(String labelText, JTextField textField, GridBagConstraints gbc, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(textField, gbc);
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        submitButton.setText(isEditMode ? "수정" : "제출");
    }

    public void loadEmployeeData(int empNo, String ename, String job, int mgr, String hiredate, double sal, double comm, int deptno) {
        empNoField.setText(String.valueOf(empNo));
        enameField.setText(ename);
        jobField.setText(job);
        mgrField.setText(String.valueOf(mgr));
        hiredateField.setText(hiredate);
        salField.setText(String.valueOf(sal));
        commField.setText(String.valueOf(comm));
        deptnoField.setText(String.valueOf(deptno));
    }
}
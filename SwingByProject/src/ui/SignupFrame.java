package ui;

import common.Util;
import java.awt.*;
import java.sql.Date;
import javax.swing.*;
import service.EmpInsertService;
import service.EmpUpdateService;

public class SignupFrame extends JFrame {

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
    private final JPanel panel;


    public Integer parseInt2(String s) {
        if (s.isEmpty()) return null;
        return Integer.valueOf(s);
    }

    public Double parseDouble2(String s) {
        if (s.isEmpty()) return null;
        return Double.valueOf(s);
    }

    public SignupFrame(String title) {
        super(title);
        // this.setLayout();
        panel = new JPanel(new GridBagLayout());
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
        panel.add(submitButton, gbc);


        submitButton.addActionListener(e -> {
            String empnoText = empNoField.getText();
            if (empnoText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "사원번호는 필수 입력항목입니다.", "경고", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int empNo = Integer.parseInt(empnoText);
                String ename = enameField.getText();
                String job = jobField.getText();
                Integer mgr = parseInt2(mgrField.getText());
                String hiredateText = hiredateField.getText();
                Date hiredate = hiredateText.trim().isEmpty() ? null : Date.valueOf(hiredateText);
                Double sal = parseDouble2(salField.getText());
                Double comm = parseDouble2(commField.getText());
                Integer deptno = parseInt2(deptnoField.getText());
                if (isEditMode) {
                    EmpUpdateService updateService = new EmpUpdateService();
                    boolean success = updateService.updateEmployee(empNo, ename, job, mgr, hiredate, sal, comm, deptno);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "회원정보가 성공적으로 수정되었습니다.");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "회원정보 수정에 실패했습니다.");
                    }
                } else {
                    // 회원가입 로직 추가
                    EmpInsertService insertService = new EmpInsertService();
                    boolean success = insertService.registerEmployee(empNo, ename, job, mgr, hiredate, sal, comm, deptno);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "회원정보가 성공적으로 등록되었습니다.");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "회원정보 등록에 실패했습니다.");
                    }
                }
            } catch (IllegalArgumentException error) {
                JOptionPane.showMessageDialog(this, "적절하지 않은 입력입니다.", "경고", JOptionPane.ERROR_MESSAGE);
                return;
            }
        });
        this.setSize(500, 600);
        this.add(panel);
    }

    private void addLabelAndField(String labelText, JTextField textField, GridBagConstraints gbc, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(textField, gbc);
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        submitButton.setText(isEditMode ? "수정" : "제출");
    }

    // public void loadEmployeeData(int empNo, String ename, String job, int mgr, String hiredate, double sal, double comm, int deptno) {
    public void loadEmployeeData(int empNo, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        empNoField.setText(Util.toTextValue(empNo));
        enameField.setText(ename);
        jobField.setText(job);
        mgrField.setText(Util.toTextValue(mgr));
        hiredateField.setText(Util.toTextValue(hiredate));
        salField.setText(Util.toTextValue(sal));
        commField.setText(Util.toTextValue(comm));
        deptnoField.setText(Util.toTextValue(deptno));
    }
}
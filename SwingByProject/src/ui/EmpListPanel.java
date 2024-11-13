package ui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import dao.EmpDAO;

public class EmpListPanel extends JPanel {

    public EmpListPanel() {
        this.setLayout(new BorderLayout());

        EmpDAO empDAO = new EmpDAO();
        List<String> empList = empDAO.getEmp2List();

        JTextArea textArea = new JTextArea();
        for (String emp : empList) {
            textArea.append(emp + "\n");
        }
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
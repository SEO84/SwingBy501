package Jpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dao.Kjh0313DAO;
import dto.Kjh0313DTO;

public class Kjh0313Jpanel extends JPanel {
    private JTextField nameField;
    private JTextArea resultArea;
    
    public Kjh0313Jpanel() {
        // 레이아웃 설정
        setLayout(new BorderLayout());
        
        // 상단 패널 생성
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        
        // 입력 필드 및 버튼 생성
        JLabel nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        
        // 입력 필드와 버튼을 패널에 추가
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(searchButton);
        
        // 결과 표시용 텍스트 영역 생성
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        
        // 상단 패널과 스크롤 패널 추가
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // 검색 버튼 클릭 시 액션 리스너 추가
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
    }
    
    // 검색 기능 구현
    private void performSearch() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // DAO를 통해 데이터 가져오기
        Kjh0313DAO dao = new Kjh0313DAO();
        List<Kjh0313DTO> dataList = dao.get();
        
        // 결과 초기화
        resultArea.setText("");
        
        boolean matchFound = false;
        for (Kjh0313DTO dto : dataList) {
            if (dto.ename.equalsIgnoreCase(name)) {
                resultArea.append("Name: " + dto.ename + ", Job: " + dto.job +
                                  ", Dept No: " + dto.deptno + ", Dept Name: " + dto.dname +
                                  ", Location: " + dto.loc + "\n");
                matchFound = true;
            }
        }
        
        if (!matchFound) {
            resultArea.append("No matching records found for the name: " + name);
        }
    }
    
    public static void main(String[] args) {
        // JFrame 생성 및 설정
        JFrame frame = new JFrame("Kjh0313DAO Name Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        
        // JPanel 추가
        frame.add(new Kjh0313Jpanel());
        
        // JFrame 표시
        frame.setVisible(true);
    }
}


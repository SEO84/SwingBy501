package boardUI;

import boardDTO.shw1013BoardDTO;
import java.awt.*;
import javax.swing.*;

public class PostWindow extends JFrame {
    private JTextField titleField; // 제목을 표시할 텍스트 필드
    private JLabel writerLabel;    // 작성자를 표시할 레이블
    private JLabel regDateLabel;   // 작성일을 표시할 레이블
    private JTextArea contentArea; // 내용을 표시할 텍스트 영역
    private JButton editButton;    // 수정 버튼
    private JButton deleteButton;  // 삭제 버튼

    public PostWindow(shw1013BoardDTO post) {
        setTitle("게시물 상세보기");
        setSize(600, 500);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 제목 및 작성자/작성일 영역
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        // 제목 영역
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("제목:"), BorderLayout.WEST);
        titleField = new JTextField(post.getTitle());
        titleField.setEnabled(false); // 텍스트 필드 비활성화 (읽기 전용)
        titlePanel.add(titleField, BorderLayout.CENTER);
        
        // 작성자 및 작성일 영역
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        writerLabel = new JLabel("작성자: " + post.getWriter());
        regDateLabel = new JLabel("작성일: " + post.getRegDate());
        infoPanel.add(writerLabel);
        infoPanel.add(Box.createHorizontalStrut(20)); // 간격 추가
        infoPanel.add(regDateLabel);

        // 제목과 작성자/작성일 영역을 headerPanel에 추가
        headerPanel.add(titlePanel, BorderLayout.NORTH);
        headerPanel.add(infoPanel, BorderLayout.SOUTH);

        // 내용 표시 영역
        contentArea = new JTextArea(post.getContent());
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEnabled(false); // 텍스트 영역 비활성화 (읽기 전용)
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setPreferredSize(new Dimension(350, 300));

        // 하단 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        editButton = new JButton("수정");
        deleteButton = new JButton("삭제");
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // 패널에 구성 요소 추가
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {

    }
}

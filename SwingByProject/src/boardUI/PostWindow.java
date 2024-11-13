package boardUI;

import boardDTO.shw1013BoardDTO;
import boardDAO.PostDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PostWindow extends JFrame {
    // 필드 정의
    private JTextField titleField;
    private JLabel writerLabel;
    private JLabel regDateLabel;
    private JTextArea contentArea;
    private JButton editButton;
    private JButton deleteButton;

    private shw1013BoardDTO post;
    private PostDAO postDAO;
    private boolean isEditing = false;

    public PostWindow(shw1013BoardDTO post) {
        this.post = post;
        this.postDAO = new PostDAO();
        setTitle("게시물 상세보기");
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("제목:"), BorderLayout.WEST);
        titleField = new JTextField(post.getTitle());
        titleField.setEnabled(false);
        titlePanel.add(titleField, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        writerLabel = new JLabel("작성자: " + post.getWriter());
        regDateLabel = new JLabel("작성일: " + post.getRegDate());
        infoPanel.add(writerLabel);
        infoPanel.add(Box.createHorizontalStrut(20));
        infoPanel.add(regDateLabel);

        headerPanel.add(titlePanel, BorderLayout.NORTH);
        headerPanel.add(infoPanel, BorderLayout.SOUTH);

        contentArea = new JTextArea(post.getContent());
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEnabled(false);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setPreferredSize(new Dimension(350, 300));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        editButton = new JButton("수정");
        deleteButton = new JButton("삭제");
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // 수정 버튼 액션 리스너
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditing) {
                    enableEditing(true);
                } else {
                    saveChanges();
                    enableEditing(false);
                }
            }
        });

        // 삭제 버튼 액션 리스너 추가
     // 삭제 버튼 액션 리스너
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    PostWindow.this,
                    "정말로 삭제하시겠습니까?",
                    "삭제 확인",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean isDeleted = postDAO.deletePost(post.getBoardNo());
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(PostWindow.this, "삭제되었습니다.");
                        dispose(); // 창 닫기
                    } else {
                        JOptionPane.showMessageDialog(PostWindow.this, "삭제에 실패했습니다.");
                    }
                }
            }
        });


        setVisible(true);
    }

    private void enableEditing(boolean enable) {
        titleField.setEnabled(enable);
        contentArea.setEnabled(enable);
        editButton.setText(enable ? "수정 완료" : "수정");
        isEditing = enable;
    }

    private void saveChanges() {
        post.setTitle(titleField.getText());
        post.setContent(contentArea.getText());
        postDAO.updatePost(post);
    }
}

package boardUI;

import boardDAO.pkh027boardDAO;
import boardDTO.shw1013BoardDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class pkh0827UI {
	private JFrame frame;
	private JTextField titleField;
	private JTextArea contentArea;
	private JTextField empNoField;

	public pkh0827UI() {
		// UI 설정
		frame = new JFrame("게시글 작성");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		// frame.setBounds(100, 100, 400, 400);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("제목:");
		lblTitle.setBounds(30, 30, 100, 30);
		frame.getContentPane().add(lblTitle);

		titleField = new JTextField();
		titleField.setBounds(120, 30, 250, 30);
		frame.getContentPane().add(titleField);
		titleField.setColumns(10);

		JLabel lblContent = new JLabel("내용:");
		lblContent.setBounds(30, 70, 100, 30);
		frame.getContentPane().add(lblContent);

		contentArea = new JTextArea();
		contentArea.setBounds(120, 70, 250, 150);
		frame.getContentPane().add(contentArea);

		JLabel lblEmpNo = new JLabel("EMP_NO:");
		lblEmpNo.setBounds(30, 230, 100, 30);
		frame.getContentPane().add(lblEmpNo);

		empNoField = new JTextField();
		empNoField.setBounds(120, 230, 250, 30);
		frame.getContentPane().add(empNoField);

		JButton btnSubmit = new JButton("작성");
		btnSubmit.setBounds(150, 280, 100, 30);
		frame.getContentPane().add(btnSubmit);

		// 게시글 작성 버튼 클릭 시 이벤트 처리
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = titleField.getText();
				String content = contentArea.getText();
				String empNoStr = empNoField.getText();

				if (title.isEmpty() || content.isEmpty() || empNoStr.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// EMP_NO는 숫자만 입력됨
				int empNo = Integer.parseInt(empNoStr);

				shw1013BoardDTO board = new shw1013BoardDTO();
				board.setTitle(title);
				board.setContent(content);
				board.setEmpNo(empNo);

				pkh027boardDAO boardDAO = new pkh027boardDAO();
				try {
					int result = boardDAO.addBoard(board, "oracle.jdbc.driver.OracleDriver",
							"jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
					if (result > 0) {
						JOptionPane.showMessageDialog(frame, "게시글이 성공적으로 작성되었습니다.", "성공",
								JOptionPane.INFORMATION_MESSAGE);
						// UI 초기화
						titleField.setText("");
						contentArea.setText("");
						empNoField.setText("");
					} else {
						JOptionPane.showMessageDialog(frame, "게시글 작성에 실패했습니다.", "실패", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, "데이터베이스 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 화면 보이기
		frame.setVisible(true);
	}

	// public static void main(String[] args) {
	// 	// UI 실행
	// 	new pkh0827UI();
	// }
}

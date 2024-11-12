package boardDTO;

import java.util.Date;

public class shw1013BoardDTO {
    private int boardNo;           // 게시판 번호 (Primary Key)
    private String title;          // 제목 (Not Null, 최대 100자)
    private String content;        // 내용 (Not Null, 최대 1000자)
    private String writer;         // 작성자 (Not Null)
    private int empNo;             // 외래 키 (emp 테이블의 empNo 참조)
    private Date regDate;          // 등록일 (기본값 sysdate)

    // 기본 생성자
    public shw1013BoardDTO() {}

    // 매개변수를 받는 생성자
    public shw1013BoardDTO(int boardNo, String title, String content, String writer, int empNo, Date regDate) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.empNo = empNo;
        this.regDate = regDate;
    }

    // Getter와 Setter
    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardNo=" + boardNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", empNo=" + empNo +
                ", regDate=" + regDate +
                '}';
    }
}

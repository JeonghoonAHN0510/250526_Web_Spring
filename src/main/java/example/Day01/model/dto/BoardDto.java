package example.Day01.model.dto;

public class BoardDto {
    // 1. 멤버변수 : private 필수, DB 테이블 속성과 일치
    private int bno;
    private String bcontent;
    private String bwriter;

    // 2. 생성자 : 기본생성자, 전체생성자 + 커스텀 생성자
    public BoardDto() { }
    public BoardDto(int bno, String bcontent, String bwriter) {
        this.bno = bno;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    } // func end
    public BoardDto(String bcontent, String bwriter) {
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    } // func end

    // 3. 메소드 : getter / setter / toString()
    public int getBno() { return bno; }
    public void setBno(int bno) { this.bno = bno; }
    public String getBcontent() { return bcontent; }
    public void setBcontent(String bcontent) { this.bcontent = bcontent; }
    public String getBwriter() { return bwriter; }
    public void setBwriter(String bwriter) { this.bwriter = bwriter; }

    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", bcontent='" + bcontent + '\'' +
                ", bwriter='" + bwriter + '\'' +
                '}';
    } // func end
} // class end

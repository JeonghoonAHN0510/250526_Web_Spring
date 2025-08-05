package 종합.예제11.model.dto;

public class BoardDto {
    // 1. 멤버변수
    private int bno;
    private String bcontent;
    private String bwriter;
    // 2. 생성자 : 기본 생성자 + 전체 생성자
    public BoardDto() { }
    public BoardDto(int bno, String bcontent, String bwriter) {
        this.bno = bno;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    } // func end
    // 3. 메소드
    public int getBno() { return bno; }
    public void setBno(int bno) { this.bno = bno; }
    public String getBcontent() { return bcontent; }
    public void setBcontent(String bcontent) { this.bcontent = bcontent; }
    public String getBwriter() { return bwriter; }
    public void setBwriter(String bwriter) { this.bwriter = bwriter; }
    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", bcontent='" + bcontent + '\'' +
                ", bwriter='" + bwriter + '\'' +
                '}';
    } // func end
} // class end

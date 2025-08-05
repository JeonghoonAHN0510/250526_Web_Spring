package 종합.실습3.model.dto;

public class WaitingDto {
    // 1. 멤버변수
    private int wno;
    private String wphone;
    private int wcount;
    // 2. 생성자
    public WaitingDto() { }
    public WaitingDto(int wno, String wphone, int wcount) {
        this.wno = wno;
        this.wphone = wphone;
        this.wcount = wcount;
    } // func end
    // 3. 메소드
    public int getWno() { return wno; }
    public void setWno(int wno) { this.wno = wno; }
    public String getWphone() { return wphone; }
    public void setWphone(String wphone) { this.wphone = wphone; }
    public int getWcount() { return wcount; }
    public void setWcount(int wcount) { this.wcount = wcount; }
    @Override
    public String toString() {
        return "WaitingDto{" +
                "wno=" + wno +
                ", wphone='" + wphone + '\'' +
                ", wcount=" + wcount +
                '}';
    } // func end
} // class end

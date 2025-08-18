package Days.Day02.exam1;

public class BoardDto {
    private int bno;
    private String btitle;
    public BoardDto() { }
    public BoardDto(int bno, String btitle) {
        this.bno = bno;
        this.btitle = btitle;
    }
    public int getBno() { return bno; }
    public void setBno(int bno) { this.bno = bno; }
    public String getBtitle() { return btitle; }
    public void setBtitle(String btitle) { this.btitle = btitle; }
    @Override
    public String toString() {
        return "BoradDto{" +
                "bno=" + bno +
                ", btitle='" + btitle + '\'' +
                '}';
    }
} // class end

import java.util.Date;

public class Board {
    private int num;
    private String title;
    private String content;
    private String writer;
    private Date date;

    // Board 의 프라이빗 생성자.
    // 객체에 넣을 수 있는 필드는 모두 바깥 클래스 생성자에 넣음. toString 했을때 정보 볼 수 있음.
    private Board(BoardBuilder builder) {
        this.num = builder.num;
        this.title = builder.title;
        this.content = builder.content;
        this.date = builder.date;
    }

    public static class BoardBuilder {
        // 생성자 호출할때 생성될 필수 필드.
        private int num;
        private String title;
        private String content;
        private String writer;
        private Date date;

        // 외부 클래스에서 부를 수 있도록 퍼블릭.
        public BoardBuilder(String content, String writer, Date date) {
            this.content = content;
            this.writer = writer;
            this.date = date;
        }


    }
}

import java.util.Date;

//@Data
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
        public BoardBuilder(Date date, String writer, String content) {
            this.content = content;
            this.writer = writer;
            this.date = date;
        }

        public BoardBuilder addNum(int num) {
            this.num = num;
            return this; // boardBuilder 객체 자체를 반환.
        }

        public BoardBuilder addTitle(String title) {
            this.title = title;
            return this;
        }

        // BoardBuilder 클래스 안이지만 Board 타입을 쓸 수 있음.
        public Board build() {
            return new Board(this);
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", date=" + date +
                '}';
    }
}

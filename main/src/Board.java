import lombok.Data;
import lombok.Setter;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Setter
public class Board {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private String date;


    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getBno() {
        return this.bno = bno;
    }

    // Board 의 프라이빗 생성자.
    // 객체에 넣을 수 있는 필드는 모두 바깥 클래스 생성자에 넣음. toString 했을때 정보 볼 수 있음.
    private Board(BoardBuilder builder) {
        this.bno = builder.bno;
        this.title = builder.title;
        this.content = builder.content;
        this.date = builder.date;
    }

    @Data
    @Setter
    public static class BoardBuilder {
        // 생성자 호출할때 생성될 필수 필드.
        private int bno;
        private String title;
        private String content;
        private String writer; // 디비 연결 되면 자동으로 누군지 알 수 있도록.
        private String date;

        // 외부 클래스에서 부를 수 있도록 퍼블릭.
        public BoardBuilder(int bno, String writer, String content) {
            this.bno = bno;
            this.content = content;
            this.writer = writer;
            // 게시물 생성될때 시간 자동 생성하기.
            Instant now = Instant.now(Clock.systemUTC());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSSSSSS z"); // 나노초까지
            ZonedDateTime nowTime = now.atZone(ZoneId.of("Asia/Seoul"));
            this.date = nowTime.format(formatter);
        }

        // 선택적으로 제목 추가 가능.
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
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", date=" + date +
                '}';
    }
}

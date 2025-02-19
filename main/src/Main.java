import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {


        // 보드 매니저를 실행시킨다.
Instant now = Instant.now(Clock.systemUTC());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSSSSSS z"); // 나노초까지
        ZonedDateTime nowTime = now.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(nowTime.format(formatter));


    }
}


public class Main {
    public static void main(String[] args) {

BoardManager manager = new BoardManager();
manager.initializeBoardMap(); // boarMap 을 초기화 해주기.
boolean status = true;


while(status) {
    int userInput = manager.getMenuInput();

    if(userInput == 1) {

        System.out.println("You have chosen menu 1.");
        manager.create();

    } else if(userInput ==2){
        int bno = manager.menu2_read(); // 먼저 2번 특정 게시물 출력, 사용자가 선택한 게시물 번호 반환.
        int subMenu = manager.getSubmenuOf2(); // 자동으로 보조 메뉴 호출
        manager.submenuOf2(subMenu, bno);


    } else if(userInput ==3) {
        manager.menu3_clear();
    } else {
        System.out.println("Invalid output! Program shutting down. ");
        status = false;

    } //////// 이외 값이나 잘못된 타입이 입력됐을때 예외 처리 필요
}

    }
}

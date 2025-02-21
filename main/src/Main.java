
public class Main {
    public static void main(String[] args) {

BoardManager manager = new BoardManager();
manager.initializeBoardMap(); // boarMap 을 초기화 해주기.

boolean status = true;

while(status) {
    try {
        int userInput = manager.getMainMenu();

        if(userInput == 1) {
            System.out.println(Constants.UPDATE_BOARD);
            Board createdBoard = manager.menu1_create();
            int submenu = manager.getSubmenuOf1();
            manager.runSubMenuOf1(submenu, createdBoard);

        } else if(userInput ==2){
            int bno = manager.menu2_read(); // 먼저 2번 특정 게시물 출력, 사용자가 선택한 게시물 번호 반환.
            int submenu = manager.getSubmenuOf2(); // 자동으로 보조 메뉴 호출
            manager.runSubmenuOf2(submenu); // submenu2 - 1 도 자동 호출.

        } else if(userInput ==3) {
            manager.menu3_clear();
        } else if(userInput == 4){
            manager.menu4_exit();
        } else {
            System.out.println(ExceptionStrings.invalidFormatMsg);
        }
    } catch (NumberFormatException e) {
        e.printStackTrace();
        System.out.println(ExceptionStrings.invalidFormatMsg);
    }

}

    }
}

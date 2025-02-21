import org.w3c.dom.ls.LSOutput;

import java.util.*;

// 보드 매니저는 게시물을 관리한다. 생성 삭제 업데이트 등의 기능을 수행한다.
public class BoardManager {

    Scanner sc = new Scanner(System.in);

    // 예외 처리 시 이용할 메시지
    final static String invalidFormatMsg = "잘못된 형식의 입력입니다. 제시된 메뉴의 숫자 중 선택하여 다시 입력하십시오.";
    final static String invalidNumberMsg = "허용되는 숫자가 아닙니다. 제시된 메뉴 숫자 중 하나를 입력하십시오.";


    public Map<Integer, Board> boardMap = new HashMap<>();

    // 초기화 메소드  // 처음 포스트 몇개 넣기!!
    public void initializeBoardMap() {
        boardMap.put(1, new Board.BoardBuilder(1, "ssg", "It's a nice weather").build());
        boardMap.put(2, new Board.BoardBuilder(2, "ssg2", "It's sunny today.").build());
    }


    // listBoards = 모든 게시물 목록을 출력한다.
    // int printMenu = 메뉴를 출력한다.
    // getMenuInput = 사용자의 값을 입력 받는다.

    // 메소드들과 사용자 입력 메뉴 매핑하기.
//    Map<Integer, Runnable> methodMap = new HashMap<>();
//
//    private void mapMethods() {
//        methodMap.put(1, this::create); // 패스할걸 여기서 미리 정해야 하나?
//    }

    // 처음 사용자로부터 메뉴 입력 받기. 입력 정수 반환.
    int getMenuInput() {
        System.out.println("Main Menu: 1. Create | 2. Read | 3. Clear | 4.Exit ");
        System.out.println("Choose your option: ");

        // 허용할 숫자 목록
        List<Integer> allowed = Arrays.asList(1, 2, 3, 4);
        while(true) {
            try {
                int inNum = Integer.parseInt(sc.nextLine().trim());
                if(allowed.contains(inNum)){ // 사용자 입력값이 허용되는 메뉴중에 있으면
                    return inNum; // return 되면 while 루프 종료됨.
                } else { // 입력 받은 숫자가 허용되는 숫자중 없으면
                    System.out.println(invalidNumberMsg);
                }
            } catch (NumberFormatException e) { // 숫자를 입력받지 않은 경우 처리.
                e.printStackTrace();
                System.out.println(invalidFormatMsg);

            }
        }

    }

    // option 1
    // 입력 받고 게시물 하나 생성하기. 보드 객체 반환.
    Board create() {
        System.out.println("Number of this post?: ");

        // 보드 하나 생성하여 리스트에 순서대로 저장하기
        int bno = 0;

        while(true) {
            try {
                bno = Integer.parseInt(sc.nextLine().trim());
                break; // 정상적으로 입력되면 루프 종료하기.
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println(invalidFormatMsg);
            }
        }

        System.out.println("내용: ");
        String content = null;
        while(true) {
            try {
                content = sc.nextLine();
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("작성자: ");
        String writer = null;
        while(true) {
            try {
                writer = sc.nextLine();
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println(invalidFormatMsg);
            }
        }


        Board board = new Board.BoardBuilder(bno, writer, content).build();
//        boardMap.put(bno, board);
//        boardMap.forEach((key, value) -> System.out.println("Post number: " + key + "Content: " + value));

            return board;
    }

    int getSubmenuOf1() {
        System.out.println("보조 메뉴: 1.OK | 2.Cancel" );
        System.out.println("메뉴 선택:" );

        // 1.ok- save to boardMap 2. cancel - cancel user input.
        while(true) {
            try {
                int input = Integer.parseInt(sc.nextLine());
                if(input == 1 || input == 2) return input; // return user's submenu input
                else {
                    System.out.println(invalidNumberMsg);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println(invalidFormatMsg); // wrong format
                // try again
            }
        }

    }

    void runSubMenuOf1(int submenu, Board board) {
        try {
            if (submenu == 1) { // 저장하기.
                // 저장 누르면 저장하기.
                boardMap.put(board.getBno(), board); /// key 와 사용자가 입력한 bno 같음 -> 나중에 변경.
                // 저장 후 전체 게시물 출력하기. --> 사용자가 지정한 bno 나오지 않고 맵의 key 출력됨.
                boardMap.forEach((key, value) -> System.out.println("Post number: " + key + "Content: " + value));
            } else if(submenu == 2) {
                System.out.println("Canceled operation. ");
            }
        } catch (NumberFormatException e) {
           e.printStackTrace();
            System.out.println(invalidNumberMsg);
        }
    }

    // 메뉴 2번 - 특정 번호의 보드 출력
    // bno 반환하기.
    int menu2_read(){
        System.out.println("[Printing your post]");
        System.out.println("Enter your post number: ");
        int bno =  Integer.parseInt(sc.nextLine().trim());
        Board selectedBoard = boardMap.get(bno); // 사용자가 선택한 게시물 번호
        System.out.println(selectedBoard); // 특정 게시물 출력

        return bno;
    }

    int getSubmenuOf2() {
        System.out.println("Choose your submenu: 1. Update | 2. Delete | 3. List");
        int subMenu =  Integer.parseInt(sc.nextLine().trim());
        return subMenu;
    }

    // 2번 보조 메뉴 - 이미 게시물 번호 선택한 상태 - 1.내용, 작성자 업데이트
    void submenuOf2(int subMenu, int bno) {

        Board selectedBoard = boardMap.get(bno); // 사용자가 선택한 게시물

        if(subMenu == 1) {
            // Update
            // 보드맵의 객체 하나하나의 필드값을 바꿀 수 있도록하기.
            // 사용자에게 입력 받기
            System.out.println("Update your content: ");
            String updatedContent = sc.nextLine();
            System.out.println("Update your author name: ");
            String updatedWriter = sc.nextLine();

            selectedBoard.setContent(updatedContent);
            selectedBoard.setWriter(updatedWriter);

        }else if(subMenu == 2) {
            // Delete
            int removeId = bno; // 인자로 받은 bno
            boardMap.remove(removeId); //  보드가 아니라 값을 반환함.
            //System.out.println("Post number " + removedBoard.getBno() + "is deleted. ");
            System.out.println("List of posts now: ");
            boardMap.forEach((key, content) -> System.out.println("Post number: " + key + "Content: " + content));


        }else if(subMenu == 3) {
            System.out.println("Listing all posts: ");
            boardMap.forEach((key, content) -> System.out.println("Post number: " + key + "Content: " + content));

        } else {
            System.out.println("주어진 값만 입력하세요");
            getSubmenuOf2();
        } //***** 숫자가 아니라 문자 입력했을때 예외 준비하기
    }

    void menu3_clear() {
        System.out.println("Are you sure you want to delete all posts?: 1. Yes | 2. No");
        int input =  Integer.parseInt(sc.nextLine().trim());
        if(input == 1) {
            boardMap.clear();
            System.out.println("All posts have been deleted");
        } else if(input ==2) {
            System.out.println("Canceled delete all operation. ");
        } else {
            System.out.println("please input a valid number. ");
        }// *********** exception 처리 문자 입력했을때 해야 할듯?

        // 남은 게시물 출력
        System.out.println("Remaining posts: ");
        boardMap.forEach((key, content) -> System.out.println("Post number: " + key + "Content: " + content));
    }


}

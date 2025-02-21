import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class BoardManager {
    // 보드 매니저는 보드를 관리한다. 생성 삭제 업데이트 등의 기능을 수행한다.

     Scanner sc = new Scanner(System.in);

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
                    System.out.println("허용되는 메뉴가 아닙니다. 제시된 메뉴 숫자 중 하나를 입력하십시오.");
                }
            } catch (NumberFormatException e) { // 숫자를 입력받지 않은 경우 처리.
                e.printStackTrace();
                System.out.println("잘못된 입력입니다. 제시된 메뉴 숫자 중 선택하여 다시 입력하십시오." );

            }
        }

    }

    // option 1
    // 입력 받고 게시물 하나 생성하기.
    public void create() {
        System.out.println("Number of this post?: ");

        // 보드 하나 생성하여 리스트에 순서대로 저장하기
        int bno = 0;
        try {
            bno = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Writer of this post?: ");
        String writer = sc.nextLine();
        System.out.println("Content of this post: ");
        String content = sc.nextLine();

        Board board = new Board.BoardBuilder(bno, writer, content).build();
        boardMap.put(bno, board);
        boardMap.forEach((key, value) -> System.out.println("Post number: " + key + "Content: " + value));


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

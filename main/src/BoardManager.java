import java.util.*;

public class BoardManager {
    // 보드 매니저는 보드를 관리한다. 생성 삭제 업데이트 등의 기능을 수행한다.

     Scanner sc = new Scanner(System.in);

    // 게시물 목록을 담을 boards ArrayList.
    public List<Board> boards01 = new ArrayList<>();
    public Map<Integer, Board> boardMap = new HashMap<>();
    ///////////// 처음 포스트 몇개 넣기!!


    // listBoards = 모든 게시물 목록을 출력한다.
    // int printMenu = 메뉴를 출력한다.
    // getMenuInput = 사용자의 값을 입력 받는다.

    // 메소드들과 사용자 입력 메뉴 매핑하기.
    Map<Integer, Runnable> methodMap = new HashMap<>();

    private void mapMethods() {
        methodMap.put(1, this::create); // 패스할걸 여기서 미리 정해야 하나?
    }

    // 처음 사용자로부터 메뉴 입력 받기. 입력 정수 반환.
    int getMenuInput() {
        System.out.println("Main Menu: 1. Create | 2. Read | 3. Clear | 4.Exit ");
        System.out.println("Choose your option: ");

        // 사용자 입력 받기
        int inNum = sc.nextInt();
        return inNum;
    }

    // option 1
    // 입력 받고 게시물 하나 생성하기.
    public void create() {

        // 보드 하나 생성하여 리스트에 순서대로 저장하기
        int bno = sc.nextInt();
        String writer = sc.nextLine();
        String content = sc.nextLine();
        // 바로 입력 받기
        Board board = new Board.BoardBuilder(bno, writer, content).build();
        boards01.add(board);
        System.out.println(boards01);

    }

    // 메뉴 2번 - 특정 번호의 보드 출력
    // bno 반환하기.
    int menu2_read(){
        boardMap.forEach((key, content) -> System.out.println("Post number: " + key + "Content: " + content));
    }

    int getSubmenuOf2() {
        System.out.println("Choose your submenu: 1. Update | 2. Delete | 3. List");
        int subMenu = sc.nextInt();
        return subMenu;
    }

    // 2번 보조 메뉴 - 1.내용, 작성자 업데이트
    void submenuOf2(int subMenu) {
        System.out.println("Enter your post number: ");
        int bno = sc.nextInt();
        Board selectedBoard = boardMap.get(bno); // 사용자가 선택한 게시물 번호

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

            // getSubmenu again
            getSubmenuOf2();
        }else if(subMenu == 2) {
            // Delete
            int removeId = bno; // 인자로 받은 bno
            Board removedBoard = boardMap.remove(removeId);
            System.out.println("Post number " + removedBoard.getBno() + "is deleted. ");
            System.out.println("List of posts now: ");
            boardMap.forEach((key, content) -> System.out.println("Post number: " + key + "Content: " + content));

            getSubmenuOf2();
        }else if(subMenu == 3) {
            System.out.println("You have canceled. ");
            getSubmenuOf2();
        } else {
            System.out.println("주어진 값만 입력하세요");
            getSubmenuOf2();
        } //***** 숫자가 아니라 문자 입력했을때 예외 준비하기
    }

   // option 3
    void menu3_clear() {
        System.out.println("Are you sure you want to delete all posts?: 1. Yes | 2. No");
        int input = sc.nextInt();
        if(input == 1) {
            methodMap.clear();
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

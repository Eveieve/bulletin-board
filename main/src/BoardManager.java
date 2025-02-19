import java.util.*;

public class BoardManager {
    // 보드 매니저는 보드를 관리한다. 생성 삭제 업데이트 등의 기능을 수행한다.

     Scanner sc = new Scanner(System.in);

    // 게시물 목록을 담을 boards ArrayList.
    public List<Board> boards01 = new ArrayList<>();
    public Map<Integer, Board> boardMap = new HashMap<>();
    // listBoards = 모든 게시물 목록을 출력한다.
    // int printMenu = 메뉴를 출력한다.
    // getMenuInput = 사용자의 값을 입력 받는다.

    // 메소드들과 사용자 입력 메뉴 매핑하기.
    Map<Integer, Runnable> methodMap = new HashMap<>();

    private void mapMethods() {
        methodMap.put(1, this::create); // 패스할걸 여기서 미리 정해야 하나?
    }


    // 메뉴 2번 - 특정 번호의 보드 출력
    // bno 반환하기.
    int printBoards(){
        int bno = sc.nextInt();
        for(Board board: boards01) {
            if(board.getBno() == bno)
            System.out.println(board);
        }
        return bno;
    }

    int getSubmenuOf2() {
        System.out.println("1. Update | 2. Delete | 3. List");
        int subMenu = sc.nextInt();
        return subMenu;
    }


    // 2번 보조 메뉴 - 1.업데이트
    void subMenuOf2(int subMenu, int bno) {

        if(subMenu == 1) {
            // Update
            Board selectedBoard = boardMap.get(bno);
            // 보드맵의 객체 하나하나의 필드값을 바꿀 수 있도록하기.
            // 사용자에게 입력 받기
            System.out.println("Update your content: ");
            String updatedContent = sc.nextLine();

        }
    }

    int getMenuInput() {
        System.out.println("1, 2, 3, 4, ? : ");

        Scanner sc = new Scanner(System.in);
        // 사용자 입력 받기
        int inNum = sc.nextInt();
        return inNum;
    }


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



}

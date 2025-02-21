
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// 보드 매니저는 게시물을 관리한다. 생성 삭제 업데이트 등의 기능을 수행한다.
public class BoardManager {

    Scanner sc = new Scanner(System.in);

    
    List<Board> boardList = new ArrayList<>();
    AtomicInteger keyGenerator = new AtomicInteger(1); // 1씩 자동 증가하는 키.
    // 초기화 메소드  // 처음 포스트 몇개 넣기!!
 
    public void initializeBoardList(){
        boardList.add(new Board.BoardBuilder(1, "공지사항", "관리자", "사이트 업데이트 공지").build());
        boardList.add(new Board.BoardBuilder(2, "자유게시판", "사용자1", "오늘 날씨 좋네요").build());
        boardList.add(new Board.BoardBuilder(3, "질문게시판", "사용자2", "자바 질문 있습니다").build());
        
    }

    // 처음 사용자로부터 메뉴 입력 받기. 입력 정수 반환.
    int getMainMenu() {
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
                   ExceptionStrings.printInvalidNumberMsg();
                }
            } catch (NumberFormatException e) { // 숫자를 입력받지 않은 경우 처리.
               ExceptionStrings.printInvalidFormatMsg(e);
            }
        }

    }

    // 입력 받고 게시물 하나 생성하기. 보드 객체 반환.
    Board menu1_create() {

        System.out.println(Constants.TITLE);
        String title = " ";
        while (true) {
            try {
                title = sc.nextLine();
                break;
            } catch (NumberFormatException e) {
                ExceptionStrings.printInvalidFormatMsg(e);
            }
        }

        System.out.println(Constants.CONTENT);
        String content = " ";
        while (true) {
            try {
                content = sc.nextLine();
                break;
            } catch (NumberFormatException e) {
                ExceptionStrings.printInvalidFormatMsg(e);
            }
        }

        System.out.println(Constants.WRITER);
        String writer = " ";
        while (true) {
            try {
                writer = sc.nextLine();
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // submenu에 따라 저장할지 취소할지 결정
        if (getSubmenuOf1() == 1) {
            Board createdBoard = new Board.BoardBuilder(title, writer, content).build();

            // 그냥 바로 뒤에 보드 추가하기.
            boardList.add(createdBoard);

            // 전체 게시물 출력하기.
            printAllBoards();
            // 생성된 보드 객체 반환.
            return createdBoard;
        } else if (getSubmenuOf1() == 2) System.out.println("작업을 취소하였습니다. ");

        return null;
    }

    int getSubmenuOf1() {
        System.out.println("보조 메뉴: 1.OK | 2.Cancel" );
        System.out.println("메뉴 선택:" );

        // 1.ok- save to boardList 2. cancel - cancel user inadd.
        while(true) {
            try {
                int inadd = Integer.parseInt(sc.nextLine());
                if(inadd == 1 || inadd == 2) return inadd; // return user's submenu inadd
                else {
                    ExceptionStrings.printInvalidNumberMsg();
                }
            } catch (NumberFormatException e) {
               ExceptionStrings.printInvalidFormatMsg(e);
            }
        }

    }

//    void runSubMenuOf1(int submenu, Board board) {
//        try {
//            if (submenu == 1) { // 저장하기.
//                // 저장 누르면 저장하기.
//                int nowBno = keyGenerator.getAndIncrement();
//                boardList.add(nowBno, board); /// key 와 사용자가 입력한 bno 같음 -> 나중에 변경.
//                board.setBno(nowBno); // 자동 생성되는 키값으로 bno 필드도 세팅.
//                // 저장 후 전체 게시물 출력하기. --> 사용자가 지정한 bno 나오지 않고 맵의 key 출력됨.
//                boardList.forEach((key, value) -> System.out.println("Post number: " + key + "Content: " + value));
//            } else if(submenu == 2) {
//                System.out.println("Canceled operation. ");
//            }
//        } catch (NumberFormatException e) {
//           e.printStackTrace();
//            System.out.println(invalidNumberMsg);
//        }
//    }

    // 메뉴 2번 - 특정 번호의 보드 출력
    // bno 반환하기.
    int menu2_read(){
        System.out.println("[게시물 읽기]");

        int bno = 0;

        while(true) {
            try {
                try {
                    System.out.print("bno: ");
                    bno = Integer.parseInt(sc.nextLine().trim());
                    Board selectedBoard = boardList.get(bno);

                    System.out.println(Constants.hashLine);
                    System.out.println(Constants.BNO_KOREAN + selectedBoard.getBno());
                    System.out.println(Constants.TITLE + selectedBoard.getTitle());
                    System.out.println(Constants.CONTENT + selectedBoard.getContent());
                    System.out.println(Constants.WRITER + selectedBoard.getWriter());
                    System.out.println(Constants.DATE + selectedBoard.getDate());
                    break;
                } catch (NumberFormatException e) {
                    ExceptionStrings.printInvalidFormatMsg(e);
                }
            } catch (NullPointerException e) {
                //selectedBoard = Objects.requireNonNull(boardList.get(bno), "null 값입니다. ");
                System.out.println("해당 게시물이 없습니다. 다시 게시물 번호를 입력하시오.");
            }
        }

        return bno;
    }

    int getSubmenuOf2() {
        System.out.println(Constants.dashLine);
        System.out.println("보조 메뉴: 1. Update | 2. Delete | 3. List");
        System.out.print("bno: ");
        int subMenu = 0;
        try {
            subMenu = Integer.parseInt(sc.nextLine().trim());
            if(subMenu == 1 || subMenu == 2 || subMenu == 3) return subMenu;
            else {
                System.out.println(ExceptionStrings.invalidNumberMsg);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println(ExceptionStrings.invalidFormatMsg);
        }
        return subMenu;
    }

    // 2번 보조 메뉴 - 이미 게시물 번호 선택한 상태 - 1.내용, 작성자 업데이트
    void runSubmenuOf2( int bno) {

        Board selectedBoard = boardList.get(bno); // 사용자가 선택한 게시물

        while(true) {
          int subMenu =   getSubmenuOf2(); // 올바른 값 입력될때까지 계속 menu2의 submenu 받기.
            try {
                if(subMenu == 1) {  // Update
                    // 보드맵의 객체 하나하나의 필드값을 바꿀 수 있도록하기.
                    // 사용자에게 입력 받기
                    System.out.println("[수정 내용 입력]");

                    String updatedTitle = null;
                    try {
                        System.out.print(Constants.TITLE);
                        updatedTitle = sc.nextLine();
                    } catch (NumberFormatException e) {
                        ExceptionStrings.printInvalidFormatMsg(e);
                    }
                    String updatedContent = null;
                    try {
                        System.out.print(Constants.CONTENT);
                        updatedContent = sc.nextLine();
                    } catch (NumberFormatException e) {
                        ExceptionStrings.printInvalidFormatMsg(e);
                    }
                    String updatedWriter = null;
                    try {
                        System.out.print(Constants.WRITER);
                        updatedWriter = sc.nextLine();
                    } catch (NumberFormatException e ) {
                        ExceptionStrings.printInvalidFormatMsg(e);
                    }

                    // 1. ok 하면
                    try { // submenu 다시 - 나중에 메소드로 빼기.
                        System.out.println("보조 메뉴: 1. OK | 2. Cancel");
                        int inadd = Integer.parseInt(sc.nextLine().trim());
                        if(inadd == 1) runSubmenu2_1(selectedBoard, updatedTitle, updatedContent, updatedWriter);
                        else if(inadd == 2) System.out.println("작업을 취소합니다.");
                        else {
                            ExceptionStrings.printInvalidNumberMsg();
                        }
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                }else if(subMenu == 2) { //  submenu 2 - Delete
                    int removeId = bno; // 인자로 받은 bno
                    // boardList.remove(removeId); //  보드가 아니라 값을 반환함.
                    System.out.println("Post number " + removeId + "is deleted. ");
                    System.out.println(Constants.LIST_ALL_BOARDS);
                    System.out.println(Constants.dashLine);
                    System.out.printf("%-3s %-10s %-12s %s%n", "no", "writer", "date", "title");
                    System.out.println(Constants.dashLine);
                    //boardList.forEach((key, content) -> System.out.println("Post number: " + key + "Content: " + content));
                    for (Board board : boardList) {
                        System.out.printf("%-3d %-10s %-12s %s%n",
                                board.getBno(), board.getWriter(), board.getDate(), board.getTitle());
                    }
                    break;
                }else if(subMenu == 3) { // submenu 3 - List - 전체 게시물 출력하기
                   printAllBoards();
                    break;
                } else {
                    ExceptionStrings.printInvalidNumberMsg();
                    // again
                }
            } catch (NumberFormatException e) {
                ExceptionStrings.printInvalidFormatMsg(e);
                // again
            }
        }

    }

    void runSubmenu2_1(Board selectedBoard, String updatedTitle, String updatedContent, String updatedWriter) { // menu2 - 1(update) - OK
        //System.out.println("보조 메뉴: 1. OK | 2. Cancel");
        selectedBoard.setTitle(updatedTitle);
        selectedBoard.setContent(updatedContent);
        selectedBoard.setWriter(updatedWriter);
        System.out.println("게시물이 수정 되었습니다. ");
    }
    void menu3_clear() {
        System.out.println("Are you sure you want to delete all posts?: 1. Yes | 2. No");
        int inadd =  Integer.parseInt(sc.nextLine().trim());
        if(inadd == 1) {
            boardList.clear();
            System.out.println("All posts have been deleted");
        } else if(inadd ==2) {
            System.out.println("Canceled delete all operation. ");
        } else {
            System.out.println("please inadd a valid number. ");
        }// *********** exception 처리 문자 입력했을때 해야 할듯?

        // 남은 게시물 출력
        System.out.println("Remaining posts: ");
        for (Board board : boardList) {
            System.out.printf("%-3d %-10s %-12s %s%n",
                    board.getBno(), board.getWriter(), board.getDate(), board.getTitle());
        }
    }

    void menu4_exit() {
        System.out.println("** 게시판 종료 **");
        System.exit(0);
    }

    void printAllBoards() {
        System.out.println(Constants.dashLine);
        System.out.println(Constants.LIST_ALL_BOARDS);
        System.out.println(Constants.dashLine);
        System.out.printf("%-3s %-10s %-12s %s%n", "no", "writer", "date", "title\n");
        System.out.println(Constants.dashLine);
        for (Board board : boardList) {
            System.out.printf("%-3d %-10s %-12s %s%n",
                    board.getBno(), board.getWriter(), board.getDate(), board.getTitle());
        }
    }
}

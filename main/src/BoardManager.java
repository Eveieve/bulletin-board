
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// 보드 매니저는 게시물을 관리한다. 생성 삭제 업데이트 등의 기능을 수행한다.
public class BoardManager {

    Scanner sc = new Scanner(System.in);

    List<Board> boardList = new ArrayList<>();
    AtomicInteger keyGenerator = new AtomicInteger(1); // 1씩 자동 증가하는 키.

    // 처음 보드 몇개 넣기.
    public void initializeBoardList() { // bno 자동생성되기.
        boardList.add(new Board.BoardBuilder(keyGenerator.getAndIncrement(), "게시판에 오신 것을 환영합니다. ", "winter", "사이트 업데이트 공지").build());
        boardList.add(new Board.BoardBuilder(keyGenerator.getAndIncrement(), "올 겨울은 많이 춥습니다.", "winter", "오늘 날씨 좋네요").build());
    }

    void runProgram() {
        boolean status = true;

        while (status) {
            try {
                int userInput = getMainMenu();

                if (userInput == 1) {
                    System.out.println();
                    System.out.println(Constants.CREATE_BOARD);
                    menu1_create();

                } else if (userInput == 2) {
                    int userBno = menu2_read(); // 먼저 2번 특정 게시물 출력, 사용자가 선택한 게시물 번호 반환.
                    runSubmenuOf2(userBno); // submenu2 - 1 도 자동 호출.

                } else if (userInput == 3) {
                    menu3_clear();
                } else if (userInput == 4) {
                    menu4_exit();
                } else {
                    System.out.println(ExceptionStrings.invalidFormatMsg);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println(ExceptionStrings.invalidFormatMsg);
            }

        }
    }

    // 처음 사용자로부터 메뉴 입력 받기. 입력 정수 반환.
    int getMainMenu() {
        System.out.println(Constants.dashLine);
        System.out.println("메인 메뉴: 1. Create | 2. Read | 3. Clear | 4.Exit ");
        System.out.print("메뉴 선택: " + "\n");

        // 허용할 숫자 목록
        List<Integer> allowed = Arrays.asList(1, 2, 3, 4);
        while (true) {
            try {
                int inNum = Integer.parseInt(sc.nextLine().trim());
                if (allowed.contains(inNum)) { // 사용자 입력값이 허용되는 메뉴중에 있으면
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
        System.out.print(Constants.TITLE);
        String title = " ";
        while (true) {
            try {
                title = sc.nextLine();
                break;
            } catch (NumberFormatException e) {
                ExceptionStrings.printInvalidFormatMsg(e);
            }
        }

        System.out.print(Constants.CONTENT);
        String content = " ";
        while (true) {
            try {
                content = sc.nextLine();
                break;
            } catch (NumberFormatException e) {
                ExceptionStrings.printInvalidFormatMsg(e);
            }
        }

        System.out.print(Constants.WRITER);
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
            int boardBno = keyGenerator.getAndIncrement();
            createdBoard.setBno(boardBno); // 자동 생성되는 키값으로 bno 필드도 세팅.
            // 전체 게시물 출력하기.
            printAllBoards();
            // 생성된 보드 객체 반환.
            return createdBoard;
        } else if (getSubmenuOf1() == 2) System.out.println("작업을 취소하였습니다. ");

        return null;
    }

    int getSubmenuOf1() {
        System.out.println(Constants.dashLine);
        System.out.println("보조 메뉴: 1.OK | 2.Cancel");
        System.out.print("메뉴 선택: " + "\n");

        // 1.ok- save to boardList 2. cancel - cancel user input.
        while (true) {
            try {
                int input = Integer.parseInt(sc.nextLine().trim());
                if (input == 1 || input == 2) return input; // return user's submenu input
                else {
                    ExceptionStrings.printInvalidNumberMsg();
                }
            } catch (NumberFormatException e) {
                ExceptionStrings.printInvalidFormatMsg(e);
            }
        }

    }

    // 메뉴 2번 - 특정 번호의 보드 출력
    // 사용자가 선택한 보드 번호 반환하기.
    int menu2_read() {
        System.out.println();
        System.out.println("[게시물 읽기]");
        System.out.println();
        System.out.println(Constants.dashLine);
        int bno = 0;
        while (true) {
            try {
                try {
                    System.out.print("bno: "); // 읽을 게시물의 bno
                    bno = Integer.parseInt(sc.nextLine().trim());
                    int finalBno = bno;

                    // stream() 데이터 스트림 형태 변환 -> 필터 조건 -> 일치하는 게시물중 첫번째 게시물 -> 아니면 널을 반환
                    Board selectedBoard = boardList.stream().filter(board -> board.getBno() == finalBno).findFirst().orElse(null);

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
            if (subMenu == 1 || subMenu == 2 || subMenu == 3) return subMenu;
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
    void runSubmenuOf2(int userBno) {

        Board selectedBoard = boardList.stream().filter(board -> board.getBno() == userBno).findFirst().orElse(null);

        while (true) {
            int subMenu = getSubmenuOf2(); // 올바른 값 입력될때까지 계속 menu2의 submenu 받기.
            try {
                if (subMenu == 1) {  // Update
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
                    } catch (NumberFormatException e) {
                        ExceptionStrings.printInvalidFormatMsg(e);
                    }

                    // 1. ok 하면
                    try { // submenu 다시 - 나중에 메소드로 빼기.
                        System.out.println("보조 메뉴: 1. OK | 2. Cancel");
                        System.out.print("메뉴 선택: ");
                        int input = Integer.parseInt(sc.nextLine().trim());
                        if (input == 1) runSubmenu2_1(selectedBoard, updatedTitle, updatedContent, updatedWriter);
                        else if (input == 2) System.out.println("작업을 취소합니다.");
                        else {
                            ExceptionStrings.printInvalidNumberMsg();
                        }
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                } else if (subMenu == 2) { //  submenu 2 - Delete
                    System.out.println("[게시물 삭제]");
                    int removeId = userBno; // 인자로 받은 bno, 0 is 1

                    boardList.remove(removeId - 1); // remove 1 board
                    // 게시판 전체 출력
                    printAllBoards();

                    break;
                } else if (subMenu == 3) { // submenu 3 - List - 전체 게시물 출력하기
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
        selectedBoard.setTitle(updatedTitle);
        selectedBoard.setContent(updatedContent);
        selectedBoard.setWriter(updatedWriter);
        System.out.println("게시물이 수정 되었습니다. ");
    }

    void menu3_clear() {
        System.out.println("[게시물 전체 삭제]");
        System.out.println(Constants.dashLine);
        try {
            int userInput = getSubMenuOf3();
            if (userInput == 1) {
                boardList.clear(); // 전체 삭제하기.
                printAllBoards(); // 전체 출력해주기.
            } else if (userInput == 2) {
                System.out.println("작업을 취소합니다. ");
            } else {
                ExceptionStrings.printInvalidNumberMsg();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    int getSubMenuOf3() {
        System.out.println("보조메뉴: 1. Yes | 2. No");
        System.out.print("메뉴 선택: " + "\n");
        return Integer.parseInt(sc.nextLine().trim());
    }

    void menu4_exit() {
        System.out.println("** 게시판 종료 **");
        System.exit(0);
    }

    void printAllBoards() {
        System.out.println(Constants.dashLine);
        System.out.println();
        System.out.println(Constants.LIST_ALL_BOARDS);
        System.out.println(Constants.dashLine);
        System.out.println();
        System.out.printf("%-3s %-10s %-12s %s%n", "no", "writer", "date", "title\n");
        System.out.println(Constants.dashLine);
        for (Board board : boardList) {
            System.out.printf("%-3d %-10s %-12s %s%n",
                    board.getBno(), board.getWriter(), board.getDate(), board.getTitle());
        }
        System.out.println();
    }
}

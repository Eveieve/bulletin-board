import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BoardManager {
    // 보드 매니저는 보드를 관리한다. 생성 삭제 업데이트 등의 기능을 수행한다.


    // 게시물 목록을 담을 boards ArrayList.
    public List<Board> boards01 = new ArrayList<>();

    // listBoards = 모든 게시물 목록을 출력한다.
    // int printMenu = 메뉴를 출력한다.
    // getMenuInput = 사용자의 값을 입력 받는다.

    void printBoards(){

    }

    void printMenu() {
        System.out.println("1, 2, 3, 4, ? : ");

    }

    //  사용자가 선택하는 메뉴값을 반음. 값을 반환.
    int getMenuInput() {
        Scanner sc = new Scanner(System.in);
        // 사용자 입력 받기
        System.out.print("작성자 입력: ");
        String writer = sc.nextLine();

        System.out.print("내용 입력: ");
        String content = sc.nextLine();
        return 0;
    }


    // menu 1 -// 입력된 값으로 보드 객체 생성. 생성된 보드 반환.
    Board getBoardInput(Date date, String writer, String content) {
        // 입력된 값으로 보드 객체 생성한다.
        Board board = new Board.BoardBuilder(date, writer, content).build();
        System.out.println(board);
        return board;
    }

    // menu 1 - 보조옵션1 - 할때 호출되는 메소드. 게시물 생성
    // 어떤 boards 리스트에 저장할건지 선택하고, 보드 객체를 그 리스트에 추가.
    void create(List boards, Board board) {
        // 보드 하나 생성하여 리스트에 순서대로 저장하기
        boards.add(board);
        System.out.println(boards);
    }



}

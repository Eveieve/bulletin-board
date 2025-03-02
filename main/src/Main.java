
public class Main {
    public static void main(String[] args) {

        BoardManager manager = new BoardManager();
        manager.connectServer(); // connect to DB
        manager.initializeBoardList();
        manager.runProgram();

    }
}

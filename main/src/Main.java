
public class Main {
    public static void main(String[] args) {

BoardManager manager = new BoardManager();
int userInput = manager.getMenuInput();

if(userInput == 1) {
    System.out.println("You have chosen menu 1.");
    manager.create();

} else if(userInput ==2){
    manager.menu2_read();
    int subMenu = manager.getSubmenuOf2();
    manager.submenuOf2(subMenu);
} else if(userInput ==3) {
    manager.menu3_clear();
}
    }
}

# Bugs
1. mainmenu2 골랐을때 바로 게시물 목록을 읽는다 -> 바로 읽지 않고 submenu가 먼저 호출 되어야 한다. 
2. submenu 2 - List - number of post? 숫자 입력했을때 모든 게시물이 출력된다 -> 특정 게시물만 출력 되어야 한다. 
3. menu 1- create 로 게시물 생성했을때 모든 포스트의 content가 입력한 내용 oioi로 변경되어 출력된다.
![img.png](img.png)
4. 제일 처음 메뉴 입력 받을때 예외처리 되지 않는다. -> 숫자가 아닌 문자 들어올 경우 예외 처리 해준다. 
5. 제일 처음 메뉴 입력 받을 시 제시된 숫자가 입력된 경우 프로그램이 비정상적으로 종료 된다 -> 잘못된 숫자가 입력된 경우 예외 처리 한다. 

# Refactor 

# Things to Improve
1. 프린트 형식 정확하게 맞추기. 
2. 입력 예외 처리하기. 
3. 인터페이스나/ 추상 클래스

4. 예외 처리 한것을 로그 파일에 기록하기. 
5. 입력된 게시물을 파일로 저장하기. 

# Things to Remember
- throw new ExceptionClass 하면 프로그램 종료됨. 

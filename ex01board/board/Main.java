package ex01board.board;

import java.util.Scanner;

public class Main {
	
	// 이상한 기능 설명
	// 게시판에 글을쓰고 게시물을 거래소에 가면 5천~10만 사이의 랜덤 가격이 정해짐.
	// 게시글에 boolean타입 변수를 넣고 게시물 생성때 false, 가격 감정 받을 시 true로 변경됨
	// ↑ 한 게시글로 다시 가격감정 받지 못하게하고, 가진 게시물중 판매가 가능한 글만 표기하는데에도 사용함 
	// 타 패키지에서 블랙잭 카드게임(퍼온거)을 불러와 플레이하는 기능
	// 게시물을 판매하고 크레딧을 받아서 블랙잭에서 판돈으로 써먹을 수 있음 (판돈 0원으로 해도 플레이가능함)

	public static Scanner input = new Scanner(System.in);
	// 퍼블릭 스태틱 적어줘야 메서드 바깥에 놔둬도 끌어짐
	public static Member[] members = new Member[100];
	// 배열 선언을 할건데, 자료형[]배열명 = new 자료형[크기]; 인데 이거를
	// Member클래스에서 members 라는 배열이 선언되었다. = 그리고 그 Member에 100칸을 할당했다. 라는 뜻으로 씀
	public static Board[] boards = new Board[1000];
	// 마찬가지로 배열 1000칸 생성
	public static Item[] items = new Item[1000];
	// 상점 상품 1000칸
	public static Member loginCheck; // 로그인 정보 유지용
	// 메인메서드 위에는 퍼블릭스태틱 넣기

	public static void main(String[] args) {

	
		Member admin = new Member();
		admin.id = "123";
		admin.pw = "123";
		admin.nick = "test";
		admin.email = "123";
		admin.price = 50000;
		loginCheck = admin;
		// 테스트용 아이디, 미리 로그인 되어있음

		boolean run = true;
		while (run) {
			System.out.println(loginCheck.nick+"님 안녕하세요\n");
			System.out.println("===== 메인 메뉴 ======");
			System.out.println("\n1.회원정보 | 2.게시판 | 3.거래소\n");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("회원정보 메뉴로 이동합니다.");
				Member member = new Member();
				loginCheck = member.menu(input, members, loginCheck);
				// 멤버쪽 메뉴 호출할때 로그인체크 값을 메인에서 돌려받질 않아버리는 바람에
				// 계속 로그인 체크가 null로 떠버려서 몇시간을 날린 대참사가 벌어짐
				// 배열정보를 로그인체크안에 넣고 통짜로 배달하는식으로 한다고 생각해야할듯
				break;
			case 2:
				System.out.println("게시판으로 이동합니다.");
				Board board = new Board();
				board.boardMenu(input, members, loginCheck, boards);
				break;
			case 3:
				System.out.println("거래소로 이동합니다.");
				Market market = new Market();
				market.marketMain(input, members, boards, items, loginCheck);
				break;
			default:
				System.out.println("1~3");

			}// 스위치

		} // 와일

	}// 메인 메서드

}// 클래스
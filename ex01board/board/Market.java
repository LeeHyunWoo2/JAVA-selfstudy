package ex01board.board;

import java.io.IOException;
import java.util.Scanner;

import ex01board.blackJack.BlackJack;
import ex01board.blackJack.Rule;

public class Market {

	int price; // 가격

	public void marketMain(Scanner input, Member[] members, Board[] boards, Item[] items, Member loginCheck) {

		boolean run = true;
		while (run) {
			System.out.println("\n사기꾼 마켓에 오신것을 환영합니다\n");
			System.out.println("게시글을 NFT화해서 팔아먹는곳 입니다."); // 이런거 진짜로 있음
			System.out.println("\n1.가격감정 \t2.상품목록\n3.인벤토리 \t4.상품판매\n5.블랙잭    \t6.나가기\n");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("가격감정 코너로 이동합니다.");
				priceSet(input, boards, loginCheck);
				break;
			case 2:
				Item item = new Item();
				item.itemList(items);
				shop(input, items, loginCheck);
				break;
			case 3:
				System.out.println("내 인벤토리를 확인합니다.");
				inventory(input, members, boards, loginCheck);
				break;
			case 4:
				System.out.println("보유한 상품을 판매합니다.");
				sell(boards, loginCheck, input);
				break;
			case 5:
				System.out.println("블랙잭을 하러 떠납니다.");
				System.out.println("1. 판돈걸기 /t 2. 도망치기");
				int select2 = input.nextInt();
				switch (select2) {
				case 1:
					System.out.println("판돈을 얼마 거시겠습니까? 보유 크레딧 : " + loginCheck.price);
					int stake = input.nextInt();
					if (stake <= loginCheck.price) {
						System.out.println(stake + " 크레딧을 걸고 블랙잭을 시작합니다.");
						loginCheck.price -= stake;
						System.out.println("현재 크레딧 : " + loginCheck.price);
						System.out.println("현재 판돈 : " + stake);
						System.out.println("출처 https://github.com/SeungHyeonTak/Java_BlackJack");
						// Q : BlackJack 패키지와 어떻게 승패결과를 연동했는가?
						// A : 타 패키지의 클래스는 맨위에 import 저걸로 불러온다. 스캐너와 같은것도 그렇게 불러옴
						//     거기서 승, 패, 무승부, 딜러 버스트, 플레이어 버스트 값을 임의로 만든 boolean 타입 변수에 저장한다.
						//     게임 종료 결과가 3개의 클래스에 나뉘어 있기 때문에 각 자리에 서로 다른 이름의 변수를 저장함
						//     그리고 타입명 클래스명.함수명 <- 이렇게 하면 불러짐 ex) Boolean Rule.victory
						//     맨 아래 gameSet 메서드에서 victory, draw, bust에 컨트롤 클릭하면 원래 위치 나옴

						gamble(loginCheck); // 게임을 구동하는 메서드를 불러오기
						gameSet(loginCheck, stake); // 게임 결과 판단, 출력 메서드
						break;
					} else {
						System.out.println("돈이없습니다.");
					}
					break;
				case 2:
					System.out.println("잘가요");
					break;
				default:
					System.out.println("1~2");
				}// 스위치
				break;

			case 6:
				System.out.println("메인메뉴로 복귀합니다.");
				run = false;
				break;
			default:
				System.out.println("1~5");
			}// switch

		} // 와일

	}// 마켓 메뉴 메서드종료

	// ===========================================

	public void priceSet(Scanner input, Board[] boards, Member loginCheck) {

		boolean run = true;
		while (run) {
			System.out.println("가격 감정 메뉴입니다.");
			System.out.println("감정을 원하는 자신의 글 번호를 입력해주세요.");
			for (int i = 0; i < boards.length; i++) {
				if (boards[i] != null && boards[i].nick == loginCheck.nick) {
					System.out.println("[" + (i + 1) + "] : " + boards[i].title + "\n");
				} // if
			} // for
			System.out.print(">>>");
			int select = input.nextInt();
			for (int i = 0; i < boards.length; i++) {
				if (boards[i] != null) {
					if (boards[i].num == (select - 1) && boards[i].nick == loginCheck.nick && boards[i].eva == false) {
						System.out.println("비밀번호를 입력해주세요.");
					} else {
						System.out.println("사용자가 작성한 글이 아니거나, 이미 감정받은 글 입니다.");
						break;
					} // else
					String passWord = input.next();
					if (passWord.equals(loginCheck.pw)) {
						System.out.println("가격 감정을 진행합니다.");

						int dice = (int) (Math.random() * (100000 - 5000 + 1) + 5000);
						// 5천원 ~ int한도치까지 가격 범위
						System.out.println("해당 글의 가격은 : " + dice + " 크레딧 입니다.");
						boards[i].price = dice;
						boards[i].eva = true; // eva값이 참인 게시물은 다시 가격 감정할 수 없음.
					} else {
						System.out.println("비밀번호 오류");
					} // else

				} // if

			} // for
			break;

		} // while

	} // 가격감정 메서드 종료

	// ===========================================

	public void shop(Scanner input, Item[] items, Member loginCheck) {

		boolean run = true;
		while (run) {
			System.out.println("\n1. 상품 목록 \t 2.나가기\n");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("상품 목록을 불러옵니다.");
				for (int i = 0; i < items.length; i++) {
					if (items[i] != null) {
						System.out.println(items[i].itemNum + "번");
						System.out.println("제목 : " + items[i].title);
						System.out.println("내용 : " + items[i].content);
						System.out.println("작성자 : " + items[i].nick);
						System.out.println("가격 : " + items[i].price + " 크레딧");
						System.out.println("재고 : " + items[i].stock);
						break;
					} // if
				} // for
				break;
			case 2:
				System.out.println("나갑니다.");
				run = false;
				break;
			default:
				System.out.println("1~2");
			}

		} // while

	}// shop

	// ===========================================

	public void inventory(Scanner input, Member[] members, Board[] boards, Member loginCheck) {

		boolean run = true;
		while (run) { // 와일 안써도 되는데 이 창 확인 후에 메인메뉴 나오는게 나을거같아서 와일넣음
			System.out.println("당신이 보유중인 제품\n");
			for (int i = 0; i < boards.length; i++) {
				if (boards[i] != null) {
					if (boards[i].nick == loginCheck.nick) {
						System.out.println(
								(boards[i].num + 1) + "번 : " + boards[i].title + "\n" + boards[i].price + " 크레딧\n");
					} // if
				} // if
			} // for
			System.out.println("보유 크레딧");
			System.out.println(loginCheck.price + " 크레딧\n");
			System.out.println("1번을 입력하면 메뉴로 복귀합니다.");
			int select = input.nextInt();
			if (select == 1) {
				run = false;
				break;
			} else {
				System.out.println("다시 입력해주세요.");
			} // else

		} // 와일

	}// 인벤토리

	// ===========================================

	public void gamble(Member loginCheck) {
		BlackJack game = new BlackJack();
		try {
			game.play();
		} catch (IOException e) {
			e.printStackTrace();
		} // 트라이 캐치뭐시기 이게 뭔지 난 모름 알려고 들면 선생님이 앞서지말라고 혼내니까 그냥 복붙만함

	}// 갬블 메서드 종료

	// ===========================================

	public void sell(Board[] boards, Member loginCheck, Scanner input) {

		boolean run = true;
		while (run) {
			System.out.println("보유한 상품 보기(감정된 물품만 보여집니다.)");
			for (int i = 0; i < boards.length; i++) {
				if (boards[i] != null) {
					if (boards[i].nick == loginCheck.nick && boards[i].eva == true) {
						System.out.println(
								(boards[i].num + 1) + "번 : " + boards[i].title + "\n" + boards[i].price + " 크레딧\n");
						System.out.println("판매할 상품번호를 입력해주세요.");
						int select = input.nextInt();
						if (select == (i + 1)) {
							System.out.println(boards[i] + " 번 글 판매 완료");
							loginCheck.price += boards[i].price;
							boards[i] = null;
							run = false;
							break;
						} else {
							System.out.println("오류");
						} // esle

					} // if

				} // if

			} // for

		} // 와일

	}// 판매 종료

	// ===========================================

	public void gameSet(Member loginCheck, int stake) {
		boolean victory = Rule.victory;  // 승리할경우 Rule 클래스에서 victory를 true로 바꿈
		boolean draw = Rule.draw;        // 무승부일경우 Rule 클래스에서 draw를 true로 바꿈
		boolean bust = BlackJack.bust;   // 딜러가 버스트될경우 BlackJack 클래스 에서 bust를 true로 바꿈
		if (victory == true || bust == true) {
			System.out.println("승리를 축하합니다.");
			System.out.println("상금 : " + (stake * 2) + " 크레딧");
			loginCheck.price += (stake * 2);
			System.out.println("현재 크레딧 : " + loginCheck.price);
		} else if (draw == true) {
			System.out.println("아쉽게도 무승부 입니다.");
			System.out.println("현재 크레딧 : " + loginCheck.price);
		} else if (victory == false) {
			System.out.println("안타깝게도 패배하셨습니다.");
			System.out.println("현재 크레딧 : " + loginCheck.price);

		} // if

	}// 게임셋

}// 클래스 종료

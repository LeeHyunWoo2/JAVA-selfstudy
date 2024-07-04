package ex01board.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Board {

	int num; // 게시물 번호로 쓸 변수
	String nick; // 닉
	String title; // 글 제목
	String content; // 글 내용
	String regDate; // 글쓴 시간
	int price; // 글의 가격
	boolean eva = false; // 가격 평가 여부를 따지는 변수, Evaluation 에서 따옴

	public void boardMenu(Scanner input, Member[] members, Member loginCheck, Board[] boards) {

		boolean boardRun = true;
		while (boardRun) {
			System.out.println("========== 게시판 메뉴 ==========");
			System.out.println("\n1.글쓰기  \t2.게시글 목록\n3.게시물 수정\t4.게시물 삭제\n5.메인 메뉴로\n");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("글쓰러갑니다.");
				if (loginCheck == null) {
					System.out.println("로그인이 필요합니다.");
					break;
				}
				Board writeBoard = write(input, members, loginCheck, boards);
				break;
			case 2:
				System.out.println("게시글 목록을 불러옵니다.");
				list(input, members, boards);
				break;
			case 3:
				System.out.println("게시물을 수정합니다.");
				postMod(input, members, boards, loginCheck);
				break;
			case 4:
				System.out.println("게시물을 삭제합니다.");
				delPost(input, members, boards, loginCheck);
				break;
			case 5:
				System.out.println("메인메뉴로 돌아갑니다.");

				boardRun = false;
				break;
			default:
				System.out.println("1~5");

			}// 스위치

		} // 와일

	}// 게시판 주메뉴 종료

	// ===========================================

	public Board write(Scanner input, Member[] members, Member loginCheck, Board[] boards) {
		Board writeBoard = new Board();

		System.out.println("제목을 입력하세요.");
		writeBoard.title = input.next();
		System.out.println("내용을 입력하세요.");
		writeBoard.content = input.next();
		writeBoard.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		// 구글에서 긁어온것
		writeBoard.nick = loginCheck.nick;
		for (int i = 0; i < boards.length; i++) { // i를 순서대로 굴려가면서 배열 탐색시작
			if (boards[i] == null) { // 만약 i번 인덱스에 값이 null = 비어있다면
				writeBoard.num = i; // 글 번호표에 자릿수만큼 번호메김
				System.out.println("게시물을 업로드 합니다.");
				boards[i] = writeBoard; // 이거하나 안써서 보드에 저장 안되는 바람에 헛수고 겁나함
				break;

			} // for

		} // else
		return writeBoard;

	}// 작성 메소드

	// ===========================================

	public void list(Scanner input, Member[] members, Board[] boards) {

		for (int i = 0; i < boards.length; i++) {
			if (boards[i] != null) {
				System.out.println("[" + (i + 1) + "] : " + boards[i].title + "\n");
			}
		}

		System.out.println("열람할 게시물 번호를 입력해주세요.");
		int post = input.nextInt();
		for (int i = 0; i < boards.length; i++) {
			if (boards[i] != null) {
				if (boards[i].num == post - 1) {
					System.out.println("\n=================================\n");
					System.out.println("[" + (boards[i].num + 1) + "]\n" + "제목 : " + boards[i].title);
					System.out.println("작성자 : " + boards[i].nick);
					System.out.println(boards[i].regDate);
					System.out.println("\n============== 내용 ==============\n");
					System.out.println(boards[i].content);
					System.out.println("\n=================================\n");
					break;

				} // if

			} // if

		} // for

	}// 리스트 메서드

	// ===========================================

	private void postMod(Scanner input, Member[] members, Board[] boards, Member loginCheck) {
		System.out.println("수정할 게시물 번호를 입력해주세요");
		System.out.print(">>>");
		int boardNum = input.nextInt();
		for (int i = 0; i < boards.length; i++) {
			if (boards[i] != null) {
				if (boards[i].num == boardNum - 1)
					System.out.println("비밀번호를 입력해주세요.");
				String passWord = input.next();
				if (passWord.equals(loginCheck.pw)) {
					System.out.println("\n1.제목\n2.내용\n3.취소 및 나가기\n");
					boolean run = true;
					while (run) {
						int select = input.nextInt();
						switch (select) {
						case 1:
							System.out.println("기존 제목");
							System.out.println(boards[i].title + "\n");
							System.out.println("수정할 제목");
							System.out.print(">>>");
							String postMod = input.next();
							boards[i].title = postMod;
							System.out.println("제목 수정완료");
							break;
						case 2:
							System.out.println("기존 내용");
							System.out.println(boards[i].content + "\n");
							System.out.println("수정할 내용");
							System.out.print(">>>");
							String postMod2 = input.next();
							boards[i].content = postMod2;
							System.out.println("내용 수정완료.");
							break;
						case 3:
							System.out.println("메뉴로 복귀합니다.");
							run = false;
							break;
						default:
							System.out.println("1~3");
						}// 스위치
					} // 와일
					break;
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
					break;
				} // if 종료

			} // if 종료

		} // for 종료

	} // 수정메뉴 종료

	// ===========================================

	public void delPost(Scanner input, Member[] members, Board[] boards, Member loginCheck) {

		System.out.println("삭제할 게시물 번호를 입력해주세요");
		System.out.print(">>>");
		int post = input.nextInt();
		for (int i = 0; i < boards.length; i++) {
			if (boards[i] != null) {
				if (boards[i].num == (post - 1) && boards[i].nick == loginCheck.nick) {
					boards[i] = null;
					System.out.println("삭제 완료\n");
				} else {
					System.out.println("작성자와 사용자의 정보가 일치하지 않습니다.");// if
				}
				break;
			} // if

		} // for

	}// 삭제메서드

}// 클래스
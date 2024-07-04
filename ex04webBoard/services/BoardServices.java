package ex04webBoard.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import ex04webBoard.DTO.*;

public class BoardServices {

	protected BoardDTO[] boardDTOs;
	// 이거 써줘야 저 아래 각종 메뉴 코드에서 boardDTOs 를 쓸수있음

	public BoardServices(BoardDTO[] boardDTOs) {
		this.boardDTOs = boardDTOs;
	}

	public void BoardTemplate(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus, String boardName) {

		boolean boardRun = true;
		while (boardRun) {
			System.out.println("========== " + boardName + " 메뉴 ==========");
			System.out.println("\n1.글쓰기  \t2.게시글 목록\n3.게시물 수정\t4.게시물 삭제\n5.메인 메뉴로\n");
			System.out.print(">>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.println(" 글쓰러갑니다 ");
				write(scanner, memberDTOs, loginStatus);
				break;
			case 2:
				System.out.println("게시글 목록을 불러옵니다.");
				list(scanner, memberDTOs);
				break;
			case 3:
				System.out.println("게시물을 수정합니다.");
				postMod(scanner, memberDTOs, loginStatus);
				break;
			case 4:
				System.out.println("게시물을 삭제합니다.");
				delPost(scanner, memberDTOs, loginStatus);
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

	public void write(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {

		BoardDTO writeBoard = createBoardDTO();

		System.out.println("제목을 입력하세요.");
		writeBoard.setTitle(scanner.next());

		System.out.println("내용을 입력하세요.");
		writeBoard.setContent(scanner.next());

		writeBoard.setRegDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		// 구글에서 긁어온것
		if (loginStatus != null) {
			writeBoard.setNick(loginStatus.getNick());
			for (int i = 0; i < boardDTOs.length; i++) { // i를 순서대로 굴려가면서 배열 탐색시작
				if (boardDTOs[i] == null) { // 만약 i번 인덱스에 값이 null = 비어있다면
					writeBoard.setNum(i); // 글 번호표에 자릿수만큼 번호메김
					System.out.println("게시물을 업로드 합니다.");
					boardDTOs[i] = writeBoard; // 이거 안쓰면 다만들어놓고 저장만 안한꼴됨
					break;
				}

			} // for

		} // else

	}// 작성 메소드

	// ===========================================

	protected BoardDTO createBoardDTO() {
		// 일단 배열 구조 넘기기 전에 초기화를 꼭 해야함.
		// 그래서 여기서 boardDTO를 닦아서 내보낼거임
		BoardDTO boardDTO = new BoardDTO(); // BoardDTO를 초기화
		// 각종 메뉴에서 boardDTO를 다 갖다 쓰기 때문에 그냥 초기화 메서드를 따로 분리하는 방식을 채택함
		return boardDTO; // 이 짧은 메서드가 정말정말 중요함
	}

	// 체크해야하는 요소
	// 1. main 메서드에서 각 메뉴로 전환할때 그 배열을 사용하게끔 호출해야함
	// ex) 자유게시판일경우 : FreeBoardServices freeBoardServices = new FreeBoardServices(freeBoardDTOs);
	// 2. 부모 클래스에서 배열이 제대로 초기화 되었는지 확인이 필요함
	// 3. 자식클래스에서 호출한다음 반드시 강제타입변환을 해야함

	// ===========================================

	public void list(Scanner scanner, MemberDTO[] memberDTOs) {

		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				System.out.println("[" + (i + 1) + "] : " + boardDTOs[i].getTitle() + "\n");
			}
		}

		System.out.println("열람할 게시물 번호를 입력해주세요.");
		int post = scanner.nextInt();
		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				if (boardDTOs[i].getNum() == post - 1) {
					System.out.println("\n=================================\n");
					System.out.println("[" + (boardDTOs[i].getNum() + 1) + "]\n" + "제목 : " + boardDTOs[i].getTitle());
					System.out.println("작성자 : " + boardDTOs[i].getNick());
					System.out.println(boardDTOs[i].getRegDate());
					System.out.println("\n============== 내용 ==============\n");
					System.out.println(boardDTOs[i].getContent());
					System.out.println("\n=================================\n");
					break;

				} // if

			} // if

		} // for

	}// 리스트 메서드

	// ===========================================

	public void postMod(Scanner scanner, MemberDTO[] members, MemberDTO loginStatus) {
		System.out.println("수정할 게시물 번호를 입력해주세요");
		System.out.print(">>>");
		int boardNum = scanner.nextInt();
		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				if (boardDTOs[i].getNum() == boardNum - 1)
					System.out.println("비밀번호를 입력해주세요.");
				String passWord = scanner.next();
				if (passWord.equals(loginStatus.getPw())) {
					System.out.println("\n1.제목\n2.내용\n3.취소 및 나가기\n");
					boolean run = true;
					while (run) {
						int select = scanner.nextInt();
						switch (select) {
						case 1:
							System.out.println("기존 제목");
							System.out.println(boardDTOs[i].getTitle() + "\n");
							System.out.println("수정할 제목");
							System.out.print(">>>");
							String postMod = scanner.next();
							boardDTOs[i].setTitle(postMod);
							System.out.println("제목 수정완료");
							break;
						case 2:
							System.out.println("기존 내용");
							System.out.println(boardDTOs[i].getContent() + "\n");
							System.out.println("수정할 내용");
							System.out.print(">>>");
							String postMod2 = scanner.next();
							boardDTOs[i].setContent(postMod2);
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

	public void delPost(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {

		System.out.println("삭제할 게시물 번호를 입력해주세요");
		System.out.print(">>>");
		boolean findInfo = false; // 계정을 찾으면 true로 바뀜
		int post = scanner.nextInt();
		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				if (boardDTOs[i].getNum() == (post - 1) && boardDTOs[i].getNick().equals(loginStatus.getNick())) {
					boardDTOs[i] = null;
					System.out.println("삭제 완료\n");
					findInfo = true;
					break;
				} // if

			} // if

		} // for
		if (findInfo == false) {
			// !findInfo 라고 써도됨
			System.out.println("작성자와 사용자의 정보가 일치하지 않습니다.");
		}

	}// 삭제메서드

}// 클래스
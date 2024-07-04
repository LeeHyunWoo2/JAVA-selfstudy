package ex04webBoard.services.child;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ex04webBoard.DTO.MemberDTO;
import ex04webBoard.DTO.child.FreeBoardDTO;
import ex04webBoard.services.BoardServices;

public class FreeBoardServices extends BoardServices {

	public FreeBoardServices(FreeBoardDTO[] freeBoardDTOs) {
		super(freeBoardDTOs);
	}

	@Override
	protected FreeBoardDTO createBoardDTO() {
		FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
		// FreeBoardDTO 라는 식탁을 닦음
		return freeBoardDTO; // 내보냄
	}

	@Override
	public void write(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {
		// 이 아래는 자유게시판에서 유저가 비로그인 사용자로 판단되는 경우
		// 보안코드가 발생하게 하는 글쓰기 파트만 재정의하는 코드

		String SecurityCode = "";

		FreeBoardDTO writeBoard = (FreeBoardDTO) createBoardDTO();

		System.out.println("제목");
		writeBoard.setTitle(scanner.next());

		System.out.println("내용");
		writeBoard.setContent(scanner.next());

		writeBoard.setRegDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

		if (loginStatus == null) {
			// 처음에 loginStatus.nick 을 입력해봤는데 오류가 발생함.
			// loginStatus 자체를 가져오는건 아무 상관이 없는데 거기서 . 찍고나서 내부를 파헤치려고 들면 오류가 터짐
			// 그래서 null일 경우에는 . 안찍으면 값이 null이라면서 빨간글씨 오류 뜨는거 방지가능

			// 로그인 스테이터스에 아무것도 없다면 = 로그인을 안했다면
			for (int i = 0; i <= 8; i++) {
				// 이걸 ↓ 8번 반복함(for)

				char j = (char) (Math.random() * 74 + 48); // 매스랜덤 * ((최댓값 - 최소값) + 최소값) 랜덤 범위 공식
				// 48이상 122 이하의 랜덤 숫자 생성

				while (!((j >= 48 && j <= 57) || (j >= 65 && j <= 90) || (j >= 97 && j <= 122))) {
					// 값이 48이상 57이하 혹은 65이상 90이하 혹은 97이상 122이하가 "아닐"경우
					j = (char) (Math.random() * 74 + 48);
					// math.random 을 다시 조건에 맞을때까지 실행

				} // while

				SecurityCode += j;
				// 조건에 맞을 경우, while에서 빠져나온다음 시큐리티코드에 문자 하나를 추가함
				// 그리고 이걸 for문이 8번 반복하는걸 만족할때까지 다시 반복함

			} // for

			System.out.println("비 로그인 사용자로 확인되었습니다.");
			System.out.println("익명 게시물로 업로드 합니다.\n");
			System.out.println("아래의 보안 코드를 입력해주세요.");
			System.out.println(SecurityCode + "\n");
			System.out.print(">>>");
			String code = scanner.next();
			if (SecurityCode.equals(code)) {
				writeBoard.setNick("ㅇㅇ");
				System.out.println("\n보안코드 확인 완료.");
//				System.out.println("해당 글 삭제시 필요한 비밀번호를 설정해주세요.");
//				String codeCode = scanner.next();
//				writeBoard.setPostCode(codeCode);
//				System.out.println("비밀번호 확인 테스트용 : " + codeCode);
				for (int k = 0; k < boardDTOs.length; k++) {
					if (boardDTOs[k] == null) {
						writeBoard.setNum(k);
						System.out.println("게시물을 업로드합니다.");
						boardDTOs[k] = writeBoard;
						break;
					} // if
				} // for
			} else {
				System.out.println("보안코드 입력 오류");
			} // 엘스
		} else {
			writeBoard.setNick(loginStatus.getNick());
			for (int k = 0; k < boardDTOs.length; k++) {
				if (boardDTOs[k] == null) {
					writeBoard.setNum(k);
					System.out.println("게시물을 업로드합니다.");
					boardDTOs[k] = writeBoard;
					break;
				} // if
			} // for
		} // else
	} // write 메서드

	@Override
	public void list(Scanner scanner, MemberDTO[] memberDTOs) {

		// 리스트를 왜 굳이 오버라이드 하냐면
		// 기존 메뉴들은 배열에서 남는자리를 찾아서 게시물을 삽입하는 로직이고, 그 행동 패턴을 따라쓰는거임.
		// null 이 아니면 -> 다음 단계로 넘어감 -> null이면 -> 게시물 저장 이 행동 자체를 갖다쓰는것일뿐이라서
		// 똑같은 코드 그대로 갖다써도 배열에 맞춰서 알아서 작동함.
		// 근데 리스트 출력은 다름. 이건 해당 배열 그 자체를 불러서 보여주는 방식이라 오버라이드를 안하면 부모 게시판 목록을 보여줘버림
		// 그래서 목록 보여주는건 오버라이드 해야됨

		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				System.out.println("[" + (i + 1) + "] : " + ((FreeBoardDTO) boardDTOs[i]).getTitle() + "\n");
			} // if
		} // for

		System.out.println("열람할 게시물 번호를 입력해주세요.");
		int post = scanner.nextInt();
		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				if (boardDTOs[i].getNum() == post - 1) {
					System.out.println("\n=================================\n");
					System.out.println("[" + (boardDTOs[i].getNum() + 1) + "]\n" + "제목 : "
							+ ((FreeBoardDTO) boardDTOs[i]).getTitle());
					System.out.println("작성자 : " + boardDTOs[i].getNick());
					System.out.println(boardDTOs[i].getRegDate());
					System.out.println("\n============== 내용 ==============\n");
					System.out.println(boardDTOs[i].getContent());
					System.out.println("\n=================================\n");
					break;
				} // if
			} // if
		} // for
	} // list 메서드

	@Override
	public void delPost(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {

		System.out.println("삭제할 게시물 번호를 입력해주세요");
		System.out.print(">>>");
		boolean findInfo = false;
		int post = scanner.nextInt();
		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				if (boardDTOs[i].getNum() == (post - 1) && loginStatus == null) {
					System.out.println("비 로그인 사용자의 게시물 삭제를 진행합니다.");
					System.out.println("원래는 비회원 글 전용 비밀번호 구현을 하려 했으나 피곤해서 포기함");
					boardDTOs[i] = null;
					findInfo = true;
					break;
				} // if종료
				if (boardDTOs[i].getNum() == (post - 1) && boardDTOs[i].getNick().equals(loginStatus.getNick())) {
					boardDTOs[i] = null;
					System.out.println("삭제 완료\n");
					findInfo = true;
					break;

					// 비 로그인 사용자의 경우 글 작성하면서 별도의 비밀번호를 지정하는식으로 하려고 했으나
					// 갈수록 피곤해져서 그냥 취소함

//					System.out.println("해당 글의 비밀번호를 입력해주세요.");
//					FreeBoardDTO freeBoardDTO = (FreeBoardDTO) boardDTOs[i];
//					// 강제타입변환을 안하면 getPostCode를 불러오지 못함 오버라이드한 저쪽 메서드에 있는걸
//					// 여기로 바로 받아오는방법이 이거뿐인듯함 강제로 끌고와서 타입변환
//					String post2 = scanner.next();
//					FreeBoardDTO freeBoard = (FreeBoardDTO) boardDTOs[i];
//					if (freeBoard.getPostCode().equals(post2)) {
//						boardDTOs[i] = null;
//						System.out.println("삭제 완료\n");
//						findInfo = true;
//						break;
					// }

				} // if 사용자와 글작성자 비교용 if문

			} // if 비 로그인 판별용 if문

		} // for
		if (findInfo == false) {
			System.out.println("작성자와 사용자의 정보가 일치하지 않습니다.");
		} // 정보 불일치 if

	} // 게시물 삭제 메서드

} // 클래스

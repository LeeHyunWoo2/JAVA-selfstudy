package ex04webBoard.services;

import java.util.Scanner;
import ex04webBoard.DTO.MemberDTO;

public class MemberServices {

	public MemberDTO memberServicesMenu(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {
		boolean run = true;
		while (run) {
			System.out.println("1.로그인 2.가입 3.수정 4.내정보확인 5.탈퇴 6.메인메뉴");
			System.out.print(">>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				loginStatus = loginMenu(scanner, memberDTOs, loginStatus);
				break;
			case 2:
				signUp(scanner, memberDTOs);
				break;
			case 3:
				modify(scanner, memberDTOs, loginStatus);
				break;
			case 4:
				LoginMemberInfo(scanner, memberDTOs, loginStatus);
				break;
			case 5:
				deleteMember(scanner, memberDTOs, loginStatus);
				break;
			case 6:
				run = false;
				break;
			default:
				System.out.println("1~6");
			}// 스위치
		} // while
		return loginStatus;
	}// 멤버메뉴

	// ================================================================

	public static MemberDTO loginMenu(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {

		System.out.println("로그인을 진행합니다.\nid를 입력해주세요.");
		System.out.print(">>>");
		String id = scanner.next();
		MemberDTO idCheck = null;

		for (int i = 0; i < memberDTOs.length; i++) {
			if (memberDTOs[i] != null && memberDTOs[i].getId().equals(id)) {
				idCheck = memberDTOs[i];
				break;
//			} else {
//				System.out.println("일치하는 id를 찾을 수 없습니다.");

				// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				// 이 부분 매우 중요함. 루프 내부에 else가 있을경우 싹 검사하면서 else에 해당되는거도 싹출력함.
				// 무슨소리냐면 내 앞에 0번인덱스 계정 정보가 들어있고, 그것도 비교를 한다는거고
				// 그럼 내 앞 계정은 당연히 내가 입력한거랑 다르니까 else 를 출력해버림
				// 만약 id 100개를 미리 가입시켰다면? 그럼 id를 찾을 수 없다는 텍스트출력이 100번 발생함
				// 그래서 이럴 경우는 else를 아예 쓰지말고 if문밑에 다른 if문을 따로 둬야함.
				// 이때 중첩if문으로 만들면 안됨. 아예 이전 if문 끝난 다음 if문 써야함
			} // if
		} // for
		if (idCheck == null) {
			System.out.println("일치하는 id를 찾을 수 없습니다.");
			return loginStatus;
			// return 을 사용해서 바로 메서드 종료
		} // if종료

		System.out.println("pw를 입력해주세요.");
		System.out.print(">>>");
		String pw = scanner.next();

		if (idCheck.getPw().equals(pw)) {
			loginStatus = idCheck;
			System.out.println(loginStatus.getNick() + "님 안녕하세요\n");
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		} // else
		return loginStatus;
	} // 로그인 메뉴 메서드 종료

	// ================================================================

	public static void signUp(Scanner scanner, MemberDTO[] memberDTOs) {
		System.out.println("가입 절차를 시작합니다.");
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("id");
		System.out.print(">>>");
		memberDTO.setId(scanner.next());

		System.out.println("pw");
		System.out.print(">>>");
		memberDTO.setPw(scanner.next());

		System.out.println("nick");
		System.out.print(">>>");
		memberDTO.setNick(scanner.next());

		System.out.println("email");
		System.out.print(">>>");
		memberDTO.setEmail(scanner.next());

		for (int i = 0; i < memberDTOs.length; i++) {
			if (memberDTOs[i] == null) {
				memberDTOs[i] = memberDTO;
				break;
			} // if
		} // for
		System.out.println(memberDTO.getNick() + " 님 환영합니다.");
	} // 가입메뉴

	// ================================================================

	public static void modify(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {
		if (loginStatus != null) {
			System.out.println("수정할 정보를 입력해주세요.");
			System.out.print("새 비밀번호: ");
			loginStatus.setPw(scanner.next());
			System.out.print("새 닉네임: ");
			loginStatus.setNick(scanner.next());
			System.out.print("새 이메일: ");
			loginStatus.setEmail(scanner.next());
			System.out.println("정보 수정이 완료되었습니다.");
		} else {
			System.out.println("로그인을 하셔야 진행됩니다.");
		} // else
	} // 정보수정메뉴 종료

	// ================================================================

	public static void LoginMemberInfo(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {

		if (loginStatus != null) {
			System.out.println("ID : " + loginStatus.getId());
			System.out.println("Password : " + loginStatus.getPw());
			System.out.println("닉네임 : " + loginStatus.getNick());
			System.out.println("Email : " + loginStatus.getEmail());
			System.out.println(" 1번을 입력하여 나가기 ");
			System.out.print(">>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				break;
			case 2:
				System.out.println("다시 입력하세요.");
			}// 스위치종료
		} else {
			System.out.println("현재 로그인한 유저가 없습니다.");
		} // else

	}// 계정 정보 종료

	// ================================================================

	public static MemberDTO deleteMember(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {
		if (loginStatus != null) {
			System.out.println("탈퇴하시겠습니까? 1.탈퇴 2.취소");
			System.out.print(">>>");
			int select = scanner.nextInt();
			if (select == 1) {
				for (int i = 0; i < memberDTOs.length; i++) {
					if (memberDTOs[i] != null && memberDTOs[i].getId().equals(loginStatus.getId())) {
						memberDTOs[i] = null;
						System.out.println("탈퇴가 완료되었습니다. 안녕히 가세요.");
						loginStatus = null;
						break;
					} // if
				} // for
			} else {
				System.out.println("탈퇴를 취소합니다.");
			} // else
		} else {
			System.out.println("탈퇴할 계정으로 로그인 해주세요.");
		} // else
		return loginStatus;
	} // 회원탈퇴 메서드 종료

}// 클

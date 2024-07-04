package ex01board.board;

import java.util.Scanner;

public class Member {

	String id;
	String pw;
	String nick;
	String email;
	int price;

	public Member menu(Scanner input, Member[] members, Member loginCheck) {

		boolean run = true;
		while (run) {
			System.out.println("\n회원정보 관련 메뉴입니다.");
			System.out.println("\n1.회원가입 \t2.로그인\n3.정보수정 \t4.회원탈퇴\n5.내 정보 확인\t6.메인메뉴로 나가기\n");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("회원가입을 진행합니다.");
				loginCheck = register(input, loginCheck);
				for (int i = 0; i < members.length; i++) {
					if (members[i] == null) {
						members[i] = loginCheck;
						System.out.println(loginCheck.nick + "님 가입을 환영합니다.");
						break; // if문 브레이크
					} // if 종료
				} // for종료
				break;
			case 2:
				System.out.println("로그인을 진행합니다.");
				Member loginMember = login(input, members, loginCheck);
				if (loginMember != null) {
					System.out.println(loginMember.nick + "님 반갑습니다.");
				}
				break;
			case 3:
				System.out.println("정보 수정 메뉴로 전환합니다.");
				modify(input, members, loginCheck);
				break;
			case 4:
				System.out.println("회원 탈퇴 절차를 시작합니다.");
				delete(input, members, loginCheck);
				break;
			case 5:
				System.out.println("현재 로그인 정보를 확인합니다.");
				accountInfo(input, members, loginCheck);
				break;
			case 6:
				System.out.println("메인 메뉴로 복귀합니다.");
				run = false;
				break;
			default:
				System.out.println("1~5");

			}// 스위치

		} // 와일
		return loginCheck;

	}// 멤버 주메뉴 메서드 종료

	// ===========================================

	public Member register(Scanner input, Member loginCheck) {
		// 회원가입 과정을 담당하는 메서드

		Member newMember = new Member();
		// 위에 로그인하고 같은 원리
		System.out.println("아이디 입력");
		newMember.id = input.next();
		System.out.println("비밀번호 입력");
		newMember.pw = input.next();
		System.out.println("닉네임 입력");
		newMember.nick = input.next();
		System.out.println("이메일 입력");
		newMember.email = input.next();

		loginCheck = newMember;

		return loginCheck;
	}// 가입 메서드 종료

	// ===========================================

	public Member login(Scanner input, Member[] members, Member loginCheck) {
		Member loginMember = new Member();

		System.out.println("아이디");
		System.out.print(">>>");
		loginMember.id = input.next();

		System.out.println("비밀번호");
		System.out.print(">>>");
		loginMember.pw = input.next();

		for (int i = 0; i < members.length; i++) {
			if (members[i] != null) {
				if (members[i].id.equals(loginMember.id) && members[i].pw.equals(loginMember.pw)) {
					loginMember = members[i];
					break;
				}
			} else {
				System.out.println("아이디 비밀번호 재확인필요");
				loginMember = null;
				break;
			} // else종료

		} // for종료
		loginCheck = loginMember;
		return loginCheck;
	}// 로그인 메서드 종료

	// ===========================================

	// 뭐가 문제인지 모르겠는데 메서드 하나로 정보수정 다 하려고 하는데 데이터 갱신이 안되서 그냥 메서드 나눔

	public Member modify(Scanner input, Member[] members, Member loginCheck) {
		Member modMember = new Member();
		System.out.println("회원정보를 수정하실 아이디를 입력해주세요.");
		String modId = input.next();
		for (int i = 0; i < members.length;) {
			if (members[i].id.equals(modId)) {
				boolean pwRun = true;
				while (pwRun) {
					System.out.println("비밀번호를 입력해주세요.");
					String modPw = input.next();
					if (members[i].pw.equals(modPw)) {
						System.out.println("어떤 정보를 수정하시겠습니까?");
						modMember = members[i];
						modfiy2(input, modMember);
						pwRun = false;
						break;
					} else {
						System.out.println("비밀번호 정보가 틀립니다.");
						break;
					} // else
				} // 비번 루프 종료
				break;
			} else {
				System.out.println("해당 아이디의 정보가 없습니다.");
			} // else종료
			break;
		} // for종료
		return loginCheck;

	} // 정보수정 메서드 종료

	// ===========================================

	public Member modfiy2(Scanner input, Member modMember) {
		Member modMember2 = new Member();
		boolean mod2run = true;
		while (mod2run) {
			System.out.println("\n1.비밀번호\n2.닉네임\n3.이메일\n4.취소&나가기");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("변경하실 패스워드를 입력해주세요.");
				String change1 = input.next();
				modMember.pw = change1;
				break;
			case 2:
				System.out.println("변경하실 닉네임을 입력해주세요.");
				String change2 = input.next();
				modMember.nick = change2;
				break;
			case 3:
				System.out.println("변경하실 이메일을 입력해주세요.");
				String change3 = input.next();
				modMember.email = change3;
				break;
			case 4:
				System.out.println("취소합니다.");
				mod2run = false;
				break;
			default:
				System.out.println("1~4");
			}// 스위치
		} // 와일

		return modMember2;

	} // 정보수정메뉴2 종료

	// ===========================================

	public Member delete(Scanner input, Member[] members, Member loginCheck) {
		Member delMember = new Member();
		boolean delrun = true;
		while (delrun) {
			System.out.println("1.탈퇴\n2.취소");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("탈퇴하실 아이디를 입력해주세요.");
				delMember.id = input.next();
				System.out.println("탈퇴하실 비밀번호를 입력해주세요.");
				delMember.pw = input.next();
				for (int i = 0; i < members.length; i++) {
					if (members[i] != null) {
						if (members[i].id.equals(delMember.id) && members[i].pw.equals(delMember.pw)) {
							members[i] = null;
							System.out.println("탈퇴완료");
							break;
						}
					} else {
						System.out.println("해당 계정 정보가 없습니다.");
						break;
					}
				}
				break;
			case 2:
				System.out.println("취소합니다.");
				delrun = false;
				break;
			default:
				System.out.println("1~2");

			} // 스위치 종료

		} // 메뉴루프 종료
		return loginCheck;

	} // 탈퇴 메서드 종료

	public Member accountInfo(Scanner input, Member[] members, Member loginCheck) {

		boolean run = true;
		while (run) {
			System.out.println("========== 다 봤으면 1번 입력하고 나가세요 ==========");
			System.out.println("ID : " + loginCheck.id);
			System.out.println("Password : " + loginCheck.pw);
			System.out.println("닉네임 : " + loginCheck.nick);
			System.out.println("Email : " + loginCheck.email);
			int select = input.nextInt();
			switch (select) {
			case 1:
				run = false;
				break;
			case 2:
				System.out.println("다시 입력하세요.");
			}// 스위치종료

		} // 와일종료
		return loginCheck;

	}// 계정 정보 종료

}// 클래스

package ex03game.service;

import java.util.Scanner;
import ex03game.dto.*;

public class MemberSV {

	public MemberDTO menu(Scanner input, MemberDTO[] memberDTOs, MemberDTO loginState) { // 부메뉴
		System.out.println("===== 회원관리 메뉴에 진입하였습니다. =====");
		boolean run = true;
		while (run) {
			System.out.println("1.가입 2.로그인 3.수정 4.삭제 5.나가기");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("계정을 생성합니다.");
				create(input, memberDTOs);
				break;

			case 2:
				System.out.println("로그인을 진행합니다.");
				loginState = login(input, memberDTOs, loginState);
				break;

			case 3:
				System.out.println("계정정보를 수정합니다.");
				modify(input, memberDTOs, loginState);
				break;

			case 4:
				System.out.println("회원 탈퇴를 진행합니다.");
				break;

			case 5:
				System.out.println("메인메뉴로 돌아갑니다.");
				run = false;
				break;

			case 99:
				System.out.println("관리자 페이지 입니다."); // 히든메뉴로 관리자 메뉴 구현해두기
				break;

			default:
				System.out.println("1~5");

			}// 스위치

		} // 와일
		return loginState; // 멤버서비스의 리턴은 로그인 상태 정보(MemberDTO)
	} // 부메뉴

	public void create(Scanner input, MemberDTO[] memberDTOs) { // 계정 생성
		System.out.println("사용할 id를 입력하세요");
		System.out.print(">>>");
		String id = input.next();
		System.out.println("사용할 pw를 입력하세요");
		System.out.print(">>>");
		String pw = input.next();
		System.out.println("사용할 닉네임을 입력하세요");
		System.out.print(">>>");
		String nickName = input.next();
		System.out.println("사용할 이메일을 입력하세요");
		System.out.print(">>>");
		String email = input.next(); // 입력받은 값을 객체에 삽입

		MemberDTO memberDTO = new MemberDTO(); // 빈 객체 생성
		memberDTO.id = id;
		memberDTO.pw = pw;
		memberDTO.nickName = nickName;
		memberDTO.email = email; // 키보드로 받은 값을 객체에 넣음

		for (int i = 0; i < memberDTOs.length; i++) {
			if (memberDTOs[i] == null) {
				memberDTOs[i] = memberDTO;
				break;

			} // if종료

		} // for종료

		System.out.println(memberDTO.nickName + "님 회원가입 완료되었습니다.");

	} // 생성 메서드 종료

	public MemberDTO login(Scanner input, MemberDTO[] memberDTOs, MemberDTO loginState) { // 로그인

		System.out.println("id를 입력해주세요.");
		System.out.println(">>>");
		String id = input.next();
		System.out.println("pw를 입력해주세요.");
		System.out.println(">>>");
		String pw = input.next();

		MemberDTO loginMember = new MemberDTO();
		loginMember.id = id;
		loginMember.pw = pw;

		for (int i = 0; i < memberDTOs.length; i++) {
			if (memberDTOs[i] != null && memberDTOs[i].id.equals(loginMember.id)
					&& memberDTOs[i].pw.equals(loginMember.pw)) {
				System.out.println("로그인 성공");
				loginState = memberDTOs[i]; // 배열에 있는 정보가 login 상태 객체에 삽입됨
				break;
			} else if (memberDTOs[i] == null) {
				System.out.println("회원가입을 진행해주세요");
				break;
			} else {
				System.out.println("아이디나 비밀번호 정보가 맞지 않습니다.");
			} // if

		} // for 종료

		System.out.println(loginState.nickName + "님 로그인 성공");
		return loginState; // 로그인성공 객체를 리턴함.

	} // 로그인 메서드 종료

	public void modify(Scanner input, MemberDTO[] memberDTOs, MemberDTO loginState) { // 수정
		System.out.println("id를 입력하세요.");
		System.out.print(">>>");
		String id = input.next();

		System.out.println("pw를 입력하세요.");
		System.out.print(">>>");
		String pw = input.next(); // 입력값과 새로운 객체값을 삽입

		MemberDTO modifyMember = new MemberDTO();
		modifyMember.id = id;
		modifyMember.pw = pw;
		if (loginState.id.equals(modifyMember.id) // 키보드id와 로그인 id비교
				&& loginState.pw.equals(modifyMember.pw)) { // pw비교
			System.out.println("변경할 pw를 입력해주세요");
			System.out.print(">>>");
			loginState.pw = input.next();
		} // if

	}// 수정 메서드 종료

	public void delete() { // 삭제

	}// 삭제 메서드 종료

}
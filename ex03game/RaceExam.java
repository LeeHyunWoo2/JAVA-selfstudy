package ex03game;

import java.util.Scanner;

import ex03game.dto.*;
import ex03game.service.*;

public class RaceExam {
	
	
	
	
	// 콘솔 실행 시 레이스 부분이 제대로 안되는 현상이 있는데
	// window -> preferences -> Run/Debug -> Console 창에서 
	// interpret ASCII control characters 하고 그 아래 있는거까지 두개 다 체크되어있어야
	// 정상작동합니다.... 이클립스 고질병이라함 (\r을 제대로 인식못함)
	
	
	
	

	// 필드
	public static Scanner input = new Scanner(System.in); // 키보드 입력
	public static MemberDTO[] memberDTOs = new MemberDTO[10]; // 회원 배열
	public static DriverDTO[] driverDTOs = new DriverDTO[15]; // 캐릭터 배열
	public static CarDTO[][] carDTOs = new CarDTO[3][3]; // 차량 배열
	public static ItemDTO[] itemDTOs = new ItemDTO[10]; // 아이템 배열

	public static MemberDTO loginState; // 로그인 상태 유지용 (세션역할)

	static {
		DriverDTO DriverDTO0 = new DriverDTO("김씨", "보통", "거의없음", 1.0);
		DriverDTO DriverDTO1 = new DriverDTO("민식이", "숙련자", "매우적음", 0.9);
		DriverDTO driverDTO2 = new DriverDTO("춘식이", "전문가", "약간자주있음", 0.8);
		DriverDTO driverDTO3 = new DriverDTO("대식이", "전문가", "거의없음", 0.5); // 객체 생성 완료

		driverDTOs[0] = DriverDTO0;
		driverDTOs[1] = DriverDTO1;
		driverDTOs[2] = driverDTO2;
		driverDTOs[3] = driverDTO3; // CharactorDTO 배열에 삽입 완료
		
		// 굳이 안해도 됐는데 그냥 2차원 배열을 한번 해보고 싶었음
		// 행 -> 제조사, 열 -> 모델
		CarDTO carDTO00 = new CarDTO(1, "포르쉐", "타이칸 터보 GT", 290, 789, 2.3);
		CarDTO carDTO01 = new CarDTO(2, "포르쉐", "911 터보 S", 330, 662, 2.7);
		CarDTO carDTO02 = new CarDTO(3, "포르쉐", "918 스파이더", 345, 887, 2.6);
		CarDTO carDTO10 = new CarDTO(1, "페라리", "458 이탈리아", 325, 570, 3.4);
		CarDTO carDTO11 = new CarDTO(2, "페라리", "F8 트리뷰토", 340, 720, 2.9);
		CarDTO carDTO12 = new CarDTO(3, "페라리", "라페라리", 372, 963, 2.9);
		CarDTO carDTO20 = new CarDTO(1, "람보르기니", "우라칸", 310, 640, 2.9);
		CarDTO carDTO21 = new CarDTO(2, "람보르기니", "아벤타도르", 351, 770, 2.8);
		CarDTO carDTO22 = new CarDTO(3, "람보르기니", "레부엘토", 350, 1015, 2.5);

		carDTOs[0][0] = carDTO00;
		carDTOs[0][1] = carDTO01;
		carDTOs[0][2] = carDTO02;
		carDTOs[1][0] = carDTO10;
		carDTOs[1][1] = carDTO11;
		carDTOs[1][2] = carDTO12;
		carDTOs[2][0] = carDTO20;
		carDTOs[2][1] = carDTO21;
		carDTOs[2][2] = carDTO22;

		ItemDTO itemDTO0 = new ItemDTO("니트로", "짧은 시간동안 차량을 폭발적으로 가속시킵니다.", 100, 10);
		ItemDTO itemDTO1 = new ItemDTO("끈적이", "후방의 넓은 범위에 차량의 속도를 늦추는 함정을 흩뿌립니다.", 80, 10);
		ItemDTO itemDTO2 = new ItemDTO("연막", "후방의 넓은 시야를 차단하는 연막탄을 투척합니다.", 60, 10);
		ItemDTO itemDTO3 = new ItemDTO("보너스", "이번판에 획득하는 상금이 1.2배가 됩니다.", 200, 10);

		itemDTOs[0] = itemDTO0;
		itemDTOs[1] = itemDTO1;
		itemDTOs[2] = itemDTO2;
		itemDTOs[3] = itemDTO3;

	} // 스태틱 블럭 -> 스태틱으로 만든 클래스에 초기값 배정

	// 메서드

	public static void main(String[] args) {

		MemberDTO admin = new MemberDTO();
		admin.id = "1";
		admin.pw = "2";
		admin.nickName = "관리자";
		admin.email = "admin@mbc.co.kr";
		loginState = admin;

		System.out.println("===== 레이스 게임을 시작합니다. =====");
		boolean run = true; // 처음 구동
		while (run) {
			System.out.println("1. 회원관리 2. 내 장비 관리 3. 게임실행 4.종료");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("회원관리 클래스로 진입합니다.");
				MemberSV memberSV = new MemberSV(); // 회원관리용 서비스객체 생성
				loginState = memberSV.menu(input, memberDTOs, loginState); // 서비스의 메뉴 호출(스캐너 객체 전송)
				break;

			case 2:
				System.out.println("게임 세팅 관리 클래스로 진입합니다.");
				GearSV cartSV = new GearSV(); // 카트 관리 클래스 객체 생성
				cartSV.menu(input, driverDTOs, carDTOs, itemDTOs, loginState); // 부메뉴 호출(스캐너, 캐릭터배열 전송)
				break;

			case 3:
				System.out.println("게임실행 클래스로 진입합니다.");
				PlaySV playSV = new PlaySV();
				playSV.menu(input, driverDTOs, carDTOs, itemDTOs, loginState);
				break;

			case 4:
				System.out.println("게임을 종료합니다.");
				run = false; // while 종료
				break;

			default:
				System.out.println("1~4");

			}// 스위치

		} // 와일

	}// 메인

}// 클래스
package ex03game.service;

import java.util.Scanner;

import ex03game.dto.*;


public class GearSV {

	// 내 계정의 장비, 캐릭터 등 셋팅을 관리하는 메뉴

	public MemberDTO menu(Scanner input, DriverDTO[] driverDTOs, CarDTO[][] carDTOs, ItemDTO[] itemDTOs,
			MemberDTO loginState) {

		boolean run = true;
		while (run) {
//			if (loginState.driverDTO.name != null && loginState.carDTO.model != null && loginState.itemDTO. != null) {
//				System.out.println(
//						"현재 장착된 아이템\n드라이버 : " + loginState.driverDTO.name + "\n차량 : " + loginState.carDTO.campany + " "
//								+ loginState.carDTO.model + "\n아이템 : " + loginState.itemDTO.itemName);
//			} // if
			System.out.println("\n정비할 요소를 입력해주세요.");
			System.out.println("1. 레이서 2. 차량 3. 아이템 4.메인 메뉴로");
			System.out.print(">>>");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("레이서 선택 메뉴로 이동합니다.");
				driverMenu(input, driverDTOs, loginState);
				break;
			case 2:
				System.out.println("차량 정비 메뉴로 이동합니다.");
				carMenu(input, carDTOs, loginState);
				break;
			case 3:
				System.out.println("아이템 장비 메뉴로 이동합니다.");
				itemMenu(input, itemDTOs, loginState);
				break;
			case 4:
				System.out.println("메인메뉴로 복귀합니다.");
				run = false;
				break;
			default:
				System.out.println("1~4");
			}// 스위치
		} // 와일
		return loginState;
	}// 셋팅 메뉴 메서드

	public void driverMenu(Scanner input, DriverDTO[] driverDTOs, MemberDTO loginState) {

		for (int i = 0; i < driverDTOs.length; i++) {
			if (driverDTOs[i] != null) {
				System.out.println("\n이름 : " + driverDTOs[i].name + "\n실력 : " + driverDTOs[i].skill + "\n실력기복 : "
						+ driverDTOs[i].consistent + "\n상금 배율" + driverDTOs[i].rewardRatio);
			} // if
		} // for
		System.out.println("원하시는 드라이버의 이름을 입력해주세요.");
		System.out.print(">>>");
		String selectDriver = input.next();
		for (int i = 0; i < driverDTOs.length; i++) {
			if (driverDTOs[i] != null && driverDTOs[i].name == selectDriver) {
				loginState.driverDTO = driverDTOs[i];
				System.out.println(loginState.driverDTO.name + " 선택 완료");
			} // if
		} // for
	}// 레이서 메뉴

	public void carMenu(Scanner input, CarDTO[][] carDTOs, MemberDTO loginState) {

		boolean run = true;
		while (run) {
			System.out.println("원하시는 제조사를 선택해 주세요.");
			System.out.println(" 1.포르쉐  |  2.페라리  |  3.람보르기니 ");
			System.out.print(">>>");
			int select = input.nextInt();
			for (int i = 0; i < carDTOs.length; i++) {
				for (int j = 0; j < carDTOs[i].length; j++) {
					if (carDTOs[i][j] != null && carDTOs[i][j] == carDTOs[select - 1][j]) {
						i = (select - 1);
						System.out.println(carDTOs[i][j].carNum + " 번" + "\n제조사 : " + carDTOs[i][j].campany + "\n모델 : "
								+ carDTOs[i][j].model + "\n최고속도 : " + carDTOs[i][j].speed + "\n마력 : "
								+ carDTOs[i][j].horsePower + "\n제로백 : " + carDTOs[i][j].zeroHun + "\n");
					} // if
				} // if
			} // for
			System.out.println("원하는 모델명 번호를 입력해주세요.");
			int carSelect = input.nextInt();
			boolean run2 = true; // while 쓸때 써먹던 방법 응용
			for (int i = 0; i < carDTOs.length && run2; i++) {
				//조건문이 맞는동안 진행하고 틀리면 브레이크 발생하게 and비교를 추가함
				for (int j = 0; j < carDTOs[i].length; j++) {
					if (carDTOs[select - 1] != null && carDTOs[select - 1][j].carNum == carSelect) {
						loginState.carDTO = carDTOs[select - 1][j];
						System.out.println(loginState.carDTO.campany + " " + loginState.carDTO.model + " 선택 완료");
						run2 = false; // 루프 탈출 조건에 false를 넣고 만족할 경우
						break; // 탈출
						// 만약 run2를 사용하지 않았으면, 그냥 순서대로 진행하다가 바로 break걸려서 나가버림
						// 그래서 i++ 이 작동 자체를 안해서 쓸모없는 코드가 되버리니까 i++에 데드코드 표시를 해버림
					} // if
				} // if
			} // for
			run = false;
		} // while
	}// 차량 메뉴

	public void itemMenu(Scanner input, ItemDTO[] itemDTOs, MemberDTO loginState) {
		
		System.out.println("현재 소지중인 아이템");
		for (int i = 0; i < itemDTOs.length; i++) {
			if (itemDTOs[i] != null) {
				System.out.println("\n이름 : " + itemDTOs[i].itemName + "\n설명 : " + itemDTOs[i].description + "\n남은 갯수 : "
						+ itemDTOs[i].stock + "\n가격 : " + itemDTOs[i].itemPrice);
			} // if
		} // for
		System.out.println("\n장착할 아이템 이름을 입력해주세요.");
		String select = input.next();
		for (int i = 0; i < itemDTOs.length; i++) {
			if (itemDTOs[i] != null && itemDTOs[i].itemName.equals(select)) {
				loginState.itemDTO = itemDTOs[i];
				System.out.println("현재 장착된 아이템 : " + loginState.itemDTO.itemName);
				break;
			} // if
		} // for
	}// 아이템 메뉴
}// 클래스
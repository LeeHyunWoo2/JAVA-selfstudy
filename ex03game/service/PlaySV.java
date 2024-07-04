package ex03game.service;

import java.util.Scanner;

import ex03game.dto.*;


public class PlaySV {

	public void menu(Scanner input, DriverDTO[] driverDTOs, CarDTO[][] carDTOs, ItemDTO[] itemDTOs,
			MemberDTO loginState) {
		boolean run = true;
		while (run) {
			System.out.println("레이스 메뉴");
			System.out.println("1.경기시작 2.나가기");
			System.out.print(">>>\n");
			int select = input.nextInt();
			switch (select) {
			case 1:
				raceMenu(input, driverDTOs, carDTOs, itemDTOs, loginState);
				break;
			case 2:
				run = false;
				break;
			default:
				System.out.println("1~2");
			}
		}
	} // 플레이 메뉴

	// TODO
	// 로딩바 스타일로 트랙 + 자동차 위치 구현
	// 선수마다 진행속도에 가중치주기 / 실력에따라 고,저점 증가 / 기복에따라 고, 저점 범위 넓어지게
	// 자동차에 가중치 줄만한지 테스트하고 생각하기
	// 0~10000으로 해서 10000을 완주 지점으로 두고 랜덤값을 매턴마다 주기
	// 매 턴은 thread.sleep() 으로 대충 주고 누군가 10000에 도달하면 종료
	// 보상은 1~3위정도로 하고 나중에 계좌를 BankExam이랑 연동해보던가 하기
	
	
	
	
	// 망헀음.... 여러대 동시에 경주시키려면 스레드 배워야한다함 근데 어렵다함

	
	
	
	
	
	public void raceMenu(Scanner input, DriverDTO[] driverDTOs, CarDTO[][] carDTOs, ItemDTO[] itemDTOs,
			MemberDTO loginState) {
		
		
		
		Runnable tk = new RaceThread();
		Thread th = new Thread(tk);
		th.start();

		boolean run = true;
		int distance = 0;
		final int MAX = 2000;
		int block = 100;
		System.out.println();
		while (run) {
			String map = "\r[";
			try {
				java.lang.Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			int gaugeLength = MAX / block; // 게이지의 총 길이
			int filledLength = distance / block; // 채워진 게이지 길이

			for (int i = 0; i < gaugeLength; i++) { // 달리면서
				if (i < filledLength) {
					map += "■"; // 내가 간 거리보다 작으면 색칠
				} else if (i == filledLength) {
					map += "□"; // 모양 다른 문자 섞으면 네모 폰트 커지는 현상 있음
				} else {
					map += " "; // 내가 간 거리보다 크면 공백
				}
			}
			map += "]";
			if (distance >= MAX) {
				System.out.printf("거리 : " + MAX + " " + map);
				System.out.println("\n골인\n");
				run = false;
				break;
			} else {
				System.out.print("거리 : " + distance + " " + map);
			}
			distance = distance + (int) (Math.random() * 200 + 1);
		} // while
	}

} // 클래스
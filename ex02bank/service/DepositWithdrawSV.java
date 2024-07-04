package ex02bank.service;

import java.util.Scanner;
import ex02bank.dto.*;

public class DepositWithdrawSV {

	public void depositWithdrawal(Scanner input, AccountDTO[] getAccounts, AccountDTO getUserLogin) {
		boolean run = true;
		while (run) {
			System.out.println("1예금 2출금 3나가기");
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("예금하러갑니다");
				deposit(input, getAccounts, getUserLogin);
				break;
			case 2:
				System.out.println("출금하러갑니다");
				withdraw(input, getAccounts, getUserLogin);
				break;
			case 3:
				run = false;
				break;
			default:
				System.out.println("1~3");
			}
		}
	}// 입출금메뉴

	private static void deposit(Scanner input, AccountDTO[] getAccountDTOs, AccountDTO getUserLogin) {
		System.out.println("예금");
		System.out.println("계좌번호 입력");
		String ano = input.next();
		System.out.println("금액 입력");
		int money = input.nextInt();
		AccountDTO accountDTO = findAccount(ano, getAccountDTOs);
		if (accountDTO == null) {
			System.out.println("없는 계좌입니다.");
			return;
		}
		accountDTO.setBalance(accountDTO.getBalance() + money);
		System.out.println("입금완료");

	}// 입

	private static void withdraw(Scanner input, AccountDTO[] getAccountDTOs, AccountDTO getUserLogin) {
		System.out.println("출금");
		System.out.println("계좌번호 입력");
		String ano = input.next();
		System.out.println("금액 입력");
		int money = input.nextInt();
		AccountDTO accountDTO = findAccount(ano, getAccountDTOs);
		if (accountDTO == null) {
			System.out.println("없는 계좌입니다.");
			return;
		} else if (accountDTO.getBalance() < money) {
			System.out.println("돈이부족해요");
		} else {
			accountDTO.setBalance(accountDTO.getBalance() - money);
			System.out.println("출금완료");
		}

	}// 출

	private static AccountDTO findAccount(String getAno, AccountDTO[] getAccountDTOs) {
		// 배열에서 찾아주는 메서드
		AccountDTO accountDTO = null;
		for (int i = 0; i < getAccountDTOs.length; i++) {
			if (getAccountDTOs[i] != null) {
				String dbAno = getAccountDTOs[i].getAno();
				if (dbAno.equals(getAno)) {
					accountDTO = getAccountDTOs[i];
					break;
				}
			}
		}
		return accountDTO;
	}
}

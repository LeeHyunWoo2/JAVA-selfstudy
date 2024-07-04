package ex02bank.service;

import java.util.Scanner;
import ex02bank.dto.*;

public class AccountSV {

	public void accountMenu(Scanner input, AccountDTO[] getAccounts, AccountDTO getUserLogin) {

		boolean run = true;
		while (run) {
			System.out.println("1.계좌생성 2.목록 3.나가기");
			int select = input.nextInt();
			switch (select) {
			case 1:
				creatAccount(input, getAccounts, getUserLogin);
				break;
			case 2:
				accountList(getAccounts, getUserLogin);
				break;
			case 3:
				run = false;
				break;
			default:
				System.out.println("1~3");

			}// switch

		} // while

	}// 부메뉴

	private void creatAccount(Scanner input, AccountDTO[] getAccounts, AccountDTO getUserLogin) {
		System.out.println("계좌번호");
		String ano = input.next();
		System.out.println("이름");
		String owner = input.next();
		System.out.println("돈");
		int balance = input.nextInt();

		AccountDTO newAccount = new AccountDTO(ano, owner, balance);
		for (int i = 0; i < getAccounts.length; i++) {
			if (getAccounts[i] == null) {
				getAccounts[i] = newAccount;
				System.out.println("생성완료");
				break;

			} // if

		} // for

	}// 계좌생성

	private static void accountList(AccountDTO[] getAccounts, AccountDTO getUserLogin) {

		System.out.println("목록");
		for (int i = 0; i < getAccounts.length; i++) {
			AccountDTO accountDTO = getAccounts[i];
			if (accountDTO != null) {
				System.out.print(accountDTO.getAno());
				System.out.print(" ");
				System.out.print(accountDTO.getOwner());
				System.out.print(" ");
				System.out.print(accountDTO.getBalance());
				System.out.print(" ");
				System.out.println();

			} // if

		} // for

	}// 목록

}// class

package ex02bank;

import java.util.Scanner;
import ex02bank.dto.*;
import ex02bank.service.*;

public class BankExam {

	private static AccountDTO[] accounts = new AccountDTO[100];
	private static AccountDTO userLogin;
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		boolean run = true;
		while (run) {
			System.out.println("1.계좌 2.입/출금 3.종료");
			int select = input.nextInt();
			switch (select) {
			case 1:
				AccountSV accountSV = new AccountSV();
				accountSV.accountMenu(input, getAccounts(), getUserLogin());
				break;
			case 2:
				DepositWithdrawSV depositWithdrawSV = new DepositWithdrawSV();
				depositWithdrawSV.depositWithdrawal(input, getAccounts(), getUserLogin());
				break;
			case 3:
				System.out.println("종료");
				run = false;
				break;
			default:
				System.out.println("1~3");
			}// 스위치

		} // 와일

	}// 메인

	public static AccountDTO[] getAccounts() {
		return accounts;
	}

	public static AccountDTO getUserLogin() {
		return userLogin;
	}

	public void setAccounts(AccountDTO[] accounts) {
		BankExam.accounts = accounts;
	}

	public void setUserLogin(AccountDTO userLogin) {
		BankExam.userLogin = userLogin;
	}

}// 클
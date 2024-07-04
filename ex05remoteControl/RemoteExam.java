package ex05remoteControl;

import java.util.Scanner;

public class RemoteExam {

	public static void main(String[] args) {
		RemoteControl rc;

		boolean run = true;
		while (run) {
			System.out.println("1. 티비 2. 오디오 3. 끄기");
			System.out.print(">>>");
			Scanner scanner = new Scanner(System.in);
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				rc = new Television();
				rc.setPower(run, "티비");
				rc.setCh(select);
				break;
			case 2:
				break;
			case 3:
				run = false;
				break;
			default:
				System.out.println("1~3");
			} // switch

		} // while

	}// main

} // class

package ex05remoteControl;

import java.util.Scanner;

public class Television implements RemoteControl {

	Scanner scanner = new Scanner(System.in);
	private int volume;
	private int channel;
	static int tvBattery;

	@Override
	public void setVol(int volume) {
		if (volume > RemoteControl.MAX_VOL) {
			this.volume = RemoteControl.MAX_VOL;
			// this(지금 내가 가지고 있는 값에) MAX볼륨을 넣어라
		} else if (volume < RemoteControl.MIN_VOL) {
			this.volume = RemoteControl.MIN_VOL;
		} else {
			this.volume = volume;
		} // if 종료
		System.out.println("현재 TV 볼륨 : " + volume);
	}

	@Override
	public void setCh(int channel) {
		System.out.println("변경할 채널을 입력해주세요.");
		int selectCh = scanner.nextInt();
		tvBattery -= 5;
		System.out.println("남은 배터리 잔량 : " + tvBattery);
		if (selectCh > MAX_CH || selectCh < MIN_CH) {
			System.out.println(" 채널 범위를 초과한 입력입니다. ");
			return;
		} else {
			channel = selectCh;
		}

		System.out.println("현재 채널 : " + channel);
	}

}

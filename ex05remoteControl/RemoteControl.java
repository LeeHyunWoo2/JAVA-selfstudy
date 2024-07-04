package ex05remoteControl;

public interface RemoteControl {
	// 인터페이스는 상수, 메서드 두개만 있다

	// 상수

	// 채널 즐찾 만들기

	public int MIN_VOL = 0;
	public int MAX_VOL = 100;
	public int MIN_CH = 1;
	public int MAX_CH = 255;
	public int MIN_BATTERY = 0;

	// 메서드

	public void setVol(int volume);

	public void setCh(int channel);

	default void setPower(boolean power, String device) {
		// 다른 클래스에선 원래 default 생략해도 기본적으로 default 처리 해주는데
		// 인터페이스에선 써야함

		if (power) {
			System.out.println(device + " 전원을 켭니다.");
		} else {
			System.out.println(device + " 전원을 끕니다.");
		}
	}

	default void setMute(boolean mute, String device) {
		if (mute) {
			System.out.println(device + " 음소거");
		} else {
			System.out.println(device + " 음소거 해제");
		}
	}

	static void batteryCheck(int battery) {
		if (battery <= 0) {
			System.out.println("리모컨이 방전되었습니다. 배터리를 교체해주세요");
		}
	}

}
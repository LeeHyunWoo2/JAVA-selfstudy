package ex03game.dto;

public class CarDTO {

	public int carNum;          // 차량번호
	public String campany;      // 회사
	public String model;        // 모델명
	public int speed;           // 최고속도
	public int horsePower;      // 마력
	public double zeroHun;      // 제로백
	public CarDTO carSelectDTO; // 사용자가 선택한 캐릭터 연결용

	public CarDTO(int carNum, String campany, String model, int speed, int horsePower, double zeroHun) {
		super();
		this.carNum = carNum;
		this.campany = campany;
		this.model = model;
		this.speed = speed;
		this.horsePower = horsePower;
		this.zeroHun = zeroHun;
	}
}

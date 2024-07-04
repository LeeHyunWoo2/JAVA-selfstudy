package ex03game.dto;

public class DriverDTO {

	public String name;        // 드라이버 이름
	public String skill;       // 실력
	public String consistent;  // 실력 기복
	public double rewardRatio; // 상금 비율

	public DriverDTO(String name, String skill, String consistent, double rewardRatio) {
		super();
		this.name = name;
		this.skill = skill;
		this.consistent = consistent;
		this.rewardRatio = rewardRatio;
	}
}
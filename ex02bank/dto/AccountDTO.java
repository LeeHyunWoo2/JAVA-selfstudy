package ex02bank.dto;

public class AccountDTO {

	private String ano; // 계좌번호 - 들어갈수있으니 스트링처리
	private String owner; // 계좌 주 이름
	private int balance; // 잔액

	public AccountDTO(String ano, String owner, int balance) {
		super();
		this.ano = ano;
		this.owner = owner;
		this.balance = balance;
	}

	public String getAno() {
		return ano;
	}

	public String getOwner() {
		return owner;
	}

	public int getBalance() {
		return balance;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
